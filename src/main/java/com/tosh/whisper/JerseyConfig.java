package com.tosh.whisper;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;

@ApplicationPath("resources")
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig()
    {
        register(JacksonFeature.class);
        register(LoggingFeature.class);
        packages("com.tosh.whisper.controller");
        
        // property(ServerProperties.PROVIDER_SCANNING_RECURSIVE, true);
        property(ServerProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
        
        property(FreemarkerMvcFeature.TEMPLATE_BASE_PATH, "templates");
        property(FreemarkerMvcFeature.ENCODING, "UTF-8");
        property(FreemarkerMvcFeature.CACHE_TEMPLATES, false);
        register(org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature.class);
    }
}
