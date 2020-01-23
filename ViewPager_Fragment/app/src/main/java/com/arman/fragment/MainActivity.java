package com.arman.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private int NUM_PAGES = 3;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;
    private ImageView leftArrow ,rightArrow , dot1, dot2, dot3;
    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = findViewById(R.id.viewPager);
        leftArrow = findViewById(R.id.leftArrow);
        rightArrow = findViewById(R.id.rightArrow);

        dot1 = findViewById(R.id.dotOne);
        dot2 = findViewById(R.id.dotTwo);
        dot3 = findViewById(R.id.dotThree);

        mPager.setPageTransformer(true, new ZoomOutPageTransformer());

        // Instantiate a ViewPager and a PagerAdapter.
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x--;
                if(x>-1) {
                    mPager.setCurrentItem(x);
                    mPager.setPageTransformer(true, new ZoomOutPageTransformer());
                    dotSetter(x);
                }
            }
        });
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x++;
                int current = mPager.getCurrentItem();
                if(x<3) {
                    mPager.setCurrentItem(x);
                    mPager.setPageTransformer(true, new ZoomOutPageTransformer());
                    dotSetter(x);
                }
                else{
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                }
            }
        });
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                dotSetter(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new First_Fragment();
                case 1:
                    return new Second_Fragment();
                case 2:
                    return new Third_Fragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    void dotSetter(int pos) {
        if (pos == 0) {
            dot1.setImageResource(R.drawable.radio_button_checked);
            dot2.setImageResource(R.drawable.radio_button_unchecked);
            dot3.setImageResource(R.drawable.radio_button_unchecked);
            rightArrow.setImageResource(R.drawable.arrow_right);
        }
        else if (pos == 1) {
            dot1.setImageResource(R.drawable.radio_button_unchecked);
            dot2.setImageResource(R.drawable.radio_button_checked);
            dot3.setImageResource(R.drawable.radio_button_unchecked);
            rightArrow.setImageResource(R.drawable.arrow_right);
        }
        else if (pos == 2) {
            dot1.setImageResource(R.drawable.radio_button_unchecked);
            dot2.setImageResource(R.drawable.radio_button_unchecked);
            dot3.setImageResource(R.drawable.radio_button_checked);
            rightArrow.setImageResource(R.drawable.home);
        }
    }
}

