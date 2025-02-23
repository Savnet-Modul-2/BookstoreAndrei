package modul2proiect.bookstore.controller;

import modul2proiect.bookstore.dto.LibrarianDTO;
import modul2proiect.bookstore.entities.Librarian;
import modul2proiect.bookstore.entities.User;
import modul2proiect.bookstore.mapper.UserMapper;
import modul2proiect.bookstore.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import modul2proiect.bookstore.mapper.LibrarianMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/librarians")
public class LibrarianController {
    @Autowired
    private LibrarianService librarianService;
    @PostMapping()
    public ResponseEntity<?> create (@RequestBody LibrarianDTO librarianDTO){
        Librarian librarianToCreate = LibrarianMapper.librarianDto2Librarian(librarianDTO);
        Librarian createdLibrarian = librarianService.create(librarianToCreate);
        return ResponseEntity.ok(LibrarianMapper.librarian2LibrarianDto(createdLibrarian));
    }

    @PutMapping("/verify")
    public ResponseEntity<?> verifyAccount(@RequestBody LibrarianDTO librarianDTO) {
        Librarian librarianToUpdate=LibrarianMapper.librarianDto2Librarian(librarianDTO);
        Librarian verifiedLibrarian = librarianService.verifyAccount(librarianToUpdate.getEmail(), librarianToUpdate.getVerificationCode());
        return ResponseEntity.ok(LibrarianMapper.librarian2LibrarianDto(verifiedLibrarian));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LibrarianDTO librarianDTO) {
        Librarian librarianToLogin = LibrarianMapper.librarianDto2Librarian(librarianDTO);
        Librarian loggedinLibrarian = librarianService.login(librarianToLogin.getEmail(), librarianToLogin.getPassword());
        return ResponseEntity.ok(LibrarianMapper.librarian2LibrarianDto(loggedinLibrarian));
    }
}