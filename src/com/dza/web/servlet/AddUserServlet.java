package com.dza.web.servlet;

import com.dza.domain.User;
import com.dza.service.UserService;
import com.dza.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet( "/addServlet")
public class AddUserServlet extends HttpServlet {
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        User add_user=new User();
        Map<String,String[]>user_map=request.getParameterMap();
        try {
            BeanUtils.populate(add_user,user_map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService service=new UserServiceImpl();
        service.addUser(add_user);
        response.sendRedirect(request.getContextPath()+"/find_user_by_pageServlet");


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
