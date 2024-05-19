package es.upsa.papps.trabajoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button Notas;
    Button Musica;
    Button Trivial;
    Button Calculadora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Notas=(Button)findViewById(R.id.Notas);
        Notas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Notas.class);
                startActivity(i);

            }
        });
        Musica=(Button)findViewById(R.id.Musica);
        Musica.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent j = new Intent(MainActivity.this, Musica.class);
                startActivity(j);

            }
        });
        Trivial=(Button)findViewById(R.id.Trivial);
        Trivial.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent k = new Intent(MainActivity.this, Trivial.class);
                startActivity(k);

            }
        });
        Calculadora=(Button)findViewById(R.id.Calculadora);
        Calculadora.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent p = new Intent(MainActivity.this, Calculadora.class);
                startActivity(p);

            }
        });


    }

}