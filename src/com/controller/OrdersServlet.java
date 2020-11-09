package com.controller;

import com.dao.CartDao;
import com.dao.OrdersDao;
import com.dao.OrdersDetailDao;
import com.dao.ProductDao;
import com.daoImpl.CartDaoImpl;
import com.daoImpl.OrdersDaoImpl;
import com.daoImpl.OrdersDetailDaoImpl;
import com.daoImpl.ProductDaoImpl;
import com.pojo.*;
import jdk.nashorn.internal.ir.CatchNode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class OrdersServlet extends HttpServlet {

    private CartDao cartDao =new CartDaoImpl();
    private OrdersDao ordersDao =new OrdersDaoImpl();
    private ProductDao productDao =new ProductDaoImpl();
    private OrdersDetailDao detailDao =new OrdersDetailDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();

        int index = path.lastIndexOf("/");

        String realPath = path.substring(index + 1);
        System.out.println("realPath:" + realPath);

        if (realPath.equals("buy")) {
            buy(request,response);
        }
    }

    protected void buy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("--------buy--------");

        HttpSession session =request.getSession();
        User user=(User)session.getAttribute("user");
        //获取用户id
        int uid =user.getId();
        String uname =user.getName();
        Date createTime = new Date();

        List<Cart> list = cartDao.getCartByUid(uid);

        int totalPrice =0;
        for(Cart cart:list){

            int pquantity =cart.getPquantity();
            int price=cart.getProduct().getPrice();

            totalPrice=totalPrice+pquantity*price;
        }
        //封装订单信息
        Orders orders = new Orders();
        orders.setCreateTime(createTime);
        orders.setOcost(totalPrice);
        orders.setUid(uid);
        orders.setUname(uname);
        //保存订单信息
        ordersDao.addOrders(orders);

        //保存订单详情
        //获取订单主键
        int oid=ordersDao.getOid(uid);

        for (Cart cart:list){
            //获取商品主键
            int pid=cart.getPid();
            int pquantity=cart.getPquantity();
            Product product = productDao.getProductById(pid);
            String pname=product.getPname();
            int price=product.getPrice();
            int total=price*pquantity;

            //封装要保存的订单详情
            OrdersDetail detail =new OrdersDetail();
            detail.setOid(oid);
            detail.setPid(pid);
            detail.setPname(pname);
            detail.setPrice(total);

            //执行保存订单详情
            detailDao.addOrdersDetail(detail);

            //根据用户id删除购物车信息
            cartDao.deleteCartByUid(uid);
        }

        request.getRequestDispatcher("shopping-result.jsp").forward(request,response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
