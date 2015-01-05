package com.michael.doubanonline;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;

import com.michael.doubanonline.base.BaseCompatableAdapter;
import com.michael.doubanonline.bean.Photo;
import com.michael.doubanonline.component.ClickEffectImageView;
import com.michael.doubanonline.util.MathUtil;
import com.michael.doubanonline.util.ScreenUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 活动主页的适配器
 * 
 * */
public class OnlineDetailAdapter extends BaseCompatableAdapter<Photo>
{

	protected ImageLoader imageLoader;
	private static final int COLUMN_NUM = 4;
	private int PICTURE_WIDTH;

	public OnlineDetailAdapter(Context context, ArrayList<Photo> data)
	{

		super(context, data);
		PICTURE_WIDTH = ScreenUtil.getScreenWidth(context) / 4;
		imageLoader = MyApplication.getImageLoader();
	}

	class ViewHolder
	{

		ClickEffectImageView ivOne;
		ClickEffectImageView ivTwo;
		ClickEffectImageView ivThree;
		ClickEffectImageView ivFour;
	}

	@Override
	public int getCount()
	{

		if (data == null)
		{
			return 0;
		}

		return MathUtil.getAbsoluteInt(data.size(), COLUMN_NUM);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		final ViewHolder viewHolder;

		if (convertView == null)
		{

			convertView = super.layoutInflater.inflate(R.layout.list_item_detail, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.ivOne = (ClickEffectImageView) convertView.findViewById(R.id.ivOne);
			viewHolder.ivTwo = (ClickEffectImageView) convertView.findViewById(R.id.ivTwo);
			viewHolder.ivThree = (ClickEffectImageView) convertView.findViewById(R.id.ivThree);
			viewHolder.ivFour = (ClickEffectImageView) convertView.findViewById(R.id.ivFour);
			LayoutParams params = new LayoutParams(PICTURE_WIDTH, PICTURE_WIDTH, 1.0f);
			params.setMargins(2, 2, 2, 2);
			viewHolder.ivOne.setLayoutParams(params);
			viewHolder.ivTwo.setLayoutParams(params);
			viewHolder.ivThree.setLayoutParams(params);
			viewHolder.ivFour.setLayoutParams(params);
			convertView.setTag(viewHolder);
		} else
		{

			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.ivOne.setOnClickListener(listener);
		viewHolder.ivTwo.setOnClickListener(listener);
		viewHolder.ivThree.setOnClickListener(listener);
		viewHolder.ivFour.setOnClickListener(listener);
		viewHolder.ivOne.setTag(position);
		viewHolder.ivTwo.setTag(position);
		viewHolder.ivThree.setTag(position);
		viewHolder.ivFour.setTag(position);

		for (int i = 0; i < COLUMN_NUM; i++)
		{

			int realIndex = (position * COLUMN_NUM) + i;
			if (realIndex >= data.size())
			{

				switch (i)
				{
				case 1:
					viewHolder.ivTwo.setVisibility(View.INVISIBLE);
					viewHolder.ivThree.setVisibility(View.INVISIBLE);
					viewHolder.ivFour.setVisibility(View.INVISIBLE);
					break;
				case 2:
					viewHolder.ivThree.setVisibility(View.INVISIBLE);
					viewHolder.ivFour.setVisibility(View.INVISIBLE);
					break;
				case 3:
					viewHolder.ivFour.setVisibility(View.INVISIBLE);
					break;

				default:
					break;
				}
				break;
			}
			Photo photo = data.get(realIndex);
			switch (i)
			{
			case 0:
				imageLoader.displayImage(photo.thumb, viewHolder.ivOne, ImageOptions.getGrayPictureOption(0));// 加载图片
				break;
			case 1:
				imageLoader.displayImage(photo.thumb, viewHolder.ivTwo, ImageOptions.getGrayPictureOption(0));// 加载图片
				break;
			case 2:
				imageLoader.displayImage(photo.thumb, viewHolder.ivThree, ImageOptions.getGrayPictureOption(0));// 加载图片
				break;
			case 3:
				imageLoader.displayImage(photo.thumb, viewHolder.ivFour, ImageOptions.getGrayPictureOption(0));// 加载图片
				break;

			default:
				break;
			}
		}

		return convertView;
	}

	private OnClickListener listener = new OnClickListener()
	{

		int location;// 在ListItem中的位置

		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.ivOne:
				location = 0;
				break;

			case R.id.ivTwo:
				location = 1;
				break;

			case R.id.ivThree:
				location = 2;
				break;

			case R.id.ivFour:
				location = 3;
				break;

			default:
				break;
			}
			setClickedPhotoPosition(v, location);
			if (onPictureClick != null)
			{
				onPictureClick.onClick(getClickedPhotoPosition());
			}
		}
	};

	private OnPictureClick onPictureClick;

	public void setOnPictureClick(OnPictureClick onPictureClick)
	{
		this.onPictureClick = onPictureClick;
	}

	public interface OnPictureClick
	{
		public void onClick(int position);
	}

	private int clickedPosition = -1;

	/**
	 * 设置当前被点击的的图片在photos中的索引
	 * */
	private void setClickedPhotoPosition(View v, int location)
	{

		int position = (Integer) v.getTag();
		this.clickedPosition = position * 4 + location;
	}

	/**
	 * 获取当前被点击的的图片在photos中的索引
	 * */
	private int getClickedPhotoPosition()
	{

		return this.clickedPosition;
	}
}
