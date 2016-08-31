package com.pzl.nestedscrolling_api21.demo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.pzl.nestedscrolling_api21.R;

public class Demo2Activity extends AppCompatActivity {
    private RecyclerView rv;
    private RelativeLayout rlContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv);
        Adapter adapter = new Adapter();
        rv.setAdapter(adapter);

        rlContainer = (RelativeLayout) findViewById(R.id.container);
        rlContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }
}
