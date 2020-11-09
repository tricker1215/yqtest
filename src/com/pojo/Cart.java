package com.pojo;

public class Cart {

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }



    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getPquantity() {
        return pquantity;
    }

    public void setPquantity(int pquantity) {
        this.pquantity = pquantity;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    private int cid;
    private int pid;
    private int pquantity;
    private int uid;
    //保存商品信息
    private Product product;

    @Override
    public String toString() {
        return "Cart{" +
                "cid=" + cid +
                ", pid=" + pid +
                ", pquantity=" + pquantity +
                ", uid=" + uid +
                ", product=" + product +
                '}';
    }
}
