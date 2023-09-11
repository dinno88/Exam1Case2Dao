package edu.satraining.controller;
import edu.satraining.dao.BookDao;
import edu.satraining.model.Book;
import java.util.Scanner;		
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    /* Fields */
    private BookDao repository = new BookDao();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    
    List<Book>books = new ArrayList<Book>();  
    /* Constructor */
    public Controller() {
    	
        System.out.printf("Controller Constructor invoked.. %n");
    }

public void readData() {
        Scanner scanner = new Scanner(System.in);
        String inputData;
        while (true) {
            try {
                System.out.printf("%nMenu Aplikasi%n");
                System.out.printf("=============%n");
                System.out.printf("[S] Tampilkan Data%n");
                System.out.printf("[A] Tambah Data%n");
                System.out.printf("[E] Ubah Data%n");
                System.out.printf("[D] Hapus Data%n");
                System.out.printf("[X] Exit%n");
                System.out.printf("==============%n%n");
                System.out.printf("Pilih menu [S/ A/ E/ D/ X]:");
                inputData = scanner.nextLine();
                if (inputData.equalsIgnoreCase("S")) {
                    showData();
                } else if (inputData.equalsIgnoreCase("A")) {
                    addData();
                } else if (inputData.equalsIgnoreCase("E")) {
                    editData();
                } else if (inputData.equalsIgnoreCase("D")) {
                    deleteData();
                } else if (inputData.equalsIgnoreCase("X")) {
                    System.out.println("Aplikasi ditutup.");
                    scanner.close();
                    System.exit(0);
                } else {
                    throw new IllegalArgumentException("Input tidak valid: " + inputData);
                }
            } catch (IllegalArgumentException e) {
                System.out.printf("%nPilihan tidak valid. Mohon pilih menu yang benar.%n");
            }
        }
    }

    public void showData() {
		List<Book> listBooks = null;
		listBooks = repository.search();
        System.out.printf("%n%-3s %-40s %-15s %-20s %5s %20s %15s %20s%n", "No", "JUDUL", "ISBN", "PENERBIT", "HARGA", "HALAMAN", "CREATED BY", "CREATED TIME");
        System.out.printf("======================================================================================================================================================================================%n");
	
            if(listBooks.size() > 0)
            {
            	int i = 0;
            	for(Book book : listBooks){
            	System.out.printf("%-3s",++i);
            	System.out.printf("%-40s ", book.getTitle());	
                System.out.printf("%-15s ", book.getIsbn());
            	System.out.printf("%-20s ", book.getPublisher());
                System.out.printf("%-20s ", book.getPrice());
                System.out.printf("%-10s ", book.getPage());
            	System.out.printf("%-10s ", book.getCreatedBy());
            	System.out.printf("%-35s ", book.getCreatedTime());
            	System.out.printf("%s%n", sdf.format(book.getCreatedTime()));
            	}
            }else{
            	System.out.printf("No Data%n");
            }
            System.out.printf("======================================================================================================================================================================================%n");
        System.out.print("Tekan Enter untuk melanjutkan...");
        scanner.nextLine();
    }

    private Scanner scanner = new Scanner(System.in);

    public void addData() {
        // Tambah data
        System.out.printf("%nTambah Data Buku%n");

        String title = "";
        while (title.isEmpty()) {
            System.out.printf("Judul         : ");
            title = scanner.nextLine().trim();
            if (title.isEmpty()) {
                System.out.printf("Judul tidak boleh kosong. Mohon masukkan judul buku.%n");
            }
        }

        String isbn = "";
        while (isbn.isEmpty() || !isbn.matches("\\d+")) {
            System.out.printf("ISBN          : ");
            isbn = scanner.nextLine().trim();
            if (isbn.isEmpty()) {
                System.out.printf("ISBN tidak boleh kosong. Mohon masukkan ISBN buku.%n");
            } else if (!isbn.matches("\\d+")) {
                System.out.printf("ISBN hanya boleh berisi angka. Mohon masukkan ISBN buku yang valid.%n");
            }
        }

        String publisher = "";
        while (publisher.isEmpty()) {
            System.out.printf("Penerbit      : ");
            publisher = scanner.nextLine().trim();
            if (publisher.isEmpty()) {
                System.out.printf("Penerbit tidak boleh kosong. Mohon masukkan penerbit buku.%n");
            }
        }

        float price = 0;
        while (price <= 0) {
            System.out.printf("Harga         : ");
            try {
                price = Float.parseFloat(scanner.nextLine());
                if (price <= 0) {
                    System.out.printf("Harga harus lebih dari 0. Mohon masukkan harga buku.%n");
                }
            } catch (NumberFormatException e) {
                System.out.printf("Format harga tidak valid. Mohon masukkan angka yang valid.%n");
            }
        }

        int page = 0;
        while (page <= 0) {
            System.out.printf("Jumlah halaman: ");
            try {
                page = Integer.parseInt(scanner.nextLine());
                if (page <= 0) {
                    System.out.printf("Jumlah halaman harus lebih dari 0. Mohon masukkan jumlah halaman buku.");
                }
            } catch (NumberFormatException e) {
                System.out.printf("Format jumlah halaman tidak valid. Mohon masukkan angka yang valid.");
            }
        }

        // Tambahkan buku baru ke dalam array book
        Book newBook = new Book(title, isbn, publisher, price, page);
        books.add(newBook); 
            int result = 0;
            result = repository.insert(newBook);
        
            // Tampilkan data buku baru yang baru saja diinput
            System.out.printf("%nTambah Data:%n");
            System.out.printf("=================================%n");
            System.out.printf("Judul         : %s%n", newBook.getTitle());
            System.out.printf("ISBN          : %s%n", newBook.getIsbn());
            System.out.printf("Penerbit      : %s%n", newBook.getPublisher());
            System.out.printf("Harga         : Rp. %.2f%n", newBook.getPrice());
            System.out.printf("Jumlah Halaman: %d%n", newBook.getPage());
            System.out.printf("=================================%n");
            System.out.printf("Data di atas berhasil ditambahkan.%n");
            System.out.printf("%nTekan Enter untuk melanjutkan...");
            scanner.nextLine();

    }

    public void editData() {
        // Edit data
        System.out.printf("%nEdit Data Buku%n");
        System.out.printf("Masukkan ISBN buku yang ingin diubah: ");
        String isbnToEdit = scanner.nextLine();
    
        int result = -1; // Inisialisasi result dengan nilai default -1
        Book book = repository.findById(isbnToEdit);
    
        if (book != null) {
            System.out.printf("Judul         : ");
            String newTitle = scanner.nextLine();
            book.setTitle(newTitle);
    
            System.out.printf("Penerbit      : ");
            String newPublisher = scanner.nextLine();
            book.setPublisher(newPublisher);
    
            System.out.printf("Harga         : ");
            float newPrice = Float.parseFloat(scanner.nextLine());
            book.setPrice(newPrice);
    
            System.out.printf("Jumlah Halaman: ");
            int newPage = Integer.parseInt(scanner.nextLine());
            book.setPage(newPage);
    
            // Display old data
            System.out.printf("%nData Saat ini:%n");
            System.out.println("=============");
            displayBookInfo(book);
    
            System.out.printf("%nData Terbaru:%n");
            System.out.println("=============");
            displayBookInfo(book);
    
            System.out.printf("%nData buku dengan ISBN " + isbnToEdit + " Berhasil diubah.%n");
    
            // Lakukan pembaruan di database
            int updateResult = repository.update(book);
    
            if (updateResult > 0) {
                System.out.printf("%nUpdate di database berhasil%n");
            } else {
                System.out.printf("%nUpdate di database gagal%n");
            }
    
            System.out.printf("%nTekan Enter untuk melanjutkan...");
            scanner.nextLine();
        } else {
            System.out.printf("Buku dengan ISBN " + isbnToEdit + " tidak ditemukan.");
        }
    }
    

    private void displayBookInfo(Book book) {
        System.out.printf("Judul         : %s%n", book.getTitle());
        System.out.printf("ISBN          : %s%n", book.getIsbn());
        System.out.printf("Penerbit      : %s%n", book.getPublisher());
        System.out.printf("Harga         : Rp. %.2f%n", book.getPrice());
        System.out.printf("Jumlah Halaman: %d%n", book.getPage());
    }

    public void deleteData() {
        System.out.printf("Masukkan ISBN buku yang ingin dihapus: ");
        String isbnToDelete = scanner.nextLine();
    
        int bookIndex = -1;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(isbnToDelete)) {
                bookIndex = i;
                break;
            }
        }
    
        if (bookIndex != -1) {
            System.out.printf("Anda yakin ingin menghapus buku dengan ISBN " + isbnToDelete + "? %n");
            System.out.printf("[Y] ya [N] tidak: ");
            String confirmation = scanner.nextLine();
            
            if (confirmation.equalsIgnoreCase("Y")) {
                books.remove(bookIndex);
                System.out.println("==========================================================");
                System.out.printf("Buku dengan ISBN " + isbnToDelete + " telah dihapus.");
                System.out.printf("%nTekan Enter untuk melanjutkan...");
                scanner.nextLine();
            } else {
                System.out.printf("Penghapusan buku dibatalkan.");
            }
        } else {
            System.out.printf("Buku dengan ISBN " + isbnToDelete + " tidak ditemukan.");
        }
    }
    
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.readData();
    }
}