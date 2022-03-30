package com.example.conversordemoneda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Region;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etMonedaActual;
    private EditText etDolares, etEuros;
    private RadioButton rbConvertirADolar, rbConvertirAEuro;
    private Button btConvertir, btCambiarValor;

    // Valores de las monedas harcodeados
    //  private double dolar = 1.11;
    //  private double euro = 0.90;

    private double valorDeMoneda = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarVista();
    }

    // MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.dolar){
            //TODO: Marcar RadioButton
            rbConvertirAEuro.setChecked(true);
            rbConvertirADolar.setChecked(false);
            return true;
        }
        if (id== R.id.euro){
            rbConvertirAEuro.setChecked(false);
            rbConvertirADolar.setChecked(true);
        }
        return true;
    }
    //  MENU

    private void inicializarVista(){
        etDolares = findViewById(R.id.etDolares);
        etEuros = findViewById(R.id.etEuros);
        etMonedaActual = findViewById(R.id.tvMonedaActual);

        // Desabilito los inputs (EditText) para solo activar el indicado con los RadioButton
        etDolares.setEnabled(false);
        etEuros.setEnabled(false);
        // Desabilito el input(EditText) para habilitarlo solo cuando oprimo CambiarValor (Button)
        etMonedaActual.setEnabled(false);

        rbConvertirADolar = findViewById(R.id.rbConvertirADolar);
        rbConvertirAEuro = findViewById(R.id.rbConvertirAEuro);

        btConvertir = findViewById(R.id.btConvertir);
        btCambiarValor = findViewById(R.id.btCambiarValor);


        //TODO: implementar funcion de Boton CambiarValor. (no entendi su funcion)
        // Habilitar el EditText para ingresar un valor de moneda
        btCambiarValor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etMonedaActual.setEnabled(true);
            }
        });

        // Desabilito un input (EditText) si el otro es seleccionado
        rbConvertirADolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDolares.setEnabled(false);
                etEuros.setEnabled(true);
            }
        });

        // Desabilito un input (EditText) si el otro es seleccionado.
        rbConvertirAEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEuros.setEnabled(false);
                etDolares.setEnabled(true);
            }
        });

        // Calcula el resultado segun el RadioButton que este activo
        btConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                valorDeMoneda = Double.parseDouble(etMonedaActual.getText().toString());

                //TODO: manejo de errores en caso de que no se introduzca un numero
                if(rbConvertirAEuro.isChecked())
                    // Mando el valor ingresado en el input (EditText) a la funcion para operar
                    // Y se lo asigno al otro EditText para mostrar la conversion
                    etEuros.setText(ConvertirDolaresAEuros(etDolares.getText().toString()));

                if(rbConvertirADolar.isChecked())
                    // Mando el valor ingresado en el input (EditText) a la funcion para operar
                    // Y se lo asigno al otro EditText para mostrar la conversion
                    etDolares.setText(ConvertirEurosADolares(etEuros.getText().toString()));
            }
        });
    }


    // Funciones para convertir monedas
    private String ConvertirEurosADolares(String numero){
        // Recibo el String que esta dentro del EditText y los parseo a double para poder operar con el.
        double euros  = Double.parseDouble(numero);

        double resultado = euros * valorDeMoneda;

        // Lo mando como String ( + " " ) para poder asignalo al EditText
        return resultado + " ";
    }

    private String ConvertirDolaresAEuros(String numero){
        // Recibo el String que esta dentro del EditText y los parseo a double para poder operar con el.
        double dolares  = Double.parseDouble(numero);

        double resultado = dolares * valorDeMoneda;

        // Lo mando como String ( + " " ) para poder asignalo al EditText
        return resultado + " ";
    }
}