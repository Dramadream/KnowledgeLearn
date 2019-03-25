package com.fkw.knowledge.widget;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fkw.knowledge.R;
import com.fkw.loadandretry.OnLoadingAndRetryListener;

/**
 * Author:          Kevin </BR>
 * CreatedTime:     2019/3/25 14:54 </BR>
 * Desc:            通用的Listener </BR>
 * <p>
 * ModifyTime:       </BR>
 * ModifyItems:      </BR>
 */
public abstract class CommonListener extends OnLoadingAndRetryListener {

    private ImageView ivRetry;
    private TextView tvRetry;
    private Button btnRetry;

    @Override
    public void setRetryEvent(View retryView) {
        ivRetry = retryView.findViewById(R.id.iv_retry);
        tvRetry = retryView.findViewById(R.id.tv_retry);
        btnRetry = retryView.findViewById(R.id.btn_retry);

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retry();
            }
        });

        setRetryViewResource(ivRetry, tvRetry, btnRetry);
    }


    @Override
    public void setLoadingEvent(View loadingView) {
        super.setLoadingEvent(loadingView);
        startLoadingAnim(loadingView);
    }


    @Override
    public void setEmptyEvent(View emptyView) {
        super.setEmptyEvent(emptyView);
    }

    @Override
    public int generateLoadingLayoutId() {
        return R.layout.layout_loading;
    }

    @Override
    public int generateRetryLayoutId() {
        return R.layout.layout_retry;
    }

    @Override
    public int generateEmptyLayoutId() {
        return R.layout.layout_empty;
    }

    /**
     * 开始Loading的动画
     */
    private void startLoadingAnim(View loadingView) {
        View tvLoading = loadingView.findViewById(R.id.tv_loading);
        AlphaAnimation alphaAnim = new AlphaAnimation(1f, 0f);
        alphaAnim.setDuration(1300);
        alphaAnim.setRepeatCount(Animation.INFINITE);
        alphaAnim.setRepeatMode(Animation.REVERSE);
        tvLoading.startAnimation(alphaAnim);
    }


    abstract protected void retry();

    /**
     * 设置重试界面的图片和文字资源
     */
    protected void setRetryViewResource(ImageView ivRetry, TextView tvRetry, Button btnRetry) {

    }


}
