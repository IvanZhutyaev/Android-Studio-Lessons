package com.example.myapp;

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

public class MainActivity extends AppCompatActivity {
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    EditText text;
    EditText text2;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        text = findViewById(R.id.editTextText);
        text2 = findViewById(R.id.editTextText2);
        textView = findViewById(R.id.textView);
        btn1.setOnClickListener((View view)-> {
            int a = Integer.parseInt(text.getText().toString())+Integer.parseInt(text2.getText().toString());
            textView.setText(String.valueOf(a));});
        btn2.setOnClickListener((View view)-> {
            int a = Integer.parseInt(text.getText().toString())-Integer.parseInt(text2.getText().toString());
            textView.setText(String.valueOf(a));});
        btn3.setOnClickListener((View view)-> {
            int a = Integer.parseInt(text.getText().toString())*Integer.parseInt(text2.getText().toString());
            textView.setText(String.valueOf(a));});
        btn4.setOnClickListener((View view)-> {
            int a = Integer.parseInt(text.getText().toString())/Integer.parseInt(text2.getText().toString());
            textView.setText(String.valueOf(a));});
    }
}