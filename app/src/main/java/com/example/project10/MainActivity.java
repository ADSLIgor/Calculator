package com.example.project10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project10.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';

    private char CURRENT_ACTION;

    private double valueOne = Double.NaN;
    private double valueTwo;

    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        decimalFormat = new DecimalFormat("#.##########");

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.btnRav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computeCalculation();
                binding.tvInfo.setText(binding.tvInfo.getText().toString() +
                        decimalFormat.format(valueTwo) + " = " + decimalFormat.format(valueOne));
                valueOne = Double.NaN;
                CURRENT_ACTION = '0';
            }
        });

        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.etEditText.getText().length() > 0) {
                    CharSequence currentText = binding.etEditText.getText();
                    binding.etEditText.setText(currentText.subSequence(0, currentText.length()-1));
                }
                else {
                    valueOne = Double.NaN;
                    valueTwo = Double.NaN;
                    binding.etEditText.setText("");
                    binding.tvInfo.setText("");
                }
            }
        });

    }

    public void OnClick(View v)
    {
        Button button = (Button)v;
        binding.etEditText.setText(binding.etEditText.getText()+""+button.getText());
    }

    public void OnClickOp(View v)
    {
        computeCalculation();
        Button button = (Button)v;
        CURRENT_ACTION = button.getText().toString().charAt(0);
        binding.tvInfo.setText(decimalFormat.format(valueOne)+""+button.getText().toString());
        binding.etEditText.setText(null);
    }

    private void computeCalculation() {
        if(!Double.isNaN(valueOne)) {
            valueTwo = Double.parseDouble(binding.etEditText.getText().toString());
            binding.etEditText.setText(null);

            if(CURRENT_ACTION == ADDITION)
                valueOne = this.valueOne + valueTwo;
            else if(CURRENT_ACTION == SUBTRACTION)
                valueOne = this.valueOne - valueTwo;
            else if(CURRENT_ACTION == MULTIPLICATION)
                valueOne = this.valueOne * valueTwo;
            else if(CURRENT_ACTION == DIVISION)
                valueOne = this.valueOne / valueTwo;
        }
        else {
            try {
                valueOne = Double.parseDouble(binding.etEditText.getText().toString());
            }
            catch (Exception e){}
        }
    }
}