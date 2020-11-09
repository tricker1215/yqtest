package com.test;

import com.dao.OrdersDao;
import com.daoImpl.OrdersDaoImpl;
import com.pojo.Orders;

public class TestOrdersDao {
    public static void main(String[] args) {
        OrdersDao ordersDao =new OrdersDaoImpl();
//        int oid =ordersDao.getOid(2);
//        System.out.println("oid:"+oid);

        Orders orders =ordersDao.getOrdersByUid(2);
        System.out.println("orders："+orders);
    }
}
