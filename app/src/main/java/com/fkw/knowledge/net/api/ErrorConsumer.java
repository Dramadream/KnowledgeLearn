package com.fkw.knowledge.net.api;

import com.blankj.utilcode.util.ToastUtils;

import io.reactivex.functions.Consumer;

/**
 * Author:          Kevin </BR>
 * CreatedTime:     2019/3/6 16:59 </BR>
 * Desc:            错误的处理 </BR>
 * <p>
 * ModifyTime:       </BR>
 * ModifyItems:      </BR>
 *
 * @author: Kevin
 */
public class ErrorConsumer implements Consumer<Throwable> {

    @Override
    public void accept(Throwable throwable) throws Exception {
        ToastUtils.showShort("网络错误");
        throwable.printStackTrace();
    }
}
