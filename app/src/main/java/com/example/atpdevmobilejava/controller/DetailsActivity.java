package com.example.atpdevmobilejava.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.atpdevmobilejava.R;
import com.example.atpdevmobilejava.model.DataModel;
import com.example.atpdevmobilejava.model.Finance;

public class DetailsActivity extends AppCompatActivity {
    EditText nameTextView;
    EditText valueTextView;
    Button button;
    Button button2;
    int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        nameTextView = findViewById(R.id.nameTextView);
        valueTextView = findViewById(R.id.valueTextView);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        Bundle extra = getIntent().getExtras();
        index = extra.getInt("index");
        if (index != -1) {
            Finance f = DataModel.getInstance().getFinance(index);
            nameTextView.setText(f.getName());
            valueTextView.setText(f.getValue());
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToOptionActivity(index);
                }
            });
        }
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    builder.setPositiveButton("Voltar mesmo assim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    builder.setNegativeButton("Corrigir", null);
                    builder.create().show();
                }
            }
        });
    }

    void goToOptionActivity (int index) {
        Intent intent = new Intent(DetailsActivity.this, OptionsActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}