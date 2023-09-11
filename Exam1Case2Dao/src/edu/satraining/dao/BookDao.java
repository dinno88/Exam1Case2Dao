package edu.satraining.dao;
import edu.satraining.model.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements DataAccess{
	private Connection con = null;
    private Statement stmt = null;
    private ResultSet resultSet = null;

	public int insert(Book book){
		int result = 0;
		String insertSql = "insert into book (isbn, title, publisher, price, page, created_by, created_time)" +
		"values ('"+book.getIsbn()+"', '"+book.getTitle()+"', '"+book.getPublisher()+"', '"+book.getPrice()+"','"+book.getPage()+"', 'Admin', current_timestamp);";
		try {
            // Create Connection to Database
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.printf("Connection to Database is successfully...%n");
            // Create Statement to execute query
            stmt = con.createStatement();
            System.out.printf("Create Statement success..%n");
            // Execute query
            result = stmt.executeUpdate(insertSql);
            System.out.printf("Execute SQL Success...%n");
        } catch (SQLException e) {
            System.out.printf("Error in database execution...%n");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.printf("Unknown Error accours...%n");
            e.printStackTrace();
        }  finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                System.out.printf("Error on Closing database resource....%n");
            }
        }
        return result;
    }

	public int update(Book book){
        int result = 0;
        String updateSql = "update " + 
        "book " + "set " + 
        " title = '"+book.getTitle()+"'," +
        " publisher = '"+book.getPublisher()+"'," +
        " price = '"+book.getPrice()+"'," +
        " page = '"+book.getPage()+"'," +
        " updated_by = 'Admin'," +
        " updated_time = current_timestamp" +
        " where " + 
        " isbn = '"+book.getIsbn()+"';" ;

        // System.out.printf("%s%n", updateSql);
        try {
            // Create Connection to Database
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.printf("Connection to Database is successfully...%n");
            // Create Statement to execute query
            stmt = con.createStatement();
            System.out.printf("Create Statement success..%n");
            // Execute query
            result = stmt.executeUpdate(updateSql);
            System.out.printf("Execute SQL Success...%n");
        } catch (SQLException e) {
            System.out.printf("Error in database execution...%n");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.printf("Unknown Error accours...%n");
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                System.out.printf("Error on Closing database resource....%n");
            }
        }
        return result;
    }

	public int delete(String isbn){
        int result = 0;
        String deleteSql = "delete from book where isbn = '"+isbn+"'";
                try {
            // Create Connection to Database
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.printf("Connection to Database is successfully...%n");
            // Create Statement to execute query
            stmt = con.createStatement();
            System.out.printf("Create Statement success..%n");
            // Execute query
            result = stmt.executeUpdate(deleteSql);
            System.out.printf("Execute SQL Success...%n");
            // Check Execute Result
            if (result > 0) {
                System.out.printf("Success Delete Data with isbn = '%s' to table book...%n", isbn);
            } else {
                System.out.printf("There is no data with isbn = '%s' Deleted to table book...%n", isbn);
            }
        } catch (SQLException e) {
            System.out.printf("Error in database execution...%n");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.printf("Unknown Error accours...%n");
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                System.out.printf("Error on Closing database resource....%n");
            }
        }
        return result;
    }

    public List<Book> search(){
        String searchAllSql = "select * from book";
        List<Book> result = new ArrayList<Book>();
        Book book = null;
                try {
            // Create Connection to Database
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.printf("Connection to Database is successfully...%n");

            // Create Statement to execute query
            stmt = con.createStatement();
            System.out.printf("Create Statement success..%n");

            // Execute query
            resultSet = stmt.executeQuery(searchAllSql);
            System.out.printf("Execute SQL Success...%n");

            System.out.printf("%nDaftar Data Buku%n");
//            System.out.printf("==================================================================================================================%n");
//            System.out.printf("%n%-3s %-40s %-15s %-20s %20s %10s%n", "No", "JUDUL", "ISBN", "PENERBIT", "HARGA", "HALAMAN");
//            System.out.printf("==================================================================================================================%n");

            // encapsulate data using model
            while (resultSet.next()) {
                book = new Book();                
                book.setIsbn(resultSet.getString("isbn"));
                book.setTitle(resultSet.getString("title"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setPrice(resultSet.getFloat("price"));
                book.setPage(resultSet.getInt("page"));
                book.setCreatedBy(resultSet.getString("created_by"));
                book.setCreatedTime(resultSet.getTimestamp("created_time"));
                result.add(book);
            }
        } catch (SQLException e) {
            System.out.printf("Error in database execution...%n");
            e.printStackTrace();
        } catch(Exception e){
            System.out.printf("Unknown Error accours.... %n");
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                System.out.printf("Error on Closing database resource....%n");
            }
        }
        return result;
    }
    
    public Book findById(String isbn){
    	String searchAllSql = "select * from book where isbn = '"+isbn+"'";
    	Book result = null;
        
        try{
			// Create Connection to Database
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.printf("Connection to Database is successfully...%n");
			// Create Statement to execute query
			stmt = con.createStatement();
			System.out.printf("Create Statement success..%n");
			// Execute query
			resultSet = stmt.executeQuery(searchAllSql);
			System.out.printf("Execute SQL Success...%n");   
            // encapsulate data using model
            while (resultSet.next()) {
                result = new Book();
				result.setIsbn(resultSet.getString("isbn"));
				result.setTitle(resultSet.getString("title"));
				result.setPublisher(resultSet.getString("publisher"));
                result.setPrice(resultSet.getFloat("price"));
                result.setPrice(resultSet.getInt("page"));
				result.setCreatedBy(resultSet.getString("created_by"));
				result.setCreatedTime(resultSet.getTimestamp("created_time"));
            }
		}catch(SQLException e){
			System.out.printf("Error in database execution...%n");
			e.printStackTrace();
		}catch(Exception e){
			System.out.printf("Unknown Error accours...%n");
			e.printStackTrace();
		}finally{
			try{
				stmt.close();
				con.close();
			}
			catch(SQLException e){
				System.out.printf("Error on Closing database resource....%n");
			}
		}
        return result;
    }
    
}

