package com.aibible;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private HomeFragment homeFragment;
    private CategoryFragment categoryFragment;
    private List<Tool> tools;
    private List<ToolCategory> categories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        tools = prepareData();
        categories = getCategories();

        homeFragment = new HomeFragment(tools);
        categoryFragment = new CategoryFragment(categories);

        tabLayout.setupWithViewPager(viewPager);
        //create viewpager adapter
        //here we will create inner class for adapter
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        //add fragments and set the adapter
        viewPagerAdapter.addFragment(homeFragment,"Top Tools");
        viewPagerAdapter.addFragment(categoryFragment,"Category");
        viewPager.setAdapter(viewPagerAdapter);
        //set the icons
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_star);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_category);
        //set the badge
//        BadgeDrawable badgeDrawable = Objects.requireNonNull(tabLayout.getTabAt(0)).getOrCreateBadge();
//        badgeDrawable.setVisible(true);
//        badgeDrawable.setNumber(5);
    }

    private List<Tool> prepareData(){
        String data = loadData();

        Gson gson =  new Gson();

        Type listType = new TypeToken<List<Tool>>(){}.getType();
        return gson.fromJson(data, listType);

    }

    private List<ToolCategory> getCategories(){
        List<ToolCategory> categories = new ArrayList<>();

        for (Tool tool: tools) {

            for(String s : tool.categories){

                boolean isExisting = false;

                for(ToolCategory toolCategory : categories){
                    if(Objects.equals(toolCategory.categoryName, s)){
                        toolCategory.count = toolCategory.count + 1;
                        isExisting = true;
                    }
                }
                if(!isExisting){
                    ToolCategory tc = new ToolCategory();
                    tc.categoryName = s;
                    tc.count = 1;
                    categories.add(tc);
                }

            }

        }

        return categories;
    }

    private String loadData()  {

        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getAssets().open("all.json")))) {

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                content.append(mLine);

            }
        } catch (IOException e) {
            //log the exception
        }
        //log the exception

        return content.toString();

    }
    private static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragments = new ArrayList<>();
        private final List<String> fragmentTitles = new ArrayList<>();
        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }
        //add fragment to the viewpager
        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            fragmentTitles.add(title);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
        @Override
        public int getCount() {
            return fragments.size();
        }

        //to setup title of the tab layout
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position);
        }


    }
}
