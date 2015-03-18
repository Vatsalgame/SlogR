package com.slogr;

import com.slogr.health.TemplateHealthCheck;
import com.slogr.resources.SlogrResource;
import com.slogr.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.assets.AssetsBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base application class for SlogR
 *
 * @author vatsalgame
 */
public class SlogrApplication extends Application<SlogrConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlogrApplication.class);

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

        LOGGER.info("Method SlogrApplication#run() called");

        // Adding the base resource
        final SlogrResource resource = new SlogrResource(configuration.getTemplate(),
                                                            configuration.getDefaultName());
        // Adding the User resource
        final UserResource userResource = new UserResource();

        // Adding the demo healthCheck
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());

        // Finally adding everything to the environment
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
        environment.jersey().register(userResource);
        // To enable static content delivery on root path "/"
        environment.jersey().setUrlPattern("/api/*");
    }
}
