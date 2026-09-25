package com.devops.web;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        response.getWriter().println(
            "<h1>DevOps Java Web Application</h1>" +
            "<p>CI/CD Pipeline is working!</p>"
        );
    }
}
