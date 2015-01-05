package com.michael.doubanonline;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.michael.doubanonline.base.BaseCompatableAdapter;
import com.michael.doubanonline.bean.OnlineObject;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainAdapter extends BaseCompatableAdapter<OnlineObject>{

	protected ImageLoader imageLoader;
	public MainAdapter(Context context, ArrayList<OnlineObject> data) {
		
		super(context, data);
		imageLoader = MyApplication.getImageLoader();
	}
	
	class ViewHolder { 
		
		ImageView ivCover;
		TextView tvTitle;
		TextView tvPicNums;
		TextView tvJoinNums;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		final ViewHolder viewHolder;

		if (convertView == null) {
			
			convertView = super.layoutInflater.inflate(R.layout.list_item_main2, null);
			viewHolder = new ViewHolder();
			viewHolder.ivCover = (ImageView)convertView.findViewById(R.id.iv_cover);
			viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			viewHolder.tvPicNums = (TextView) convertView.findViewById(R.id.tv_pic_num);
			viewHolder.tvJoinNums = (TextView) convertView.findViewById(R.id.tv_join_num);
			convertView.setTag(viewHolder);
		} else {

			viewHolder = (ViewHolder) convertView.getTag();
		}

		OnlineObject online = data.get(position);
		viewHolder.tvTitle.setText(online.getTitle());
		viewHolder.tvPicNums.setText(online.getPhoto_count()+"");
		viewHolder.tvJoinNums.setText(online.getParticipant_count()+"");
		
		imageLoader.displayImage(online.getImage(), viewHolder.ivCover, ImageOptions.getTransPictureOption(9));//加载图片
		
		return convertView;
	}

}
