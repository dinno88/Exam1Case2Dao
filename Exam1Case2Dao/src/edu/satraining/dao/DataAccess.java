package edu.satraining.dao;
import edu.satraining.model.Book;
import java.sql.SQLException;
import java.util.List;

public interface DataAccess {
	String DRIVER = "org.postgresql.Driver";
	String DB_URL = "jdbc:postgresql://localhost/postgres";
	String USER = "postgres";
	String PASS = "127001";

		int insert(Book book) throws SQLException;
		int update(Book book) throws SQLException;
		int delete(String isbn) throws SQLException;
		List<Book> search() throws SQLException;
		Book findById(String isbn) throws SQLException;
}
