package com.fkw.knowledge.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.fkw.knowledge.R
import com.fkw.knowledge.base.App
import com.fkw.knowledge.ui.greendao.GreenDaoActivity
import com.fkw.knowledge.ui.wan_android.ArticleActivity
import com.socks.library.KLog
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission


class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val REQ_CODE_PERMISSIONS = 0x10;
    }

    /** 被拒绝的权限组 */
    private lateinit var mDeniedPermissions: List<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        initPermissions()
        initView()
        initData()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    private fun initView() {
        findViewById<Button>(R.id.btn_transparent_activity).setOnClickListener(this)
        findViewById<Button>(R.id.btn_request_overlay_permission).setOnClickListener(this)
        findViewById<Button>(R.id.btn_request_notification_permission).setOnClickListener(this)
        findViewById<Button>(R.id.btn_kotlin_test).setOnClickListener(this)
        findViewById<Button>(R.id.btn_wan_android).setOnClickListener(this)
        findViewById<Button>(R.id.btn_green_dao).setOnClickListener(this)
    }

    private fun initData() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_transparent_activity -> startActivity(Intent(this, TransparentActivity::class.java))
            R.id.btn_request_overlay_permission -> requestOverlayPermission()
            R.id.btn_request_notification_permission -> requestNotificationPermission()
            R.id.btn_kotlin_test -> ActivityUtils.startActivity(KotlinTestActivity::class.java)
            R.id.btn_wan_android -> ActivityUtils.startActivity(ArticleActivity::class.java)
            R.id.btn_green_dao -> ActivityUtils.startActivity(GreenDaoActivity::class.java)
        }
    }


    override fun onStart() {
        super.onStart()
        KLog.i()
    }


    override fun onResume() {
        super.onResume()
        KLog.i()
    }

    override fun onRestart() {
        super.onRestart()
        KLog.i()
    }

    override fun onPause() {
        super.onPause()
        KLog.i()
    }

    override fun onStop() {
        super.onStop()
        KLog.i()
    }

    override fun onDestroy() {
        super.onDestroy()
        KLog.i()
    }


    /**
     * App第一次启动时请求必须的权限
     *
     *
     * 1.检查是否拥有权限；
     * 2.如果没有弹窗给提示，然后申请权限
     * 3.如果拒绝，就给回调
     */
    private fun initPermissions() {

        AndPermission.with(this)
                .runtime()
                .permission(Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_PHONE_STATE, Permission.ACCESS_FINE_LOCATION)
                .onGranted { permissionList ->
                    // 如果权限被允许，走下一步
                    mDeniedPermissions = permissionList
                    LogUtils.d("被允许  " + getPermissionText())
                    App.getInstance().initCrashHandler()
                }
                .onDenied { permissionList ->

                    mDeniedPermissions = permissionList
                    LogUtils.d("被拒绝  " + getPermissionText())
                    // 如果权限被拒绝，直接跳到设置界面
                    if (AndPermission.hasAlwaysDeniedPermission(this, permissionList)) {
                        AndPermission.with(this)
                                .runtime()
                                .setting()
                                .start(REQ_CODE_PERMISSIONS)
                    }
                }
                .start()
    }


    /**
     * 请求悬浮窗的权限
     */
    private fun requestOverlayPermission() {

        AndPermission.with(this)
                .overlay()
                .onGranted {
                    ToastUtils.showShort("悬浮窗权限申请成功")
                    LogUtils.d("悬浮窗权限申请成功");
                }
                .onDenied {
                    ToastUtils.showShort("悬浮窗权限申请失败")
                    LogUtils.d("悬浮窗权限申请失败");
                }
                .start()
    }

    /**
     * 请求通知栏的权限
     */
    private fun requestNotificationPermission() {

        AndPermission.with(this)
                .notification()
                .permission()
                .rationale { _, _, requestExecutor ->
                    requestExecutor.execute()
                }
                .onGranted {
                    ToastUtils.showShort("通知权限申请成功")
                    LogUtils.d("通知权限申请成功");
                }
                .onDenied {
                    ToastUtils.showShort("通知权限申请失败")
                    LogUtils.d("通知权限申请失败");
                }
                .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE_PERMISSIONS) {

            if (AndPermission.hasPermissions(this, mDeniedPermissions.toTypedArray())) {
                LogUtils.d("允许了对应的权限" + getPermissionText())
                App.getInstance().initCrashHandler()
            } else {
                LogUtils.d("没有对应的权限：" + getPermissionText())
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun getPermissionText(): String {
        val permissionNames = Permission.transformText(this, mDeniedPermissions)
        return TextUtils.join("\n", permissionNames)
    }
}
