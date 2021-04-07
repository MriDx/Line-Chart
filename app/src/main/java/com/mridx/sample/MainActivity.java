package com.mridx.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mridx.sample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.lineChart.setDataChart(new float[]{5, 1, 2, 3, 5, 6, 7, 8, 9, 8, 13, 10});

    }
}