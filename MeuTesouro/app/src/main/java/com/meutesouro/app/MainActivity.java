package com.meutesouro.app;

import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;

import com.meutesouro.controller.MainController;
import com.meutesouro.observer.Action;
import com.meutesouro.view.FragmentView;

public class MainActivity extends FragmentActivity implements FragmentView {

    private static final String TAG = MainActivity.class.getSimpleName();
	private SlidingMenu menu;
	
	private MainController mMainController;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     
        setContentView(R.layout.activity_main);
         
        //List<MoneyTitle> title2 = (List<MoneyTitle>)xmlUti\l.XmlLoadData( "teste.xml" );
        //Log.d(TAG, "Total de Elementos: " + title2.size());
        
        // configure the SlidingMenu
        menu = new SlidingMenu(this);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.menu_frame);
        
        mMainController = new MainController(this);
        
//        XmlUtils xmlUtil = new XmlUtils(getBaseContext());
//        xmlUtil.XmlSaveData("teste.xml", titleList);
         
        //List<MoneyTitle> title2 = (List<MoneyTitle>)xmlUti\l.XmlLoadData( "teste.xml" );
        //Log.d(TAG, "Total de Elementos: " + title2.size());
        
       
    }

	@Override
	public void changeView(int resourceId, Fragment fragment) {
		getSupportFragmentManager()
        .beginTransaction()
        .replace(resourceId, fragment)
        .commit();
	}

	@Override
	public void receive(Action action) {
		switch (action) {
		case Hide_Menu:
			menu.toggle();
			break;
		default:
			break;
		}
	}
    
}
