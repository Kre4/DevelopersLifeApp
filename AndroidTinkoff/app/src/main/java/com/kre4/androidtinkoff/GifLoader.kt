package com.kre4.androidtinkoff

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.kre4.androidtinkoff.callback.UpdateUICallback
import com.kre4.androidtinkoff.exception.GifLoadingException
import com.kre4.androidtinkoff.retrofit.AnswerResult
import com.kre4.androidtinkoff.state.SectionState

class GifLoader(private val activity: Activity, private var sectionState: SectionState) {

    private val image = activity.findViewById<ImageView>(R.id.gif_player)
    private val description = activity.findViewById<TextView>(R.id.description_text)
    private val progressBar = activity.findViewById<ProgressBar>(R.id.progress_circular)

    init {
        nextGif()
    }
    fun changeState(newState: SectionState) {
        sectionState = newState
        setGif(sectionState.getCurrentGif())
    }

    fun nextGif() {
        startLoadingAnimation()
        if (sectionState.needToLoadNewGif()) {

            sectionState.load(activity.resources ,object : UpdateUICallback<AnswerResult> {
                override fun onResponse(answerResults: AnswerResult) {
                   setGif(answerResults)
                }

                override fun onFailure(answerResults: AnswerResult) {
                    nextGif()
                }

            })
        } else
            setGif(sectionState.nextGif())
    }

    fun previousGif() {
        setGif(sectionState.previousGif())
        if (sectionState.getCurrentGifPos() == 0)
            throw GifLoadingException()
    }

    private fun setGif(answerResult: AnswerResult){
        setGifByUrl(answerResult.getUrl())
        setDescription(answerResult.getDescription())
    }

    private fun setGifByUrl(gifUrl: String) {
        if (sectionState.getGifsCount() <= 1)
            activity.findViewById<CardView>(R.id.description_text_container).visibility =
                View.VISIBLE

        Glide.with(activity)
            .load(gifUrl)
            .apply(
                RequestOptions()
                    .error(R.drawable.ic_baseline_error_outline_24).centerCrop()
            )
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    stopLoadingAnimation()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    stopLoadingAnimation()
                    return false
                }

            })
            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .into(image)

    }

    private fun setDescription(descriptionText: String) {
        description.text = descriptionText
    }

    private fun startLoadingAnimation() {
        progressBar.visibility = View.VISIBLE
    }

    private fun stopLoadingAnimation() {
        progressBar.visibility = View.GONE
    }
}