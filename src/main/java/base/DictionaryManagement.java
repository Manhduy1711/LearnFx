package base;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DictionaryManagement {
    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=Dic;"
            + "user=duy;"
            + "password=12345;";
    public static ObservableList<Word> dbFetchAll() {
        ObservableList<Word> list = FXCollections.observableArrayList();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            c = DriverManager.getConnection(url);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "SELECT * FROM dbo.N3  ORDER BY [STT]";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Word word = new Word(rs.getString("kanji"),
                        rs.getString("Nghia"),
                        rs.getString("Hiragana"),
                        rs.getString("HanViet"),
                        rs.getInt("isClick"),
                        rs.getInt("STT"),
                        rs.getInt("history")
                );
                list.add(word);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public static ObservableList<Word> dbSearchWord(String keyWord) {
        ObservableList<Word> list = FXCollections.observableArrayList();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            c = DriverManager.getConnection(url);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "SELECT * FROM dbo.N3  WHERE kanji like N" + keyWord + " ORDER BY [STT]";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Word word = new Word(rs.getString("kanji"),
                        rs.getString("Nghia"),
                        rs.getString("Hiragana"),
                        rs.getString("HanViet"),
                        rs.getInt("isClick"),
                        rs.getInt("STT"),
                        rs.getInt("history")
                );
                list.add(word);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public static ObservableList<Word> dbSearchWordHistory(String keyWord) {
        ObservableList<Word> list = FXCollections.observableArrayList();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            c = DriverManager.getConnection(url);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "SELECT * FROM dbo.N3  WHERE kanji like N" + keyWord + " ORDER BY [STT]";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Word word = new Word(rs.getString("kanji"),
                        rs.getString("Nghia"),
                        rs.getString("Hiragana"),
                        rs.getString("HanViet"),
                        rs.getInt("isClick"),
                        rs.getInt("STT"),
                        rs.getInt("history")
                );
                if (word.getHistory() == 1) {
                    list.add(word);
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public static ObservableList<Word> dbSearchWordBookMark(String keyWord) {
        ObservableList<Word> list = FXCollections.observableArrayList();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            c = DriverManager.getConnection(url);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "SELECT * FROM dbo.N3  WHERE kanji like N" + keyWord + " ORDER BY [STT]";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Word word = new Word(rs.getString("kanji"),
                        rs.getString("Nghia"),
                        rs.getString("Hiragana"),
                        rs.getString("HanViet"),
                        rs.getInt("isClick"),
                        rs.getInt("STT"),
                        rs.getInt("history")
                );
                if (word.getIsClick() == 1) {
                    list.add(word);
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public static void dbDeleteWord(int stt) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            c = DriverManager.getConnection(url);

            stmt = c.createStatement();
            String sql = "DELETE FROM dbo.N3  where STT = " + stt;
            int rs = stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void dbSetIsClick(int stt, int status) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            c = DriverManager.getConnection(url);

            stmt = c.createStatement();
            String sql = "UPDATE dbo.N3 SET isClick = " + status + " WHERE STT = "  + stt;
            int rs = stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void dbUpdate(String word, String furi, String cv, String explain, int stt) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            c = DriverManager.getConnection(url);

            stmt = c.createStatement();
            String sql = "UPDATE dbo.N3 SET kanji = N'" + word + "', Hiragana = N'"+ furi +"', HanViet = N'" + cv + "', Nghia = N'" + explain +"' WHERE STT = "  + stt;
            int rs = stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void dbInsert(String word, String furi, String cv, String explain) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            c = DriverManager.getConnection(url);

            stmt = c.createStatement();
            String sql = "INSERT  INTO dbo.N3 (kanji, HanViet, Hiragana, Nghia, isClick) values (N'" + word + "',N'" + cv  + "',N'" + furi  + "',N'" + explain +"', 0)";
            int rs = stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static ObservableList<Word> dbFetchBookMark() {
        ObservableList<Word> list = FXCollections.observableArrayList();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            c = DriverManager.getConnection(url);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "SELECT * FROM dbo.N3 ORDER BY [STT]";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Word word = new Word(rs.getString("kanji"),
                        rs.getString("Nghia"),
                        rs.getString("Hiragana"),
                        rs.getString("HanViet"),
                        rs.getInt("isClick"),
                        rs.getInt("STT"),
                        rs.getInt("history")
                );
                if (word.getIsClick() == 1) {
                    list.add(word);
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public static ObservableList<Word> dbFetchHistory() {
        ObservableList<Word> list = FXCollections.observableArrayList();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            c = DriverManager.getConnection(url);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "SELECT * FROM dbo.N3 ORDER BY [history]";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Word word = new Word(rs.getString("kanji"),
                        rs.getString("Nghia"),
                        rs.getString("Hiragana"),
                        rs.getString("HanViet"),
                        rs.getInt("isClick"),
                        rs.getInt("STT"),
                        rs.getInt("history")
                );
                if (word.getHistory() != 0) {
                    list.add(word);
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public static void dbSetHistory(int stt, int status) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            c = DriverManager.getConnection(url);

            stmt = c.createStatement();
            String sql = "UPDATE dbo.N3 SET history = " + status + " WHERE STT = "  + stt;
            int rs = stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static int dbGetHistory() {
        Connection c = null;
        Statement stmt = null;
        int maxHistory = 0;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            c = DriverManager.getConnection(url);

            stmt = c.createStatement();
            String sql = "SELECT MAX(history) from dbo.N3";
            stmt.executeQuery(sql);
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                maxHistory = rs.getInt(1);
            }
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return maxHistory;
    }
}
