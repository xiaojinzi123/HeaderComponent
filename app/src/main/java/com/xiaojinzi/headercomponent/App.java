package com.xiaojinzi.headercomponent;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaojinzi.FitsSystemHeaderAdapter;
import com.xiaojinzi.HeadActivityLifecycleCallbackImpl;
import com.xiaojinzi.HeadAdapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by xiaojinzi on 09/11/2017 10:51 AM
 * blog: http://blog.csdn.net/u011692041
 * github: github.com/xiaojinzi123
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        HeadAdapter headAdapter = new HeadAdapter() {
            @Nullable
            @Override
            public View headerView(@NonNull final Activity act, @Nullable Annotation... annotations) {
                return getView(act, annotations);
            }

        };

        HeadActivityLifecycleCallbackImpl callback = new HeadActivityLifecycleCallbackImpl(new FitsSystemHeaderAdapter(headAdapter));

        registerActivityLifecycleCallbacks(callback);

    }

    private View getView(@NonNull final Activity act, @Nullable Annotation[] annotations) {

        if (annotations == null) return null;

        boolean isHaveTitle = false;

        Title titleAnnotation = null;

        for (Annotation annotation : annotations) {
            if (annotation instanceof Title) {
                titleAnnotation = (Title) annotation;
                isHaveTitle = true;
            }
        }

        if (!isHaveTitle || titleAnnotation == null) {
            return null;
        }

        // init view

        View view = View.inflate(act.getBaseContext(), R.layout.ehai_ui_titlebar1, null);
        TextView tv_title = view.findViewById(R.id.tv_title);
        ImageView iv_back = view.findViewById(R.id.iv_back);
        ImageView iv_menu = view.findViewById(R.id.iv_menu);
        TextView tv_menu = view.findViewById(R.id.tv_menu);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.finish();
            }
        });

        int backImg = titleAnnotation.backImg();
        if (backImg != -1) {
            iv_back.setImageResource(backImg);
        }

        String titleText = titleAnnotation.value();
        tv_title.setText(titleText);

        String menuText = titleAnnotation.menuText();
        tv_menu.setText(menuText);

        int ivMenu = titleAnnotation.menuImg();
        if (ivMenu != -1) {
            iv_menu.setImageResource(ivMenu);
        }

        // get the click method name of menu
        String menuClickMethod = titleAnnotation.menuClick();

        try {
            final Method onTitleClick = act.getClass().getMethod(menuClickMethod, new Class[]{View.class});
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        onTitleClick.invoke(act, v);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            tv_menu.setOnClickListener(listener);
            iv_menu.setOnClickListener(listener);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return view;
    }


}
