import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DemoEx5 {
    public static void main(String[] args) throws Exception {
        // 1. Register the driver.
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. Establish connection
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/DemoDB?useSSL=false","demoUser", "demoPwd");

        con.setAutoCommit(false);

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the source account id: ");
        int sourceId = scan.nextInt();

        System.out.println("Enter the destination account id: ");
        int destId = scan.nextInt();

        System.out.println("Enter the amount to transfer: ");
        double amount = scan.nextDouble();

        PreparedStatement ps =
                con.prepareStatement(
                        "update account "
                                + "set balance = balance - ? "
                                + "where id = ?");

        // Debit
        ps.setDouble(1, amount);
        ps.setInt(2, sourceId);

        int n = ps.executeUpdate();

        // Credit
        ps.setDouble(1, - amount);
        ps.setInt(2, destId);

        int m = ps.executeUpdate();

        if (n == 1 && m == 1) {
            System.out.println("Transaction Successful hence commiting the transaction.");
            // commit
            con.commit();
        } else {
            System.out.println("Transaction failed hence rolling back the transaction.");
            // rollback
            con.rollback();
        }

        con.close();
    }
}
