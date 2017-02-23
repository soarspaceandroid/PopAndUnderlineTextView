package com.example.administrator.myapplication;

/**
 * Created by Administrator on 2017/2/15.
 */

public class MenuItemBean {

    public String menuString;
    public int color;

    public MenuItemBean(String menuString, int color) {
        this.menuString = menuString;
        this.color = color;
    }


    public String getMenuString() {
        return menuString;
    }

    public void setMenuString(String menuString) {
        this.menuString = menuString;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
