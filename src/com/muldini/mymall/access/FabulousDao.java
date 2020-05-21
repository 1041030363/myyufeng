package com.muldini.mymall.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.muldini.mymall.common.Fabulous;

public class FabulousDao {
	private Connection connection;

	public FabulousDao(Connection connection) {
		this.connection = connection;
	}
	
	//创建点赞或点踩
		public int createFabulous(Fabulous fabulous) throws SQLException {
	        int result = 0;
	        String sql = "insert into fabulous (fid,name,post_id,type ,status,notlike) values(?, ?,?,?,?,?)";
	        PreparedStatement statement = null;
	        try {
	            // 创建statement。
	            statement = connection.prepareStatement(sql);
	            
	            statement.setInt(1, fabulous.getFid()); 
	            statement.setString(2,fabulous.getName());
	            statement.setInt(3, fabulous.getPostId());
	            statement.setInt(4,fabulous.getType());
	            statement.setInt(5,fabulous.getStatus());
	            statement.setInt(6,fabulous.getNotlike());

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
		//根据点赞id删除点赞
		 public int deleteFabulousById(int fid  ) throws SQLException {
		        int result = 0;
		        String sql = "delete from fabulous where fid   = ?";
		        PreparedStatement statement = null;
		        try {
		            // 创建statement。
		            statement = connection.prepareStatement(sql);
		            statement.setInt(1, fid  );

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
