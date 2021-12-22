package com.example.chatkit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class Intro extends AppCompatActivity {

    ImageView backGround;
    LottieAnimationView lottieAnimationView;
    ImageView imageView;

    private static final int NUM_P = 3;
    private ViewPager viewPager;
    private SlideSPA pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        backGround = findViewById(R.id.backG);
        lottieAnimationView = findViewById(R.id.lottie);
        viewPager = findViewById(R.id.pager);
        imageView = findViewById(R.id.imageView);

        pagerAdapter = new SlideSPA(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        backGround.animate().translationY(-2500).setDuration(1000).setStartDelay(8000);
        imageView.animate().translationY(-2500).setDuration(1000).setStartDelay(8000);
        lottieAnimationView.animate().translationY(2000).setDuration(1000).setStartDelay(8000);
    }

    private class SlideSPA extends FragmentStatePagerAdapter{

        public SlideSPA(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    OBF1 tab1 = new OBF1();
                    return tab1;
            }
            switch (position){
                case 1:
                    OBF2 tab2 = new OBF2();
                    return tab2;
            }
            switch (position){
                case 2:
                    OBF3 tab3 = new OBF3();
                    return tab3;
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_P;
        }
    }

}