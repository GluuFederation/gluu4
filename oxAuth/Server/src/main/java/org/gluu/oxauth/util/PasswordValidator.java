package org.gluu.oxauth.util;

import org.gluu.model.attribute.AttributeValidation;
import org.gluu.oxauth.i18n.LanguageBean;
import org.gluu.service.AttributeService;
import org.gluu.service.cdi.util.CdiUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
@FacesValidator(value = "gluuPasswordValidator", managed = true)
public class PasswordValidator implements javax.faces.validator.Validator {

	private static final String USER_PASSWORD = "userPassword";
	private String newPassword;
	private Pattern pattern;
	private Matcher matcher;
	private boolean hasValidation = false;

	@Inject
	private AttributeService attributeService;

	@Inject
	private LanguageBean languageBean;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// This is workaround unless this bug will be fixed in JSF
		if (attributeService == null) {
			attributeService = CdiUtil.bean(AttributeService.class);
		}
		if (languageBean == null) {
			languageBean = CdiUtil.bean(LanguageBean.class);
		}

		AttributeValidation validation = attributeService.getAttributeByName(USER_PASSWORD).getAttributeValidation();
		if (validation != null) {
			String regexp = validation.getRegexp();
			if (regexp != null && !regexp.isEmpty()) {
				pattern = Pattern.compile(regexp);
				matcher = pattern.matcher(value.toString());
				hasValidation = true;
			}
		}
		if (hasValidation && !matcher.matches()) {
			String message = languageBean.getMessage("password.validation.invalid");
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
			context.validationFailed();
			((UIInput) component).setValid(false);
		}

	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
