package org.gluu.oxauth.model.ldap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yuriy Zabrovarnyy
 */
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class TokenAttributes implements Serializable {

    @JsonProperty("x5cs256")
    private String x5cs256;
    @JsonProperty("attributes")
    private Map<String, String> attributes;

    public String getX5cs256() {
        return x5cs256;
    }

    public void setX5cs256(String x5cs256) {
        this.x5cs256 = x5cs256;
    }

    public Map<String, String> getAttributes() {
        if (attributes == null) attributes = new HashMap<>();
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "TokenAttributes{" +
                "attributes='" + attributes + '\'' +
                "x5cs256='" + x5cs256 + '\'' +
                '}';
    }
}
