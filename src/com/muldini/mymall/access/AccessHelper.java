/*
COPYRIGHT (C) 2017 BY MULDINI. ALL RIGHTS RESERVED.
 */

package com.muldini.mymall.access;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 资源访问的帮助类（工具类），用单例设计模式。
 * 
 
 */
public final class AccessHelper {

    private DataSource dataSource; // 数据源

    public static final AccessHelper INSTANCE = new AccessHelper(); // 单例

    /*
     * 私有构造器，以便实现单例模式，即该类只有1个对象（实例）供其他类使用。
     */
    private AccessHelper() {
    }

    /**
     * 获得一个数据库连接，自动提交模式设为false
     * 
     * @return 一个数据库连接对象
     */
    public Connection createConnection() {
        return createConnection(false);
    }

    /**
     * 根据传入的自动提交参数，获得一个数据库连接
     * 
     * @param autoCommit
     *            false表示不自动提交; true表示自动提交
     * 
     * @return 一个数据库连接对象
     */
    public Connection createConnection(boolean autoCommit) {

        Connection connection = null;
        try {
            // 若当前仍未获取数据源对象，则去获取。如此可保证只获取一次，即实现缓存的效果。
            if (dataSource == null) {
                dataSource = getDataSource();
            }
            
            // 创建数据库连接。
            connection = dataSource.getConnection();

            // 设置数据库连接的自动提交模式，默认为true，即开启自动提交。
            connection.setAutoCommit(autoCommit);

        } catch (SQLException e) {
            // 将SQLException转化为RuntimeException，如此，该方法调用者就不需显式地处理该SQLException。
            throw new RuntimeException(e);
        }
        return connection;
    }

    /*
     * 使用JNDI，根据名称，获取数据源。
     */
    private DataSource getDataSource() {
        DataSource dataSource = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/17playDb1");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }

}
