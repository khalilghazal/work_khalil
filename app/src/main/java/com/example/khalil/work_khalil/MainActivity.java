package com.example.khalil.work_khalil;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.LayoutParams;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    public ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            JSONObject obj = new JSONObject(loadJSONFund());
            Log.d("obj ",obj.getJSONObject("screen").getString("title"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        final Button contatoBut = findViewById(R.id.contato);
        final Button investimentoBut = findViewById(R.id.investimento);

        ViewGroup.LayoutParams params = contatoBut.getLayoutParams();
        params.height = 150;
        contatoBut.setBackgroundColor(getResources().getColor(R.color.colorRed2));
        investimentoBut.setBackgroundColor(getResources().getColor(R.color.colorRed));
        contatoBut.setTransformationMethod(null);

        contatoBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup.LayoutParams params = contatoBut.getLayoutParams();
                params.height = 150;
                contatoBut.setLayoutParams(params);

                params = investimentoBut.getLayoutParams();
                params.height = 140;
                investimentoBut.setLayoutParams(params);

                viewPager.setCurrentItem(0, true);
                contatoBut.setBackgroundColor(getResources().getColor(R.color.colorRed2));
                investimentoBut.setBackgroundColor(getResources().getColor(R.color.colorRed));
            }
        });
                
        investimentoBut.setTransformationMethod(null);
        investimentoBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ViewGroup.LayoutParams params = investimentoBut.getLayoutParams();
                params.height = 150;
                investimentoBut.setLayoutParams(params);

                params = contatoBut.getLayoutParams();
                params.height = 140;
                contatoBut.setLayoutParams(params);

                contatoBut.setBackgroundColor(getResources().getColor(R.color.colorRed));
                investimentoBut.setBackgroundColor(getResources().getColor(R.color.colorRed2));
                viewPager.setCurrentItem(1, true);

            }
        });

        // tabLayout = (TabLayout) findViewById(R.id.tabs);
        // tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new Fragment_Contato(), "ONE");
        adapter.addFragment(new Fragment_Investimentos(), "TWO");
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }




    public String loadJSONFund() {

        String json = null;
        try {
            InputStream is = getAssets().open("fund.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}





