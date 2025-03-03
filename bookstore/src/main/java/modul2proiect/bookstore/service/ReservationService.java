package modul2proiect.bookstore.service;

import jakarta.persistence.EntityNotFoundException;
import modul2proiect.bookstore.dto.ReservationDTO;
import modul2proiect.bookstore.entities.*;
import modul2proiect.bookstore.repository.BookRepository;
import modul2proiect.bookstore.repository.ExemplaryRepository;
import modul2proiect.bookstore.repository.ReservationRepository;
import modul2proiect.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
//import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ExemplaryRepository exemplaryRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository, BookRepository bookRepository, ExemplaryRepository exemplaryRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.exemplaryRepository = exemplaryRepository;
    }

    public Page<Book> searchBooks(String title, String author, Integer pageNumber, Integer pageSize) {
        if (pageNumber != null && pageSize != null) {
            Pageable pageable = (Pageable) PageRequest.of(pageNumber, pageSize);
            return bookRepository.searchBooks(title, author, (org.springframework.data.domain.Pageable) pageable);
        }

        return bookRepository.searchBooks(title, author, Pageable.unpaged());
    }

    public Reservation reserveBook(Long userID, Long bookID, LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate) || startDate.isBefore(LocalDate.now()) || endDate.isBefore(LocalDate.now())) {
            throw new InputMismatchException("Reservation unsuccessful. Invalid reservation dates.");
        }

        User user = userRepository.findById(userID)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userID + " not found."));

        bookRepository.findById(bookID)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookID + " not found."));

        Exemplary exemplary = exemplaryRepository.reserveExemplary(bookID, startDate, endDate)
                .orElseThrow(() -> new EntityNotFoundException("No exemplars of book with ID " + bookID + " available."));

        Reservation reservation = new Reservation();

        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setStatus(ReservationStatus.PENDING);

        exemplary.addReservation(reservation);
        user.addReservation(reservation);

        reservationRepository.save(reservation);
        exemplaryRepository.save(exemplary);
        userRepository.save(user);

        return reservation;
    }
}