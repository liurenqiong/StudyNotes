package com.snail.framework.codemaker.db;

import com.snail.framework.codemaker.pojo.ColumnInfo;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author snail
 * @create 2019/8/27.
 **/
@Slf4j
public abstract class DataBaseTemplate {

    // JDBC
    protected List<ColumnInfo> query(String driverClassName , String url , String userName , String password , String sql) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ColumnInfo> columnInfo = null;
        try {
            log.info("执行的sql为:{}" , sql);
            conn  = getConnection(driverClassName , url , userName , password);
            ps = conn.prepareStatement(sql);
            rs =  ps.executeQuery();
            columnInfo =  convert(rs);
        } catch (Exception e) {
            log.info("执行sql:{},出现异常:{}" , sql , e);
        } finally {
            close(rs , ps , conn);
        }

        return columnInfo;
    }

    protected abstract List<ColumnInfo> convert(ResultSet rs);


    /**
     * 关闭连接
     * @param rs
     * @param ps
     * @param conn
     */
    private void close(ResultSet rs, PreparedStatement ps, Connection conn) {

       try {
           if(rs != null) {
               rs.close();
           }

           if(ps != null) {
               ps.close();
           }

           if(conn != null) {
               ps.close();
           }
       } catch (Exception e) {
           log.error("关闭连接出现异常，e:{}" , e);
       }
    }


    /**
     * 获取连接
     * @param driverClassName
     * @param url
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    private Connection getConnection(String driverClassName , String url , String userName , String password) throws Exception {

        // 1.加载驱动
        Class.forName(driverClassName);

        // 连接数据库
        return DriverManager.getConnection(url , userName , password);
    }

    /**
     *
     * @param connection
     * @param sql
     * @return
     * @throws Exception
     */
    private PreparedStatement getStatement(Connection connection , String sql) throws Exception {
        return connection.prepareStatement(sql);
    }
}
