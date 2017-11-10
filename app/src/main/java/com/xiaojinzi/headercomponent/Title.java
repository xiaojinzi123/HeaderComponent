package com.xiaojinzi.headercomponent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xiaojinzi on 09/11/2017 5:19 PM
 * blog: http://blog.csdn.net/u011692041
 * github: github.com/xiaojinzi123
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Title {

    /**
     * the title text of titlebar
     *
     * @return
     */
    String value() default "";

    /**
     * the back image rsd of titlebar
     */
    int backImg() default R.drawable.ehai_ui_back_icon1;

    /**
     * the menu image rsd of titlebar
     */
    int menuImg() default -1;

    /**
     * the menu text rsd of titlebar
     */
    String menuText() default "";

    /**
     * the menu click will call a method in Activity which name is this value
     *
     * @return
     */
    String menuClick() default "";

}
