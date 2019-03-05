package com.fkw.knowledge.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fkw.knowledge.R;
import com.socks.library.KLog;

/**
 * Author:          Kevin <BR/>
 * CreatedTime:     2019/2/14 15:19 <BR/>
 * Desc:            透明的Activity <BR/>
 * <p/>
 * ModifyTime:      <BR/>
 * ModifyItems:     <BR/>
 *
 * @author: Kevin <BR/>
 */
public class TransparentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent);
        KLog.i();
        //throw new NullPointerException();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        KLog.i();
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
}
