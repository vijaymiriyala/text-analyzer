package com.analyzer.resource;

import com.analyzer.service.TextAnalyzerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/analyzer")
@Consumes({MediaType.TEXT_PLAIN})
@Produces({MediaType.APPLICATION_JSON})
@Resource
@Slf4j
public class TextAnalyzerResource {

    @Autowired
    private TextAnalyzerService service;

    @POST
    public Response create(String phrase) throws Exception {
        List<String> data = service.analyze(phrase);
        return (data == null || data.size() == 0) ?
                Response.status(Response.Status.BAD_REQUEST).entity("No words found").type("text/plain").build() :
                Response.ok(data).build();
    }

}
