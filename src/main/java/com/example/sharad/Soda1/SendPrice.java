package com.example.sharad.Soda1;

/**
 * Created by admin on 29/3/18.
 */

public class SendPrice {
    String Time ;
    String  Date;
    Integer price;

    public SendPrice(String time, String date, Integer price) {
        Time = time;
        Date = date;
        this.price = price;
    }

    public String getTime() {
        return Time;
    }

    public String getDate() {
        return Date;
    }

    public Integer getPrice() {
        return price;
    }
    public SendPrice() {
    }
}
