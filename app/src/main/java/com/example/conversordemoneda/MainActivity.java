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

    private TextView tvValorDeConversion;
    private EditText etDolares, etEuros;
    private RadioButton rbConvertirADolar, rbConvertirAEuro;
    private Button btConvertir, btCambiarValor;

    // Valores de las monedas harcodeados
    private double dolar = 1.11;
    private double euro = 0.90;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarVista();
    }

    // TODO: implementar las opciones desplegables de la APPBar (barra superior)
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
            //TODO: Desmarcar RadioButton
            return true;
        }
        return true;
    }

    private void inicializarVista(){
        etDolares = findViewById(R.id.etDolares);
        etEuros = findViewById(R.id.etEuros);

        // Desabilito los inputs (EditText) para solo activar el indicado con los RadioButton
        etDolares.setEnabled(false);
        etEuros.setEnabled(false);

        tvValorDeConversion = findViewById(R.id.tvValorDeConversion);

        rbConvertirADolar = findViewById(R.id.rbConvertirADolar);
        rbConvertirAEuro = findViewById(R.id.rbConvertirAEuro);

        btConvertir = findViewById(R.id.btConvertir);
        //TODO: implementar funcion de Boton CambiarValor. (no entendi su funcion)
        btCambiarValor = findViewById(R.id.btCambiarValor);

        // Desabilito un input (EditText) si el otro es seleccionado.
        rbConvertirADolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDolares.setEnabled(false);
                etEuros.setEnabled(true);
                // Actualizo el valor de conversion segun el RadioButton seleccionado
                tvValorDeConversion.setText("1EUR = "+Double.toString(dolar) + "USD");
            }
        });

        // Desabilito un input (EditText) si el otro es seleccionado.
        rbConvertirAEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEuros.setEnabled(false);
                etDolares.setEnabled(true);
                // Actualizo el valor de conversion segun el RadioButton seleccionado
                tvValorDeConversion.setText("1USD = " + Double.toString(euro) + "EUR");
            }
        });

        // Calcula el resultado segun el RadioButton que este activo
        btConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

        double resultado = euros * dolar;

        // Lo mando como String ( + " " ) para poder asignalo al EditText
        return resultado + " ";
    }

    private String ConvertirDolaresAEuros(String numero){
        // Recibo el String que esta dentro del EditText y los parseo a double para poder operar con el.
        double dolares  = Double.parseDouble(numero);

        double resultado = dolares * euro;

        // Lo mando como String ( + " " ) para poder asignalo al EditText
        return resultado + " ";
    }
}