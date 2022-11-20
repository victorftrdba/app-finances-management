package com.example.atpdevmobilejava.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.atpdevmobilejava.R;
import com.example.atpdevmobilejava.model.DataModel;
import com.example.atpdevmobilejava.model.Finance;

import java.util.Objects;

public class OptionsActivity extends AppCompatActivity {
    boolean switchOptionView;
    Switch switchValue;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        switchValue = findViewById(R.id.switchOptionView);
        Bundle extra = getIntent().getExtras();
        index = extra.getInt("index");
        Finance f = DataModel.getInstance().getFinance(index);
        if (Objects.equals(f.getStatus(), "Paga")) {
            switchValue.setChecked(true);
        }
    }

    @Override
    public void onBackPressed() {
        Finance f = DataModel.getInstance().getFinance(index);
        switchOptionView = ((Switch) findViewById(R.id.switchOptionView)).isChecked();
        f.setStatus(switchOptionView ? "Paga" : "Em aberto");
        DataModel.getInstance().updateStatus(f, index);
        finish();
    }
}