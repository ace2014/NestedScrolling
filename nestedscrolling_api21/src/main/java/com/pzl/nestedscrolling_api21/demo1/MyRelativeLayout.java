package com.pzl.nestedscrolling_api21.demo1;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.pzl.nestedscrolling_api21.R;

/**
 * Created by zl.peng on 2016/8/17 21:39.
 * <p/>
 * Log.i(TAG, "是否可往下滑动 " + ViewCompat.canScrollVertically(target, -1));
 * Log.i(TAG, "是否可往上滑动 " + ViewCompat.canScrollVertically(target, 1));
 */

/**
 * api 21 及以上 listview,scrollview或recycleview,nestedscrollview都可以实现嵌套滑动；
 * api 21 以下只能使用recycleview,nestedscrollview实现嵌套滑动(或自定义listview和scrollview并
 * 实现nestedscrolling接口然后重写里面部分关键方法，难度大)；
 * 若要兼容所有api 请使用支持包里的recycleview,nestedscrollview；
 */

public class MyRelativeLayout extends RelativeLayout {
    String TAG = "NestedRelativeLayout";
    int maxTop = 200;
    int currentTop = 0;
    RelativeLayout rlTop;
    ScrollView sv;

    public MyRelativeLayout(Context context) {
        this(context, null);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {

    }

    @Override
    public void onStopNestedScroll(View target) {

    }

    /**
     * 子View一定会处理这些事件（消费或不消费），只是在告知父View子View是怎么处理这次事件的（告知详情给父View）,
     * 父View知道后需不需要做一些自己的逻辑处理，由父View自己决定
     *
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.i(TAG, "dyUnconsumed=" + dyUnconsumed + ",dyConsumed=" + dyConsumed);

        int dy = 0;

        if (dyUnconsumed < 0 && dyConsumed == 0) {//已经在顶部状态，向下滑动
            dy = dyUnconsumed;
        } else if (dyUnconsumed > 0 && dyConsumed == 0) {//已经在底部状态，向上滑动
            dy = dyUnconsumed;
        } else if (dyUnconsumed == 0 && dyConsumed < 0) {//中间状态，向下滑动
            dy = dyConsumed;
        } else if (dyUnconsumed == 0 && dyConsumed > 0) {//中间状态，向上滑动
            dy = dyConsumed;
        } else if (dyUnconsumed > 0 && dyConsumed > 0) { //状态=离滑动到底端还有小段距离，小段距离<moveUnit;向上滑的时候

        } else if (dyUnconsumed < 0 && dyConsumed < 0) {//状态=离滑动到顶端还有小段距离，小段距离<moveUnit;向下滑动的时候
            dy = dyUnconsumed;
        }

        /**
         * 边界判断
         */
        if (currentTop - dy > maxTop) {
            currentTop = maxTop;
        } else if (currentTop - dy < 0) {
            currentTop = 0;
        } else {
            currentTop -= dy;
        }

        ViewGroup.LayoutParams lp_tlTop = rlTop.getLayoutParams();
        lp_tlTop.height = currentTop;
        requestLayout();
    }

    /**
     * 子View给个机会让父View先处理到来的事件，当然父View可以消费也可以不消费；父View如果消费事件那么参数consumed数组至少有一个元素不为0，
     * 否则父View不消费事件的话那么参数consumed数组内2个元素都为0；而子View根据dispatchNestedPreScroll的返回值将会知道父View消没消费，
     * 然后子View自己决定自己还要不要处理这次事件，如果子View想处理剩下父View没消费完的事件，可根据参数consumed和参数offsetInWindow做一个协同处理
     *
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     */
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {

    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return 0;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        rlTop = (RelativeLayout) findViewById(R.id.rlTop);
        sv = (ScrollView) findViewById(R.id.sv);
    }
}
