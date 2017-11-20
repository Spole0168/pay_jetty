package com.ibs.test.integration;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyLauncher {
    public static final int PORT = 8080;

    public static final String CONTEXT = "/coreweb";

    public static void main(String[] args) throws Exception {
        Server server = new Server(PORT);
        WebAppContext context = new WebAppContext("WebContent", CONTEXT);
        server.setHandler(context);
        server.setStopAtShutdown(true);
        server.start();
    }

}
