package cholog.roomescape;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class ReservationFlowTest {

    private ReservationTimeDao reservationTimeDao;
    private ReservationDao reservationDao;
    private ReservationService reservationService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        reservationTimeDao = new ReservationTimeDao(jdbcTemplate);
        reservationDao = new ReservationDao(jdbcTemplate);
        reservationService = new ReservationService(reservationDao, reservationTimeDao);

        jdbcTemplate.execute("DROP TABLE reservation IF EXISTS");
        jdbcTemplate.execute("DROP TABLE reservation_time IF EXISTS");

        jdbcTemplate.execute("""
                CREATE TABLE reservation_time (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    start_at TIME NOT NULL
                )
                """);

        jdbcTemplate.execute("""
                CREATE TABLE reservation (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(255) NOT NULL,
                    date DATE NOT NULL,
                    time_id BIGINT NOT NULL,
                    CONSTRAINT fk_reservation_time FOREIGN KEY (time_id) REFERENCES reservation_time(id)
                )
                """);

        jdbcTemplate.update("insert into reservation_time(start_at) values (?)", Time.valueOf(LocalTime.of(10, 0)));
        jdbcTemplate.update("insert into reservation_time(start_at) values (?)", Time.valueOf(LocalTime.of(11, 0)));
    }

    @Test
    void findReservationTimeById() {
        ReservationTime reservationTime = reservationTimeDao.findById(1L);

        assertThat(reservationTime).isNotNull();
        assertThat(reservationTime.getId()).isEqualTo(1L);
        assertThat(reservationTime.getStartAt()).isEqualTo(LocalTime.of(10, 0));
    }

    @Test
    void createReservationShouldReturnFullTimeInformation() {
        ReservationReq reservationReq = new ReservationReq(
                LocalDate.of(2023, 8, 5),
                "브라운",
                1L
        );

        Reservation savedReservation = reservationService.create(reservationReq);

        assertThat(savedReservation.getId()).isNotNull();
        assertThat(savedReservation.getName()).isEqualTo("브라운");
        assertThat(savedReservation.getDate()).isEqualTo(LocalDate.of(2023, 8, 5));
        assertThat(savedReservation.getTime().getId()).isEqualTo(1L);
        assertThat(savedReservation.getTime().getStartAt()).isEqualTo(LocalTime.of(10, 0));
    }

    @Test
    void findAllReservationsShouldIncludeStartAt() {
        jdbcTemplate.update(
                "insert into reservation(name, date, time_id) values (?, ?, ?)",
                "브라운",
                java.sql.Date.valueOf(LocalDate.of(2023, 8, 5)),
                1L
        );

        List<Reservation> reservations = reservationDao.findAllReservations();

        assertThat(reservations).hasSize(1);
        assertThat(reservations.get(0).getTime().getId()).isEqualTo(1L);
        assertThat(reservations.get(0).getTime().getStartAt()).isEqualTo(LocalTime.of(10, 0));
    }
}
