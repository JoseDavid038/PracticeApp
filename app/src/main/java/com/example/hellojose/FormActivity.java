package com.example.hellojose;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormActivity extends AppCompatActivity {

    RadioGroup rdgHousing;
    RadioButton radioButtonSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rdgHousing = findViewById(R.id.rdgHousing);


        rdgHousing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButtonSelected = findViewById(checkedId);
                Toast.makeText(FormActivity.this, "Kind of Housing: " + radioButtonSelected.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void sendIn(View view){

        int idRadioButton = rdgHousing.getCheckedRadioButtonId();
        radioButtonSelected = findViewById(idRadioButton);
        Toast.makeText(this, "Housing: "+radioButtonSelected.getText(), Toast.LENGTH_SHORT).show();

    }
}