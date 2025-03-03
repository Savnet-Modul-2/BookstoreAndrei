package modul2proiect.bookstore.controller;

import modul2proiect.bookstore.dto.ReservationDTO;
import modul2proiect.bookstore.entities.Book;
import modul2proiect.bookstore.entities.Reservation;
import modul2proiect.bookstore.mapper.ReservationMapper;
import modul2proiect.bookstore.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import modul2proiect.bookstore.mapper.BookMapper;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<?> searchBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        Page<Book> foundBooks = reservationService.searchBooks(title, author, pageNumber, pageSize);

        return ResponseEntity.ok(foundBooks.stream()
                .map(BookMapper::book2BookDto)
                .toList());
    }

    @PostMapping(path = "/{userID}/{bookID}")
    public ResponseEntity<?> reserveBook(@PathVariable(name = "userID") Long userID, @PathVariable(name = "bookID") Long bookID, @RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = reservationService.reserveBook(userID, bookID, reservationDTO.getStartDate(), reservationDTO.getEndDate());
        return ResponseEntity.ok(ReservationMapper.reservation2ReservationDTO(reservation));
    }
}
