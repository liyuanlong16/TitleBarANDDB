package com.titlebarui.lijie.titlebarui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements TitleBarListener{
    private TitleBarUI titleBarUI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTitleBar();
    }
    // 设置toolbar
    public void initTitleBar() {
        titleBarUI = (TitleBarUI) findViewById(R.id.titleBarUI);
        titleBarUI.setzhongjianText("");
        titleBarUI.setLeftText("全部病例");
        titleBarUI.setSanjiao(R.drawable.share_nows);
        titleBarUI.setLeftImage(R.drawable.btn_leftflyout_n);
        titleBarUI.setRightImage(R.drawable.index_serch);
        titleBarUI.setRightSecond(R.drawable.warn_icon);
        titleBarUI.setRightText("");
        titleBarUI.setListener(MainActivity.this);
    }


    @Override
    public void zuobian() {
//        toggle();
    }

    @Override
    public void youbian() {
//        Intent intent = new Intent(CaseActivity.this, RemindAllActivity.class);
//        startActivity(intent);
    }

    @Override
    public void fenzu(View v) {

    }

    @Override
    public void youbianImage1() {

    }

    @Override
    public void zuobianTextView(View v) {

    }
}
