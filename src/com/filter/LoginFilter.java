package com.filter;

import com.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //登录验证
    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        //向下整形
        HttpServletRequest request=(HttpServletRequest)arg0;
        HttpServletResponse response=(HttpServletResponse)arg1;
        //获取HttpSession对象
        HttpSession session =request.getSession();

        //获取当前登录的用户对象
        User user=(User)session.getAttribute("user");
        if(user!=null){
            arg2.doFilter(request,response);
        }else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
