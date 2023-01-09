package Model;

import db.DbConnection;
import to.Book;
import to.Member;
import util.Crudutil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookModel {
    public static boolean addBook(Book book) throws ClassNotFoundException, SQLException {
        return Crudutil.execute("INSERT INTO book VALUES(?,?,?,?)",
                book.getId(),
                book.getTittle(),
                book.getAuthor(),
                book.getIsbn()
        );
    }
    public static ArrayList<Book> getAllbooks() throws ClassNotFoundException, SQLException{
        ResultSet rst = DbConnection.getInstance().getConnection()
                .createStatement().executeQuery("SELECT * FROM book");

        ArrayList<Book>customerList=new ArrayList<>();

        while(rst.next()){
            Book book = new Book(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getString(4));
            customerList.add(book);
        }
        return customerList;
    }
    public static String generateNextBookid() throws SQLException, ClassNotFoundException {
        String sql = "SELECT id FROM book ORDER BY id DESC LIMIT 1";
        ResultSet result = Crudutil.execute(sql);

        if (result.next()) {
            return generateNextOrderId(result.getString(1));
        }
        return generateNextOrderId(result.getString(null));
    }

    private static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("B0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "B0" + id;
        }
        return "O001";

    }
    public static boolean deleteBook(String id) throws ClassNotFoundException, SQLException {
        return  DbConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From book where id ='"+id+"'")>0;
    }
    public static ArrayList<String> loadBookids() throws SQLException, ClassNotFoundException {
        String sql = "SELECT id FROM book";
        ResultSet result = Crudutil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }
}
