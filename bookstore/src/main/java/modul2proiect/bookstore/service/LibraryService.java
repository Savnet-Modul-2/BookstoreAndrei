package modul2proiect.bookstore.service;

import modul2proiect.bookstore.entities.Book;
import modul2proiect.bookstore.entities.Library;
import modul2proiect.bookstore.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;
    public Library create(Library library) {
        if(library.getId() != null){
            throw new RuntimeException("You cannot provide an ID to a new application that you want to create");
        }
        return libraryRepository.save(library);
    }
    public List<Library> findAll() {
        return libraryRepository.findAll();
    }
}