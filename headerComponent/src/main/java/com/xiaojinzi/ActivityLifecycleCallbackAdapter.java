package com.xiaojinzi;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by xiaojinzi on 09/11/2017 1:15 PM
 * blog: http://blog.csdn.net/u011692041
 * github: github.com/xiaojinzi123
 */
public class ActivityLifecycleCallbackAdapter implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }

}
