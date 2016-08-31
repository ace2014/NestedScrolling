package com.pzl.nestedscrolling_api21.demo2;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author zl.peng
 * @version [1.0, 2016-08-31]
 */
public class Adapter extends RecyclerView.Adapter {
    private static final String TAG = "Adapter";

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder-position:" + position);
        ((MyViewHolder) holder).tv.setText(String.valueOf((char) (65 + position)));
    }

    @Override
    public int getItemCount() {
        return 26;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }

}
