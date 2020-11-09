package com.pojo;

public class OrdersDetail {
    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private int did;
    private int oid;
    private int pid;
    private String pname;
    private int price;
    private String picName;

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }


    @Override
    public String toString() {
        return "OrdersDetail{" +
                "did=" + did +
                ", oid=" + oid +
                ", pid=" + pid +
                ", pname='" + pname + '\'' +
                ", price=" + price +
                ", picName='" + picName + '\'' +
                '}';
    }
}
