import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DemoEx3 {
    public static void main(String[] args) throws Exception {
        // 1. Register the driver.
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. Establish connection
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/DemoDB?useSSL=false","demoUser", "demoPwd");

        // 3. Statement
        Statement st = con.createStatement();

        st.execute("insert into account values(5, 'd', 5000)");

        int n = st.executeUpdate("update account set balance = balance + 100 ");

        System.out.println("No of records affected - " + n);

        ResultSet rs = st.executeQuery("select * from account");

        while(rs.next()) {

            int id = rs.getInt("id");
            String name = rs.getString("name");
            double balance = rs.getDouble("balance");

            System.out.printf("id: %d, name : %s, balance: %f %n", id, name, balance);
        }

        con.close();
    }
}
