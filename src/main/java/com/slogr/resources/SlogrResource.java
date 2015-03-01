package com.slogr.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.slogr.core.Saying;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Base resource class for SlogR
 *
 * @author vatsalgame
 */
@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class SlogrResource {

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public SlogrResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.or(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }

}