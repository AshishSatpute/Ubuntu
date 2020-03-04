package com.xlix.basuons.Model;

public class AnkModel {
    String et_ank_qty;
    int tv_ank_num;

    public AnkModel(String et_ank_qty, int tv_ank_num) {
        this.et_ank_qty = et_ank_qty;
        this.tv_ank_num = tv_ank_num;
    }

    public String getEt_ank_qty() {
        return et_ank_qty;
    }

    public void setEt_ank_qty(String et_ank_qty) {
        this.et_ank_qty = et_ank_qty;
    }

    public int getTv_ank_num() {
        return tv_ank_num;
    }

    public void setTv_ank_num(int tv_ank_num) {
        this.tv_ank_num = tv_ank_num;
    }

    @Override
    public String toString() {
        return "AnkModel{" +
                "et_ank_qty='" + et_ank_qty + '\'' +
                ", tv_ank_num=" + tv_ank_num +
                '}';
    }
}
