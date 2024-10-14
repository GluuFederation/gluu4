package org.gluu.oxauth.service.external;

import org.gluu.model.custom.script.CustomScriptType;
import org.gluu.model.custom.script.conf.CustomScriptConfiguration;
import org.gluu.model.custom.script.type.uma.UmaRptClaimsType;
import org.gluu.oxauth.service.external.context.ExternalUmaRptClaimsContext;
import org.gluu.service.custom.script.ExternalScriptService;
import org.json.JSONObject;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Yuriy Zabrovarnyy
 */
@ApplicationScoped
public class ExternalUmaRptClaimsService extends ExternalScriptService {

    @Inject
    private Logger log;

    public ExternalUmaRptClaimsService() {
        super(CustomScriptType.UMA_RPT_CLAIMS);
    }

    public boolean externalModify(JSONObject rptAsJson, ExternalUmaRptClaimsContext context) {
        final List<CustomScriptConfiguration> scripts = getCustomScriptConfigurationsByDns(context.getClient().getAttributes().getRptClaimsScripts());
        if (scripts.isEmpty()) {
            return false;
        }
        log.trace("Found {} RPT Claims scripts.", scripts.size());

        for (CustomScriptConfiguration script : scripts) {
            if (!externalModify(rptAsJson, script, context)) {
                return false;
            }
        }

        log.debug("ExternalModify returned 'true'.");
        return true;
    }

    public boolean externalModify(JSONObject rptAsJson, CustomScriptConfiguration scriptConfiguration, ExternalUmaRptClaimsContext context) {
        try {
            log.trace("Executing external 'externalModify' method, script name: {}, context: {}", scriptConfiguration.getName(), context);

            UmaRptClaimsType script = (UmaRptClaimsType) scriptConfiguration.getExternalType();
            context.setScript(scriptConfiguration);
            final boolean result = script.modify(rptAsJson, context);

            log.trace("Finished external 'externalModify' method, script name: {}, context: {}, result: {}", scriptConfiguration.getName(), context, result);
            return result;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            saveScriptError(scriptConfiguration.getCustomScript(), ex);
            return false;
        }
    }
}
