package modul2proiect.bookstore.mapper;



import modul2proiect.bookstore.dto.ReservationDTO;
import modul2proiect.bookstore.entities.Reservation;

public class ReservationMapper {
    public static ReservationDTO reservation2ReservationDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setID(reservation.getID());
        reservationDTO.setStartDate(reservation.getStartDate());
        reservationDTO.setEndDate(reservation.getEndDate());
        reservationDTO.setStatus(reservation.getStatus());
        reservationDTO.setExemplaryDTO(ExemplaryMapper.exemplary2ExemplaryDTO(reservation.getExemplary()));
        reservationDTO.setUserDTO(UserMapper.user2UserDTO(reservation.getUser()));

        return reservationDTO;
    }
    public static  Reservation reservationDTO2Reservation(ReservationDTO reservationDTO){
        Reservation reservation=new Reservation();
        reservation.setID(reservationDTO.getID());
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setStatus(reservationDTO.getStatus());
        if (reservationDTO.getExemplaryDTO() != null) {
            reservation.setExemplary(ExemplaryMapper.exemplaryDTO2Exemplary(reservationDTO.getExemplaryDTO()));
        }
        reservation.setUser(UserMapper.userDTO2User(reservationDTO.getUserDTO()));

        return reservation;
    }
}