package com.fkw.knowledge.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.fkw.knowledge.R
import com.socks.library.KLog

/**
 * Author:          Kevin <BR></BR>
 * CreatedTime:     2019/2/14 15:19 <BR></BR>
 * Desc:            透明的Activity <BR></BR>
 *
 *
 * ModifyTime:      <BR></BR>
 * ModifyItems:     <BR></BR>
 *
 * @author: Kevin <BR></BR>
 */
class TransparentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transparent)
        KLog.i()
        //throw new NullPointerException();
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        KLog.i()
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
}
