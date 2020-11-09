package com.daoImpl;

import com.dao.ProductDao;
import com.pojo.Product;
import com.utils.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDaoImpl extends BaseDao implements ProductDao {
    public Product getProductById(int pid){
        String sql="SELECT pname,price FROM product WHERE pid=?";
        Object params[]={pid};

        Product product=null;

        ResultSet rs =query(sql,params);

        try {
            if (rs.next()){
                product=new Product();
                product.setPname(rs.getString("pname"));
                product.setPrice(rs.getInt("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return product;
    }
}
