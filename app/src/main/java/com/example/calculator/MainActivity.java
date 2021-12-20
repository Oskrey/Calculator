package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    String numberOne = "";
    String numberTwo = "";
    String operand = "";
    String test = "";

    boolean frac = false;
    boolean lastbt = false;
    boolean plusMinus = false;
    boolean percentUsed = false;

    Button button;

    public void fraction(View view)
    {TextView resultLabel = findViewById(R.id.result);

        if(!frac)
        {
            if (percentUsed)
            {
                numberTwo = "";
                percentUsed = false;
                frac = false;
            }
            if (operand.isEmpty())
            {
                if (!numberOne.isEmpty())
                numberOne += ".";
                else
                numberOne += "0.";
                resultLabel.setText(numberOne);
            }
            else
            {
                if (!numberTwo.isEmpty())
                    numberTwo += ".";
                else
                    numberTwo += "0.";
                resultLabel.setText(numberTwo);
            }
            frac = true;
        }

    }
    public void inputNumber(View view)
    {    TextView resultLabel = findViewById(R.id.result);
        String text = ((Button) view).getText().toString();
        if (operand.isEmpty())
        {
            if (percentUsed)
            {
                numberOne = "";
                percentUsed = false;
            }
            numberOne += text;
            resultLabel.setText(numberOne);
            if (text.equals("0") && numberOne.equals("0"))
            {
                numberOne = "";
                resultLabel.setText("0");
            }

        }
        else
        {
            if (percentUsed)
            {
                numberTwo = "";
                percentUsed = false;
            }
            numberTwo += text;
            resultLabel.setText(numberTwo);
            if (text.equals("0") && numberTwo.equals("0"))
            {
                numberTwo = "";
                resultLabel.setText("0");
            }
        }
    }

    public void clearOneAction(View view)
    {    TextView resultLabel = findViewById(R.id.result);

        if (operand.isEmpty())
        {
            if (!numberOne.isEmpty())
            {
                test = numberOne.substring(numberOne.length() - 1);
                if (test.equals("."))
                {
                    frac = false;
                }
                numberOne = numberOne.substring(0, numberOne.length() - 1);
                resultLabel.setText(numberOne);
                if (numberOne.isEmpty())
                    resultLabel.setText("0");

            }
            frac = false;

        }
        else
        {
            if (!numberTwo.isEmpty())
            {
                test = numberTwo.substring(numberTwo.length() - 1);
                if (test.equals("."))
                    frac = false;
                numberTwo =  numberTwo.substring(0, numberTwo.length() - 1);
                resultLabel.setText(numberTwo);
                if (numberTwo.isEmpty())
                    resultLabel.setText("0");
            }
        }
    }
    public void clearAllAction(View view)
    {    TextView resultLabel = findViewById(R.id.result);

        numberOne = "";
        numberTwo = "";
        operand = "";
        frac = false;
        plusMinus = false;
        percentUsed = true;
        resultLabel.setText("0");
        if (lastbt)
        {
            button.setBackgroundResource(R.color.SpecOrange);
            button.setTextColor(Color.rgb(0, 0, 0));
        }
        lastbt = false;

    }
    public void percent(View view)
    {

        Double resPercent;
        if(!numberOne.isEmpty() && numberTwo.isEmpty())
        {
            resPercent = Double.parseDouble(numberOne) / 100;
            numberOne = resPercent.toString();
            numberOne = inputFine(numberOne);
            percentUsed = true;
        }
        if(!numberOne.isEmpty() && !numberTwo.isEmpty())
        {
            resPercent = Double.parseDouble(numberTwo) / 100;
            numberTwo = resPercent.toString();
            numberTwo = inputFine(numberTwo);
            percentUsed = true;
        }

    }
    public void inputOperand(View view)
    {TextView tw = findViewById(R.id.result);
        if(numberTwo.isEmpty() && !numberOne.isEmpty())
        {
            ((Button) view).setBackgroundResource(R.color.white);
            ((Button) view).setTextColor(Color.rgb(246, 133, 31));

            if (lastbt)
            {
                button.setBackgroundResource(R.color.SpecOrange);
                button.setTextColor(Color.rgb(0, 0, 0));
                button = ((Button) view);
            }
            String text = ((Button) view).getText().toString();
            operand = text;
            lastbt = true;
            button = ((Button) view);
            frac = false;
            numberOne = inputFine(numberOne);
            tw.setText("0");
            Toast.makeText(getApplicationContext(), numberOne + " "+ text, Toast.LENGTH_SHORT).show();
            plusMinus = false;
        }
    }

    public void plusOrMinus(View view)
    {

        Double resPlusOrMinus;
        if(!numberOne.isEmpty() && numberTwo.isEmpty())
        {
            resPlusOrMinus = Double.parseDouble(numberOne) * -1;
            numberOne = resPlusOrMinus.toString();
            numberOne = inputFine(numberOne);
        }
        if(!numberOne.isEmpty() && !numberTwo.isEmpty())
        {
            resPlusOrMinus = Double.parseDouble(numberTwo) * -1;
            numberTwo = resPlusOrMinus.toString();
            numberTwo =  inputFine(numberTwo);
        }
    }

    public void resultAction(View view)
    {

        if(!(numberOne.isEmpty()||numberTwo.isEmpty()||operand.isEmpty()))
        {
            Double result = 0.0;

            switch (operand)
            {
                case "/":
                    if (!(Double.parseDouble(numberTwo) == 0))
                        result = Double.parseDouble(numberOne) / Double.parseDouble(numberTwo);
                    else {Toast.makeText(getApplicationContext(), getString(R.string.error_msg), Toast.LENGTH_SHORT).show(); numberTwo = ""; return;}
                    break;
                case "*":
                    result = Double.parseDouble(numberOne) * Double.parseDouble(numberTwo);
                    break;
                case "+":
                    result = Double.parseDouble(numberOne) + Double.parseDouble(numberTwo);
                    break;
                case "-":
                    result = Double.parseDouble(numberOne) - Double.parseDouble(numberTwo);
                    break;
                default:
                    break;
            }
            numberOne = result.toString();
            numberOne = inputFine(numberOne);
            numberTwo = "";
            operand = "";
            frac = false;
            plusMinus = false;
            percentUsed = true;
            lastbt = false;
            button.setBackgroundResource(R.color.SpecOrange);
            button.setTextColor(Color.rgb(0, 0, 0));
        }
        else
        {
            Toast.makeText(getApplicationContext(), getString(R.string.error_msg), Toast.LENGTH_SHORT).show();
        }
    }

    public  String inputFine(String str)
    {
        TextView resultLabel = findViewById(R.id.result);
        DecimalFormat decimalFormat = new DecimalFormat("#.###########");
        str = decimalFormat.format(Double.parseDouble(str));
        str = str.replaceAll(",",".");
        resultLabel.setText(str);
        return str;
    }

}