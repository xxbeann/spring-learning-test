package cholog.roomescape;

import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationDao reservationDao;
    private final ReservationTimeDao reservationTimeDao;

    public ReservationService(ReservationDao reservationDao, ReservationTimeDao reservationTimeDao) {
        this.reservationDao = reservationDao;
        this.reservationTimeDao = reservationTimeDao;
    }

    public Reservation create(ReservationReq reservationReq) {
        Long id = reservationDao.insertWithKeyHolder(reservationReq);
        ReservationTime reservationTime = reservationTimeDao.findById(reservationReq.timeId());

        return new Reservation(
                id,
                reservationReq.name(),
                reservationReq.date(),
                reservationTime
        );
    }
}
