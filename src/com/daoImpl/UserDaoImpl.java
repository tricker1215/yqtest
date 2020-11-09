package com.daoImpl;

import com.dao.UserDao;
import com.pojo.User;
import com.utils.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDaoImpl extends BaseDao implements UserDao {

    //添加用户
    public void addUser(User user) {
        String sql="INSERT INTO USER(NAME,PASSWORD,realName,identity,sex,phone) \n" +
                "  VALUE(?,?,?,?,?,?)";
        Object params[]={user.getName(),user.getPassword(),user.getRealName(),user.getIdentity(),
        user.getSex(),user.getPhone()};
        update(sql,params);
        close();
    }

    public User login(String name,String password){
        String sql="SELECT* FROM USER WHERE NAME=? AND PASSWORD=?";
        Object params[]={name,password};
        User user = null;
        ResultSet rs =query(sql,params);
        try{
            while (rs.next()){
                user =new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setRealName(rs.getString("realName"));
                user.setIdentity(rs.getString("identity"));
                user.setSex(rs.getString("sex"));
                user.setPhone(rs.getString("phone"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        close();
        return user;
    }

}
