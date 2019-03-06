package com.fkw.knowledge.base;

import android.support.v7.app.AppCompatActivity;

import com.fkw.knowledge.util.DisposeUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Author:          Kevin </BR>
 * CreatedTime:     2019/3/6 16:42 </BR>
 * Desc:            所有Activity的基类 </BR>
 * <p>
 * ModifyTime:       </BR>
 * ModifyItems:      </BR>
 *
 * @author: Kevin
 */
public class BaseActivity extends AppCompatActivity {

    private List<Disposable> mDisposableList;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        DisposeUtil.dispose(mDisposableList);
    }

    /**
     * 将订阅关系添加到列表中
     *
     * @param disposable
     */
    protected void addDisposable(Disposable disposable) {
        if (mDisposableList == null) {
            mDisposableList = new ArrayList<>();
        }

        if (!mDisposableList.contains(disposable)) {
            mDisposableList.add(disposable);
        }
    }

}
