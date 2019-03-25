package com.fkw.knowledge.ui.greendao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;
import com.fkw.knowledge.R;
import com.fkw.knowledge.base.BaseActivity;
import com.fkw.knowledge.db.DaoManager;
import com.fkw.knowledge.db.bean.TestBean;
import com.fkw.knowledge.db.gen.TestBeanDao;

import java.util.List;

/**
 * Author:          Kevin </BR>
 * CreatedTime:     2019/3/15 9:28 </BR>
 * Desc:            TODO </BR>
 * <p>
 * ModifyTime:       </BR>
 * ModifyItems:      </BR>
 *
 * @author: Kevin
 */
public class GreenDaoActivity extends BaseActivity implements View.OnClickListener {

    private Button btnSelectAll;
    private Button btnAdd;
    private Button btnUpdate;
    private TestBeanDao mTestDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        initView();
        initData();
    }


    private void initView() {
        btnSelectAll = (Button) findViewById(R.id.btn_select_all);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnUpdate = (Button) findViewById(R.id.btn_update);

        btnSelectAll.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    private void initData() {
        mTestDao = DaoManager.getInstance().getDaoSession().getTestBeanDao();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_select_all:
                queryAll();

                break;
            case R.id.btn_add:
                addOne();
                break;
            case R.id.btn_update:
                updateOne();
                break;
        }
    }


    private void queryAll() {
        List<TestBean> testBeans = mTestDao.loadAll();
        LogUtils.i(testBeans);
    }

    private void addOne() {
        TestBean bean = new TestBean();
        bean.setId(4L);
        bean.setName("小明");
        bean.setAge(13);
        bean.setSex(1);
        mTestDao.insertOrReplace(bean);
    }

    private void updateOne() {
        TestBean bean = new TestBean();
        bean.setId(4L);
        bean.setName("小明");
        bean.setAge(15);
        //mTestDao.update(bean);
        mTestDao.save(bean);

    }
}
