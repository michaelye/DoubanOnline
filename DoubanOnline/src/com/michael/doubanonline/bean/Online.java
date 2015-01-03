package com.michael.doubanonline.bean;

import java.io.Serializable;



/**
 * 线上活动对象
 * */
public class Online implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** id是String类型的*/
	public String id;
	/** 链接地址*/
	public String alt;
	/** 标题*/
	public String title;
	/** 描述*/
	public String desc;
	/** 标签*/
	public String tags;
	/** 创建时间*/
	public String created;
	/** 开始时间*/
	public String begin_time;
	/** 结束时间*/
	public String end_time;
	/** 相关的链接*/
	public String related_url;
	/** 对应广播的#主题#*/
	public String shuo_topic;
	/** 用户能不能邀请友邻加入*/
	public boolean cascade_invite;
	/** 关联小组的id*/
	public String group_id;
	/** 对应相册的id*/
	public String album_id;
	/** 参加人数*/
	public long participant_count;
	/** 照片数*/
	public long photo_count;
	/** 喜欢数*/
	public long liked_count;
	/** 推荐数*/
	public long recs_count;
	/** icon*/
	public String icon;
	/** thumb*/
	public String thumb;
	/** cover*/
	public String cover;
	/** image*/
	public String image;
	/** 用户*/
	public User owner;
	/** 当前用户是否喜欢*/
	public boolean liked = false;
	/** 当前用户是否参加*/
	public boolean joined = false;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Online [id=");
		builder.append(id);
		builder.append(", alt=");
		builder.append(alt);
		builder.append(", title=");
		builder.append(title);
		builder.append(", desc=");
		builder.append(desc);
		builder.append(", tags=");
		builder.append(tags);
		builder.append(", created=");
		builder.append(created);
		builder.append(", begin_time=");
		builder.append(begin_time);
		builder.append(", end_time=");
		builder.append(end_time);
		builder.append(", related_url=");
		builder.append(related_url);
		builder.append(", shuo_topic=");
		builder.append(shuo_topic);
		builder.append(", cascade_invite=");
		builder.append(cascade_invite);
		builder.append(", group_id=");
		builder.append(group_id);
		builder.append(", album_id=");
		builder.append(album_id);
		builder.append(", participant_count=");
		builder.append(participant_count);
		builder.append(", photo_count=");
		builder.append(photo_count);
		builder.append(", liked_count=");
		builder.append(liked_count);
		builder.append(", recs_count=");
		builder.append(recs_count);
		builder.append(", icon=");
		builder.append(icon);
		builder.append(", thumb=");
		builder.append(thumb);
		builder.append(", cover=");
		builder.append(cover);
		builder.append(", image=");
		builder.append(image);
		builder.append(", owner=");
		builder.append(owner);
		builder.append(", liked=");
		builder.append(liked);
		builder.append(", joined=");
		builder.append(joined);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public boolean equals(Object o) {
		return false;
	}

}
