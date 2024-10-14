/*
 * oxCore is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2017, Gluu
 */
package org.gluu.config.oxtrust;

import java.io.Serializable;

import org.gluu.persist.model.base.Entry;
import org.gluu.persist.annotation.AttributeName;
import org.gluu.persist.annotation.DataEntry;
import org.gluu.persist.annotation.JsonObject;
import org.gluu.persist.annotation.ObjectClass;

/**
 * Shibboleth IDP CAS-related settings configuration entry.
 *
 * @author Dmitry Ognyannikov
 */
@DataEntry
@ObjectClass(value = "oxShibbolethCASProtocolConfiguration")
public class LdapShibbolethCASProtocolConfiguration extends Entry implements Serializable {

    private static final long serialVersionUID = -11887457695212971L;

    @AttributeName(ignoreDuringUpdate = true)
    private String inum;

    @JsonObject
    @AttributeName(name = "oxConfApplication")
    private ShibbolethCASProtocolConfiguration casProtocolConfiguration;

    @AttributeName(name = "oxRevision")
    private long revision;

    public LdapShibbolethCASProtocolConfiguration() {
    }

    public String getInum() {
        return inum;
    }

    public void setInum(String inum) {
        if (casProtocolConfiguration != null) {
            casProtocolConfiguration.setInum(inum);
        }

        this.inum = inum;
    }

    /**
     * @return the casProtocolConfiguration
     */
    public ShibbolethCASProtocolConfiguration getCasProtocolConfiguration() {
        return casProtocolConfiguration;
    }

    /**
     * @param casProtocolConfiguration
     *            the casProtocolConfiguration to set
     */
    public void setCasProtocolConfiguration(ShibbolethCASProtocolConfiguration casProtocolConfiguration) {
        this.casProtocolConfiguration = casProtocolConfiguration;
    }

    /**
     * @return the revision
     */
    public long getRevision() {
        return revision;
    }

    /**
     * @param revision
     *            the revision to set
     */
    public void setRevision(long revision) {
        this.revision = revision;
    }

}
