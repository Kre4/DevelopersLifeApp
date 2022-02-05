package com.kre4.androidtinkoff.callback



interface UpdateUICallback<T> {
    fun onResponse(answerResults: T)
    fun onFailure(answerResults: T)
}