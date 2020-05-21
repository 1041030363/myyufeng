package com.muldini.mymall.common;

import java.io.Serializable;

public class Fabulous implements Serializable{
	private int fid; // 点赞id
	private String name ; // 用户名
	private int postId; // 帖子id
	private int type ; // 点赞类型
	private int status ; // 点赞状态
	private int notlike ; // 点踩状态 
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getNotlike() {
		return notlike;
	}
	public void setNotlike(int notlike) {
		this.notlike = notlike;
	}
	@Override
	public String toString() {
		return "Fabulous [fid=" + fid + ", name=" + name + ", postId=" + postId + ", type=" + type + ", status="
				+ status + ", notlike=" + notlike + "]";
	}
	
}
