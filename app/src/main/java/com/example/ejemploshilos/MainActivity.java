package com.example.ejemploshilos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private EditText entrada;
    private TextView salida;
    private String salida_str;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entrada = (EditText) findViewById(R.id.entrada);
        salida = (TextView) findViewById(R.id.salida);
        salida_str = "";
    }

    //Cuando se hace click en el botón ejecuta el método
    /*public void calcularOperacion(View view) {
        int n = Integer.parseInt(entrada.getText().toString());
        salida_str += (n +"! = ");
        int res = factorial(n);
        salida_str += res +"\n";
        salida.setText(salida_str);
    }*/


    //Cuando se hace click en el botón ejecuta el método
    public void calcularOperacion(View view) {
        int n = Integer.parseInt(entrada.getText().toString());
        salida_str += n + "! = ";
        salida.setText(salida_str);
        MiThread thread = new MiThread(n);
        thread.start();
    }


    //Calculaa el factorial de n
    public int factorial(int n) {
        int res = 1;
        for (int i = 1; i<=n; i++){
            res *= i;
            SystemClock.sleep(500);
        }
        return res;
    }

    //Hilo

    class MiThread extends Thread {
        private int n, res;
        public MiThread(int n) {
            this.n = n;
        }
        @Override
        public void run() {
            int res = factorial(n);
            runOnUiThread(new Runnable() {
                @Override public void run() {
                    salida_str += res + "\n";
                    salida.setText(salida_str);
                }
            });
        }
    }
}
