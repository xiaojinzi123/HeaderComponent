package com.xiaojinzi.headercomponent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

@Title(value = "我是小金子", menuText = "菜单")
public class MainAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
    }

    public void onTitleClick(View view) {
        Toast.makeText(this, "v= " + view, Toast.LENGTH_SHORT).show();
    }

}
