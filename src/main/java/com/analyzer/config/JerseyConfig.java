package com.analyzer.config;

import com.analyzer.resource.TextAnalyzerResource;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(JacksonFeature.class);
        register(TextAnalyzerResource.class);
        register(CORSResponseFilter.class);
    }

}
