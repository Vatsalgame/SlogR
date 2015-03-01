package com.slogr;

import com.slogr.health.TemplateHealthCheck;
import com.slogr.resources.SlogrResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 *
 *
 * @author vatsalgame
 */
public class SlogrApplication extends Application<SlogrConfiguration> {

    public static void main(String[] args) throws Exception {
        new SlogrApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<SlogrConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(SlogrConfiguration configuration,
                    Environment environment) {
        // Adding the base resource
        final SlogrResource resource = new SlogrResource(configuration.getTemplate(),
                                                            configuration.getDefaultName());

        // Adding the demo healthCheck
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());

        // Finally adding everything to the environment
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }
}
