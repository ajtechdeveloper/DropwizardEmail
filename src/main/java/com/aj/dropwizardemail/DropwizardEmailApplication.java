package com.aj.dropwizardemail;

import com.aj.dropwizardemail.resource.DropwizardEmailHealthCheckResource;
import com.aj.dropwizardemail.resource.EmailResource;
import com.aj.dropwizardemail.resource.PingResource;
import com.aj.dropwizardemail.service.EmailService;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DropwizardEmailApplication extends Application<DropwizardEmailConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(DropwizardEmailApplication.class);

	public static void main(String[] args) throws Exception {
		new DropwizardEmailApplication().run("server", args[0]);
	}

    @Override
    public void initialize(Bootstrap<DropwizardEmailConfiguration> b) {
	}

	@Override
	public void run(DropwizardEmailConfiguration config, Environment env) {
        logger.info("Registering RESTful API resources");
		env.jersey().register(new PingResource());
		env.healthChecks().register("DropwizardEmailHealthCheck",
				new DropwizardEmailHealthCheckResource(config));
		env.jersey().register(new EmailResource(new EmailService()));
	}
}
