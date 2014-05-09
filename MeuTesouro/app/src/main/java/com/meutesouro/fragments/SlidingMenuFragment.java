package com.meutesouro.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.meutesouro.app.R;
import com.meutesouro.observer.Action;
import com.meutesouro.observer.Observer;

@SuppressLint("ValidFragment")
public class SlidingMenuFragment extends ListFragment {

	private final Observer mMenuObserver;

	public SlidingMenuFragment() {
		mMenuObserver = null;
	}
	
	public SlidingMenuFragment(Observer menuObserver)
	{
		this.mMenuObserver = menuObserver;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// Creating Menu Items
		SampleAdapter adapter = new SampleAdapter(getActivity());
		
		adapter.add(new SampleItem("Titulos", R.drawable.ic_launcher, Action.Open_Money_Title_Screen));
		adapter.add(new SampleItem("Favoritos", R.drawable.starred, Action.Open_Starred_Money_Title_Screen));
		
		setListAdapter(adapter);
	}

	private class SampleItem {
		public String tag;
		public int iconRes;
		public Action action;
		public SampleItem(String tag, int iconRes, Action action) {
			this.tag = tag; 
			this.iconRes = iconRes;
			this.action = action;
		}
	}
	
	private static class ViewHolder
	{
		ImageView icon;
		TextView title;
		SampleItem item;
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			
			ViewHolder viewHolder = null;
			
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, null);
				
				viewHolder = new ViewHolder();
				viewHolder.icon = (ImageView) convertView.findViewById(R.id.row_icon);
				viewHolder.title = (TextView) convertView.findViewById(R.id.row_title);
				
				convertView.setTag(viewHolder);
				
				convertView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						mMenuObserver.receive(((ViewHolder)v.getTag()).item.action);
					}
				});
			}
			else
			{
				viewHolder = (ViewHolder) convertView.getTag();
			}
			
			viewHolder.item = getItem(position);
			viewHolder.title.setText(getItem(position).tag);
			viewHolder.icon.setImageResource(getItem(position).iconRes);

			return convertView;
		}

	}
}
