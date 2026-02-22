package com.example.myapplication;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private static final String PREFS = "dad_clicker_prefs";
    private static final String KEY_TAPS = "taps";
    private static final String KEY_UNLOCKED = "unlocked_count";
    private static final int TAPS_PER_ACHIEVEMENT = 10;
    private static final int ACHIEVEMENTS_TOTAL = 5;
    private SharedPreferences prefs;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefs=getSharedPreferences(PREFS,MODE_PRIVATE);

        setupAchievementsTexts();
        render();

        binding.centerButton.setOnClickListener(v->{
            int taps=prefs.getInt(KEY_TAPS, 0)+1;
            prefs.edit().putInt(KEY_TAPS, taps).apply();

            v.animate().cancel();
            v.setScaleX(1f);
            v.setScaleY(1f);
            v.animate().scaleX(1.03f)
                    .scaleY(1.03f)
                    .setDuration(90)
                    .withEndAction(()->v.animate().scaleX(1f).scaleY(1f).setDuration(120).start()).start();

            int unlocked=prefs.getInt(KEY_UNLOCKED,0);
            int shouldBeUnlocked=Math.min(ACHIEVEMENTS_TOTAL, taps/TAPS_PER_ACHIEVEMENT);
            if(shouldBeUnlocked>unlocked){
                prefs.edit().putInt(KEY_UNLOCKED,shouldBeUnlocked).apply();
                playUnlockAnimation(shouldBeUnlocked);
                showUnlockedDialog(shouldBeUnlocked);
            }
            render();
        });
        binding.subtitle.setOnClickListener(View v->
        {
            prefs.edit()
                    .putInt(KEY_TAPS,0)
                    .putInt(KEY_UNLOCKED,0)
                    .apply();
            binding.finalCard.setVisibility(View.GONE);
            binding.progress.setProgress(0);
            render();
        });
    }



    private void setupAchievementsTexts() {
        setAchievementsTexts(binding.a1.getRoot(), "Опора семьи", "Всегда рядом, когда это важно");
        setAchievementsTexts(binding.a2.getRoot(), "Мастер на все руки", "Любая задача решается спокойно уверенно");
        setAchievementsTexts(binding.a3.getRoot(), "Мудрый совет", "Слова, которые помогают идти дальше");
        setAchievementsTexts(binding.a4.getRoot(), "Сила терпения", "Спокойствие,которое держит дом");
        setAchievementsTexts(binding.a5.getRoot(), "Большое сердце", "Любовь, которую чувствуешь всегда");
    }

    private void setAchievementsTexts(MaterialCardView root, String name, String desc) {
        MaterialTextView tvName = root.findViewById(R.id.name);
        MaterialTextView tvDesc= root.findViewById(R.id.desc);
        tvName.setText(name);
        tvDesc.setText(desc);
    }
    private void render() {
        int taps = prefs.getInt(KEY_TAPS,0);
        int unlocked = prefs.getInt(KEY_UNLOCKED,0);
        binding.tapCounter.setText("Нажатий: "+taps);
        int nextUnlockIn = TAPS_PER_ACHIEVEMENT-(taps%TAPS_PER_ACHIEVEMENT);
        if(unlocked>=ACHIEVEMENTS_TOTAL){
            binding.nextUnlock.setText("Все достижения открыты");
            binding.progress.setMax(TAPS_PER_ACHIEVEMENT);
            binding.progress.setProgress(TAPS_PER_ACHIEVEMENT);
        }
        else {
            binding.nextUnlock.setText("До следующего достижения "+nextUnlockIn);
            binding.progress.setMax(TAPS_PER_ACHIEVEMENT);
            binding.progress.setProgress(taps%TAPS_PER_ACHIEVEMENT);
        }
        appleAchievementState(binding.a1.getRoot(), unlocked>=1);
        appleAchievementState(binding.a2.getRoot(), unlocked>=2);
        appleAchievementState(binding.a3.getRoot(), unlocked>=3);
        appleAchievementState(binding.a4.getRoot(), unlocked>=4);
        appleAchievementState(binding.a5.getRoot(), unlocked>=5);

        if(unlocked>=ACHIEVEMENTS_TOTAL){
            binding.finalCard.setVisibility(View.VISIBLE);
        }
        else
            binding.finalCard.setVisibility(View.GONE);

    }

    private void appleAchievementState(MaterialCardView root, boolean b) {
        View icon  = root.findViewById(R.id.icon);
        Chip chip = root.findViewById(R.id.status);
        if(b){
            icon.setBackground(null);
            ((android.widget.ImageView)icon).setImageResource(R.drawable.ic_star);
            chip.setText("ОТКРЫТО");
            chip.setChipIconVisible(false);
            chip.setEnabled(false);
            root.setAlpha(1f);
        }
        else {
            ((android.widget.ImageView)icon).setImageResource(R.drawable.ic_lock);
            chip.setEnabled(false);
            root.setAlpha(0.85f);
        }
    }

    private void playUnlockAnimation(int unlockedCount){
        View target;
        switch (unlockedCount){
            case 1:target=binding.a1.getRoot();break;
            case 2:target=binding.a2.getRoot();break;
            case 3:target=binding.a3.getRoot();break;
            case 4:target=binding.a4.getRoot();break;
            case 5:target=binding.a5.getRoot();break;
            default:return;
        }
        target.animate().cancel();
        target.setScaleX(0.98f);
        target.setScaleY(0.98f);
        target.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(420)
                .setInterpolator(new OvershootInterpolator(1.2f))
                .start();
        if(unlockedCount>=ACHIEVEMENTS_TOTAL){
            binding.finalCard.setAlpha(0f);
            binding.finalCard.setVisibility(View.VISIBLE);
            binding.finalCard.animate().alpha(1f).setDuration(350).start();
        }
    }
    private void showUnlockedDialog(int unlockedCount){
        if(unlockedCount<ACHIEVEMENTS_TOTAL){
            new AlertDialog.Builder(this)
                    .setTitle("Новое достижение")
                    .setMessage("Открыто Достижение")
                    .setPositiveButton("С праздником!!!", null)
                    .show();
        }
    }
}