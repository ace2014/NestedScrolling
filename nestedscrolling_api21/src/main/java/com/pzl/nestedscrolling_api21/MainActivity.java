package com.pzl.nestedscrolling_api21;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.pzl.nestedscrolling_api21.demo1.Demo1Activity;
import com.pzl.nestedscrolling_api21.demo2.Demo2Activity;
import com.pzl.nestedscrolling_api21.demo3.Demo3Activity;
import com.pzl.nestedscrolling_api21.demo4.Demo4Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void click(View view) {
        Intent i = new Intent();
        switch (view.getId()) {
            case R.id.btn1:
                i.setClass(this, Demo1Activity.class);
                break;
            case R.id.btn2:
                i.setClass(this, Demo2Activity.class);
                break;
            case R.id.btn3:
                i.setClass(this, Demo3Activity.class);
                break;
            case R.id.btn4:
                i.setClass(this, Demo4Activity.class);
                break;
        }
        startActivity(i);
    }
}
