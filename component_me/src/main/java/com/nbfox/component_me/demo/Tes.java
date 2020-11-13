package com.nbfox.component_me.demo;

import java.util.ArrayList;
import java.util.List;

public class Tes {
    public static void main (String[] args) throws java.lang.Exception
    {
        List<String> list1=new ArrayList<String>();
        list1.add("我的你");
        List<String> list=new ArrayList<String>(list1);
        for (int i=0;i<list.size() ;i++ ){
            System.out.println("---hi="+list.get(i));
        }
        System.out.println("hi");
    }
}
