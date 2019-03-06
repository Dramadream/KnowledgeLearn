package com.fkw.knowledge.util;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Author:          Kevin <BR/>
 * CreatedTime:     2018/6/15 11:44 <BR/>
 * Desc:            取消Rx订阅的工具类 <BR/>
 * <p/>
 * ModifyTime:      <BR/>
 * ModifyItems:     <BR/>
 *
 * @author: Kevin <BR/>
 */
public class DisposeUtil {


    public static void dispose(Disposable disposable) {
        if (disposable != null) {
            if (!disposable.isDisposed()) {
                disposable.dispose();
            }
            disposable = null;
        }
    }


    public static void dispose(Disposable... disposables) {
        for (Disposable disposable : disposables) {
            dispose(disposable);
        }
    }

    public static void dispose(List<Disposable> disposableList) {
        if (disposableList == null || disposableList.size() <= 0) {
            return;
        }
        Disposable[] arr = new Disposable[disposableList.size()];
        dispose(disposableList.toArray(arr));
    }
}
