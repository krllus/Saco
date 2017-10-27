package com.example.biox.myapplication;

import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView btnLaunch;
    SacoCustomView sacoCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLaunch = findViewById(R.id.txt_launch);
        btnLaunch.setOnClickListener(v -> launchDialod());

        sacoCustomView = findViewById(R.id.sacoView);
        sacoCustomView.setFilledPercentage(0.25);
    }


    public void launchDialod() {
        FragmentManager fragmentManager = getFragmentManager();
        CustomDialogFragment newFragment = new CustomDialogFragment();

        newFragment.show(fragmentManager, "tag");

    }
}
