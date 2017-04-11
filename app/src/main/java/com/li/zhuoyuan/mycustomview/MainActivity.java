package com.li.zhuoyuan.mycustomview;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TitleBar titleBar;
    private CoordinatorLayout cLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cLayout = (CoordinatorLayout) findViewById(R.id.constraintlayout);
        titleBar = (TitleBar) findViewById(R.id.titleBar);
        titleBar.setTitleBarOnClickListener(new TitleBar.titlebarClickListener() {
            @Override
            public void rightclick() {
                Snackbar.make(cLayout, "是否撤销？", Snackbar.LENGTH_LONG).setAction("撤销", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "action", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }

            @Override
            public void leftclick() {
                finish();
            }
        });
    }
}
