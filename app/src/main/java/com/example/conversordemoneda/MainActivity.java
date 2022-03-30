package com.example.conversordemoneda;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Region;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static EditText etMonedaActual;
    private EditText etDolares, etEuros;
    private RadioButton rbConvertirADolar, rbConvertirAEuro;
    private Button btConvertir, btCambiarValor;

    // Valores de las monedas harcodeados
    //  private double dolar = 1.11;
    //  private double euro = 0.90;

    private static double valorDeMoneda = 0.90;


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


    public static class DialogoFormulario extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
            LayoutInflater li=getLayoutInflater();
            View view=li.inflate(R.layout.dialogo_cambio,null);
            builder.setView(view);
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Seteo el valor nuevo de dolar que está
                    EditText dolar=view.findViewById(R.id.etDolarDialog);
                    valorDeMoneda = Double.parseDouble(dolar.getText().toString());
                    etMonedaActual.setText(dolar.getText());
                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            return builder.create();
        }
    }


    private void inicializarVista(){
        etDolares = findViewById(R.id.etDolares);
        etEuros = findViewById(R.id.etEuros);
        etMonedaActual = findViewById(R.id.tvMonedaActual);

        // Desabilito los inputs (EditText) para solo activar el indicado con los RadioButton
        etDolares.setEnabled(false);
        etEuros.setEnabled(false);
        // Desabilito el input(EditText) para habilitarlo solo cuando oprimo CambiarValor (Button)
        etMonedaActual.setEnabled(false);
        // TODO: setear valor inicial a los inputs para tener un valor inicial para operar o implementar Try Cacht
        // Seteo valor de moneda actual en 0.0 para que inice con un valor valido para operar
        etMonedaActual.setText(valorDeMoneda + "");

        rbConvertirADolar = findViewById(R.id.rbConvertirADolar);
        rbConvertirAEuro = findViewById(R.id.rbConvertirAEuro);

        btConvertir = findViewById(R.id.btConvertir);
        btCambiarValor = findViewById(R.id.btCambiarValor);

        // Habilitar el EditText para ingresar un valor de moneda
        btCambiarValor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etMonedaActual.setEnabled(true);

                DialogoFormulario df = new DialogoFormulario();
                df.show(getSupportFragmentManager(),"dialogo");
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

//                valorDeMoneda = Double.parseDouble(etMonedaActual.getText().toString());

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
        double moneda  = Double.parseDouble(numero);
        double resultado = moneda / valorDeMoneda;

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