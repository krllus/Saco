package com.example.biox.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import jp.wasabeef.glide.transformations.MaskTransformation;

/**
 * Created by João Carlos on 10/23/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */
public class SacoParcialDialogAdapter extends RecyclerView.Adapter<SacoParcialViewHolder> {
    private Context context;
    private ArrayList<SacoParcial> elements;

    public SacoParcialDialogAdapter(Context context, ArrayList<SacoParcial> elements) {
        this.context = context;
        this.elements = elements;
    }

    @Override
    public SacoParcialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.row_saco_parcial, parent, false);

        return new SacoParcialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SacoParcialViewHolder holder, int position) {
        SacoParcial element = elements.get(holder.getAdapterPosition());

        fillContent(holder, element);
        setUpClickListener(holder, element);
    }

    private void setUpClickListener(final SacoParcialViewHolder holder, SacoParcial element) {
        //TODO create click event here
    }

    private void fillContent(final SacoParcialViewHolder holder, SacoParcial element) {
        holder.txtNome.setText(element.getNome());

        setColorToImage(holder.imgFill, element.getMultiplier());
    }

    private void setColorToImage(ImageView image, Double percentage) {
        int CANVAS_SIZE = 512;
//        percentage = 1 - percentage;

        Bitmap bitmap = Bitmap.createBitmap(CANVAS_SIZE, CANVAS_SIZE, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();

        //fill the canvas with white
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);

        // draw a rectangle over the canvas
        Rect rect = new Rect(CANVAS_SIZE, CANVAS_SIZE, 0, (int) (CANVAS_SIZE * percentage));
        paint.setColor(context.getResources().getColor(R.color.colorAccent));
        canvas.drawRect(rect, paint);

        //apply bitmap to canvas
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

        Log.d("tag", "percentage: " + (int) (CANVAS_SIZE * percentage));

        Glide.with(context)
                .load(stream.toByteArray())
                .apply(RequestOptions.bitmapTransform(new MaskTransformation(R.drawable.bag_mask)))
                .into(image);
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public ArrayList<SacoParcial> getElements() {
        return this.elements;
    }
}
