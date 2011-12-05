package sqlitepokus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SqlLitePokus {
  public static void main(String[] args) throws Exception {
    System.out.println("JEDU");
    Class.forName("org.sqlite.JDBC");
    Connection conn =
      DriverManager.getConnection("jdbc:sqlite:geogetpokus.db3");
    PreparedStatement ps = conn.prepareStatement("select id, name from geocache");

    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      System.out.println(rs.getString(0+1) + " --- " + rs.getString(1+1));
    }
    rs.close();
    conn.close();
  }
}
//public class SqlLitePokus {
//  public static void main(String[] args) throws Exception {
//    System.out.println("JEDU");
//    Class.forName("org.sqlite.JDBC");
//    Connection conn =
//      DriverManager.getConnection("jdbc:sqlite:test.db");
//    Statement stat = conn.createStatement();
//    stat.executeUpdate("drop table if exists people;");
//    stat.executeUpdate("create table people (name, occupation);");
//    PreparedStatement prep = conn.prepareStatement(
//    "insert into people values (?, ?);");
//
//    prep.setString(1, "Gandhi");
//    prep.setString(2, "politics");
//    prep.addBatch();
//    prep.setString(1, "Turing");
//    prep.setString(2, "computers");
//    prep.addBatch();
//    prep.setString(1, "Wittgenstein");
//    prep.setString(2, "smartypants");
//    prep.addBatch();
//
//    conn.setAutoCommit(false);
//    prep.executeBatch();
//    conn.setAutoCommit(true);
//
//    ResultSet rs = stat.executeQuery("select * from people;");
//    while (rs.next()) {
//      System.out.println("name = " + rs.getString("name"));
//      System.out.println("job = " + rs.getString("occupation"));
//    }
//    rs.close();
//    conn.close();
//  }
//}
