package modul2proiect.bookstore.cronjob;

import modul2proiect.bookstore.entities.Reservation;
import modul2proiect.bookstore.entities.ReservationStatus;
import modul2proiect.bookstore.repository.ExemplaryRepository;
import modul2proiect.bookstore.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;


@Configuration
@EnableScheduling
public class CronJobs {


    @Autowired
    private ReservationRepository reservationRepository;

        //pentru cron expression regex: https://docs.spring.io/spring-framework/docs/3.0.x/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html
        @Scheduled(cron = "0 */5 * * * *")
        public void reminderAddWinner() {





                LocalDate now = LocalDate.now();
                List<Reservation> reservationsToBeCanceled = reservationRepository
                        .findAllReservationsToBeCanceled(now);
                List<Reservation> reservationsToBeDelayed = reservationRepository
                        .findAllReservationsToBeDelayed(now);

                reservationsToBeCanceled.forEach(reservation ->
                        reservation.setStatus(ReservationStatus.CANCELED));
                reservationsToBeDelayed.forEach(reservation ->
                        reservation.setStatus(ReservationStatus.DELAYED));

                reservationRepository.saveAll(reservationsToBeCanceled);
                reservationRepository.saveAll(reservationsToBeDelayed);


            }


        }


