package com.zyapp.sm.teacher;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zyapp.sm.R;
import com.zyapp.sm.teacher.base.BaseFragment;
import com.zyapp.sm.teacher.fragment.AchievementFragment;
import com.zyapp.sm.teacher.fragment.MyFragment;
import com.zyapp.sm.teacher.fragment.StudentFragment;
import com.zyapp.sm.teacher.fragment.WorkFragment;

public class TeacherActivity extends AppCompatActivity {

    private static final String TAG = "TeacherActivity";
    private StudentFragment mStudentFragment;
    private AchievementFragment mAchievementFragment;
    private WorkFragment mWorkFragment;
    private MyFragment mMyFragment;
    private FragmentManager mFm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        //去掉标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //设置系统状态栏的颜色
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorTeacher));
        //初始化Fragment
        initfragment();
        //底部导航栏的点击事件
        initBottomListener();
        //接收来自登陆界面的工号
        String work_num = getIntent().getStringExtra("work_num");
        Log.d(TAG, "接收到工号==> " + work_num);
        //
        Bundle bundle = new Bundle();
        bundle.putString("work_num", work_num);
        mMyFragment.setArguments(bundle);
        mStudentFragment.setArguments(bundle);
    }

    private void initBottomListener() {
        //初始化
        BottomNavigationView bottomNavigationView = findViewById(R.id.bnv_TA_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //
                if (menuItem.getItemId() == R.id.student_menu) {
                    Log.d(TAG, "切换到管理");
                    //
                    switchFragment(mStudentFragment);
                }
                if (menuItem.getItemId() == R.id.achievement_menu) {
                    Log.d(TAG, "切换到成绩");
                    switchFragment(mAchievementFragment);
                }
                if (menuItem.getItemId() == R.id.work_menu) {
                    Log.d(TAG, "切换到作业");
                    switchFragment(mWorkFragment);
                }
                if (menuItem.getItemId() == R.id.my_menu) {
                    Log.d(TAG, "切换到我");
                    switchFragment(mMyFragment);
                }

                return true;
            }
        });
    }

    /**
     *
     */
    private void switchFragment(BaseFragment targeFragment) {
        //开始事务
        FragmentTransaction transaction = mFm.beginTransaction();

        //切换
        transaction.replace(R.id.fl_M_content, targeFragment);

        //提交事务
        transaction.commit();
    }

    /**
     * 初始化碎片
     */
    private void initfragment() {
        mStudentFragment = new StudentFragment();
        mAchievementFragment = new AchievementFragment();
        mWorkFragment = new WorkFragment();
        mMyFragment = new MyFragment();
        //拿到一个支持包
        mFm = getSupportFragmentManager();
        //设置一个默认
        switchFragment(mStudentFragment);
    }
}
