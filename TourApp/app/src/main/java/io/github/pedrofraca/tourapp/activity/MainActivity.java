package io.github.pedrofraca.tourapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import io.github.pedrofraca.tourapp.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void hideProgressBar(){
        findViewById(R.id.activity_main_progress_bar).setVisibility(View.GONE);
    }

    public void showErrorMessage(String error){
        findViewById(R.id.activity_main_message).setVisibility(View.VISIBLE);
        TextView message = (TextView) findViewById(R.id.activity_main_text_message);
        message.setText(error);
    }
}
