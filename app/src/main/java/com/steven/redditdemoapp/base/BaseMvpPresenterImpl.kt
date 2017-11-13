package client.yalantis.com.githubclient.mvp

import com.steven.redditdemoapp.base.BaseMvpView

/**
 * Created by Steven Reyes on 09/11/2017
 */
open class BaseMvpPresenterImpl<V : BaseMvpView> : BaseMvpPresenter<V> {

    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }
}