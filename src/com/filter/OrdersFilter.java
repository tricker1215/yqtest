package com.filter;

import com.dao.OrdersDao;
import com.dao.OrdersDetailDao;
import com.daoImpl.OrdersDaoImpl;
import com.daoImpl.OrdersDetailDaoImpl;
import com.pojo.Orders;
import com.pojo.OrdersDetail;
import com.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrdersFilter implements Filter {
    private OrdersDao ordersDao =new OrdersDaoImpl();
    private OrdersDetailDao detailDao =new OrdersDetailDaoImpl();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    //从order_view.jsp注入订单信息和订单详情信息
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        System.out.println("-----OrdersFilter----------");
        //向下转型
        HttpServletRequest request=(HttpServletRequest)arg0;
        HttpServletResponse response=(HttpServletResponse)arg1;

        //获取HttpSession对象
        HttpSession session=request.getSession();
        User user =(User)session.getAttribute("user");
        //获取用户id
        int uid =user.getId();
        //获取订单主键
        Orders orders= ordersDao.getOrdersByUid(uid);
        int oid =orders.getOid();
        //查询oid订单信息
        List<OrdersDetail> list=detailDao.getOrdersDetailsByOid(oid);
         //将查询的结果保存到session中
        session.setAttribute("orders",orders);
        session.setAttribute("list",list);

        //向下传递
        arg2.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
