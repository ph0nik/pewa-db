package com.pewa;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.scan.StandardJarScanner;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

public class Main {

    private static final int PORT = 8081;

    public static void main(String[] args) throws ServletException, LifecycleException {

        String appBase = ".";
        String webappDirLocation = "src/main/webapp";
        String webxmlDirLocation = "src/main/webapp/WEB-INF/web.xml";

        Tomcat tomcat = new Tomcat();
        // set temp directory for tomcat server
        String baseDir = createTempTomcatDir();
        tomcat.setBaseDir(baseDir);
        // set port
        tomcat.setPort(PORT);
        tomcat.getHost().setAppBase(baseDir);
        tomcat.getHost().setDeployOnStartup(true);
        tomcat.getHost().setAutoDeploy(true);
        StandardContext context = (StandardContext) tomcat.addWebapp("", new File((webappDirLocation)).getAbsolutePath());
        // settting web.xml file location
        context.setDefaultContextXml(new File(webxmlDirLocation).getAbsolutePath());
        // turning off scanning for jar files, it throws multiple exceptions
        ((StandardJarScanner) context.getJarScanner()).setScanClassPath(false);
//        StandardJarScanner asd = new StandardJarScanner();
//        asd.setScanAllFiles(false);
//        tomcat.addWebapp("", appBase);

        tomcat.start();
        tomcat.getServer().await();

    }

    /**
     * Creates temporary tomcat directory and returns absolute path
    */
    private static String createTempTomcatDir() {
        try {
            File tempDir = File.createTempFile("tomcat.","." + PORT);
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit();
            return tempDir.getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to create temporary directory. java.io.tmpdir is set to "
                            + System.getProperty("java.io.tmpdir"), e
            );
        }
    }
}
