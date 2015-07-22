package com.example.learn.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.learn.R;
import com.example.learn.model.DataDB;
import com.example.learn.model.MyScore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Fragment_Score_Result extends Fragment {
	private View view;
	private ListView listView;
	private DataDB dataDB;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_score_result, null, false);
		init();
		return view;

	}

	private void init() {
		dataDB = DataDB.getInstance(getActivity());
		listView = (ListView) view
				.findViewById(R.id.fragment_score_result_listview);

		ListAdapter adapter = new SimpleAdapter(getActivity(), setDataToList(),
				R.layout.list_item_score, new String[] { "name", "score" },
				new int[] { R.id.list_textView1, R.id.lsit_textView2 });
		listView.setAdapter(adapter);
	}

	private List setDataToList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		List dateList = dataDB.loadMyScore();
		Map<String, Object> map;
		
		for (int i = 0; i < dateList.size(); i++) {
			map = new HashMap<String, Object>();
			MyScore myScore = (MyScore) dateList.get(i);
			Log.i("Fragment_Score_Result", myScore.getName());
			map.put("name", myScore.getName());
			map.put("score", myScore.getScore());
			list.add(map);
		}
		Log.i("Fragment_Score_Result", list.toString());
		return list;
	}
}
