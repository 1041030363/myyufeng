/*
COPYRIGHT (C) 2014-2015 BY MULDINI. ALL RIGHTS RESERVED.
 */

package com.muldini.mymall.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.muldini.mymall.common.User;

/**
 * 数据访问对象Data Access Object for 用户。
 * 
 * 
 */

public final class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * 创建用户。
     * 
     * @param user
     *            待创建的用户信息
     * @return 创建的用户记录的主键值
     * @throws SQLException
     *             当数据库访问出现异常时
     */
    public int createUser(User user) throws SQLException {
        int result = 0;
        String sql = "insert into user (name, password) values(?, ?)";
        PreparedStatement statement = null;
        try {
            // 创建statement。
            statement = connection.prepareStatement(sql);
            int i = 1;
            statement.setString(i++, user.getName());
            statement.setString(i, user.getPassword());

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

    /**
     * 根据用户id取得用户记录。
     * 
     * @param id
     *            用户id
     * @return 取得的用户
     * @throws SQLException
     *             当数据库访问出现异常时
     */
    public User retrieveUserById(String id) throws SQLException {
        User user = null;
        String sql = "select name, password from user where name = ?";
        PreparedStatement statement = null;
        try {
            // 创建statement。
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);

            // 执行statement，并取得结果。
            ResultSet resultSet = statement.executeQuery();

            // 处理结果。
            while (resultSet.next()) {
                user = new User();
                user.setName(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
            }
        } finally {
            // 关闭statement，以释放占用的资源。
            if (statement != null) {
                statement.close();
            }
        }
        return user;
    }
}
