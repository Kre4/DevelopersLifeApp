package com.kre4.androidtinkoff

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kre4.androidtinkoff.buttons.LeafButtons
import com.kre4.androidtinkoff.state.RandomSectionState
import com.kre4.androidtinkoff.state.SectionState
import android.app.Application



class MainActivity : AppCompatActivity() {
    private lateinit var states: Array<SectionState>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        states = arrayOf(RandomSectionState())
        resources
        val loader = GifLoader(this, states[0])
        LeafButtons(findViewById(R.id.previous), findViewById(R.id.next), loader)
    }
}