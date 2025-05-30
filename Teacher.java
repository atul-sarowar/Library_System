package LibrarySystem;

public class Teacher extends User {
    private static final int MAX_BORROW = 5;
    public String designation;

    public Teacher(int userId, String name, String designation) {
        super(userId, name);
        this.designation = designation;
    }

    @Override
    public String toString() {
        return name + " (" + designation + ", East West University)";
    }

    @Override
    public void borrowBook(Book book, int qty) throws BookNotAvailableException {
        if (borrowedBooks.size() + qty > MAX_BORROW) {
            System.out.printf("%s cannot borrow more than %d books.%n", name, MAX_BORROW);
            return;
        }
        if (!book.borrow(qty)) {
            throw new BookNotAvailableException("Only " + book.availableCopies + " copies available");
        }
        borrowedBooks.add(book);
        System.out.printf("%s (Teacher) borrowed %d copy(ies) of \"%s\".%n",
                name, qty, book.title);
    }
}