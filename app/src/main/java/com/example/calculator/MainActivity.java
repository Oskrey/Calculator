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
    {
        TextView result = findViewById(R.id.result);
        if(!frac)
        {
            if (operand.isEmpty())
            {
                numberOne += ".";
                result.setText(numberOne);
            }
            else
            {
                numberTwo += ".";
                result.setText(numberTwo);
            }
            frac = true;
        }
    }
    public void inputNumber(View view)
    {
        TextView result = findViewById(R.id.result);

        if (operand.isEmpty())
        {
            if (percentUsed)
            {
                numberOne = "";
                percentUsed = false;
            }
            String text = ((Button) view).getText().toString();
            numberOne += text;
            result.setText(numberOne);
        }
        else
        {
            if (percentUsed)
            {
                numberTwo = "";
                percentUsed = false;
            }
            String text = ((Button) view).getText().toString();
            numberTwo += text;
            result.setText(numberTwo);
        }
    }

    public void clearOneAction(View view)
    {
        TextView result = findViewById(R.id.result);

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
                result.setText(numberOne);
            }
            else
            result.setText("0");
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
                result.setText(numberTwo);
            }
            else
                result.setText("0");
        }
    }
    public void clearAllAction(View view)
    {
        TextView result = findViewById(R.id.result);
        numberOne = "";
        numberTwo = "";
        operand = "";
        frac = false;
        plusMinus = false;
        percentUsed = true;
        result.setText("0");
        if (lastbt)
        {
            button.setBackgroundResource(R.color.SpecOrange);
            button.setTextColor(Color.rgb(0, 0, 0));
        }
        lastbt = false;

    }
    public void percent(View view)
    {
        TextView result = findViewById(R.id.result);
        Double resPercent;
        if (operand.isEmpty())
        {
            if (!numberOne.isEmpty())
            {
                resPercent = Double.parseDouble(numberOne) / 100;
                numberOne = resPercent.toString();
                result.setText(numberOne);
                percentUsed = true;
            }

        }
        else
        {
            if (!numberOne.isEmpty())
            {
                resPercent = Double.parseDouble(numberTwo) / 100;
                numberTwo = resPercent.toString();
                result.setText(numberTwo);
                percentUsed = true;
            }
        }

    }
    public void inputOperand(View view)
    {

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

            plusMinus = false;
        }
    }

    public void plusOrMinus(View view)
    {
        TextView result = findViewById(R.id.result);
        Double resPlusOrMinus;
        if (operand.isEmpty())
        {
            resPlusOrMinus = Double.parseDouble(numberOne) *-1;
            numberOne = resPlusOrMinus.toString();
            result.setText(numberOne);
        }
        else
        {
            resPlusOrMinus = Double.parseDouble(numberTwo) *-1;
            numberTwo = resPlusOrMinus.toString();
            result.setText(numberTwo);
        }
    }

    public void resultAction(View view)
    {
        if(!(numberOne.isEmpty()||numberTwo.isEmpty()||operand.isEmpty()))
        {
            Double result = 0.0;
            TextView resultLable = findViewById(R.id.result);

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
            DecimalFormat decimalFormat = new DecimalFormat("#.########");
            numberOne = decimalFormat.format(result);

            resultLable.setText(numberOne);
            numberTwo = "";
            operand = "";
            frac = false;
            plusMinus = false;
            percentUsed = true;
            button.setBackgroundResource(R.color.SpecOrange);
            button.setTextColor(Color.rgb(0, 0, 0));
        }
        else
        {
            Toast.makeText(getApplicationContext(), getString(R.string.error_msg), Toast.LENGTH_SHORT).show();
        }
    }

}