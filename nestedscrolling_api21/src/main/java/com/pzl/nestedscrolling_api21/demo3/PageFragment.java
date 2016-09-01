package com.pzl.nestedscrolling_api21.demo3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pzl.nestedscrolling_api21.R;
import com.pzl.nestedscrolling_api21.demo2.Adapter;


/**
 * @author zl.peng
 * @version [1.0, 2016-08-30]
 */
public class PageFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_page, container, false);
        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        Adapter adapter = new Adapter();
        rv.setAdapter(adapter);
        return v;
    }

    public static PageFragment newInstance() {
        return new PageFragment();
    }
}
