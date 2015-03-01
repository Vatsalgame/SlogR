package com.slogr;

import com.slogr.health.TemplateHealthCheck;
import com.slogr.resources.SlogrResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.assets.AssetsBundle;

/**
 * Base application class for SlogR
 *
 * @author vatsalgame
 */
public class SlogrApplication extends Application<SlogrConfiguration> {

    public static void main(String[] args) throws Exception {
        new SlogrApplication().run(args);
    }

    @Override
    public String getName() {
        return "SlogR";
    }

    @Override
    public void initialize(Bootstrap<SlogrConfiguration> bootstrap) {
//        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.htm", "indexFile"));
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
        // To enable static content delivery on root path "/"
        environment.jersey().setUrlPattern("/api/*");
    }
}
