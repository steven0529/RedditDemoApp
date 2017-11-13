package client.yalantis.com.githubclient.mvp

import com.steven.redditdemoapp.base.BaseMvpView

/**
 * Created by Steven Reyes on 09/11/2017
 */
interface BaseMvpPresenter<in V : BaseMvpView> {

    fun attachView(view: V)

    fun detachView()
}