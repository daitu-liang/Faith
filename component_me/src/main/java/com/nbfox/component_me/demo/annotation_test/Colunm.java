package com.nbfox.component_me.demo.annotation_test;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Retention定义了该注解被保留的时间长短
 * 1.SOURCE:在源文件中有效（即源文件保留）
 * 2.CLASS:在class文件中有效（即class保留）
 * 3.RUNTIME:在运行时有效（即运行时保留）
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Colunm {
    String value();
}
