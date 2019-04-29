package com.shunan.baseproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.orhanobut.logger.Logger;
import com.shunan.webviewjsbridge.WebViewJsBridgeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate");
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebViewJsBridgeActivity.class);
                intent.putExtra("url","http://s.caihuimall.net/eq-cms/policy/index?token=e5793ffd1a7ddb2712a6c2d86fe08bbe");
                startActivity(intent);
            }
        });
    }
}
