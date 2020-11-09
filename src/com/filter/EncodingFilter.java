package com.filter;

import javax.servlet.*;
import java.io.IOException;


public class EncodingFilter implements Filter {

    public void destroy(){

    }
    public void doFilter(ServletRequest arg0, ServletResponse arg1 , FilterChain arg2)throws IOException, ServletException {
        arg0.setCharacterEncoding("utf-8");
        arg1.setCharacterEncoding("utf-8");
        arg2.doFilter(arg0,arg1);
    }

    public void init(FilterConfig arg0)throws ServletException{

    }
}
