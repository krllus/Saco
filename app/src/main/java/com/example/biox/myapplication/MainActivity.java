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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLaunch = findViewById(R.id.txt_launch);
        btnLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDialod();
            }
        });

        int size = 200;
        double percentage = 1 - 0.3;

        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();

        //fill the canvas with white
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);

        // draw a rectangle over the canvas
        Rect rect = new Rect(size, size, 0, (int) (size * percentage));
        paint.setColor(getResources().getColor(R.color.colorAccent));
        canvas.drawRect(rect, paint);

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
    }


    public void launchDialod() {
        FragmentManager fragmentManager = getFragmentManager();
        CustomDialogFragment newFragment = new CustomDialogFragment();

        newFragment.show(fragmentManager, "tag");

    }
}
