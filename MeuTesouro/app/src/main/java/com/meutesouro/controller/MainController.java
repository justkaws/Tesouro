package com.meutesouro.controller;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.meutesouro.entity.MoneyTitle;
import com.meutesouro.fragments.MoneyTitleFragment;
import com.meutesouro.app.R;
import com.meutesouro.observer.Action;
import com.meutesouro.observer.Observer;
import com.meutesouro.parser.HtmlParser;
import com.meutesouro.parser.HtmlParser.ErrorCode;
import com.meutesouro.parser.IParserListener;
import com.meutesouro.view.FragmentView;

public class MainController implements Observer, IParserListener {

	private static final String TAG = MainController.class.getSimpleName();
	
	private FragmentView mFragmentView;
	private List<MoneyTitle> mTitleList = new ArrayList<MoneyTitle>();

	public MainController(FragmentView fragmentView) {
		this.mFragmentView = fragmentView;
		
		HtmlParser html = HtmlParser.getInstance();
        html.parse(this);
		
        mTitleList.add(new MoneyTitle().setName("NTNB Principal 150519"));
        mTitleList.add(new MoneyTitle().setName("NTNB 150820"));
        mTitleList.add(new MoneyTitle().setName("NTNB Principal 150824").setFavorite(true));
        mTitleList.add(new MoneyTitle().setName("NTNB 150535")); 
        
        //mFragmentView.changeView(R.id.menu_frame, new SlidingMenuFragment(this));
        
        receive(Action.Open_Money_Title_Screen);
	}

	private List<MoneyTitle> getStarredTitles()
	{
		List<MoneyTitle> starredTitles = new ArrayList<MoneyTitle>();
		
		for (MoneyTitle title : mTitleList)
		{
			if (title.isFavorite())
			{
				starredTitles.add(title);
			}
		}
		
		return starredTitles;
	}
	
	@Override
	public void infoReceived(List<MoneyTitle> moneyTitlesList) {
		mTitleList = moneyTitlesList;
	}

	@Override
	public void error(ErrorCode errorCode, String errorMessage) {
		Log.d(TAG, errorMessage);
	}
	
	@Override
	public void receive(Action action) {
		switch(action)
		{
		case Open_Money_Title_Screen:
			mFragmentView.changeView(R.id.main_frame_layout, new MoneyTitleFragment(mTitleList));
			mFragmentView.receive(Action.Hide_Menu);
			break;
		case Open_Starred_Money_Title_Screen:
			mFragmentView.changeView(R.id.main_frame_layout, new MoneyTitleFragment(getStarredTitles()));
			mFragmentView.receive(Action.Hide_Menu);
			break;
		default:
			break;
		}
	}
	
}
