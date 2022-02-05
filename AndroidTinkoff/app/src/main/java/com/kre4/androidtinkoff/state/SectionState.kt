package com.kre4.androidtinkoff.state

import android.content.res.Resources
import com.kre4.androidtinkoff.callback.UpdateUICallback
import com.kre4.androidtinkoff.exception.GifLoadingException
import com.kre4.androidtinkoff.retrofit.AnswerResult
import javax.security.auth.callback.Callback

abstract class SectionState() {

    protected var currentGifIndex = -1
    // Glide provides cache automatically, that's why I save only url+description
    protected val cachedGifsUrls: MutableList<AnswerResult> = arrayListOf()


    fun nextGif() : AnswerResult{
        if (currentGifIndex != cachedGifsUrls.size-1)
            return cachedGifsUrls[++currentGifIndex]
        throw GifLoadingException("No available gifs")
    }

    fun needToLoadNewGif(): Boolean{
        return currentGifIndex == cachedGifsUrls.size-1
    }

    abstract fun load(resources: Resources,callback: UpdateUICallback<AnswerResult>)

    fun previousGif() : AnswerResult{
        if (currentGifIndex <= 0)
            throw GifLoadingException("No more cached gifs")
        return cachedGifsUrls[--currentGifIndex]
    }

    fun getCurrentGif():AnswerResult{
        return cachedGifsUrls[currentGifIndex]
    }

    fun getGifsCount(): Int = cachedGifsUrls.size
    fun getCurrentGifPos(): Int = currentGifIndex

}