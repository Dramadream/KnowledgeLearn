package com.fkw.knowledge.net.api;

import com.blankj.utilcode.util.ToastUtils;
import com.fkw.knowledge.data.ApiResponse;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Author:          Kevin </BR>
 * CreatedTime:     2019/3/12 17:49 </BR>
 * Desc:            网络请求的结果的处理类 </BR>
 * <p>
 * ModifyTime:       </BR>
 * ModifyItems:      </BR>
 *
 * @author: Kevin
 */
public class NetSingleObserver<T> implements SingleObserver<ApiResponse<T>> {

    private SmartRefreshLayout mSmartRefreshLayout;

    public NetSingleObserver() {
    }

    public NetSingleObserver(SmartRefreshLayout smartRefreshLayout) {
        mSmartRefreshLayout = smartRefreshLayout;
    }

    @Override
    public void onSubscribe(Disposable d) {
        startAnim();
    }


    @Override
    public void onSuccess(ApiResponse<T> response) {
        endAnim();
    }

    @Override
    public void onError(Throwable e) {
        endAnim();
        ToastUtils.showShort("网络错误");
        e.printStackTrace();
    }


    /**
     * 开始动画
     */
    protected void startAnim() {
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.autoRefreshAnimationOnly();
        }
    }

    /**
     * 结束动画
     */
    protected void endAnim() {
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh(100);
        }
    }
}
