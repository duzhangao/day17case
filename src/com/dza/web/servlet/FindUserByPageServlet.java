package com.dza.web.servlet;

import com.dza.domain.PageBean;
import com.dza.domain.User;
import com.dza.service.UserService;
import com.dza.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/find_user_by_pageServlet")
public class FindUserByPageServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String currentPage=request.getParameter("currentPage");//当前页码
        String rows=request.getParameter("rows");//每页显示的条数
        if(currentPage==null||"".equals(currentPage)){
            currentPage="1";
        }
        if(rows==null||"".equals(rows)){
            rows="5";
        }
        //获取条件查询数据
        Map<String,String[]>condition=request.getParameterMap();

        UserService service=new UserServiceImpl();
        PageBean<User>pb=service.findUserByPage(currentPage,rows,condition);//查询
        System.out.println(pb);
        request.setAttribute("pb",pb);//存入
        request.setAttribute("condition",condition);
        request.getRequestDispatcher("list.jsp").forward(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost( request, response);
    }
}
