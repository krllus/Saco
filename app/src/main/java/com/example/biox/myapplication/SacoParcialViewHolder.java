package com.example.biox.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


class SacoParcialViewHolder extends RecyclerView.ViewHolder {

    SacoCustomView imgFill;
    TextView txtNome;

    SacoParcialViewHolder(View itemView) {
        super(itemView);
        imgFill = itemView.findViewById(R.id.saco_imagem);
        txtNome = itemView.findViewById(R.id.saco_parcial_nome);
    }
}
