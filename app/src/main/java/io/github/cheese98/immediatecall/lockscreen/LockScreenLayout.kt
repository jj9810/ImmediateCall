package io.github.cheese98.immediatecall.lockscreen

import android.annotation.SuppressLint
import android.content.Context
import android.view.MotionEvent
import android.widget.RelativeLayout

class LockScreenLayout : RelativeLayout {
    constructor(context: Context) : super(context) {
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        super.onTouchEvent(motionEvent)
        return true
    }
}