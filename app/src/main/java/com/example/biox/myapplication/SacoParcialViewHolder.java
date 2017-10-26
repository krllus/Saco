package com.example.biox.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


class SacoParcialViewHolder extends RecyclerView.ViewHolder {

    ImageView imgFill;
    TextView txtNome;

    SacoParcialViewHolder(View itemView) {
        super(itemView);
        imgFill = itemView.findViewById(R.id.saco_parcial_imagem_fill);
        txtNome = itemView.findViewById(R.id.saco_parcial_nome);
    }
}
