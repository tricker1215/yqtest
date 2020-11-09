package com.test;

import com.dao.ProductDao;
import com.daoImpl.ProductDaoImpl;
import com.pojo.Product;

public class TestProductDao {
    public static void main(String[] args) {
        ProductDao productDao =new ProductDaoImpl();
        Product product =productDao.getProductById(1);
        System.out.println(product);
    }
}
