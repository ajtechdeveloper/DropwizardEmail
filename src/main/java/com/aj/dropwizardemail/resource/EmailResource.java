package com.aj.dropwizardemail.resource;

import com.aj.dropwizardemail.service.EmailService;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;


@Path("/email")
@Produces(MediaType.APPLICATION_JSON)
public class EmailResource {

    private static final Logger logger = LoggerFactory.getLogger(EmailResource.class);

    private final EmailService emailService;

    public EmailResource(EmailService emailService) {
        this.emailService = emailService;
    }

	@Timed
	@GET
    @Path("/send")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendMail() {
		Map<String, String> response = new HashMap<>();
		String result = emailService.sendMail();
		response.put("message", result);
		return Response.ok(response).build();
	}


    @Timed
    @GET
    @Path("/send/attachment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendMailAttachment() {
        Map<String, String> response = new HashMap<>();
        String result = emailService.sendMailAttachment();
        response.put("message", result);
        return Response.ok(response).build();
    }

    @Timed
    @GET
    @Path("/send/attachment/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendMailCreateAttachment() {
        Map<String, String> response = new HashMap<>();
        String result = emailService.sendMailCreateAttachment();
        response.put("message", result);
        return Response.ok(response).build();
    }
}
