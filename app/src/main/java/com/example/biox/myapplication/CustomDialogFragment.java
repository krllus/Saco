package com.example.biox.myapplication;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CustomDialogFragment extends DialogFragment {

    private ArrayList<SacoParcial> elements;

    private SacoParcialDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (SacoParcialDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }

    }

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

        RecyclerView recyclerView = rootView.findViewById(R.id.dialog_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setHasFixedSize(true);

        elements.addAll(getListOfAllSacosParciais());

        SacoParcialDialogAdapter adapter = new SacoParcialDialogAdapter(elements, listener);
        recyclerView.setAdapter(adapter);

        builder
                .setTitle(R.string.dialog_title)
                .setView(rootView)
                .setPositiveButton(R.string.clean, (dialog, id) -> listener.onDialogCleanSelected())
                .setNegativeButton(R.string.cancel, (dialog, id) -> CustomDialogFragment.this.getDialog().cancel());

        return builder.create();
    }

    public static List<SacoParcial> getListOfAllSacosParciais() {
        List<SacoParcial> sacos = new ArrayList<>();
        sacos.addAll(Arrays.asList(SacoParcial.values()));
        sacos.remove(SacoParcial.SACO_VAZIO);
        return sacos;
    }
}
