package jdbcplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseService {
    private String url = "jdbc:mysql://localhost:3306/magazyn?user=root&password=zawisza";
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    public void addWerehouse(String name, String code, String adress) {
        resetService();
        try {
            connection = connectToDatabase();
            preparedStatement = connection.prepareStatement("INSERT INTO warehouse(nazwa,kod,adres) Values(?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, code);
            preparedStatement.setString(3, adress);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseExe.close(preparedStatement, connection);
        }
    }
    public List<Warehouse> getAllWerehouses() {
        resetService();
        List<Warehouse> warehouses = new ArrayList<>();
        try {
            connection = connectToDatabase();
            preparedStatement = connection.prepareStatement("SELECT * FROM warehouse");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                warehouses.add(new Warehouse(
                        resultSet.getInt("id"),
                        resultSet.getString("nazwa"),
                        resultSet.getString("kod"),
                        resultSet.getString("adres")


                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseExe.close(resultSet, preparedStatement, connection);

        }
        return warehouses;

    }
    public void deleteWarehouseById(int warehouseId){
        resetService();
        try {
            connection=connectToDatabase();
            preparedStatement=connection.prepareStatement("DELETE FROM warehouse WHERE id=?");
            preparedStatement.setInt(1,warehouseId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            CloseExe.close(preparedStatement,connection);
        }
    }
    private Connection connectToDatabase() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void resetService() {
        connection = null;
        preparedStatement = null;
        resultSet = null;
    }
}
