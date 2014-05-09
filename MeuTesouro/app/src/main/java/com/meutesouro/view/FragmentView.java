package com.meutesouro.view;

import com.meutesouro.observer.Observer;

import android.support.v4.app.Fragment;

public interface FragmentView extends Observer {
	void changeView(int resourceId, Fragment fragment);
}
