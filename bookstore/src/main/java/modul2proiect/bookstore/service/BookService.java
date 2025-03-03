package modul2proiect.bookstore.service;

import modul2proiect.bookstore.entities.Book;
import modul2proiect.bookstore.entities.Library;
import modul2proiect.bookstore.repository.BookRepository;
import modul2proiect.bookstore.repository.LibraryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LibraryRepository libraryRepository;

    public Book create(Book book) {
        if(book.getId() != null){
            throw new RuntimeException("You cannot provide an ID to a new application that you want to create");
        }
        return bookRepository.save(book);
    }
    public Book create(Long libraryId, Book book) {
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new EntityNotFoundException("Library not found"));

        book.setLibrary(library);
        return bookRepository.save(book);
    }

    public Book getById(Long bookIdToSearchFor) {
        return bookRepository.findById(bookIdToSearchFor)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookIdToSearchFor + " not found."));
    }
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public void deleteById(Long bookIdToDelete) {
        bookRepository.deleteById(bookIdToDelete);
    }

    public Book updateById(Long bookIdToUpdate, Book bookEntity) {
        Book updatedBook = bookRepository.findById(bookIdToUpdate)
                .orElseThrow(EntityNotFoundException::new);

        updatedBook.setTitle(bookEntity.getTitle());
        updatedBook.setAuthor(bookEntity.getAuthor());
        updatedBook.setAppearanceDate(bookEntity.getAppearanceDate());
        updatedBook.setNrOfPages(bookEntity.getNrOfPages());
        updatedBook.setCategory(bookEntity.getCategory());
        updatedBook.setLanguage(bookEntity.getLanguage());

        return bookRepository.save(updatedBook);
    }
    public void removeFromLibrary(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        book.setLibrary(null);
        bookRepository.save(book);
    }

}