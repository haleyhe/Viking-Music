package com.vikings.config;

import java.util.TimeZone;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {
    
    // CONFIGURATION FOR FILE UPLOADS
    // thanks http://websystique.com/springmvc/spring-mvc-4-file-upload-example-using-multipartconfigelement/
    private static final String LOCATION = System.getProperty("java.io.tmpdir"); // Temp storage location
    private static final long MAX_FILE_SIZE = 10000000; // Max file size [10 MB]
    private static final long MAX_REQUEST_SIZE = 10000000; // Max total request size [10 MB]
    private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk
    
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        ctx.setServletContext(container);
        
        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
        servlet.addMapping("*.htm");
        servlet.setMultipartConfig(new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD));
        
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
