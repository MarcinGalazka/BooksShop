import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.*;

public class Library {

    Connection connection;

    public Library(){
        try {
            this.connection= DriverManager.getConnection(
                    "jdbc:mysql://localhost/library?useUnicode=true&useJDBCCompliantTimezoneShift=" +
                            "true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createTable(){
        try {
            Statement statement = connection.createStatement();
            String dropIfExists= "drop table if exists books2";

            statement.executeUpdate(dropIfExists);

            String createTable=
                    "create table if not exists books2 (\n" +
                            "id int not null auto_increment,\n" +
                            "title varchar(255),\n" +
                            "author varchar(255),\n" +
                            "price decimal(10,2), \n" +
                            "quantity int,\n" +
                            "primary key (id)\n" +
                            ");";
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void addBook(String title, String author, float price, int quantity){
        String insertBook= "INSERT INTO books2(title, author, price, quantity) values (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertBook);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setFloat(3, price);
            preparedStatement.setInt(4, quantity);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBooks(){
        try {
            Statement statement = connection.createStatement();
            String deleteBokks ="delete from books2";
            statement.executeUpdate(deleteBokks);
            showBooks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(String title, int quantity){
        try {
            String deleteBook = "update books2 set quantity= (quantity - ?) where title = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteBook);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setString(2, title);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setDiscount(String title, int discount){

        try {
            String setDiscount = "update books2 set price=(price*(1-?/100)) where title =? ";
            PreparedStatement preparedStatement = connection.prepareStatement(setDiscount);
            preparedStatement.setInt(1, discount);
            preparedStatement.setString(2, title);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
      ;
    }

    public void setPrice(String title, BigDecimal newPrice){
        try {
            String deleteBook = "update books2 set price= ? where title =? ";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteBook);
            preparedStatement.setBigDecimal(1, newPrice);
            preparedStatement.setString(2, title);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showBooks(){
        String showRecords = "select * from books2";
        try {
            Statement  statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(showRecords);
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                BigDecimal price = resultSet.getBigDecimal("price");

                int quantity = resultSet.getInt("quantity");
                System.out.println(title + " by " + author + " - " + price + " PLN. " +quantity + " szt.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
