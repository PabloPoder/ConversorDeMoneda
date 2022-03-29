package com.example.conversordemoneda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private EditText etDolar, etEuro;
    private RadioButton rbConvertirADolar, rbConvertirAEuro;
    private Button btConvertir, btCambiarValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarVista();
    }

    // Menu
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
        etDolar = findViewById(R.id.etDolar);
        etEuro = findViewById(R.id.etEuro);

        rbConvertirADolar = findViewById(R.id.rbConvertirADolar);
        rbConvertirAEuro = findViewById(R.id.rbConvertirAEuro);

        btConvertir = findViewById(R.id.btConvertir);
        btCambiarValor = findViewById(R.id.btCambiarValor);

        //set enable false a los input
    }
}