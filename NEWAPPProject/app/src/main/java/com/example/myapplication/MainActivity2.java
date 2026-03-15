package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityMain2Binding;
import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding= ActivityMain2Binding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.prevBtn.setOnClickListener(c->{
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(intent);
        });
        binding.nextBtn.setOnClickListener(c->{
            Toast.makeText(this, "Ne zmay", Toast.LENGTH_LONG).show();
        });
    }
}