package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ActivityResultLauncher<Intent> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        Bundle bundle= new Bundle();
        launcher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result->{
           if (result.getResultCode()==RESULT_OK){
               String data = result.getData().getStringExtra("name");
               Toast.makeText(this,data,Toast.LENGTH_LONG).show();
            }
        });
        binding.resultBtn.setOnClickListener(c->{
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                launcher.launch(intent);
        }
        );

        binding.nextBtn.setOnClickListener(c->{
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            bundle.putString("name", binding.nameEdt.getText().toString());
            bundle.putString("city", binding.cityEdt.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
        });
        binding.prevBtn.setOnClickListener(c->{
            Toast.makeText(this, "Ne zmay", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:55.23422, 45.45444"));
            startActivity(intent);
        });
    }
}