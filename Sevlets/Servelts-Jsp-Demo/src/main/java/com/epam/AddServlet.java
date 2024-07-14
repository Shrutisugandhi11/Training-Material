package com.epam;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        int num1 = Integer.parseInt(req.getParameter("num1"));
        int num2 = Integer.parseInt(req.getParameter("num1"));
        int result = num2 + num1;
        res.sendRedirect("square?square=" + result);


//        req.setAttribute("sqaure",result);
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/square");
//        requestDispatcher.forward(req, res);
//        PrintWriter printWriter=res.getWriter();
//        printWriter.println("Here is your result:"+ result);
    }

//    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        int num1 = Integer.parseInt(req.getParameter("num1"));
//        int num2 = Integer.parseInt(req.getParameter("num2"));
//        int result = num2 + num1;
//        PrintWriter printWriter = res.getWriter();
//        printWriter.println("Here is your result:" + result);
//    }

//    @Override
//    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        int num1=Integer.parseInt(req.getParameter("num1"));
//        int num2=Integer.parseInt(req.getParameter("num1"));
//        int result=num2+num1;
//        PrintWriter printWriter=res.getWriter();
//        printWriter.println("Here is your result:"+ result);
//    }


}
