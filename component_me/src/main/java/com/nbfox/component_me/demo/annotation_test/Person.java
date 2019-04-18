package com.nbfox.component_me.demo.annotation_test;
@Table("persontable")
public class Person {
    @Colunm("user_name")
    private String name;
    @Colunm("user_pwd")
    private String pwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
