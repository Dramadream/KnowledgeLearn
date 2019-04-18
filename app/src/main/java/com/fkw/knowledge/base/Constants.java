package com.fkw.knowledge.base;

import android.os.Environment;

import com.blankj.utilcode.util.AppUtils;

/**
 * Author:          Kevin </BR>
 * CreatedTime:     2019/4/18 16:23 </BR>
 * Desc:            TODO </BR>
 * <p>
 * ModifyTime:       </BR>
 * ModifyItems:      </BR>
 *
 * @author: Kevin
 */
public interface Constants {
    /** 基础路径 */
    String BASE_DIR_PATH = Environment.getExternalStorageDirectory().getPath() + "/" + AppUtils.getAppPackageName() + "/";

    /** 崩溃日志存放位置 */
    String CRASH_DIR_PATH = BASE_DIR_PATH + "crash/";
}
