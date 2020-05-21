package com.muldini.mymall.common;

import java.io.Serializable;

public class Comment implements Serializable{
	private int cid; // 回帖序号
	private int postId; // 帖子id，
	private String name ; // 回帖用户名
	private String time; // 回帖时间
	private String text ; // 回帖内容
	private int grade ; // 评论上级
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
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
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "Comment [cid=" + cid + ", postId=" + postId + ", name=" + name + ", time=" + time + ", text=" + text
				+ ", grade=" + grade + "]";
	}
	
}
