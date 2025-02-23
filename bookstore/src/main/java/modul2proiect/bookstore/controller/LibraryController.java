package modul2proiect.bookstore.controller;

import modul2proiect.bookstore.dto.LibraryDTO;
import modul2proiect.bookstore.entities.Library;
import modul2proiect.bookstore.mapper.LibraryMapper;
import modul2proiect.bookstore.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody LibraryDTO libraryDTO) {
        Library libraryToCreate = LibraryMapper.libraryDto2Library(libraryDTO);
        Library createdLibrary = libraryService.create(libraryToCreate);
        return ResponseEntity.ok(LibraryMapper.library2LibraryDto(createdLibrary));
    }
    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Library> libraryList = libraryService.findAll();
        return ResponseEntity.ok(libraryList.stream().map(LibraryMapper::library2LibraryDto).toList());
    }
}