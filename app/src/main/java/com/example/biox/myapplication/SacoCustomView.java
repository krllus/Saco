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

public class SacoCustomView extends android.support.v7.widget.AppCompatImageView {
    private static final String LOG_TAG = SacoCustomView.class.getSimpleName();

    private Bitmap maskBitmap;
    private Bitmap resultBitmap;

    private Paint paintWhite;
    private Paint paintRectangle;
    private Paint paintMask;

    private Rect rect;

    private Double filledPercentage;

    private static final int CANVAS_SIZE = 512;

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
        resultBitmap = getResizedBitmap(maskBitmap, CANVAS_SIZE, CANVAS_SIZE);
        //resultBitmap = Bitmap.createBitmap(maskBitmap.getWidth(), maskBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        setUpWhitePaint();
        setUpRectanglePaint(context);
        setUpMaskPaint();

        setUpRectangleDimensions();

        array.recycle();
    }

    public void setFilledPercentage(double filledPercentage) {
        this.filledPercentage = filledPercentage;
        setUpRectangleDimensions();
        invalidate();
    }

    private void setUpWhitePaint() {
        paintWhite = new Paint();
        paintWhite.setColor(Color.WHITE);
        paintWhite.setStyle(Paint.Style.FILL);
    }

    private void setUpRectanglePaint(Context context) {
        paintRectangle = new Paint();
        paintRectangle.setColor(context.getResources().getColor(R.color.colorAccent));
        paintRectangle.setStyle(Paint.Style.FILL);
    }

    private void setUpMaskPaint() {
        paintMask = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintMask.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    private void setUpRectangleDimensions() {
        rect = new Rect(CANVAS_SIZE, CANVAS_SIZE, 0, (int) (CANVAS_SIZE * filledPercentage));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // fill canvas with white paint
        canvas.drawPaint(paintWhite);

        // create rectangle
        canvas.drawRect(rect, paintRectangle);

        //apply mask
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
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();

        return resizedBitmap;
    }
}
