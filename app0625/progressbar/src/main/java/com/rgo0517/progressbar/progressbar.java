package com.rgo0517.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class progressbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);

        final ProgressBar lect = (ProgressBar)findViewById(R.id.progressbar);
        final ProgressBar indicator = (ProgressBar)findViewById(R.id.indicator);
        Button start = (Button)findViewById(R.id.start);
        Button end = (Button)findViewById(R.id.end);

        start.setOnClickListener((view) -> {
            lect.setProgress(75);
            lect.setSecondaryProgress(99);
            indicator.setVisibility(View.VISIBLE);
        });

        end.setOnClickListener((view) -> {
            indicator.setVisibility(View.GONE);
        });

    }
}
