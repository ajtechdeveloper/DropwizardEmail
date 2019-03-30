package com.aj.dropwizardemail.resource;

import com.aj.dropwizardemail.DropwizardEmailConfiguration;
import com.codahale.metrics.health.HealthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DropwizardEmailHealthCheckResource extends HealthCheck {

    private static final Logger logger = LoggerFactory.getLogger(DropwizardEmailHealthCheckResource.class);

    private static String appName;

    public DropwizardEmailHealthCheckResource(DropwizardEmailConfiguration dropwizardEmailConfiguration){
       this.appName = dropwizardEmailConfiguration.getAppName();
    }

    @Override
    protected Result check() throws Exception {
        logger.info("App Name is: {}", appName);
        if("DropwizardEmail".equalsIgnoreCase(appName)) {
            return Result.healthy();
        }
        return Result.unhealthy("Basic Dropwizard Service is down");
    }
}