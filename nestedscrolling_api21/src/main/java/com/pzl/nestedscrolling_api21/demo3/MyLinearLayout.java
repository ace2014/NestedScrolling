package com.pzl.nestedscrolling_api21.demo3;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

public class MyLinearLayout extends LinearLayout {
    String TAG = "MyLinearLayout";
    int maxTop;
    ViewPager vp;

    LinearLayout header;
    TabLayout tabLayout;

    public MyLinearLayout(Context context) {
        this(context, null);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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
        //rv

        boolean hiddenTop = dy > 0 && getScrollY() < maxTop;
        boolean showTop = dy < 0 && getScrollY() >= 0 && !ViewCompat.canScrollVertically(target, -1);

        if (hiddenTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        header.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        tabLayout.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

        maxTop = header.getMeasuredHeight();

        ViewGroup.LayoutParams lp = vp.getLayoutParams();
        lp.height = getMeasuredHeight() - tabLayout.getMeasuredHeight();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        header = (LinearLayout) findViewById(R.id.header);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        vp = (ViewPager) findViewById(R.id.vp);
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > maxTop) {
            y = maxTop;
        }
        if (y != getScrollY()) {
            super.scrollTo(x, y);
        }
    }
}
