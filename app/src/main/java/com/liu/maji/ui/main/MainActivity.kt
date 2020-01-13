package com.liu.maji.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cn.com.liu.maji.R
import com.liu.maji.base.MySupportActivity
import com.liu.maji.ui.home.HomeFragment
import com.liu.maji.ui.home.HomePresenter

class MainActivity : MySupportActivity<MainView,MainPresenter>() {

    private var homeFragment :HomeFragment?= null
    override fun createPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun getContentViewResId(): Int {
       return R.layout.activity_main
    }

    override fun init() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (homeFragment == null)
            loadRootFragment(R.id.fl,HomeFragment())
    }

    override fun onDestroy() {
        println("MainActivity销毁")
        super.onDestroy()
    }
}
