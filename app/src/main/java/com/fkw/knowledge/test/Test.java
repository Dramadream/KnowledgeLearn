package com.fkw.knowledge.test;

import com.fkw.knowledge.net.api.ApiManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ```
 * Author:          Kevin
 * CreatedTime:     2019/3/5 17:51
 * Desc:            TODO
 * <p>
 * ModifyTime:
 * ModifyItems:
 * ```
 *
 * @author: Kevin
 */
public class Test {

    public void test() {
        ApiManager.Companion.getInstance().getWanAndroidApi().getHomePageArticles(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(articleApiResponse -> articleApiResponse.getData());
    }
}
