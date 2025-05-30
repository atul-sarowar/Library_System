package LibrarySystem;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Library {
    private List<Book> bookList = new ArrayList<>();

    public void loadBooksFromExcel(String path) {
        try (FileInputStream fis = new FileInputStream(path);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                int id = (int) row.getCell(0).getNumericCellValue();
                String title = row.getCell(1).getStringCellValue();
                String author = row.getCell(2).getStringCellValue();
                int copies = (int) row.getCell(3).getNumericCellValue();

                bookList.add(new Book(id, title, author, copies));
            }
        } catch (Exception e) {
            System.err.println("Error loading books: " + e.getMessage());
        }
    }

    public Book getBookById(int id) throws BookNotAvailableException {
        for (Book b : bookList) {
            if (b.bookId == id) {
                if (b.availableCopies == 0)
                    throw new BookNotAvailableException("No copies left of " + b.title);
                return b;
            }
        }
        throw new BookNotAvailableException("Book ID " + id + " not found");
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books :");
        for (Book b : bookList) {
            if (b.availableCopies > 0) {
                b.displayInfo();
            }
        }
    }
}