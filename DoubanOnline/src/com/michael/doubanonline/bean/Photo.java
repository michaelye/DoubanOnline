package com.michael.doubanonline.bean;

import java.io.Serializable;


/**
 * 照片
 * 
 * */
public class Photo implements Serializable{
	 private static final long serialVersionUID = 1L;

	/** id */
	public String id;
	/** photo所在网页的链接 */
	public String alt;
	/** 所属相册id */
	public String album_id;
	/** 所属相册名称 */
	public String album_title;
	/** icon */
	public String icon;
	/** thumb */
	public String thumb;
	/** cover */
	public String cover;
	/** image */
	public String image;
	/** 描述 */
	public String desc;
	/** 创建时间 */
	public String created;
	/** 可见性，public, friend, private */
	public String privacy;
	/** 位置 */
	public int position;
	/** 上一张 */
	public String prev_photo;
	/** 下一张 */
	public String next_photo;
	/** 喜欢数 */
	public int liked_count;
	/** 推荐数 */
	public int recs_count;
	/** 回复数 */
	public int comments_count;
	/** Author对象 */
	public Author author;
	/** 当前登录用户是否喜欢该图片 */
	public String liked;
	/** 各个规格尺寸, 有的尺寸是没有的 */
	public String sizes;

	@Override
	public boolean equals(Object o) {
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Photo [id=");
		builder.append(id);
		builder.append(", alt=");
		builder.append(alt);
		builder.append(", album_id=");
		builder.append(album_id);
		builder.append(", album_title=");
		builder.append(album_title);
		builder.append(", icon=");
		builder.append(icon);
		builder.append(", thumb=");
		builder.append(thumb);
		builder.append(", cover=");
		builder.append(cover);
		builder.append(", image=");
		builder.append(image);
		builder.append(", desc=");
		builder.append(desc);
		builder.append(", created=");
		builder.append(created);
		builder.append(", privacy=");
		builder.append(privacy);
		builder.append(", position=");
		builder.append(position);
		builder.append(", prev_photo=");
		builder.append(prev_photo);
		builder.append(", next_photo=");
		builder.append(next_photo);
		builder.append(", liked_count=");
		builder.append(liked_count);
		builder.append(", recs_count=");
		builder.append(recs_count);
		builder.append(", comments_count=");
		builder.append(comments_count);
		builder.append(", author=");
		builder.append(author);
		builder.append(", liked=");
		builder.append(liked);
		builder.append(", sizes=");
		builder.append(sizes);

		return builder.toString();
	}

}
