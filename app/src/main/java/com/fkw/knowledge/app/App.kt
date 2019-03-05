package com.fkw.knowledge.app

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.view.Gravity

import com.blankj.utilcode.util.CrashUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import com.socks.library.KLog

/**
 * Author:          Kevin <BR></BR>
 * CreatedTime:     2019/2/14 14:22 <BR></BR>
 * Desc:            TODO <BR></BR>
 *
 *
 * ModifyTime:      <BR></BR>
 * ModifyItems:     <BR></BR>
 *
 * @author: Kevin <BR></BR>
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initApp()

    }

    private fun initApp() {
        initToast()
        checkPermissionAndInit()
    }

    private fun initToast() {
        ToastUtils.setGravity(Gravity.CENTER, 0, 0)
    }

    fun checkPermissionAndInit() {
        initCrashHandler()
    }

    @SuppressLint("MissingPermission")
    private fun initCrashHandler() {
        if (PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            CrashUtils.init(Constants.CRASH_DIR_PATH) { crashInfo, e -> KLog.i(crashInfo) }
        }
    }
}
