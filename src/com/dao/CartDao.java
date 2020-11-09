package com.dao;

import com.pojo.Cart;

import java.util.List;

public interface CartDao {
    //添加商品到购物车

    public void addCart(Cart cart);

    public List<Cart> getCartByUid(int uid);

    public void deleteCart(int cid);

    public void deleteCartByUid(int uid);
}
