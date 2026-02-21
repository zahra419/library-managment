package controller;

import java.io.*;               // for PrintWriter, IOException
import javax.servlet.*;          // HttpServlet, ServletException
import javax.servlet.http.*;     // HttpServletRequest, HttpServletResponse

public class HelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello from Servlet ✅</h1>");
    }
}