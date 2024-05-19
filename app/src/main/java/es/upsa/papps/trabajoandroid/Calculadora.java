package es.upsa.papps.trabajoandroid;

import static es.upsa.papps.trabajoandroid.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Calculadora extends AppCompatActivity {

    double firstNum;
    String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_calculadora);

        Button num0 = findViewById(id.n0);
        Button num1 = findViewById(id.n1);
        Button num2 = findViewById(id.n2);
        Button num3 = findViewById(id.n3);
        Button num4 = findViewById(id.n4);
        Button num5 = findViewById(id.n5);
        Button num6 = findViewById(id.n6);
        Button num7 = findViewById(id.n7);
        Button num8 = findViewById(id.n8);
        Button num9 = findViewById(id.n9);
        Button on = findViewById(id.on);
        Button off = findViewById(id.off);
        Button ac = findViewById(id.ac);
        Button del = findViewById(id.del);
        Button div = findViewById(id.div);
        Button multi = findViewById(id.mult);
        Button resta = findViewById(id.rest);
        Button suma = findViewById(id.sum);
        Button igual = findViewById(id.igual);
        Button decimal = findViewById(id.dec);

        TextView screen = findViewById(id.screen);

        ac.setOnClickListener(view->{
            firstNum=0;
            screen.setText("0");
        });

        off.setOnClickListener(view -> screen.setVisibility(View.GONE));
        on.setOnClickListener(View ->{
            screen.setVisibility(View.VISIBLE);
            screen.setText("0");
        });

        ArrayList<Button> nums = new ArrayList<>();
        nums.add(num0);
        nums.add(num1);
        nums.add(num2);
        nums.add(num3);
        nums.add(num4);
        nums.add(num5);
        nums.add(num6);
        nums.add(num7);
        nums.add(num8);
        nums.add(num9);

        for (Button b: nums){
            b.setOnClickListener(view->{
                if (!screen.getText().toString().equals("0")){
                    screen.setText(screen.getText().toString() + b.getText().toString());
                } else {
                    screen.setText(b.getText().toString());
                }
            });
        }

        ArrayList<Button> opers=new ArrayList<>();
        opers.add(div);
        opers.add(multi);
        opers.add(resta);
        opers.add(suma);
        for (Button b:opers){
            b.setOnClickListener(view->{
                firstNum=Double.parseDouble(screen.getText().toString());
                operation= b.getText().toString();
                screen.setText("0");
            });
        }
        del.setOnClickListener(view-> {
            String num = screen.getText().toString();
            if (num.length()>1){
                screen.setText(num.substring(0,num.length()-1));
            }else if (num.length()==1 && num.equals("0")){
                screen.setText("0");
            }
        });
        decimal.setOnClickListener(view->{
            if (!screen.getText().toString().contains(".")){
                screen.setText(screen.getText().toString()+".");
            }

        });
        igual.setOnClickListener(view->{
            double secondNum = Double.parseDouble(screen.getText().toString());
            double result;
            switch (operation){
                case"/":
                    result=firstNum/secondNum;
                    break;
                case"X":
                    result=firstNum*secondNum;
                    break;
                case"+":
                    result=firstNum+secondNum;
                    break;
                case"-":
                    result=firstNum-secondNum;
                    break;
                default:
                    result=firstNum+secondNum;
            }
            screen.setText(String.valueOf(result));
            firstNum=result;
        });

    }
}