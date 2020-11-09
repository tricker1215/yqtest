package com.daoImpl;

import com.dao.OrdersDetailDao;
import com.pojo.OrdersDetail;
import com.utils.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDetailDaoImpl extends BaseDao implements OrdersDetailDao {

    public void addOrdersDetail(OrdersDetail detail){
        String sql="\n" +
                "INSERT INTO orders_detail(oid,pid,pname,price)\n" +
                "VALUES(?,?,?,?)";
        Object params[]={detail.getOid(),detail.getPid(),detail.getPname(),detail.getPrice()};

        update(sql,params);
        close();
    }

    public List<OrdersDetail> getOrdersDetailsByOid(int oid){
        String sql="SELECT* FROM orders_detail WHERE oid=?";
        Object params[]={oid};
        List<OrdersDetail> list =new ArrayList<OrdersDetail>();

        //执行查询操作
        ResultSet rs=query(sql,params);

        try {
            while (rs.next()) {
                OrdersDetail detail =new OrdersDetail();
                detail.setDid(rs.getInt("did"));
                detail.setOid(rs.getInt("oid"));
                detail.setPid(rs.getInt("pid"));
                detail.setPrice(rs.getInt("price"));
                detail.setPname(rs.getString("pname"));
                detail.setPicName(rs.getString("picName"));
                list.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return list;

    }
}
