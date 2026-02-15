import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.Scanner;

public class DemoEx6 {
    public static void main(String[] args) throws Exception {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/DemoDB?useSSL=false");
        dataSource.setUsername("demoUser");
        dataSource.setPassword("demoPwd");
        dataSource.setMaxTotal(10);
        dataSource.setMaxIdle(2);
        dataSource.setValidationQuery("select 1");

        Connection con = dataSource.getConnection();

        // 3. Statement
        Statement st = con.createStatement();

        // 4. Execute the command
        ResultSet rs = st.executeQuery("select * from account");

        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            double balance = rs.getDouble(3);

            System.out.printf("id :%d , name: %s, balance: %f %n", id, name, balance);
        }

        con.close();
    }
}
