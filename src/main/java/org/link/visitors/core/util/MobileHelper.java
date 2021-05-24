package org.link.visitors.core.util;

import org.link.visitors.core.entity.SysData;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author link-kit
 */
public class MobileHelper {

    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String JDBC_URL = "jdbc:mysql://%s:3306/link_visitors?useUnicode=true&zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8";

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Connection conn = createConnection("127.0.0.1", "root", "123456");
        bigFileRead0(conn);
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

    public static void batchInsert(Connection conn, List<SysData> sysDataList) throws SQLException {
        String insertSql = "INSERT INTO `link_visitors`.`sys_data`(`mobile`, `qq`, `isp_type`, `post_code`, `country`, `province`, `city`, `name`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = conn.prepareStatement(insertSql);
        handleInsertSql(preparedStatement, sysDataList);
        preparedStatement.executeBatch();
    }

    public static void handleInsertSql(PreparedStatement preparedStatement, List<SysData> sysDataList) throws SQLException {
        String insertSql = "INSERT INTO `link_visitors`.`sys_data`(`mobile`, `qq`, `isp_type`, `post_code`, `country`, `province`, `city`, `name`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');";
        for (SysData sysData : sysDataList) {
            String sql = String.format(insertSql, sysData.getMobile(), sysData.getQq(), sysData.getIspType(), sysData.getPostCode(),
                    sysData.getCountry(), sysData.getProvince(), sysData.getCity(), sysData.getName());
            preparedStatement.addBatch(sql);
        }
    }

    public static void bigFileRead0(Connection conn) throws IOException {
        String filePath = System.getProperty("user.dir") + "/mobile.txt";
        RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "r");

        String line = null;
        int count = 0;
        List<SysData> sysDataList = new ArrayList<>();
        long bt = System.currentTimeMillis();
        while ((line = randomAccessFile.readLine()) != null) {
            count += 1;
            System.out.println(count);
            String[] split = line.split("---");
            if (split.length >= 2) {
                String mobile = split[0];
                String qq = split[1];

                // 查询号段信息
                String province = null;
                String city = null;
                String isp = null;
                try {
                    String pre = mobile.substring(0, 7);
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sys_phone_pre WHERE `pre`='" + pre + "'");
                    ResultSet resultSet = stmt.executeQuery();
                    while (resultSet.next()) {
                        province = resultSet.getString("province");
                        city = resultSet.getString("city");
                        isp = resultSet.getString("isp");
                    }
                    resultSet.close();
                    stmt.close();
                } catch (Exception ignore) {

                }
                SysData sysData = new SysData();
                sysData.setMobile(mobile);
                sysData.setQq(qq);
                sysData.setIspType(isp == null ? "-" : isp);
                sysData.setPostCode("-");
                sysData.setCountry("中国");
                sysData.setProvince(province == null ? "-" : province);
                sysData.setCity(city == null ? "-" : city);
                sysData.setName("-");
                sysDataList.add(sysData);

                try {
                    batchInsert(conn, sysDataList);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    sysDataList.clear();
                }
            }
        }
        long et = System.currentTimeMillis();
        System.out.println((et - bt) + " ms");
    }

}
