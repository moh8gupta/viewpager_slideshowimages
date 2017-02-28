package com.example.mohit.slideshow_images;

import android.content.Intent;
import android.graphics.Picture;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    int position;
    public static String[] str;


    AndroidImageAdapter adapterView = new AndroidImageAdapter(this);

    // the number of pages to show

    private static final int NUM_PAGES = 5;

    /*
     The pager widget that handles animation and allows swiping horizontally to access previous and next pages
     */
    private ViewPager vPager;

    // the pager adapter that provides the pages to the view pager widget

    private PagerAdapter pAdapter;

    private Handler handler=new Handler();
    private Runnable runnale=new Runnable(){
        public void run(){
            vPager.setCurrentItem(position,true);
            if(position>=NUM_PAGES ) position=0;
            else position++;
            // Move to the next page after 10s
            handler.postDelayed(runnale, 2000);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize position variable used in auto slideshow
        position=0;
        // Initialize the array of page contents
        str=getResources().getStringArray(R.array.page_content);
        // Instantiate a ViewPager and a PagerAdapter
        vPager = (ViewPager) findViewById(R.id.pager);
        pAdapter = new PagerAdapter(getSupportFragmentManager());
        vPager.setAdapter(adapterView);

    }



    public void clicked(View view)
    {
        Intent intent=new Intent(MainActivity.this,NextActivity.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        if (vPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            vPager.setCurrentItem(vPager.getCurrentItem() - 1);
        }
    }
    // PagerAdater class that extends the FragmentStatePagerAdapter


    private class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            return SlidePageFragment.newInstance(pos);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
    public void onPause(){
        super.onPause();
        if(handler!=null)
            handler.removeCallbacks(runnale);
    }
    public void onResume(){
        super.onResume();
        // Start auto screen slideshow after 1s
        handler.postDelayed(runnale, 1000);
    }

}
