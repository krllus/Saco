package com.example.biox.myapplication;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SacoParcialDialogListener {

    TextView txtLaunch;
    SacoCustomView sacoCustomView;

    SacoParcial selectedSaco;
    CustomDialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedSaco = SacoParcial.SACO_VAZIO;

        txtLaunch = findViewById(R.id.txt_launch);
        txtLaunch.setOnClickListener(v -> launchDialod());

        sacoCustomView = findViewById(R.id.sacoView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCurrentSelectedSacoName();
    }

    private void setCurrentSelectedSacoName() {
        txtLaunch.setText(selectedSaco.getNome());
    }

    public void launchDialod() {
        FragmentManager fragmentManager = getFragmentManager();
        dialogFragment = new CustomDialogFragment();

        dialogFragment.show(fragmentManager, "tag");

    }

    @Override
    public void onDialogItemSelected(SacoParcial sacoParcial) {
        this.selectedSaco = sacoParcial;
        setCurrentSelectedSacoName();
        dialogFragment.dismiss();
    }

    @Override
    public void onDialogCleanSelected() {
        this.selectedSaco = SacoParcial.SACO_VAZIO;
        setCurrentSelectedSacoName();
    }
}
