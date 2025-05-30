package LibrarySystem;

import java.util.ArrayList;
import java.util.List;

public abstract class User implements Borrowable {
    public int userId;
    public String name;
    public List<Book> borrowedBooks = new ArrayList<>();

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.returnBook();
            System.out.printf("%s returned \"%s\".%n", name, book.title);
        } else {
            System.out.printf("%s did not have \"%s\" borrowed.%n", name, book.title);
        }
    }

    public void viewBorrowedBooks() {
        System.out.printf("%s's borrowed books:%n", name);
        if (borrowedBooks.isEmpty()) {
            System.out.println("  (none)");
        } else {
            for (Book b : borrowedBooks) {
                System.out.printf("  - %s%n", b.title);
            }
        }
    }
}