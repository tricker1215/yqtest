package com.utils;
//操作数据库的工具类

import java.sql.*;

public class BaseDao {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet rs;


    //获取数据库链接
    public boolean getConnection(){
        boolean flag =false;
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取链接
            String url="jdbc:mysql://localhost:3306/mytest";
            String user="root";
            String password="123456";
            connection= DriverManager.getConnection(url,user,password);
            if (connection!=null){
                System.out.println("连接成功");
                flag =  true;
            }else{
                System.out.println("数据库连接失败");
                flag = false;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return  flag;
    }

    //执行添加，修改，删除功能的方法
    public int update(String sql,Object params[]){
            int total =0;
            //获取连接，获取返回值
            //获取PreParedStatement
            try {
                if(getConnection()) {
                statement = connection.prepareStatement(sql);
                for(int i=0;i<params.length;i++) {
                    statement.setObject(i+1,params[i]);
                }
                total=statement.executeUpdate();
            }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return  total;
    }

    //执行所有查询操作
    public ResultSet query(String sql ,Object params[]){

            try {
                if(getConnection()) {
                    statement = connection.prepareStatement(sql);

                    for(int i=0;i<params.length;i++){
                        statement.setObject(i+1,params[i]);
                    }

                    rs=statement.executeQuery();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return rs;
    }

    //关闭资源，先开后关
    public void close(){
            try {
                if (rs!=null) {
                    rs.close();
                }
                if(statement!=null){
                    statement.close();
                }
                if (connection!=null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

}

