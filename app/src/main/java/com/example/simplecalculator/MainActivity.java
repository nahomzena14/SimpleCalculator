package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView calculatorView;
    private double currentValue = 0.0;
    private double storedValue = 0.0;
    private Operand operand = Operand.NONE;
    private boolean periodPressed = false;

    enum Operand {
        NONE,
        PLUS,
        MINUS,
        MULTIPLY,
        MODULUS,
        DIVISION,
        SIN,
        COS,
        TAN,
        SQUARE_ROOT,
        LOG
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculatorView = findViewById(R.id.result_textView);
    }

    public void onClick(View view){

        switch (view.getId()){
            case R.id.ac_button:
                calculatorView.setText("0");
                currentValue = 0.0;
                break;
            case R.id.zero_button:
                if(currentValue != 0) {
                    double value = currentValue * 10;
                    calculatorView.setText(value + "");
                    currentValue = value;
                }
                break;
            case R.id.nine_button:
                setNumber(9);
                break;
            case R.id.eight_button:
                setNumber(8);
                break;
            case R.id.seven_button:
                setNumber(7);
                break;
            case R.id.six_button:
                setNumber(6);
                break;
            case R.id.five_button:
                setNumber(5);
                break;
            case R.id.four_button:
                setNumber(4);
                break;
            case R.id.three_button:
                setNumber(3);
                break;
            case R.id.two_button:
                setNumber(2);
                break;
            case R.id.one_button:
                setNumber(1);
                break;

            case R.id.multiply_button:
                doCalculation(Operand.MULTIPLY);
                break;
            case R.id.division_button:
                doCalculation(Operand.DIVISION);
                break;
            case R.id.module_button:
                doCalculation(Operand.MODULUS);
                break;
            case R.id.minus_button:
                doCalculation(Operand.MINUS);
                break;
            case R.id.plus_button:
                doCalculation(Operand.PLUS);
                break;
            case R.id.equal_button:
                getResult();
                break;
            case R.id.period_button:
                if(!calculatorView.getText().toString().contains(".")) {
                    String temp1 = calculatorView.getText().toString()+".0";
                    String temp2 = temp1.substring(0,temp1.length()-1);
                    calculatorView.setText(temp2);
                    currentValue = Double.parseDouble(temp2);
                }
                break;
            case R.id.negate_button:
                if(currentValue != 0) {
                    double value2 = currentValue * -1;
                    calculatorView.setText(value2 + "");
                    currentValue = value2;
                }
                break;
            case R.id.sin_button:
                double value3 = Math.sin(currentValue);
                calculatorView.setText(value3 + "");
                currentValue = value3;
                break;
            case R.id.cos_button:
                double value4 = Math.cos(currentValue);
                calculatorView.setText(value4 + "");
                currentValue = value4;
                break;
            case R.id.tan_button:
                double value5 = Math.tan(currentValue);
                calculatorView.setText(value5 + "");
                currentValue = value5;
                break;
            case R.id.square_root_button:
                if (currentValue>=0){
                    double value6 = Math.sqrt(currentValue);
                    calculatorView.setText(value6 + "");
                    currentValue = value6;
                }
                break;
            case R.id.log_button:
                double value7 = Math.log(currentValue);
                calculatorView.setText(value7 + "");
                currentValue = value7;
                break;

        }
    }

    private void doCalculation(Operand operand) {
        getResult();
        storedValue = currentValue;
        currentValue = 0;
        this.operand = operand;
    }

    private void getResult(){

        double result = 0;

        switch (operand){
            default:
                result = currentValue;
                break;
            case PLUS:
                result = storedValue + currentValue;
                break;
            case MINUS:
                result = storedValue - currentValue;
                break;
            case DIVISION:
                result = storedValue / currentValue;
                break;
            case MODULUS:
                result = storedValue % currentValue;
                break;
            case MULTIPLY:
                result = storedValue * currentValue;
                break;
        }

        operand = Operand.NONE;
        storedValue = 0;
        currentValue = result;
        calculatorView.setText(currentValue+"");
    }

    private void setNumber(int num) {
        if(currentValue == 0){
            calculatorView.setText(""+num);
            currentValue = num;
        } else {
            if(periodPressed){
                String value = calculatorView.getText().toString();

            }
            String value = calculatorView.getText().toString() + num;
            Log.d("TAG_X", value);
            currentValue = Double.parseDouble(value);
            calculatorView.setText(value);
        }
    }
}