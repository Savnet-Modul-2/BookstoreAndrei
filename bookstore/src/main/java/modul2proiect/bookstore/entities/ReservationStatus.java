package modul2proiect.bookstore.entities;


public enum ReservationStatus {




    PENDING{

        @Override
        public boolean updateState(ReservationStatus reservationStatus){
            return reservationStatus==ReservationStatus.IN_PROGRESS || reservationStatus==ReservationStatus.DELAYED;
        }

    }, IN_PROGRESS{

         @Override
        public boolean updateState(ReservationStatus reservationStatus){
            return reservationStatus==ReservationStatus.FINISHED || reservationStatus==ReservationStatus.CANCELED;
        }

    }, DELAYED{

         @Override
        public boolean updateState(ReservationStatus reservationStatus){
            return reservationStatus==ReservationStatus.IN_PROGRESS || reservationStatus==ReservationStatus.CANCELED;
        }


    },
    FINISHED{

         @Override
        public boolean updateState(ReservationStatus reservationStatus){
            return false;
        }


    },CANCELED{

        @Override
        public boolean updateState(ReservationStatus reservationStatus){
            return false;
        }

    };
    public abstract boolean updateState(ReservationStatus reservationStatus);



}