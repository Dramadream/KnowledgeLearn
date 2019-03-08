package com.fkw.knowledge.base

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.view.Gravity
import com.blankj.utilcode.util.CrashUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import com.fkw.knowledge.db.DaoManager
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.*
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.socks.library.KLog

/**
 * Author:          Kevin <BR></BR>
 * CreatedTime:     2019/2/14 14:22 <BR></BR>
 * Desc:            Application <BR></BR>
 *
 *
 * ModifyTime:      <BR></BR>
 * ModifyItems:     <BR></BR>
 *
 * @author: Kevin <BR></BR>
 */
class App : Application() {


    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(object : DefaultRefreshHeaderCreator {
            override fun createRefreshHeader(context: Context, layout: RefreshLayout): RefreshHeader {
                return ClassicsHeader(context)
            }
        })

        SmartRefreshLayout.setDefaultRefreshFooterCreator(object : DefaultRefreshFooterCreator {
            override fun createRefreshFooter(context: Context, layout: RefreshLayout): RefreshFooter {
                return ClassicsFooter(context)
            }
        })
    }


    override fun onCreate() {
        super.onCreate()

        initApp()
        DaoManager.getInstance().init(this)
    }

    private fun initApp() {
        initUtils()
    }

    /**
     * 初始化工具类相关参数
     */
    private fun initUtils() {
        initCrashHandler()
        initToast()

    }

    private fun initToast() {
        ToastUtils.setGravity(Gravity.CENTER, 0, 0)
    }

    @SuppressLint("MissingPermission")
    private fun initCrashHandler() {
        if (PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            CrashUtils.init(Constants.CRASH_DIR_PATH) { crashInfo, e -> KLog.i(crashInfo) }
        }
    }
}
