package hw8;

import org.sqlite.JDBC;

import java.sql.*;

public class DbAuthService implements AuthService {

    private Connection connection = null;
    private static final String DB_PATH = "/...";

    /*
    private void createDb(){
        try {
            //Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(JDBC.PREFIX + DB_PATH);
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users\n" +
                    "                    (\n" +
                    "                            UserID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                    "                            Login TEXT NOT NULL,\n" +
                    "                            Password TEXT NOT NULL,\n" +
                    "                            Nick TEXT NOT NULL\n" +
                    "                            Active INTEGER\n" +
                    "                    );");

        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        }
    }
    */

    @Override
    public String authByLoginAndPassword(String login, String password) {
        try {
            connection = DriverManager.getConnection(JDBC.PREFIX + DB_PATH);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT Login, Password, Active FROM Users");
            while (rs.next()){
                if (login.equals(rs.getString("Login"))){
                    if (password.equals(rs.getString("Password"))){
                        if (rs.getString("Active").equals("1")) return rs.getString("Nick");
                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            connClose();
        }
        return null;
    }

    @Override
    public void createOrActivateUser(String login, String password, String nick) {
        try {
            connection = DriverManager.getConnection(JDBC.PREFIX + DB_PATH);
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Users SET Active = 1 WHERE UserID = ?");
            ResultSet rs = statement.executeQuery("SELECT * FROM Users");
            while (rs.next()){
                if (nick.equals(rs.getString("Login"))){
                    if (password.equals(rs.getString("Password"))){
                        if (nick.equals(rs.getString("Nick"))){
                            preparedStatement.setInt(1, rs.getInt("UserID"));
                            preparedStatement.executeUpdate();
                            return;
                        } else {
                            System.out.println("invalid nick");
                            return;
                        }
                    } else {
                        System.out.println("Invalid password");
                        return;
                    }
                }
            }
            preparedStatement = connection.prepareStatement("INSERT INTO Users (Login, Password, Nick, Active) " +
                    "VALUES(?, ? , ?, 1");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, nick);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            connClose();
        }
        return;
    }

    @Override
    public boolean deactivateUser(String nick) {
        try {
            connection = DriverManager.getConnection(JDBC.PREFIX + DB_PATH);
            PreparedStatement updPrdStm = connection.prepareStatement("UPDATE Users SET Active = ? WHERE UserID = ?");
            PreparedStatement slctPrdStm = connection.prepareStatement("SELECT UsersID FROM Users WHERE Nick = ?");
            slctPrdStm.setString(1, nick);
            ResultSet resultSet = slctPrdStm.executeQuery();
            updPrdStm.setInt(1, 0);
            updPrdStm.setInt(1, resultSet.getInt("UsersID"));
            int rs = updPrdStm.executeUpdate();
            if (rs != 0) return true;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            connClose();
        }
        return false;
    }

    @Override
    public String nickChange(String thisnick, String newNick){
        try {
            connection = DriverManager.getConnection(JDBC.PREFIX + DB_PATH);
            PreparedStatement updPrdStm = connection.prepareStatement("UPDATE Users SET Nick = ? WHERE UserID = ?");
            PreparedStatement slctPrdStm = connection.prepareStatement("SELECT UserID FROM Users WHERE Nick = ?");
            slctPrdStm.setString(1, thisnick);
            ResultSet resultSet = slctPrdStm.executeQuery();
            updPrdStm.setString(1, newNick);
            updPrdStm.setInt(1, resultSet.getInt("UsersID"));
            updPrdStm.executeUpdate();
            return newNick;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            connClose();
        }
        System.out.println("Nick not changed");
        return thisnick;
    }

    private void connClose(){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }
}
