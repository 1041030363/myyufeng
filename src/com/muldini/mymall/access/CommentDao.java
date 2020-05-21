package com.muldini.mymall.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.muldini.mymall.common.Comment;
import com.muldini.mymall.common.Posts;

public class CommentDao {
	private Connection connection;

	public CommentDao(Connection connection) {
		this.connection = connection;
	}
	//创建一条评论
	public int createComment(Comment comment) throws SQLException {
        int result = 0;
        String sql = "insert into comment (cid ,post_id,name,comment_time,text,grade ) values(?, ?,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            // 创建statement。
            statement = connection.prepareStatement(sql);
            
            statement.setInt(1, comment.getCid());
            statement.setInt(2, comment.getPostId());
            statement.setString(3,comment.getName());
            statement.setString(4,comment.getTime());
            statement.setString(5,comment.getText());
            statement.setInt(6,comment.getGrade());

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
	//根据帖子id查看全部评论
	 public Comment retrieveCommentById(int post_id) throws SQLException {
		 Comment comment = null;
	        String sql = "select cid ,post_id ,name,comment_time,text,grade from comment where post_id = ?";
	        PreparedStatement statement = null;
	        try {
	            // 创建statement。
	            statement = connection.prepareStatement(sql);
	            statement.setInt(1, post_id);

	            // 执行statement，并取得结果。
	            ResultSet resultSet = statement.executeQuery();

	            // 处理结果。
	            while (resultSet.next()) {
	            	comment = new Comment();
	            	comment.setCid(resultSet.getInt(1));
	            	comment.setPostId(resultSet.getInt(2));
	            	comment.setName(resultSet.getString(3));
	            	comment.setTime(resultSet.getString(4));
	            	comment.setText(resultSet.getString(5));
	            	comment.setGrade(resultSet.getInt(6));
	            }
	        } finally {
	            // 关闭statement，以释放占用的资源。
	            if (statement != null) {
	                statement.close();
	            }
	        }
	        return comment;
	    }
	 //根据评论id删除评论
	  public int deleteCommentById(int cid ) throws SQLException {
	        int result = 0;
	        String sql = "delete from comment where cid  = ?";
	        PreparedStatement statement = null;
	        try {
	            // 创建statement。
	            statement = connection.prepareStatement(sql);
	            statement.setInt(1, cid );

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
