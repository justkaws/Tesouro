package com.meutesouro.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.meutesouro.entity.MoneyTitle;
import com.meutesouro.app.R;

public class FavoriteListAdapter extends ArrayAdapter<MoneyTitle> {

	public FavoriteListAdapter(Context context, int resource, List<MoneyTitle> objects) {
		super(context, resource, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
	   MoneyTitle title = getItem(position);
	   ViewHolder viewHolder = null;
	   
	   // Check if an existing view is being reused, otherwise inflate the view
	   if (convertView == null) {
	      convertView = LayoutInflater.from(getContext()).inflate(R.layout.favorite_list_item, parent, false);
	      viewHolder = new ViewHolder();
	      viewHolder.tvTitleName = (TextView) convertView.findViewById(R.id.money_title);
	      viewHolder.cbStarred = (CheckBox) convertView.findViewById(R.id.chkIos);
	      viewHolder.cbStarred.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

	    	   @Override
	    	   public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
	    		   ViewHolder viewHolder = (ViewHolder) ((View) buttonView.getParent()).getTag();
	    		   
	    		   if (viewHolder.title != null)
	    		   {
	    			   viewHolder.title.setFavorite(isChecked);
	    		   }
	    	   }
	      });
	      
	      convertView.setTag(viewHolder);
	   }
	   else
	   {
		 viewHolder = (ViewHolder) convertView.getTag();
	   }

	   // Populate the data into the template view using the data object
	   viewHolder.title = title;
	   viewHolder.tvTitleName.setText(title.getName());
	   viewHolder.cbStarred.setChecked(title.isFavorite());
	   // Return the completed view to render on screen
	   return convertView;
	}
	
	private static class ViewHolder
	{
		TextView tvTitleName;
		CheckBox cbStarred;
		MoneyTitle title = null;
	}
}
