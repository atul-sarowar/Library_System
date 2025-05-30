package LibrarySystem;

public class Student extends User {
    private static final int MAX_BORROW = 3;
    public String department;

    public Student(int userId, String name, String department) {
        super(userId, name);
        this.department = department;
    }

    @Override
    public String toString() {
        return name + " (" + department + ", East West University)";
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
        System.out.printf("%s (Student) borrowed %d copy(ies) of \"%s\".%n",
                name, qty, book.title);
    }
}