package com.shunan.baseproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.orhanobut.logger.Logger;
import com.shunan.webviewjsbridge.WebViewJsBridgeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate");
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, WebViewJsBridgeActivity.class);
        intent.putExtra("url","file:////android_asset/test.html");
        startActivity(intent);
//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}
