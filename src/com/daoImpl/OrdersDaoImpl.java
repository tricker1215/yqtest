package com.daoImpl;

import com.dao.OrdersDao;
import com.pojo.Orders;
import com.utils.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersDaoImpl extends BaseDao implements OrdersDao {
    public void  addOrders(Orders orders){
        String sql="INSERT INTO orders(uid,uname,createTime,ocost)\n" +
                " VALUES(?,?,?,?)";
        Object params[]={orders.getUid(),orders.getUname(),orders.getCreateTime(),
                        orders.getOcost()};

        update(sql,params);

        close();
    }

    //获取最后一条oid
     public int getOid(int uid){
        String sql="SELECT oid FROM orders WHERE uid=?";
        Object params[]={uid};

        int oid =0;
        //执行查询
         ResultSet rs=query(sql,params);

         try {
             if (rs.last()){
                 oid=rs.getInt("oid");
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         close();
         return oid;

     }

    public Orders getOrdersByUid(int uid){
        String sql="SELECT* FROM orders WHERE uid=?";
        Object params[]={uid};
        Orders orders=null;

        ResultSet rs=query(sql,params);

        try {
            if (rs.last()){
                orders =new Orders();
                orders.setOid(rs.getInt("oid"));
                orders.setUid(rs.getInt("uid"));
                orders.setOcost(rs.getInt("ocost"));
                orders.setUname(rs.getString("uname"));
                orders.setCreateTime(rs.getTimestamp("createTime"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return orders;
    }
}
