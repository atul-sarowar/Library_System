package LibrarySystem;

public interface Borrowable {
    void borrowBook(Book book, int qty) throws BookNotAvailableException;
}