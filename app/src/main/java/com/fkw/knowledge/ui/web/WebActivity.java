package com.fkw.knowledge.ui.web;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fkw.knowledge.R;
import com.fkw.knowledge.base.BaseActivity;

/**
 * Author:          Kevin </BR>
 * CreatedTime:     2019/3/6 17:46 </BR>
 * Desc:            WebView的Activity </BR>
 * <p>
 * ModifyTime:       </BR>
 * ModifyItems:      </BR>
 *
 * @author: Kevin
 */
public class WebActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
    }
}
