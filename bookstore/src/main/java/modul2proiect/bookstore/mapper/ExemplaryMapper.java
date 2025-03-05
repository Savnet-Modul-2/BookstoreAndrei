package modul2proiect.bookstore.mapper;

import modul2proiect.bookstore.dto.ExemplarsCreateDTO;
import modul2proiect.bookstore.entities.Exemplary;
import modul2proiect.bookstore.dto.ExemplaryDTO;

import java.util.ArrayList;
import java.util.List;


public class ExemplaryMapper {
    public static ExemplaryDTO exemplary2ExemplaryDTO(Exemplary exemplary) {
        ExemplaryDTO exemplaryDTO = new ExemplaryDTO();

        exemplaryDTO.setId(exemplary.getId());
        exemplaryDTO.setPublisher(exemplary.getPublisher());
        exemplaryDTO.setMaximumBookingTime(exemplary.getMaximumBookingTime());
        exemplaryDTO.setBookDTO(BookMapper.book2BookDto(exemplary.getBook()));

        return exemplaryDTO;
    }


    public static Exemplary exemplaryDTO2Exemplary(ExemplaryDTO exemplaryDTO) {
        Exemplary exemplary = new Exemplary();

        exemplary.setPublisher(exemplaryDTO.getPublisher());
        exemplary.setMaximumBookingTime(exemplaryDTO.getMaximumBookingTime());
        exemplary.setBook(BookMapper.bookDto2Book(exemplaryDTO.getBookDTO()));

        return exemplary;
    }




    public static List<Exemplary> exemplarsCreateDTO2Exemplars(ExemplarsCreateDTO exemplarsCreateDTO) {
        List<Exemplary> exemplars = new ArrayList<>();

        for (int i = 0; i < exemplarsCreateDTO.getNumberOfExemplars(); i++) {
            Exemplary exemplary = new Exemplary();

            exemplary.setPublisher(exemplarsCreateDTO.getPublisher());
            exemplary.setMaximumBookingTime(exemplarsCreateDTO.getMaximumBookingTime());

            exemplars.add(exemplary);
        }

        return exemplars;
    }
}