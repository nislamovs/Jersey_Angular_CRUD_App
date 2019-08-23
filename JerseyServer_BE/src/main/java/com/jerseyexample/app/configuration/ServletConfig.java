package com.jerseyexample.app.configuration;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.SecuredRedirectHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {


//    @Bean
//    public ServletWebServerFactory servletContainer(@Value("${server.http.port}") int httpPort) {
//        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//        connector.setPort(httpPort);
//
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//        tomcat.addAdditionalTomcatConnectors(connector);
//        return tomcat;
//    }

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
        factory.addServerCustomizers(new JettyServerCustomizer() {
            @Override
            public void customize(Server server) {
                final HttpConnectionFactory httpConnectionFactory = server.getConnectors()[0].getConnectionFactory(HttpConnectionFactory.class);

                final ServerConnector httpConnector = new ServerConnector(server, httpConnectionFactory);
                httpConnector.setPort(8080 /* HTTP */);
                server.addConnector(httpConnector);

                final HandlerList handlerList = new HandlerList();
                handlerList.addHandler(new SecuredRedirectHandler());
                for(Handler handler : server.getHandlers())
                    handlerList.addHandler(handler);
                server.setHandler(handlerList);
            }
        });
        return factory;
    }
}