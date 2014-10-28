package com.neokree.materialtabsample;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.neokree.materialtabs.MaterialTab;
import com.neokree.materialtabs.MaterialTabHost;
import com.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends ActionBarActivity implements MaterialTabListener {
	
	private ViewPager pager;
	private ViewPagerAdapter pagerAdapter;
	MaterialTabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// init toolbar (old action bar)
		Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
		toolbar.setTitleTextColor(Color.WHITE);
		this.setSupportActionBar(toolbar);
		
		tabHost = (MaterialTabHost) this.findViewById(R.id.materialTabHost);
		pager = (ViewPager) this.findViewById(R.id.viewpager);
		
		// init view pager
		pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(pagerAdapter);
		pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            	// when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);
            }
        });
		
		// insert all tabs from pagerAdapter data
		for (int i = 0; i < pagerAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab() 
                            .setText(pagerAdapter.getPageTitle(i))
                            .setTabListener(this)
                            );
        }
		
		// set the first item selected
		tabHost.setSelectedNavigationItem(0);
		//tabHost.setTextColor(Color.WHITE);
	}

	@Override
	public void onTabSelected(MaterialTab tab) {
		// when the tab is clicked the pager swipe content to the tab position
		pager.setCurrentItem(tab.getPosition());
		
	}

	@Override
	public void onTabReselected(MaterialTab tab) {
	}

	@Override
	public void onTabUnselected(MaterialTab tab) {
	}

	private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public Fragment getItem(int num) {
            return new indexFragment();
        }

        @Override
        public int getCount() {
            return 3;
        }
        
        @Override
        public CharSequence getPageTitle(int position) {
        	switch(position) {
        	case 0: return "tab 1";
        	case 1: return "tab 2";
        	case 2: return "tab 3";
        	default: return null;
        	}
        }
        
    }
	
}
