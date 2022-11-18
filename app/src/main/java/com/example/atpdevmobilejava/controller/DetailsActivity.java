package com.example.atpdevmobilejava.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

import com.example.atpdevmobilejava.R;
import com.example.atpdevmobilejava.model.DataModel;
import com.example.atpdevmobilejava.model.Finance;

public class DetailsActivity extends AppCompatActivity {
    EditText nameTextView;
    EditText valueTextView;
    int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        nameTextView = findViewById(R.id.nameTextView);
        valueTextView = findViewById(R.id.valueTextView);

        Bundle extra = getIntent().getExtras();
        index = extra.getInt("index");
        if (index != -1) {
            Finance f = DataModel.getInstance().finances.get(index);
            nameTextView.setText(f.getName());
            valueTextView.setText(f.getValue());
        }
    }

    @Override
    public void onBackPressed() {
        String name = nameTextView.getText().toString();
        String value = valueTextView.getText().toString();

        if (name.length() > 1 && value.length() > 1) {
            if (index == -1) {
                DataModel.getInstance().addFinance(
                        new Finance(name, value, "Em aberto")
                );
            } else {
                Finance f = DataModel.getInstance().getFinance(index);
                f.setName(name);
                f.setValue(value);
                DataModel.getInstance().updateFinance(f, index);
            }
            finish();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
            builder.setTitle(R.string.attention);
            builder.setMessage(R.string.attention_description);
            builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setNegativeButton(android.R.string.no, null);
            builder.create().show();
        }
    }
}