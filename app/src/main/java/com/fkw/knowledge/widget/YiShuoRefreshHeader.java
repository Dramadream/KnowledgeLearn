package com.fkw.knowledge.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;
import com.fkw.knowledge.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * Author:          Kevin </BR>
 * CreatedTime:     2019/3/8 10:10 </BR>
 * Desc:            自定义的Header </BR>
 * <p>
 * ModifyTime:       </BR>
 * ModifyItems:      </BR>
 *
 * @author: Kevin
 */
public class YiShuoRefreshHeader extends LinearLayout implements RefreshHeader {


    private ImageView ivRefresh;
    private TextView tvRefresh;
    private GifDrawable mGifDrawable;
    private View mView;

    public YiShuoRefreshHeader(Context context) {
        this(context, null);
    }


    public YiShuoRefreshHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mView = LayoutInflater.from(context).inflate(R.layout.layout_header_common, this);
        initView();
    }

    private void initView() {
        ivRefresh = findViewById(R.id.iv_refresh);
        tvRefresh = findViewById(R.id.tv_refresh);
        Glide.with(this)
                .asGif()
                .load(R.drawable.icon_loading_header)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(ivRefresh);
        mGifDrawable = (GifDrawable) ivRefresh.getDrawable();
    }


    @NonNull
    @Override
    public View getView() {
        return mView;
    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {
        stopAnimation();
    }


    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            // 下拉刷新
            case PullDownToRefresh:
                tvRefresh.setText("下拉开始刷新");
                stopAnimation();
                break;
            case ReleaseToRefresh:
                tvRefresh.setText("释放立即刷新");
                stopAnimation();
                break;
            case Refreshing:
                tvRefresh.setText("正在刷新");
                startAnim();
                break;
            // 刷新完成的操作在下面onFinish中处理了
            //case RefreshFinish:
            //    tvRefresh.setText("刷新完成");
            //    break;
            default:
                break;
        }
    }

    private void startAnim() {
        if (mGifDrawable != null) {
            mGifDrawable.start();
        }
    }

    private void stopAnimation() {
        if (mGifDrawable != null) {
            mGifDrawable.stop();
        }
    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        //startAnim();
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        stopAnimation();
        tvRefresh.setText(success ? "刷新完成" : "刷新失败");
        // 延迟300毫秒再弹回
        return 300;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }


    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }


    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }


}
