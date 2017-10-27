package com.example.biox.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

public class SacoCustomView extends android.support.v7.widget.AppCompatImageView {
    private static final String LOG_TAG = SacoCustomView.class.getSimpleName();

    private Bitmap maskBitmap;
    private Bitmap resultBitmap;

    private Paint paintWhite;
    private Paint paintRectangle;
    private Paint paintMask;

    private Rect rect;

    private Double filledPercentage;

    public SacoCustomView(@NonNull Context context) {
        this(context, null);
    }

    public SacoCustomView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SacoCustomView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SacoCustomView, 0, defStyle);

        // get maskResId from atributes
        int maskResId = array.getResourceId(R.styleable.SacoCustomView_mask, 0);
        if (maskResId == 0) {
            throw new IllegalArgumentException(array.getPositionDescription() +
                    ": The content attribute is required and must refer to a valid image.");
        }

        filledPercentage = 0.0;

        maskBitmap = BitmapFactory.decodeResource(getResources(), maskResId);
        //resultBitmap = Bitmap.createBitmap(maskBitmap.getWidth(), maskBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        setUpWhitePaint();
        setUpRectanglePaint();
        setUpMaskPaint();

        array.recycle();
    }

    public void setFilledPercentage(double filledPercentage) {
        //TODO insure that filled percentage is between 0~1
        this.filledPercentage = 1 - filledPercentage;
        invalidate();
    }

    private void setUpWhitePaint() {
        paintWhite = new Paint();
        paintWhite.setColor(Color.WHITE);
        paintWhite.setStyle(Paint.Style.FILL);
    }

    private void setUpRectanglePaint() {
        paintRectangle = new Paint();
        paintRectangle.setColor(Color.MAGENTA);
        paintRectangle.setStyle(Paint.Style.FILL);
    }

    private void setUpMaskPaint() {
        paintMask = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintMask.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    private void setUpRectangleDimensions(int width, int height) {
        int padding = (int) (height * filledPercentage);
        Log.d(LOG_TAG, "filled layout: " + padding);
        rect = new Rect(0, padding, width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // fill canvas with white paint
        canvas.drawPaint(paintWhite);

        // create rectangle
        setUpRectangleDimensions(canvas.getWidth(), canvas.getHeight());
        canvas.drawRect(rect, paintRectangle);

        //apply mask
        resultBitmap = getResizedBitmap(maskBitmap, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(resultBitmap, 0, 0, paintMask);
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        //bm.recycle();
        return Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
    }
}
