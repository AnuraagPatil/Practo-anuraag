package com.sourcekode.practo.practo;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sourcekode.practo.practo.Fragment.About;
import com.sourcekode.practo.practo.Fragment.Contact;
import com.sourcekode.practo.practo.Fragment.Feedback;
import com.sourcekode.practo.practo.SampleDataProvider.DataProvider;

/**
 * Created by Swanand Borikar on 3/7/2018.
 */

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        MyAdapter adapter=new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));

        tabLayout.setupWithViewPager(viewPager);


    }

}

class MyAdapter extends FragmentPagerAdapter
{

    public MyAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment= null;
        if(position==0)
        {
            fragment=new About();
        }
        if (position==1)
        {
            fragment=new Contact();

        }
        if (position==2)
        {
            fragment=new Feedback();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        if (position==0)
        {
            return "About";
        }
        if (position==1)
        {
            return "Contact";
        }
        if (position==2)
        {
            return "Feedback";
        }
        return  null;
    }
}

