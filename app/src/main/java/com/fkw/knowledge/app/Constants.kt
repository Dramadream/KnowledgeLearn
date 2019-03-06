package com.fkw.knowledge.app

import android.os.Environment

import com.blankj.utilcode.util.AppUtils

/**
 * ```
 * Author:          Kevin
 * CreatedTime:     2019/2/14 14:23
 * Desc:            一些App全局常量数据
 *
 *
 * ModifyTime:
 * ModifyItems:
 * ```
 * @author: Kevin <BR></BR>
 */
object Constants {

    /** 基础路径  */
    private val BASE_DIR_PATH = Environment.getExternalStorageDirectory().path + "/" + AppUtils.getAppPackageName() + "/"

    /** 崩溃日志存放位置  */
    val CRASH_DIR_PATH = BASE_DIR_PATH + "crash/"

}
