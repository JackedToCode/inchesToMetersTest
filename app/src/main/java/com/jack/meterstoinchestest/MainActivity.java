package com.jack.meterstoinchestest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextMeters;
    private EditText editTextInches;
    private Button buttonConvert;
    private Button buttonReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    // Prepares the buttons
    private void findViews() {
        editTextMeters = findViewById(R.id.edit_text_meters);
        editTextInches = findViewById(R.id.edit_text_inches);
        buttonConvert = findViewById(R.id.button_convert);
        buttonReset = findViewById(R.id.button_reset);
    }

    private void setupButtonClickListener() {
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Clicks");
                chooseCalculation();
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetInput();
            }
        });
    }

    private void chooseCalculation() {

        String textMeters = editTextMeters.getText().toString();
        String textInches = editTextInches.getText().toString();

        if (!textMeters.isEmpty() && textInches.isEmpty()) {
            double meters = Double.parseDouble(textMeters);
            convertToInches(meters);
        } else if (!textInches.isEmpty() && textMeters.isEmpty()) {
            double inches = Double.parseDouble(textInches);
            convertToMeters(inches);
        } else {
            Toast myToast = Toast.makeText(getApplicationContext(), "Please enter something, and only enter one at a time!", Toast.LENGTH_LONG);
            myToast.show();
        }

    }

    private void convertToInches(double meters) {
        double result = meters * 39.37;
        DecimalFormat inchDecimalFormatter = new DecimalFormat("0.00");
        String textResult = inchDecimalFormatter.format(result);
        displayResults(textResult, false);
    }

    private void convertToMeters(double inches) {
        DecimalFormat meterDecimalFormatter = new DecimalFormat("0.00");
        double result = inches * 0.0254;
        String textResult = meterDecimalFormatter.format(result);
        displayResults(textResult, true);
    }


    private void displayResults(String result, boolean convertsToMeters) {
        if (!convertsToMeters) {
            editTextInches.setText(result);
        } else {
            editTextMeters.setText(result);
        }
    }

    private void resetInput() {
        editTextInches.setText(null);
        editTextMeters.setText(null);
    }

}