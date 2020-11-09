package com.test;

import com.dao.CartDao;
import com.daoImpl.CartDaoImpl;
import com.pojo.Cart;

import java.util.List;

public class TestCartDao {
    public static void main(String[] args) {
        CartDao cartDao =new CartDaoImpl();

        Cart cart =new Cart();
        cart.setPid(2);
        cart.setPquantity(2);
        cart.setUid(2);
        cartDao.addCart(cart);

//        List<Cart> list=cartDao.getCartByUid(2);
//        for(Cart cart:list){
//            System.out.println(cart);
//        }
    }
}
