package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;


public class JsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            if (req.getRequestURI().equals("/api/")) {
                resp.setContentType("text/html");
                resp.getWriter().print("<html><head></head><body><h1>Hello</h1><body></html>");
            } else if (req.getRequestURI().startsWith("/api/rest/")) {
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                Greeting greeting = new Greeting(25, "my fucking content", new GreetingPerson("im","911"));
                String json = ow.writeValueAsString(greeting);
                resp.getWriter().print(json);
            } else {
                throw new IllegalStateException("wrong url");
            }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/api/rest/")) {
            System.out.println(req.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
            System.out.println(req.getHeader("MQRFH2.usr.ServiceName"));
            resp.getWriter().print(200);
        }
    }
}
