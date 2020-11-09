package com.test;

import com.dao.OrdersDetailDao;
import com.daoImpl.OrdersDetailDaoImpl;
import com.pojo.OrdersDetail;

import java.util.List;

public class TestOrderDetail {
    public static void main(String[] args) {
        OrdersDetailDao detailDao =new OrdersDetailDaoImpl();
        List<OrdersDetail> list=detailDao.getOrdersDetailsByOid(5);
        for (OrdersDetail detail:list){
            System.out.println(detail);
        }
    }
}
