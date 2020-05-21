package com.muldini.mymall.common;

import java.io.Serializable;

public class Posts implements Serializable{
	
	 private int postId; // 帖子id
	 private int themeId; // 板块id
	 private String name ; // 发帖用户名
	 private String time; // 发帖时间
	 private String text ; // 帖子内容
	 private String title ; // 标题
	 public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getThemeId() {
		return themeId;
	}
	public void setThemeId(int themeId) {
		this.themeId = themeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Posts [postId=" + postId + ", themeId=" + themeId + ", name=" + name + ", time=" + time + ", text="
				+ text + ", title=" + title + "]";
	}
	
}
