package com.dza.web.servlet;

import com.dza.domain.User;
import com.dza.service.UserService;
import com.dza.service.impl.UserServiceImpl;
import com.mysql.cj.xdevapi.Session;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet( "/loginServlet")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取数据
        HttpSession session= (HttpSession) request.getSession();
        String checkcode1=request.getParameter("verifycode");
        String checkcode2=(String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        //3校验验证码
         if(!checkcode1.equalsIgnoreCase(checkcode2) ){
             request.setAttribute("login_msg","验证码错误");
             request.getRequestDispatcher("/login.jsp").forward(request,response);
             return;

         }
        // 4.封装user对象
        Map<String,String[]>user_map=request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,user_map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //5.调用service查询
        UserService service=new UserServiceImpl();
        User login_user=service.login(user);
        //6.判断是否登陆成功
        if(login_user!=null){
            session.setAttribute("user",login_user);
            response.sendRedirect(request.getContextPath()+"/index.jsp");
            return;

        }
        else {
            request.setAttribute("login_msg","无此用户");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;

        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
