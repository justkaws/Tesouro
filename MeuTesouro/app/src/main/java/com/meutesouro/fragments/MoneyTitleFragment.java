package com.meutesouro.fragments;

import java.util.ArrayList;
import java.util.List;

import com.meutesouro.adapter.FavoriteListAdapter;
import com.meutesouro.entity.MoneyTitle;
import com.meutesouro.app.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class MoneyTitleFragment extends Fragment {

	private List<MoneyTitle> mMoneyTitleList;

	public MoneyTitleFragment() {}
	
	public MoneyTitleFragment(List<MoneyTitle> moneyTitleList) {
		this.mMoneyTitleList = moneyTitleList;
		
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.title_list_layout, null);
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
        SetListContent(mMoneyTitleList);
	}
	
	public void SetListContent(List<MoneyTitle> data){
    	
    	ListView listView = (ListView) getView().findViewById(R.id.listView1);
    	listView.setAdapter(new FavoriteListAdapter(getActivity(), R.layout.favorite_list_item, data));
    	
    }
	
}
