package com.sn.dtai.admin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Admin.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link tech.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private String restUrlPaie;
    private String restUrlCarriere;

    public String getRestUrlCarriere() {
        return restUrlCarriere;
    }

    public void setRestUrlCarriere(String restUrlCarriere) {
        this.restUrlCarriere = restUrlCarriere;
    }

    public String getRestUrlPaie() {
        return restUrlPaie;
    }

    public void setRestUrlPaie(String restUrlPaie) {
        this.restUrlPaie = restUrlPaie;
    }
}
