package com.fkw.knowledge.activity

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import com.fkw.knowledge.R
import com.fkw.knowledge.ui.greendao.GreenDaoActivity
import com.fkw.knowledge.ui.wan_android.ArticleActivity
import com.socks.library.KLog


class MainActivity : AppCompatActivity(), View.OnClickListener {


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

        val btnTransparentActivity = findViewById<Button>(R.id.btn_transparent_activity)
        btnTransparentActivity.setOnClickListener(this)
        val btnRequestFloatPermission = findViewById<Button>(R.id.btn_request_float_permission)
        btnRequestFloatPermission.setOnClickListener(this)
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
            R.id.btn_request_float_permission -> requestFloatPermission()
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

        if (!PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE)) {
            AlertDialog.Builder(this)
                    .setTitle("权限申请")
                    .setMessage("我们必须有存储权限和您的手机设备码才能进行下一步操作")
                    .setPositiveButton("同意") { dialog, which -> requestNecessaryPermission() }
                    .setCancelable(false)
                    .show()
        }


    }

    /**
     * 具体的请求权限的逻辑
     */
    private fun requestNecessaryPermission() {
        PermissionUtils.permission(PermissionConstants.STORAGE, PermissionConstants.PHONE)
                .rationale { shouldRequest -> LogUtils.i("被拒绝后重新申请") }
                .callback(object : PermissionUtils.FullCallback {
                    override fun onGranted(permissionsGranted: List<String>) {
                        KLog.i(permissionsGranted)

                    }

                    override fun onDenied(permissionsDeniedForever: List<String>, permissionsDenied: List<String>) {
                        KLog.i(permissionsDeniedForever)
                        KLog.i(permissionsDenied)
                    }
                })
                .request()
    }


    /**
     * 请求悬浮窗的权限
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun requestFloatPermission() {
        if (!PermissionUtils.isGrantedDrawOverlays()) {
            PermissionUtils.requestDrawOverlays(object : PermissionUtils.SimpleCallback {
                override fun onGranted() {
                    ToastUtils.showShort("权限申请成功")
                }

                override fun onDenied() {
                    ToastUtils.showShort("权限申请失败")

                    PermissionUtils.launchAppDetailsSettings()
                }
            })
        }
    }

}
