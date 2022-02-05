package com.kre4.androidtinkoff.buttons

import android.view.MotionEvent
import android.view.View
import android.view.animation.AlphaAnimation
import com.kre4.androidtinkoff.GlobalConstants


class LeafButtonsAnimation {
    private val buttonDown =
        AlphaAnimation(GlobalConstants.unpressedAlphaParameter, GlobalConstants.pressedAlphaParameter)
    private val buttonUp =
        AlphaAnimation(GlobalConstants.pressedAlphaParameter, GlobalConstants.unpressedAlphaParameter)


    fun addButton(btn: View){
        btn.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action){
                    MotionEvent.ACTION_UP ->{
                        v?.performClick()
                        v?.startAnimation(buttonUp)
                        v?.alpha = GlobalConstants.unpressedAlphaParameter
                        return true;
                    }
                    MotionEvent.ACTION_DOWN -> {
                        v?.startAnimation(buttonDown)
                        v?.alpha = GlobalConstants.pressedAlphaParameter
                        return true
                    }
                }
                return false;
            }

        })
    }
}