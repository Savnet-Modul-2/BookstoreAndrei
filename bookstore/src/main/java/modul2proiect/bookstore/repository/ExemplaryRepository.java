package modul2proiect.bookstore.repository;


import modul2proiect.bookstore.entities.Exemplary;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
//import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.Optional;


@Repository
public interface ExemplaryRepository extends JpaRepository<Exemplary, Long> {
    Page<Exemplary> findByBookId(Long bookID, Pageable pageable);



    @Query(value = """
            SELECT aExemplary.* FROM exemplars aExemplary
            LEFT JOIN reservations aReservation
            ON aExemplary.ID = aReservation.EXEMPLARY_ID
            WHERE aExemplary.BOOK_ID = :bookID
            AND
            (aReservation.ID IS NULL OR aReservation.STATUS = 'FINISHED' OR :startDate > aReservation.END_DATE OR :endDate < aReservation.START_DATE)
            ORDER BY aExemplary.id ASC
            LIMIT 1
            """, nativeQuery = true)
    Optional<Exemplary> reserveExemplary(@Param("bookID") Long bookID, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}