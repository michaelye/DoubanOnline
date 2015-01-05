package com.michael.doubanonline.bean;

import java.io.Serializable;

/**
 * 评论
 * */
public class Comments extends BeanBase implements Serializable
{

	private static final long serialVersionUID = 1L;

	/** id是String类型的 */
	public String id;
	/** 创建时间 */
	public String created;
	/** 评论内容 */
	public String content;
	/** 用户对象 */
	public User author;

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Online [id=");
		builder.append(id);
		builder.append("created=");
		builder.append(created);
		builder.append("content=");
		builder.append(content);
		builder.append("author=");
		builder.append(author);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public boolean equals(Object o)
	{
		return false;
	}

}
