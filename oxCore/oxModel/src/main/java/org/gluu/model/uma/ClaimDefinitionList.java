package org.gluu.model.uma;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author yuriyz on 06/16/2017.
 */
public class ClaimDefinitionList extends ArrayList<ClaimDefinition> {

    @JsonIgnore
    public ClaimDefinitionList addPermission(ClaimDefinition claim) {
        add(claim);
        return this;
    }

    public static ClaimDefinitionList instance(ClaimDefinition... claims) {
        ClaimDefinitionList instance = new ClaimDefinitionList();
        Collections.addAll(instance, claims);
        return instance;
    }
}
