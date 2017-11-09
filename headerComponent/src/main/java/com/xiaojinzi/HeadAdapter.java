package com.xiaojinzi;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.lang.annotation.Annotation;

/**
 * Created by xiaojinzi on 09/11/2017 1:12 PM
 * blog: http://blog.csdn.net/u011692041
 * github: github.com/xiaojinzi123
 */
public interface HeadAdapter {

    /**
     * @param act     context of {@link android.app.Activity}
     * @param annotations a Annotation array of {@link android.app.Activity} Activity.class,
     *                    you need to parsing the Annotation and then get value to init your header View
     * @return you must return a View which will be added to phone DecorView
     */
    @Nullable
    View headerView(@NonNull Activity act, @Nullable Annotation... annotations);


}
