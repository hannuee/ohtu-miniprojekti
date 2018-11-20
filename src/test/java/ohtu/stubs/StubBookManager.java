package ohtu.stubs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ohtu.db.sqlManager;
import ohtu.types.Book;

public class StubBookManager implements sqlManager {
    
    private HashMap<Integer,Book> bookDatabase;

    public StubBookManager() {
        bookDatabase = new HashMap<>();
        initialize();
    }
    
    public void addBook(Book book) {
        bookDatabase.put(book.getId(), book);
    }

    @Override
    public Object findOne(Object key) {
        return bookDatabase.get((Integer) key);
    }

    @Override
    public List findAll() {
        ArrayList<Book> books = new ArrayList();
        for (Book book : bookDatabase.values()) books.add(book);
        return books;
    }

    @Override
    public void delete(Object key) {
        bookDatabase.remove((Integer) key);
    }
    
    private void initialize() {
        bookDatabase.put(1, new Book(1, "1234567891011", "Testikirja", "MOnta tekijää", 1978));
        bookDatabase.put(2, new Book(2, "9788431676377", "Don Quijote", "Miguel de Cervantes", 1615));
        bookDatabase.put(3, new Book(2, "0-395-08254-4", "The Fellowship of the Ring", "J. R. R. Tolkien", 1954));
    }
    
}