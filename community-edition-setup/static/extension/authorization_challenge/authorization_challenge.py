from org.apache.commons.lang3 import StringUtils
from org.gluu.model import SimpleCustomProperty
from org.gluu.model.custom.script.model import CustomScript
from org.gluu.model.custom.script.type.authzchallenge import AuthorizationChallengeType
from org.gluu.oxauth.authorize.ws.rs import AuthorizationChallengeSessionService
from org.gluu.oxauth.model.common import User
from org.gluu.oxauth.model.session import AuthorizationChallengeSession
from org.gluu.oxauth.service import UserService
from org.gluu.oxauth.service.external.context import ExternalScriptContext
from org.gluu.persist import PersistenceEntryManager
from org.gluu.service.cdi.util import CdiUtil
from org.gluu.service.custom.script import CustomScriptManager
from org.slf4j import LoggerFactory

import java

class AuthorizationChallenge(AuthorizationChallengeType):
    def __init__(self, currentTimeMillis):
        self.currentTimeMillis = currentTimeMillis

    def authorize(self, context):
        # 1. As first step we get username
        username = self.getParameterOrCreateError(context, "username")
        if StringUtils.isBlank(username):
            return False

        # 2. OTP validation
        otp = self.getParameterOrCreateError(context, "otp")
        if StringUtils.isBlank(otp):
            return False

        print "All required parameters are present"

        # Main authorization logic
        userService = CdiUtil.bean(UserService)
        entryManager = CdiUtil.bean(PersistenceEntryManager)

        user = userService.getUser(username)
        if user is None:
            print "User is not found"
            self.createError(context, "username_invalid")
            return False

        isUserActive = StringUtils.equals(user.getStatus(), "ACTIVE")
        if not isUserActive:
            print "User is not active"
            self.createError(context, "username_inactive")
            return False

        ok = entryManager.authenticate(user.getDn(), User, otp)
        if ok:
            context.getExecutionContext().setUser(user)
            print "User is authenticated successfully."
            return True

        # Error case
        print "Failed to authenticate user. Please check username and OTP."
        self.createError(context, "username_or_otp_invalid")
        return False

    def getParameterOrCreateError(self, context, parameterName):
        value = context.getHttpRequest().getParameter(parameterName)

        if StringUtils.isBlank(value):
            value = self.getParameterFromSession(context, parameterName)

        if StringUtils.isBlank(value):
            self.createError(context, "%s_required" % parameterName)
            return None

        return value

    def createError(self, context, errorCode):
        sessionPart = self.prepareSessionSubJson(context)
        entity = "{\"error\": \"%s\"%s}" % (errorCode, sessionPart)
        context.createWebApplicationException(401, entity)

    def prepareSessionSubJson(self, context):
        sessionObject = context.getExecutionContext().getAuthzRequest().getAuthorizationChallengeSessionObject()
        if sessionObject is not None:
            self.prepareSession(context, sessionObject)
            return ",\"auth_session\":\"%s\"" % sessionObject.getId()
        elif context.getExecutionContext().getAuthzRequest().isUseAuthorizationChallengeSession():
            sessionObject = self.prepareSession(context, None)
            return ",\"auth_session\":\"%s\"" % sessionObject.getId()
        return ""

    def prepareSession(self, context, sessionObject):
        authzSessionService = CdiUtil.bean(AuthorizationChallengeSessionService)
        newSave = sessionObject is None
        if newSave:
            sessionObject = authzSessionService.newAuthorizationChallengeSession()

        username = context.getHttpRequest().getParameter("username")
        if StringUtils.isNotBlank(username):
            sessionObject.getAttributes().getAttributes().put("username", username)

        otp = context.getHttpRequest().getParameter("otp")
        if StringUtils.isNotBlank(otp):
            sessionObject.getAttributes().getAttributes().put("otp", otp)

        if newSave:
            authzSessionService.persist(sessionObject)
        else:
            authzSessionService.merge(sessionObject)

        return sessionObject

    def getParameterFromSession(self, context, parameterName):
        sessionObject = context.getExecutionContext().getAuthzRequest().getAuthorizationChallengeSessionObject()
        if sessionObject is not None:
            return sessionObject.getAttributes().getAttributes().get(parameterName)
        return None

    def init(self, configurationAttributes):
        print "Initialized Default AuthorizationChallenge Python custom script."
        return True

    def init(self, customScript, configurationAttributes):
        print "Initialized Default AuthorizationChallenge Python custom script."
        return True

    def destroy(self, configurationAttributes):
        print "Destroyed Default AuthorizationChallenge Python custom script."
        return True

    def getApiVersion(self):
        return 11