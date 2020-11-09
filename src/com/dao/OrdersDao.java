package com.dao;

import com.pojo.Orders;
import com.pojo.OrdersDetail;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

public interface OrdersDao {

    //保存订单信息
    public void addOrders(Orders orders);

    //获取最后一条记录的oid
    public int getOid(int uid);

    public Orders getOrdersByUid(int uid);
}
