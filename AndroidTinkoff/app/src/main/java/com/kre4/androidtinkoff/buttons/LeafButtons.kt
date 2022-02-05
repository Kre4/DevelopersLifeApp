package com.kre4.androidtinkoff.buttons

import android.view.View
import com.kre4.androidtinkoff.GifLoader
import com.kre4.androidtinkoff.exception.GifLoadingException

class LeafButtons(private val prevButton: View,
                  private val nextButton: View,
                  private val gifLoader: GifLoader){

    init {
        LeafButtonsAnimation().apply {
            addButton(prevButton)
            addButton(nextButton)
        }
        nextButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                gifLoader.nextGif()
                if (prevButton.visibility == View.GONE)
                    prevButton.visibility = View.VISIBLE
            }
        }
        )
        prevButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                try {
                    gifLoader.previousGif()
                } catch (e: GifLoadingException){
                    prevButton.visibility = View.GONE
                }
            }

        })
    }

}