import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DemoEx1 {
    public static void main(String[] args) throws Exception {
        // 1. Register the driver.
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. Establish connection
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/DemoDB?useSSL=false&allowPublicKeyRetrieval=true","demoUser", "demoPwd");

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
