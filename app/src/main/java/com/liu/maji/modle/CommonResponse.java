package com.liu.maji.modle;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/28.
 */

public class CommonResponse implements Serializable {



    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private String message;
    private boolean success;


    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }



}

