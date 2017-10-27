package com.example.biox.myapplication;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CustomDialogFragment extends DialogFragment {

    private RecyclerView recyclerView;
    private ArrayList<SacoParcial> elements;
    private SacoParcialDialogAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        elements = new ArrayList<>();
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View rootView = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialogbrand_layout, null, false);

        recyclerView = rootView.findViewById(R.id.dialog_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setHasFixedSize(true);

        elements.addAll(getListOfAllSacosParciais());

        adapter = new SacoParcialDialogAdapter(elements);
        recyclerView.setAdapter(adapter);

        builder
                .setTitle(R.string.dialog_title)
                .setView(rootView)
                .setPositiveButton(R.string.clean, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // select option zero
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CustomDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    public static List<SacoParcial> getListOfAllSacosParciais() {
        List<SacoParcial> sacos = new ArrayList<>();
        sacos.addAll(Arrays.asList(SacoParcial.values()));
        sacos.remove(SacoParcial.SACO_VAZIO);
        return sacos;
    }
}
