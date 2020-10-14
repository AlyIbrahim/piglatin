package com.redhat.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.reactivestreams.Publisher;

import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.SseElementType;

@Path("/piglatin")
public class PigLatinResource {

    private static final Logger LOG = Logger.getLogger(PigLatinResource.class);

    @Inject
    @Channel("slack") Publisher<PigLatin> statements;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS) 
    @SseElementType("application/json") 
    public Publisher<PigLatin> stream() { 
        LOG.info("SSE Stated");
        return statements;
    }
}