package com.test.showcase.masterdetailkotlintechnology

import android.app.Activity
import android.os.Handler
import androidx.annotation.IdRes
import androidx.test.espresso.*
import android.view.View

import java.lang.ref.WeakReference

/**
 * [IdlingResource] which monitors a [View] for a given visibility state. The resource is considered idle when the
 * View has the desired state.
 *
 * @author vaughandroid@gmail.com
 */
class ViewVisibilityIdlingResource
/**
 * @param view the View to monitor
 * @param visibility One of [View.VISIBLE], [View.INVISIBLE], or [View.GONE].
 */
(view: View, private val mVisibility: Int) : IdlingResource {

    /** Hold weak reference to the View, so we don't leak memory even if the resource isn't unregistered.  */
    private val mView: WeakReference<View> = WeakReference(view)
    private val mName: String

    private var mResourceCallback: IdlingResource.ResourceCallback? = null

    /**
     * @param activity which owns the View
     * @param viewId ID of the View to monitor
     * @param visibility One of [View.VISIBLE], [View.INVISIBLE], or [View.GONE].
     */
    constructor(activity: Activity, @IdRes viewId: Int, visibility: Int) : this(activity.findViewById<View>(viewId), visibility) {}

    init {
        mName = "View Visibility for view " + view.id + "(@" + System.identityHashCode(mView) + ")"
    }

    override fun getName(): String {
        return mName
    }

    override fun isIdleNow(): Boolean {
        val view = mView.get()
        val isIdle = view == null || view.visibility == mVisibility
        if (isIdle) {
            if (mResourceCallback != null) {
                mResourceCallback!!.onTransitionToIdle()
            }
        } else {
            /* Force a re-check of the idle state in a little while.
             * If isIdleNow() returns false, Espresso only polls it every few seconds which can slow down our tests.
             * Ideally we would watch for the visibility state changing, but AFAIK we can't detect when a View's
             * visibility changes to GONE.
             */
            Handler().postDelayed({ isIdleNow }, IDLE_POLL_DELAY_MILLIS.toLong())
        }

        return isIdle
    }

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        mResourceCallback = resourceCallback
    }

    companion object {

        private const val IDLE_POLL_DELAY_MILLIS = 100
    }
}