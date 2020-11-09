package com.test;

import com.dao.UserDao;
import com.daoImpl.UserDaoImpl;
import com.pojo.User;

public class TestUserDao {
    public static void main(String[] args) {
        UserDao userDao =new UserDaoImpl();
//        User user =new User();
//        user.setName("admin");
//        user.setPassword("admin");
//        user.setRealName("张三");
//        user.setPhone("13312312312");
//        user.setSex("女");
//        user.setIdentity("1231123");
//        //测试添加功能
//        userDao.addUser(user);

        //测试登录
        User user=userDao.login("admin","admin");
        System.out.println(user);


    }
}
