package e.wolfsoft1.pizza_order_animation;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Adapter.Custom1TabAdapter;

public class Custom1 extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_main);


        tabLayout = findViewById(R.id.tab_layout);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


        tabLayout.addTab(tabLayout.newTab().setText("BestSeller"));
        tabLayout.addTab(tabLayout.newTab().setText("Veg Pizza"));
        tabLayout.addTab(tabLayout.newTab().setText("Non Veg Pizza"));
        tabLayout.addTab(tabLayout.newTab().setText("Beverages"));



        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager1 = (ViewPager) findViewById(R.id.pager);
        Custom1TabAdapter adapter = new Custom1TabAdapter(getSupportFragmentManager(), 4);
        viewPager1.setAdapter(adapter);
        viewPager1.setOffscreenPageLimit(4);
        viewPager1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager1.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


}
