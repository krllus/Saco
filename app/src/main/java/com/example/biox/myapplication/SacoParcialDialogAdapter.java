package com.example.biox.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by João Carlos on 10/23/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */
public class SacoParcialDialogAdapter extends RecyclerView.Adapter<SacoParcialViewHolder> {
    private ArrayList<SacoParcial> elements;
    private SacoParcialDialogListener sacoListner;


    SacoParcialDialogAdapter(ArrayList<SacoParcial> elements, SacoParcialDialogListener listener) {
        this.elements = elements;
        this.sacoListner = listener;
    }

    @Override
    public SacoParcialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.row_dialog_saco_partial, parent, false);

        return new SacoParcialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SacoParcialViewHolder holder, int position) {
        SacoParcial element = elements.get(holder.getAdapterPosition());

        fillContent(holder, element);
        setUpClickListener(holder, element);
    }

    private void setUpClickListener(final SacoParcialViewHolder holder, SacoParcial element) {
        holder.itemView.setOnClickListener(view -> {
            sacoListner.onDialogItemSelected(element);
        });
    }

    private void fillContent(final SacoParcialViewHolder holder, SacoParcial element) {
        holder.txtNome.setText(element.getNome());
        holder.imgFill.setFilledPercentage(element.getMultiplier());
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public ArrayList<SacoParcial> getElements() {
        return this.elements;
    }


}
