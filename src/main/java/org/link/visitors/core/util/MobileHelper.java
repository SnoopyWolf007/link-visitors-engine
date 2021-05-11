package org.link.visitors.core.util;

import org.link.visitors.core.entity.SysData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author changming.jiang
 */
public class MobileHelper {

    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String JDBC_URL = "jdbc:mysql://%s:3306/mysql";

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private MobileHelper() {
    }

    public static void loadData4File() {

    }

    public static Connection createConnection(String ip, String username, String password) {
        try {
            String jdbcUrl = String.format(JDBC_URL, ip);
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (Exception ignore) {
            return null;
        }
    }

    public List<SysData> handleSysData(List<String> mobileList) {

        return null;
    }

    public void batchInsert(Connection conn, List<SysData> sysDataList) throws SQLException {
        String insertSql = "INSERT INTO `link_visitors`.`sys_data`(`mobile`, `qq`, `isp_type`, `post_code`, `country`, `province`, `city`, `name`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = conn.prepareStatement(insertSql);
        handleInsertSql(preparedStatement, sysDataList);
        preparedStatement.executeBatch();

    }

    public void handleInsertSql(PreparedStatement preparedStatement, List<SysData> sysDataList) throws SQLException {
        String insertSql = "INSERT INTO `link_visitors`.`sys_data`(`mobile`, `qq`, `isp_type`, `post_code`, `country`, `province`, `city`, `name`) VALUES (%s, %s, %s, %s, %s, %s, %s, %s);";
        for (SysData sysData : sysDataList) {
            String sql = String.format(insertSql, sysData.getMobile(), sysData.getQq(), sysData.getIspType(), sysData.getPostCode(),
                    sysData.getCountry(), sysData.getProvince(), sysData.getCity(), sysData.getName());
            preparedStatement.addBatch(sql);
        }
    }

}
