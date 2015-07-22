package com.example.learn.ui;

import com.example.learn.R;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_Score extends Fragment{
	private View view;
	private static final String[] CONTENT = new String[] { "成绩", "学分", "期末"};
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.fragment_score, null, false);
		init();
		return view;
	
	}
	
	private void init(){
		FragmentPagerAdapter adapter = new GoogleMusicAdapter(getChildFragmentManager());

        ViewPager pager = (ViewPager)view.findViewById(R.id.pager);
        pager.setAdapter(adapter);

//        TabPageIndicator indicator = (TabPageIndicator)view.findViewById(R.id.indicator);
//        PagerTitleStrip pagerTitleStrip=(PagerTitleStrip)view.findViewById(R.id.title);
//        pagerTitleStrip.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30);
        PagerTabStrip pagerTabStrip=(PagerTabStrip)view.findViewById(R.id.pagerTabStrip);
        pagerTabStrip.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30);
        pagerTabStrip.setAlpha(0.8f);
        pagerTabStrip.setClickable(true);
        //        indicator.setViewPager(pager);
	}
	
	
	 class GoogleMusicAdapter extends FragmentPagerAdapter {
	        public GoogleMusicAdapter(FragmentManager fm) {
	            super(fm);
	        }

	        @Override
	        public Fragment getItem(int position) {
	        	Fragment fragment;
	        	switch (position) {
				case 0:
					fragment=new Fragment_Score_Result();
					break;
				default:
					fragment=new Fragment_Score_StudentScore();
					break;
				}
	            return fragment;
	        }

	        @Override
	        public CharSequence getPageTitle(int position) {
	            return CONTENT[position % CONTENT.length].toUpperCase();
	        }

	        @Override
	        public int getCount() {
	          return CONTENT.length;
	        }
	    }
	

}
