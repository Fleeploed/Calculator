package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {


    TextView calculation, answer;
    String sCalculation="", sAnswer="", number_one="", current_operator="";
    Double Result=0.0, numberOne=0.0, temp=0.0;
    // we need to reformat answer
    NumberFormat format,longformat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculation = findViewById(R.id.calculation);
        //set movement to the text view

        calculation.setMovementMethod(new ScrollingMovementMethod());
        //initialize answer
        answer = findViewById(R.id.answer);

        //we set the answer upto four decimal
        format=new DecimalFormat("#.####");
        longformat = new DecimalFormat("0.#E0");



    }

    public void onClickNumber(View v) {
        //we need to find which button is pressed
        Button bn=(Button)v;
        sCalculation+=bn.getText();
        number_one+=bn.getText();
        numberOne=Double.parseDouble(number_one);
        switch (current_operator){
            case "":
                temp = Result + numberOne;
                sAnswer = format.format(temp).toString();
                break;

            case "+":
                temp = Result + numberOne;
                sAnswer = format.format(temp).toString();
                break;

            case "-":
                temp = Result - numberOne;
                sAnswer = format.format(temp).toString();
                break;

            case "*":
                temp = Result * numberOne;
                sAnswer = format.format(temp).toString();
                break;

            case "/":
                try{
                    //divided by 0 cause exception
                    temp = Result + numberOne;
                    sAnswer = format.format(temp).toString();
                }catch (Exception e){
                    sAnswer = e.getMessage();
                }
                break;
        }
    }

    public void onClickOperator(View v) {
        Button ob= (Button)v;
        //if sAnswer is null means no calculation needed
        if(sAnswer!=""){
            sCalculation+="\n"+ob.getText();
            number_one="";
            numberOne=0.0;
            Result= temp;
            temp=0.0;
            sAnswer=format.format(Result).toString();
            current_operator=ob.getText().toString();
            updateCalculator();
        }
    }


    public void onClickClear(View v) {
        sCalculation = "";
        sAnswer = "";
        current_operator = "";
        number_one = "";
        Result = 0.0;
        numberOne = 0.0;
        temp = 0.0;
        updateCalculator();
    }


    public void updateCalculator() {
        calculation.setText(sCalculation);
        answer.setText(sAnswer);
    }

}
