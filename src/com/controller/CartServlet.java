package com.controller;

import com.dao.CartDao;
import com.daoImpl.CartDaoImpl;
import com.pojo.Cart;
import com.pojo.Product;
import com.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CartServlet extends HttpServlet {
    private CartDao cartDao = new CartDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();

        int index = path.lastIndexOf("/");

        String realPath = path.substring(index + 1);
        System.out.println("realPath:" + realPath);

        if (realPath.equals("shopping")) {
            shopping(request, response);
        } else if (realPath.equals("query")) {
            query(request, response);
        } else if(realPath.equals("deleteCart")){
            deleteCart(request,response);
        }
    }

    protected void shopping(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("-------shopping------");
        String pids = request.getParameter("pid");
        String pq = request.getParameter("pquantity");
        System.out.println("pids:" + pids);
        System.out.println("pquantitys:" + pids);
        //类型转换
        int pid = Integer.parseInt(pids);
        int pquantity = Integer.parseInt(pq);
        //获取当前用户的主键
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int uid = user.getId();
        System.out.println("uid:" + uid);

        //封装数据
        Cart cart = new Cart();
        cart.setPid(pid);
        cart.setPquantity(pquantity);
        cart.setUid(uid);

        cartDao.addCart(cart);

        response.sendRedirect("detail.jsp");

    }

    protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("---------query---------");
        //获取用户主键
        HttpSession session =request.getSession();
        User user =(User)session.getAttribute("user");
        int uid =user.getId();


        //根据用户主键查询购物车
        List<Cart> list=cartDao.getCartByUid(uid);
        int totalPrice =0;
        for (Cart cart:list){
            //获取商品信息
            Product product =cart.getProduct();

            int price =product.getPrice();
            int pquantity =cart.getPquantity();

            totalPrice=totalPrice+price*pquantity;
        }


        request.setAttribute("list",list);

        request.setAttribute("totalPrice",totalPrice);

        //只能转发
        request.getRequestDispatcher("cart.jsp").forward(request,response);
    }

    protected void deleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("----------deleteCart--------");
        String cids=request.getParameter("cid");
        System.out.println("cids:"+cids);

        //数据类型转换
        int cid =Integer.parseInt(cids);

        //删除操作
        cartDao.deleteCart(cid);

        //跳转查询
        query(request,response);
    }
}