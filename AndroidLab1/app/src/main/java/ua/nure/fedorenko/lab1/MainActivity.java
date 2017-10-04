package ua.nure.fedorenko.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import lab1.fedorenko.nure.ua.ua.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton calculatorButton = (ImageButton) findViewById(R.id.calculatorButton);
        ImageButton colorButton = (ImageButton) findViewById(R.id.colorButton);
        ImageButton notesButton = (ImageButton) findViewById(R.id.notesButton);
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CalculatorActivity.class));
            }
        });
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ColorEditorActivity.class));
            }
        });
        notesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 02.10.2017 create notes activity
              //  startActivity(new Intent(MainActivity.this, NotesActivity.class));
            }
        });
    }
}
