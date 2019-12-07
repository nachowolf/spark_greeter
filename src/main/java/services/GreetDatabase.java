package services;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class GreetDatabase {
    private Connection conn;
    private final String host = "jdbc:postgresql://localhost/Greets";
    private final String user = "coder";
    private final String pw = "coder";

    //    ##########| CREATE |##########
         private final String ADD_NEW_USER_SQL = "INSERT into greeted (username, greet_count) values (?, 0)";
         private PreparedStatement psAddNewUser;

    //    ##########| READ |##########
          private final String GET_ID_OF_USER_SQL = "SELECT id from greeted where username = ?";
          private PreparedStatement psGetIdOfUser;

          private final String GET_TOTAL_GREETS_OF_USER_SQL = "SELECT greet_count from greeted where username = ?";
          private PreparedStatement psGetTotalGreetsOfUser;

          private final String GET_TOTAL_USERS_GREETS_SQL = "SELECT count(*) from greeted";
          private PreparedStatement psGetUsersTotalGreets;

          private final String GET_ALL_USERS_SQL = "Select * from greeted";
          private PreparedStatement psGetAllUsers;


    //    ##########| UPDATE |##########

          private final String UPDATE_GREET_COUNT_SQL = "UPDATE greeted set greet_count = greet_count + 1 where username = ?";
          private PreparedStatement psUpdateGreetCount;

//          private final String RESET_ID_SERIAL_COUNT_SQL = "ALTER SEQUENCE greeted_id_seq RESTART with 1";
//          private PreparedStatement psRestartSerialId;

    //    ##########| DELETE |##########
          private final String TRUNCATE_TABLE_SQL = "TRUNCATE table greeted";
          private PreparedStatement psTruncateTable;

          private final String DELETE_USER_SQL = "Delete from greeted where username = ?";
          private PreparedStatement psDeleteUser;

    public GreetDatabase(){
        try {
            conn = DriverManager.getConnection(host, user, pw);
            psAddNewUser = conn.prepareStatement(ADD_NEW_USER_SQL);
            psGetIdOfUser = conn.prepareStatement(GET_ID_OF_USER_SQL);
            psGetTotalGreetsOfUser = conn.prepareStatement(GET_TOTAL_GREETS_OF_USER_SQL);
            psGetUsersTotalGreets = conn.prepareStatement(GET_TOTAL_USERS_GREETS_SQL);
            psUpdateGreetCount = conn.prepareStatement(UPDATE_GREET_COUNT_SQL);
            psGetAllUsers = conn.prepareStatement(GET_ALL_USERS_SQL);
            psTruncateTable = conn.prepareStatement(TRUNCATE_TABLE_SQL);
            psDeleteUser = conn.prepareStatement(DELETE_USER_SQL);

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public boolean exists(String name){
        boolean result = false;
        try {
            psGetIdOfUser.setString(1, name);
            ResultSet rs = psGetIdOfUser.executeQuery();
            result = rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }

    public void addUser(String name){
        try {
            psAddNewUser.setString(1, name);
            psAddNewUser.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getTotalGreets(){
        int total = 0;
        try {
            ResultSet rs = psGetAllUsers.executeQuery();
            while (rs.next()){
                total += rs.getInt("greet_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return total;
        }

    }

    public int getTotalGreets(String name){
        int total = 0;
        try {
            psGetTotalGreetsOfUser.setString(1, name);
            ResultSet rs = psGetTotalGreetsOfUser.executeQuery();
            rs.next();
            total = rs.getInt("greet_count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return total;
        }
    }

    public int getTotalUsers(){
        int total = 0;
        try {
            ResultSet rs = psGetUsersTotalGreets.executeQuery();
            rs.next();
            total = rs.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return total;
        }
    }

    public void incrementGreets(String name){
        try {
            psUpdateGreetCount.setString(1, name);
            psUpdateGreetCount.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Integer> getUsers(){
        Map<String, Integer> result = new HashMap<>();

        try {
            ResultSet rs = psGetAllUsers.executeQuery();
            while (rs.next()){
                result.put(rs.getString("username"), rs.getInt("greet_count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }

    public void clear(){
        try {
            psTruncateTable.execute();
            Statement statement = conn.createStatement();
            statement.addBatch("ALTER SEQUENCE greeted_id_seq RESTART with 1");
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String name){
        try {
            psDeleteUser.setString(1, name);
            psDeleteUser.execute();
            Statement statement = conn.createStatement();
            statement.addBatch("ALTER SEQUENCE greeted_id_seq RESTART with 1");
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
