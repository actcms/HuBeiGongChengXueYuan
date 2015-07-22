package com.example.learn.ui;


import com.example.learn.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_Score_StudentScore extends Fragment{
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.fragment_score_studentscore, null, false);
		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
