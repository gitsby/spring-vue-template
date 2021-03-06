package com.github.gitsby.spring_vue_template._develop_;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

public class DebugServer {



        private static final int DEFAULT_PORT = 8090;
        private static final String CONTEXT_PATH = "/";
        private static final String CONFIG_LOCATION = "com.github.gitsby.spring_vue_template.configurations";
        private static final String MAPPING_URL = "/*";

        public static void main(String[] args) throws Exception {
            new DebugServer().startJetty(getPortFromArgs(args));
        }

        private static int getPortFromArgs(String[] args) {
            if (args.length > 0) {
                try {
                    return Integer.valueOf(args[0]);
                } catch (NumberFormatException ignore) {
                }
            }
            return DEFAULT_PORT;
        }

        private void startJetty(int port) throws Exception {
            Server server = new Server(port);
            server.setHandler(getServletContextHandler(getContext()));
            server.start();
            server.join();
        }

        private static ServletContextHandler getServletContextHandler(WebApplicationContext context) throws IOException {
            ServletContextHandler contextHandler = new ServletContextHandler();
            contextHandler.setErrorHandler(null);
            contextHandler.setContextPath(CONTEXT_PATH);
            contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
            contextHandler.addEventListener(new ContextLoaderListener(context));
            return contextHandler;
        }

        private static WebApplicationContext getContext() {
            AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
            context.setConfigLocation(CONFIG_LOCATION);
            context.getEnvironment().setActiveProfiles("debug");
            return context;
        }


}
