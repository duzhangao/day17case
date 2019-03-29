package com.dza.web.servlet;

import com.dza.service.UserService;
import com.dza.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( "/delselectedServlet")
public class DelSelectedServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[]ids=request.getParameterValues("uid");
        UserService service=new UserServiceImpl();
        service.deleteUsers(ids);
        response.sendRedirect(request.getContextPath()+"/find_user_by_pageServlet");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
