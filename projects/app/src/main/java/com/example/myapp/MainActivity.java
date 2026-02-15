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

import com.example.myapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding.button.setOnClickListener((View view)-> {
            int a = Integer.parseInt(binding.editTextText.getText().toString())+Integer.parseInt(binding.editTextText2.getText().toString());
            binding.textView.setText(String.valueOf(a));});
        binding.button2.setOnClickListener((View view)-> {
            int a = Integer.parseInt(binding.editTextText.getText().toString())-Integer.parseInt(binding.editTextText2.getText().toString());
            binding.textView.setText(String.valueOf(a));});
        binding.button3.setOnClickListener((View view)-> {
            int a = Integer.parseInt(binding.editTextText.getText().toString())*Integer.parseInt(binding.editTextText2.getText().toString());
            binding.textView.setText(String.valueOf(a));});
        binding.button4.setOnClickListener((View view)-> {
            int a = Integer.parseInt(binding.editTextText.getText().toString())/Integer.parseInt(binding.editTextText2.getText().toString());
            binding.textView.setText(String.valueOf(a));});
    }
}