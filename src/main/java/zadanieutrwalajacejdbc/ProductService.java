package zadanieutrwalajacejdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private String url = "jdbc:mysql://localhost:3306/magazyn?user=root&password=zawisza";
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void addProduct(Product product) {
        resetService();
        try {
            connection = connectToDatabase();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO product(warehouse_id,nazwa,producent,count) VALUES(?,?,?,?)");
            preparedStatement.setInt(1, product.getWarehouseId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getProducent());
            preparedStatement.setInt(4, product.getCount());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseExe.close(preparedStatement, connection);
        }
    }

    public void addProductToWarehouse(Product product, int warehouseId) {
        resetService();
        try {

            connection = connectToDatabase();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO product(warehouse_id,nazwa,producent,count) VALUES(?,?,?,?)");


            preparedStatement.setInt(1, warehouseId);
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getProducent());
            preparedStatement.setInt(4, product.getCount());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseExe.close(preparedStatement, connection);
        }
    }

    public void addProductsToWarehouse(List<Product> product, int warehouseId) {
        resetService();
        try {

            connection = connectToDatabase();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO product(warehouse_id,nazwa,producent,count) VALUES(?,?,?,?)");
            for (Product product1 : product) {


                preparedStatement.setInt(1, warehouseId);
                preparedStatement.setString(2, product1.getName());
                preparedStatement.setString(3, product1.getProducent());
                preparedStatement.setInt(4, product1.getCount());
                preparedStatement.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseExe.close(preparedStatement, connection);
        }
    }

    public List<Product> getAllProducts() {
        resetService();
        List<Product> products = new ArrayList<>();
        try {
            connection = connectToDatabase();
            preparedStatement = connection.prepareStatement("SELECT * FROM product");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getInt("warehouse_id"),
                        resultSet.getString("nazwa"),
                        resultSet.getString("producent"),
                        resultSet.getInt("count")


                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseExe.close(resultSet, preparedStatement, connection);
        }
        return products;

    }

    public Product getProductById(int id) {
        resetService();
        Product product = null;
        try {
            connection = connectToDatabase();
            preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            product = new Product(
                    resultSet.getInt("id"),
                    resultSet.getInt("warehouse_id"),
                    resultSet.getString("nazwa"),
                    resultSet.getString("producent"),
                    resultSet.getInt("count")

            );


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseExe.close(resultSet, preparedStatement, connection);
        }
        return product;
    }

    public List<Product> getProductsByWarehouse(int warehouseId) {
        resetService();
        List<Product> products = new ArrayList<>();
        try {
            connection = connectToDatabase();
            preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE warehouse_id=?");
            preparedStatement.setInt(1, warehouseId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getInt("warehouse_id"),
                        resultSet.getString("nazwa"),
                        resultSet.getString("producent"),
                        resultSet.getInt("count")


                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseExe.close(resultSet, preparedStatement, connection);
        }
        return products;

    }

    public void moveProduct(int product_Id, int warehouseId) {
        Product product = getProductById(product_Id);
        addProductToWarehouse(product, warehouseId);
        deleteProductFromWarehouse(product_Id);
    }

    public void updateCountOfProduct(int id, int count) {
        resetService();

        try {
            connection = connectToDatabase();
            preparedStatement = connection.prepareStatement("UPDATE product SET count=? WHERE id=?");
            preparedStatement.setInt(1, count);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseExe.close(preparedStatement, connection);
        }


    }

    public void deleteAllProductsFromWarehouse(int warehouseId) {
        resetService();

        try {
            connection = connectToDatabase();
            preparedStatement = connection.prepareStatement("DELETE FROM product WHERE warehouse_id=?");
            preparedStatement.setInt(1, warehouseId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseExe.close(preparedStatement, connection);
        }


    }

    public void deleteProductFromWarehouse(int productId) {
        resetService();

        try {
            connection = connectToDatabase();
            preparedStatement = connection.prepareStatement("DELETE FROM product WHERE id=?");
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseExe.close(preparedStatement, connection);
        }


    }


    private Connection connectToDatabase() {

        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void resetService() {
        connection = null;
        preparedStatement = null;
        resultSet = null;
    }


}
