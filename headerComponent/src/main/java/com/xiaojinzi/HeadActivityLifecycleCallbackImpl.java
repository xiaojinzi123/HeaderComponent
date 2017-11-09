package com.xiaojinzi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import java.lang.annotation.Annotation;

/**
 * Created by xiaojinzi on 09/11/2017 1:14 PM
 * blog: http://blog.csdn.net/u011692041
 * github: github.com/xiaojinzi123
 * <p>
 * this class is help user to implement the Custom TitleBar easily
 */
public class HeadActivityLifecycleCallbackImpl extends ActivityLifecycleCallbackAdapter {

    @NonNull
    private HeadAdapter headAdapter;

    public HeadActivityLifecycleCallbackImpl(@NonNull HeadAdapter headAdapter) {
        if (headAdapter == null) {
            throw new IllegalArgumentException("the HeadAdapter parameter can't be null");
        }
        this.headAdapter = headAdapter;
    }

    @Override
    public void onActivityCreated(final Activity act, Bundle savedInstanceState) {

        System.out.println("act = " + act.getClass().getName());

        Annotation[] annotations = act.getClass().getAnnotations();

        System.out.println("annotations = " + annotations.length);

        View headerView = headAdapter.headerView(act, annotations);
        if (headerView == null) return;

        ViewGroup decorView = (ViewGroup) act.getWindow().getDecorView();

        if (decorView.getChildCount() != 1) return;

        View contentRoot = decorView.getChildAt(0);
        decorView.removeView(contentRoot);

        LinearLayout linearLayout = new LinearLayout(act.getBaseContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        linearLayout.addView(headerView);
        linearLayout.addView(contentRoot);

        decorView.addView(linearLayout);

        // try to compatible machines which api is lowwer than 19

        act.getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                try {
                    ViewGroup decorView = (ViewGroup) act.getWindow().getDecorView();
                    View contentRoot = ((ViewGroup) decorView.getChildAt(0)).getChildAt(1);
                    if (contentRoot.getPaddingTop() != getStatusHeight(act.getBaseContext()))
                        return;
                    contentRoot.setPadding(
                            contentRoot.getPaddingLeft(),
                            0,
                            contentRoot.getPaddingRight(),
                            contentRoot.getPaddingBottom()
                    );
                } catch (Throwable ignore) {
                }
            }
        });

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
