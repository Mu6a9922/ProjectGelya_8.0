package com.mu6a.android8test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DBHelper dbHelper;
    TextView tvOut;
    EditText ename,esname,eyear;
    Button btnDel, btnAdd, btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DBHelper(this);
        tvOut = findViewById(R.id.tvOut);

        ename = findViewById(R.id.editName);
        esname = findViewById(R.id.editSurname);
        eyear = findViewById(R.id.editYear);

        btnDel = findViewById(R.id.buttonDel);
        btnAdd = findViewById(R.id.buttonAdd);
        btnGet = findViewById(R.id.buttonGet);

        btnDel.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnGet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonDel){
            dbHelper.deleteAll();
        } else if (v.getId() == R.id.buttonAdd) {
            String name = ename.getText().toString();
            String surname = esname.getText().toString();
            int year = Integer.parseInt(eyear.getText().toString());
            Data data = new Data(name,surname,year);
            dbHelper.addOne(data);
        } else if (v.getId() == R.id.buttonGet) {
            LinkedList<Data> list = dbHelper.getAll();

            String text = "";
            for (Data data: list
            ) {
                text = text + data.name + " " + data.surname + " " + data.year + "\n";
            }
            tvOut.setText(text);
        }
    }
}