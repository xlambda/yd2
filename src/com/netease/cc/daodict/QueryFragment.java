package com.netease.cc.daodict;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class QueryFragment extends Fragment{

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.query_fragment, container, false);
        return view;
    }
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

}
