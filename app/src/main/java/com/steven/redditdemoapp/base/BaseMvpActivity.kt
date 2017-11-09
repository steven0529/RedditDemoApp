package com.steven.redditdemoapp.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import client.yalantis.com.githubclient.mvp.BaseMvpPresenter

/**
 * Created by Steven Reyes on 09/11/2017
 */
abstract class BaseMvpActivity<in V : BaseMvpView, T : BaseMvpPresenter<V>> : AppCompatActivity(), BaseMvpView {

    protected abstract var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}