package com.controller;

import com.dao.UserDao;
import com.daoImpl.UserDaoImpl;
import com.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    UserDao userDao = new UserDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();

        int index = path.lastIndexOf("/");

        String realPath = path.substring(index + 1);
        System.out.println("realPath:" + realPath);

        if (realPath.equals("register")) {
            addUser(request, response);
        } else if (realPath.equals("login")) {
            login(request,response);
        }
    }

    protected void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("------addUser--------");
        //接受前端页面参数
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String realName = request.getParameter("realName");
        String identity = request.getParameter("identity");
        String phone = request.getParameter("phone");
        String sex = request.getParameter("sex");

        System.out.println("name:" + name);
        System.out.println("password:" + password);
        System.out.println("realName:" + realName);
        System.out.println("identity:" + identity);
        System.out.println("phone:" + phone);
        System.out.println("sex:" + sex);

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setIdentity(identity);
        user.setSex(sex);
        user.setPhone(phone);
        user.setRealName(realName);

        userDao.addUser(user);

        response.sendRedirect("login.jsp");
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("----------login-------");
        String name=request.getParameter("name");
        String password =request.getParameter("password");
        System.out.println("name:"+name);
        System.out.println("password:"+password);
        //根据用户名和密码进行查询
        User user=userDao.login(name,password);

        if (user!=null){//用户登录成功
            //转发or重定向
            //获取HttpSession对象
            HttpSession session=request.getSession();
            session.setAttribute("user",user);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else{
            response.sendRedirect("login.jsp");
        }

    }
}