package com.aj.dropwizardemail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DropwizardEmailConfiguration extends Configuration {

    @JsonProperty
    public String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
