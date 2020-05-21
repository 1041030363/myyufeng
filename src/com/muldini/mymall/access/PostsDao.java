package com.muldini.mymall.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.muldini.mymall.common.Posts;
import com.muldini.mymall.common.User;

public class PostsDao {
	 private Connection connection;

	public PostsDao(Connection connection) {
		this.connection = connection;
	}
	
	//创建一个帖子
	 public int createPosts(Posts posts) throws SQLException {
	        int result = 0;
	        String sql = "insert into posts (post_id,theme_id, name,release_time,text,title ) values(?, ?,?,?,?,?)";
	        PreparedStatement statement = null;
	        try {
	            // 创建statement。
	            statement = connection.prepareStatement(sql);
	            
	            statement.setInt(1, posts.getPostId());
	            statement.setInt(2, posts.getThemeId());
	            statement.setString(3,posts.getName());
	            statement.setString(4,posts.getTime());
	            statement.setString(5,posts.getText());
	            statement.setString(6,posts.getTitle());

	            // 执行statement，并取得结果。
	            result = statement.executeUpdate();

	        } finally {
	            // 关闭statement，以释放占用的资源。
	            if (statement != null) {
	                statement.close();
	            }
	        }
	        return result;
	    }
	 //根据	板块id来查帖子
	  public Posts retrievePostsById(int id) throws SQLException {
		  Posts posts = null;
	        String sql = "select post_id,theme_id, name,release_time,text,title from posts where theme_id = ?";
	        PreparedStatement statement = null;
	        try {
	            // 创建statement。
	            statement = connection.prepareStatement(sql);
	            statement.setInt(1, id);

	            // 执行statement，并取得结果。
	            ResultSet resultSet = statement.executeQuery();

	            // 处理结果。
	            while (resultSet.next()) {
	            	posts = new Posts();
	            	posts.setPostId(resultSet.getInt(1));
	            	posts.setThemeId(resultSet.getInt(2));
	            	posts.setName(resultSet.getString(3));
	            	posts.setTime(resultSet.getString(4));
	            	posts.setText(resultSet.getString(5));
	            	posts.setTitle(resultSet.getString(6));
	            }
	        } finally {
	            // 关闭statement，以释放占用的资源。
	            if (statement != null) {
	                statement.close();
	            }
	        }
	        return posts;
	    }
	  
	  
	  //根据用户名来查帖子
	  public Posts retrievePostsByName(String name) throws SQLException {
		  Posts posts = null;
	        String sql = "select post_id,theme_id, name,release_time,text,title from posts where name = ?";
	        PreparedStatement statement = null;
	        try {
	            // 创建statement。
	            statement = connection.prepareStatement(sql);
	            statement.setString(1, name);

	            // 执行statement，并取得结果。
	            ResultSet resultSet = statement.executeQuery();

	            // 处理结果。
	            while (resultSet.next()) {
	            	posts = new Posts();
	            	posts.setPostId(resultSet.getInt(1));
	            	posts.setThemeId(resultSet.getInt(2));
	            	posts.setName(resultSet.getString(3));
	            	posts.setTime(resultSet.getString(4));
	            	posts.setText(resultSet.getString(5));
	            	posts.setTitle(resultSet.getString(6));
	            }
	        } finally {
	            // 关闭statement，以释放占用的资源。
	            if (statement != null) {
	                statement.close();
	            }
	        }
	        return posts;
	    }
	  
	  //根据帖子id删除帖子
	  public int deletePostsById(int post_id) throws SQLException {
	        int result = 0;
	        String sql = "delete from posts where post_id = ?";
	        PreparedStatement statement = null;
	        try {
	            // 创建statement。
	            statement = connection.prepareStatement(sql);
	            statement.setInt(1, post_id);

	            // 执行statement，并取得结果。
	            result = statement.executeUpdate();

	        } finally {
	            // 关闭statement，以释放占用的资源。
	            if (statement != null) {
	                statement.close();
	            }
	        }
	        return result;
	    }
}
