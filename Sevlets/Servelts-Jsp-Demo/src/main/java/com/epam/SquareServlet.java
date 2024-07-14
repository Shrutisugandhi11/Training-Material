package com.epam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SquareServlet extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
      int square=Integer.parseInt( req.getParameter("square"));
      square*=square;
       PrintWriter printWriter=res.getWriter();
       printWriter.println("Square of numbers is:" +square);
    }
}
