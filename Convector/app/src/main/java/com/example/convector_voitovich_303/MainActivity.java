package com.example.convector_voitovich_303;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spFrom;
    private Spinner spTo;
    private EditText plFrom;
    private TextView plTo;
    private Button btConvert;
    //adapter
    private ArrayAdapter<Unit> adp;
    private ArrayList<Unit> Lengths = new ArrayList<>();
    private ArrayList<Unit> Squares = new ArrayList<>();
    private ArrayList<Unit> Weights = new ArrayList<>();
    //radiobuttons group
    private RadioGroup radioGroup;






    RadioGroup rdGroup = findViewById(R.id.rdGroup);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spTo = findViewById(R.id.spinnerTo);
        spFrom = findViewById(R.id.spinnerFrom);
        plFrom = findViewById(R.id.plainFrom);
        plTo = findViewById(R.id.plainRes);
        btConvert = findViewById(R.id.buttonConvert);


        RadioGroup radioGroup = findViewById(R.id.rdGroup);

        adp = new ArrayAdapter<Unit>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        Lengths.add(new Unit("mm", 0.001));
        Lengths.add(new Unit("cm", 0.01));
        Lengths.add(new Unit("m", 0.1));
        Lengths.add(new Unit("km", 1.0));

        Squares.add(new Unit("mm2", 0.000001));
        Squares.add(new Unit("cm2", 0.0001));
        Squares.add(new Unit("m2", 1.0));
        Squares.add(new Unit("km2", 1000000.0));

        Weights.add(new Unit("mg", 0.001));
        Weights.add(new Unit("g", 1.0));
        Weights.add(new Unit("kg", 1000.0));
        Weights.add(new Unit("t", 1000000.0));

        adp.addAll(Lengths);

        spFrom.setAdapter(adp);
        spTo.setAdapter(adp);


        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int ch_Id) {
                switch (ch_Id) {
                    case R.id.radioButtonLength:
                        adp.clear();
                        adp.addAll(Lengths);
                        break;
                    case R.id.radioButtonWeight:
                        adp.clear();
                        adp.addAll(Weights);
                        break;
                    case R.id.radioButtonSquares:
                        adp.clear();
                        adp.addAll(Squares);
                        break;
                }
            }

        });


        btConvert.setOnClickListener((View view) -> {
            checkError();
            if (!plFrom.getText().toString().isEmpty()) {
                Double result = Double.parseDouble(plFrom.getText().toString()) *
                        (adp.getItem(spFrom.getSelectedItemPosition()).coeff / adp.getItem(spTo.getSelectedItemPosition()).coeff);
                plTo.setText(result.toString());
            }

        });
    }

    private boolean checkError() {
        if (plFrom.getText().toString().isEmpty()) {
            plFrom.setError("Пустое поле");
            return true;
        }

        double plFromMinus = Double.parseDouble(plFrom.getText().toString());
        if (plFromMinus < 0) {
            plFrom.setError("Введите положительное число!");
            return true;
        }
        return false;

    }
}
