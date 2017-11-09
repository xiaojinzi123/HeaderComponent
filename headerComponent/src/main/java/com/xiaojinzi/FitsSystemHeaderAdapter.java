package com.xiaojinzi;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.lang.annotation.Annotation;

/**
 * Created by xiaojinzi on 09/11/2017 5:13 PM
 * blog: http://blog.csdn.net/u011692041
 * github: github.com/xiaojinzi123
 */
public class FitsSystemHeaderAdapter implements HeadAdapter {

    private HeadAdapter adapter;

    public FitsSystemHeaderAdapter(HeadAdapter adapter) {
        if (adapter == null) {
            throw new IllegalArgumentException("the HeadAdapter can't be null");
        }
        this.adapter = adapter;
    }

    @Nullable
    @Override
    public View headerView(@NonNull Activity act, @Nullable Annotation... annotations) {
        View headerView = adapter.headerView(act, annotations);
        if (headerView != null) {
            headerView.setPadding(
                    headerView.getPaddingLeft(),
                    getStatusHeight(act.getBaseContext()),
                    headerView.getPaddingRight(),
                    headerView.getPaddingBottom()
            );
        }
        return headerView;
    }

    /**
     * get the height of status
     *
     * @param context context
     * @return
     */
    public int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

}
