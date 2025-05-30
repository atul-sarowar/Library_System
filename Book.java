package LibrarySystem;

public class Book {
    public int bookId;
    public String title;
    public String author;
    public int availableCopies;

    public Book() {

    }

    public Book(int bookId, String title, String author, int copies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.availableCopies = copies;
    }

    public void displayInfo() {
        System.out.printf("%d: \"%s\" by %s (Available: %d)%n",
                bookId, title, author, availableCopies);
    }

    public boolean borrow(int qty) {
        if (availableCopies >= qty) {
            availableCopies -= qty;
            return true;
        }
        return false;
    }

    public void returnBook() {
        availableCopies++;
    }
}