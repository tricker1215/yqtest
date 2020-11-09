package com.dao;

import com.pojo.OrdersDetail;

import java.util.List;

public interface OrdersDetailDao {
    //保存订单信息
    public void addOrdersDetail(OrdersDetail detail);

    public List<OrdersDetail> getOrdersDetailsByOid(int oid);
}
