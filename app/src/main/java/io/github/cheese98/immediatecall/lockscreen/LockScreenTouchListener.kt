package io.github.cheese98.immediatecall.lockscreen

import android.annotation.SuppressLint
import android.content.Context
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

open class LockScreenTouchListener(val context: Context, val lockScreenView: ConstraintLayout) : View.OnTouchListener {


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        return true
    }

}