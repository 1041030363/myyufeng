/*
COPYRIGHT (C) 2017 BY MULDINI. ALL RIGHTS RESERVED.
 */

package com.muldini.mymall.biz.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.muldini.mymall.access.AccessHelper;
import com.muldini.mymall.access.UserDao;
import com.muldini.mymall.common.User;

/**
 * 用户服务类。用户服务类的实例化采用单例模式
 * 

 */

public final class UserService {

    /**
     * UserService实例，单例模式
     */
    public static final UserService INSTANCE = new UserService();

    private AccessHelper accessHelper = AccessHelper.INSTANCE; // 资源访问助手

    private UserService() {
    }

    /**
     * 创建User，若User已存在，则返回false。
     * 
     * @param user
     *            User对象，包含id和password属性值。
     * 
     * @return true表示成功；false表示失败。
     */
    public boolean createUser(User user) {

        // 创建数据库连接，并关闭自动提交（autoCommit）。
        Connection connection = accessHelper.createConnection(false);

        // 创建DAO对象。
        UserDao userDao = new UserDao(connection);

        try {
            // 判断是否有同名的user，若有则返回false。
            User existingUser = userDao.retrieveUserById(user.getName());
            if (existingUser == null) {
                userDao.createUser(user);
            } else {
                return false;
            }

            // 提交事务。
            connection.commit();

        } catch (SQLException e) {

            // 若抛出异常，需回滚事务，最好如下显式地回滚。
            rollback(connection);

            // 将SQLException转化为RuntimeException，如此，该方法调用者就不需显式地处理该SQLException。
            throw new RuntimeException(e);

        } finally {
            // 关闭连接，以释放占用的资源。
            close(connection);
        }
        return true;
    }

    /**
     * 用户登录
     * 
     * @param user
     *            包含用户信息的用户类
     * @return 登录结果的HashMap，两个key，1是STATUS_CODE，2是MSG
     *         （11:表示用户不存在，12:密码不正确，10:登录成功）
     */
    public Map login(User user) {
        Map result = new HashMap();
        Connection connection = accessHelper.createConnection();
        UserDao userDao = new UserDao(connection);
        try {
            User existingUser = userDao.retrieveUserById(user.getName());
            if (existingUser == null) {
                result.put("STATUS_CODE", "11");
                result.put("MSG", "用户名不存在");
            } else if (!existingUser.getPassword().equals(user.getPassword())) {
                result.put("STATUS_CODE", "12");
                result.put("MSG", "密码不正确");
            } else {
                result.put("STATUS_CODE", "10");
                result.put("MSG", "登录成功");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection);
        }
        return result;
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
