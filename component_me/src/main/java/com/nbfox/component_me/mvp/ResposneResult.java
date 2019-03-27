package com.nbfox.component_me.mvp;

public class ResposneResult {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



    @Override
    public String toString() {
        return "ResposneResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
