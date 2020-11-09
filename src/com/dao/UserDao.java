package com.dao;

import com.pojo.User;

public interface UserDao {

    //添加用户
    public void addUser(User user);

    public User login(String name,String password);
}
