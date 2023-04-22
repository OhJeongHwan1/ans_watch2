package com.example.ans_watch;

import android.app.Application;

public class MyApplication extends Application {

    private boolean isCheck = false;

    public boolean getIsChecked() {
        return isCheck;
    }

    public void setMyData(boolean isChecked) {
        this.isCheck = isChecked;
    }

    private String st = "no";

    public String getSt() {return st;}

    public void setSt(String st){ this.st = st;}
}
