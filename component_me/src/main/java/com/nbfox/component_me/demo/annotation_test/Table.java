package com.nbfox.component_me.demo.annotation_test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target：用于描述注解的使用范围（即：被描述的注解可以用在什么地方）注解（annotation）可被用于 packages、types（类、接口、枚举、Annotation类型）、类型成员（方法、构造方法、成员变量、枚举值）、方法参数和本地变量（如循环变量、catch参数）。在注解类型的声明中使用了target可更加明晰其修饰的目标。取值(ElementType)有：
 *
 * ElementType.ANNOTATION_TYPE 可以应用于注释类型。
 * ElementType.CONSTRUCTOR 可以应用于构造函数。
 * ElementType.FIELD 可以应用于字段或属性。
 * ElementType.LOCAL_VARIABLE 可以应用于局部变量。
 * ElementType.METHOD 可以应用于方法级注释。
 * ElementType.PACKAGE 可以应用于包声明。
 * ElementType.PARAMETER 可以应用于方法的参数。
 * ElementType.TYPE 可以应用于类的任何元素。
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
   String value();
}

