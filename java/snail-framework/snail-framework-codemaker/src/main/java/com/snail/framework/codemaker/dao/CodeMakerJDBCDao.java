package com.snail.framework.codemaker.dao;

import com.snail.framework.codemaker.db.DataBaseTemplate;
import com.snail.framework.codemaker.pojo.ColumnInfo;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author snail
 * @create 2019/8/27.
 **/
@Slf4j
public class CodeMakerJDBCDao extends DataBaseTemplate {

    private String driverClassName;
    private String url ;
    private String userName ;
    private String password;


    public CodeMakerJDBCDao(String driverClassName, String url, String userName, String password) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public List<ColumnInfo> selectList(String dbName , String tableName) {
        return this.query(driverClassName , url , userName , password , createSQL(dbName , tableName));
    }


    @Override
    protected List<ColumnInfo> convert(ResultSet resultSet)  {
        List<ColumnInfo> columnInfos = new ArrayList<>();
        try {
            while (resultSet.next()) {
                ColumnInfo columnInfo = new ColumnInfo();

                columnInfo.setDb(resultSet.getString("db"));
                columnInfo.setTableName(resultSet.getString("tableName"));
                columnInfo.setTableComment(resultSet.getString("tableComment"));
                columnInfo.setColumnName(resultSet.getString("columnName"));
                columnInfo.setDataType(resultSet.getString("dataType"));
                columnInfo.setColLength(resultSet.getInt("colLength"));
                columnInfo.setScale(resultSet.getInt("scale"));
                columnInfo.setColumnComment(resultSet.getString("columnComment"));
                columnInfos.add(columnInfo);
            }
        } catch (Exception e) {
            log.info("异常e:{}" , e);
        }

        return columnInfos;
    }


    String createSQL(String dbName , String tableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("select t.TABLE_SCHEMA db,t.TABLE_NAME tableName,t.TABLE_COMMENT tableComment,c.COLUMN_NAME columnName, ");
        sb.append("c.DATA_TYPE dataType,IFNULL(c.CHARACTER_MAXIMUM_LENGTH,c.NUMERIC_PRECISION) colLength, ");
        sb.append("c.NUMERIC_SCALE scale,  c.COLUMN_COMMENT columnComment ");
        sb.append("from information_schema.TABLES t ");
        sb.append("left join information_schema.COLUMNS c on t.TABLE_SCHEMA=c.TABLE_SCHEMA and t.TABLE_NAME=c.TABLE_NAME ");
        sb.append("where t.TABLE_SCHEMA =").append("\'").append(dbName).append("\'");
        sb.append(" AND t.TABLE_NAME = ").append("\'").append(tableName).append("\'");

        return sb.toString();
    }
}
