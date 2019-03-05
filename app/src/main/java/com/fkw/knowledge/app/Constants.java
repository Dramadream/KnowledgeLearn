package com.fkw.knowledge.app;

import android.os.Environment;

import com.blankj.utilcode.util.AppUtils;

/**
 * Author:          Kevin <BR/>
 * CreatedTime:     2019/2/14 14:23 <BR/>
 * Desc:            一些App全局常量数据 <BR/>
 * <p/>
 * ModifyTime:      <BR/>
 * ModifyItems:     <BR/>
 *
 * @author: Kevin <BR/>
 */
public class Constants {

    /** 基础路径 */
    private static String BASE_DIR_PATH = Environment.getExternalStorageDirectory().getPath() + "/" + AppUtils.getAppPackageName() + "/";

    /** 崩溃日志存放位置 */
    static String CRASH_DIR_PATH = BASE_DIR_PATH + "crash/";

}
