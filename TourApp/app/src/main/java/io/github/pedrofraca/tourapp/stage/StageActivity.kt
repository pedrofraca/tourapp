package io.github.pedrofraca.tourapp.stage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import io.github.pedrofraca.tourapp.R

class StageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        if (supportFragmentManager.findFragmentByTag(StagesFragment.TAG) == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, StagesFragment(), StagesFragment.TAG)
                    .commit()
        }
    }
}