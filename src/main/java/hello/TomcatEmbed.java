package hello;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.servlet.DispatcherServlet;

public class TomcatEmbed {
        public static void main(String[] args) throws LifecycleException {
            Tomcat tomcat = new Tomcat();
            tomcat.setBaseDir("temp");
            tomcat.setPort(8080);

            String contextPath = "/";
            String docBase = new File(".").getAbsolutePath();

            Context context = tomcat.addContext(contextPath, docBase);

            DispatcherServlet dispatcherServlet = new DispatcherServlet();

            HttpServlet servlet = new HttpServlet() {
                @Override
                protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                        throws ServletException, IOException {
                    PrintWriter writer = resp.getWriter();

                    writer.println("<html><title>Welcome</title><body>");
                    writer.println("<h1>Have a Great Day!</h1>");
                    writer.println("</body></html>");
                }
            };

            String servletName = "jersey";
            String urlPattern = "/api/*";

            tomcat.addServlet(contextPath, servletName,new JsonServlet());
            context.addServletMappingDecoded(urlPattern, servletName);

            tomcat.start();
            tomcat.getServer().await();
        }

    protected static ServletContainer resourceConfig() {
        return new ServletContainer(new ResourceConfig().register(new GreetingController()));
    }
}
