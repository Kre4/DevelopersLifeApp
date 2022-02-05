package com.kre4.androidtinkoff.state

import android.content.res.Resources
import com.kre4.androidtinkoff.R
import com.kre4.androidtinkoff.callback.UpdateUICallback
import com.kre4.androidtinkoff.retrofit.AnswerResult
import com.kre4.androidtinkoff.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RandomSectionState : SectionState() {

    override fun load(resources: Resources,callback: UpdateUICallback<AnswerResult>) {

        val call = RetrofitClient.instance.service.getGifs()

        call.enqueue(object : Callback<AnswerResult> {
            override fun onResponse(
                call: Call<AnswerResult>,
                response: Response<AnswerResult>
            ) {
                val successResult = response.body() ?: return
                try {
                    val answerResult = AnswerResult(
                        successResult.getUrl().replace("http:", "https:"),
                        successResult.getDescription()
                    )
                    // There is no check for cachedGifsUrls.contains(answerResult)
                    // to make previous button work fine
                    cachedGifsUrls.add(answerResult)
                    ++currentGifIndex
                    callback.onResponse(answerResult)

                } catch (t: Throwable) {

                    callback.onFailure(
                        AnswerResult(
                            resources.getString(R.string.empty_url),
                            resources.getString(R.string.unexpected_error_message)
                        )
                    )
                }
            }

            override fun onFailure(call: Call<AnswerResult>, t: Throwable) {
                callback.onFailure(
                    AnswerResult(
                        resources.getString(R.string.empty_url),
                        resources.getString(R.string.connection_error_message)
                    )
                )
            }

        })

    }

}