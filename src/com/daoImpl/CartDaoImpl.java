package com.daoImpl;

import com.dao.CartDao;
import com.pojo.Cart;
import com.pojo.Product;
import com.utils.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl extends BaseDao implements CartDao {
    @Override
    public void addCart(Cart cart) {
        String sql="INSERT INTO cart(pid,pquantity,uid) VALUES(?,?,?)";
        Object params[]={cart.getPid(),cart.getPquantity(),cart.getUid()};

        //执行
        update(sql,params);
        close();
    }

    public List<Cart> getCartByUid(int uid){
        String sql="SELECT c.*,p.pname,p.price"
        +" FROM cart c,product p"
        +" WHERE c.pid=p.pid AND c.uid=?";
        Object params[]={uid};
        List<Cart> list =new ArrayList<Cart>();

        //执行查询
        ResultSet rs=query(sql,params);
        //封装数据
        try{
            while (rs.next()){
                //封装cart信息
                Cart cart=cart=new Cart();
                cart.setCid(rs.getInt("cid"));
                cart.setPid(rs.getInt("pid"));
                cart.setPquantity(rs.getInt("pquantity"));
                cart.setUid(rs.getInt("uid"));

                //封装商品信息
                Product product =new Product();
                product.setPname(rs.getString("pname"));
                product.setPrice(rs.getInt("price"));

                //建立商品与购物车关系
                cart.setProduct(product);

                //将购物车保存到集合
                list.add(cart);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        close();
        return list;
    }

    public void deleteCart(int cid){
        String sql="DELETE FROM cart WHERE cid=?";
        Object params[]={cid};
        update(sql,params);
        close();
    }

    public void deleteCartByUid(int uid){
        String sql="DELETE FROM cart WHERE uid=?\n";
        Object params[]={uid};
        update(sql,params);
        close();
    }
}
