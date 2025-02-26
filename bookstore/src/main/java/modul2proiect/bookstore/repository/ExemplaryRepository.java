package modul2proiect.bookstore.repository;


import modul2proiect.bookstore.entities.Exemplary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplaryRepository extends JpaRepository<Exemplary, Long> {
}