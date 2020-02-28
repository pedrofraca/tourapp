package io.github.pedrofraca.tourapp.stage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import io.github.pedrofraca.tourapp.R;
import io.github.pedrofraca.tourapp.stage.StagesFragment;


public class StageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportFragmentManager().findFragmentByTag(StagesFragment.TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new StagesFragment(), StagesFragment.TAG)
                    .commit();
        }

    }
}
