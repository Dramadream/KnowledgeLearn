package com.fkw.knowledge.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.fkw.knowledge.R;
import com.fkw.knowledge.app.App;
import com.socks.library.KLog;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        initPermissions();
        initView();
        initData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initView() {

        Button btnTransparentActivity = findViewById(R.id.btn_transparent_activity);
        btnTransparentActivity.setOnClickListener(this);
        Button btnRequestFloatPermission = findViewById(R.id.btn_request_float_permission);
        btnRequestFloatPermission.setOnClickListener(this);
    }

    private void initData() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_transparent_activity:
                startActivity(new Intent(this, TransparentActivity.class));
                break;
            case R.id.btn_request_float_permission:
                requestFloatPermission();
                break;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        KLog.i();
    }


    @Override
    protected void onResume() {
        super.onResume();
        KLog.i();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        KLog.i();
    }

    @Override
    protected void onPause() {
        super.onPause();
        KLog.i();
    }

    @Override
    protected void onStop() {
        super.onStop();
        KLog.i();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KLog.i();
    }

    /**
     * App第一次启动时请求必须的权限
     * <p>
     * 1.检查是否拥有权限；
     * 2.如果没有弹窗给提示，然后申请权限
     * 3.如果拒绝，就给回调
     */
    private void initPermissions() {

        if (!PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE)) {
            new AlertDialog.Builder(this)
                    .setTitle("权限申请")
                    .setMessage("我们必须有存储权限和您的手机设备码才能进行下一步操作")
                    .setPositiveButton("同意", (dialog, which) -> requestNecessaryPermission())
                    .setCancelable(false)
                    .show();
        }


    }

    /**
     * 具体的请求权限的逻辑
     */
    private void requestNecessaryPermission() {
        PermissionUtils.permission(PermissionConstants.STORAGE, PermissionConstants.PHONE)
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        KLog.i(permissionsGranted);

                        ((App) getApplication()).checkPermissionAndInit();
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        KLog.i(permissionsDeniedForever);
                        KLog.i(permissionsDenied);
                    }
                })
                .request();
    }


    /**
     * 请求悬浮窗的权限
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestFloatPermission() {
        if (!PermissionUtils.isGrantedDrawOverlays()) {
            PermissionUtils.requestDrawOverlays(new PermissionUtils.SimpleCallback() {
                @Override
                public void onGranted() {
                    ToastUtils.showShort("权限申请成功");
                }

                @Override
                public void onDenied() {
                    ToastUtils.showShort("权限申请失败");

                    PermissionUtils.launchAppDetailsSettings();
                }
            });
        }
    }

}
