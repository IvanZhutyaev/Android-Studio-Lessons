package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private static final String PREFS = "dad_clicker_prefs";
    private static final String KEY_TAPS = "taps";
    private static final String KEY_UNLOCKED = "unlocked_count";
    private static final int TAPS_PER_ACHIVIEMENT = 10;
    private static final int ACHIVIEMENTS_TOTAL = 5;
    private SharedPreferences prefs;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefs=getSharedPreferences(PREFS,MODE_PRIVATE);

        setupAchievementsTexts();
        render();

    }

    private void setupAchievementsTexts() {
        setAchievementsTexts(binding.a1.getRoot(), "Опора семьи", "Всегда рядом, когда это важно");
        setAchievementsTexts(binding.a2.getRoot(), "Мастер на все руки", "Любая задача решается спокойно уверенно");
        setAchievementsTexts(binding.a3.getRoot(), "Мудрый совет", "Слова, которые помогают идти дальше");
        setAchievementsTexts(binding.a4.getRoot(), "Сила терпения", "Сопокойствие,которое держит дом");
        setAchievementsTexts(binding.a5.getRoot(), "Большое сердце", "Любовь, которую чувствуешь всегда");
    }
}