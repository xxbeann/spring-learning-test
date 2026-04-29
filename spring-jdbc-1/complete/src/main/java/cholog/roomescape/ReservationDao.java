package cholog.roomescape;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDao {

    private final JdbcTemplate jdbcTemplate;

    public ReservationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insertWithKeyHolder(ReservationReq reservationReq) {
        String sql = "insert into reservation (name, date, time_id) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    sql,
                    new String[]{"id"}
            );
            ps.setString(1, reservationReq.name());
            ps.setDate(2, Date.valueOf(reservationReq.date()));
            ps.setLong(3, reservationReq.timeId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public List<Reservation> findAllReservations() {
        String sql = """
                select r.id,
                       r.name,
                       r.date,
                       rt.id as time_id,
                       rt.start_at
                from reservation r
                join reservation_time rt on r.time_id = rt.id
                order by r.id
                """;

        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new Reservation(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("date").toLocalDate(),
                        new ReservationTime(
                                resultSet.getLong("time_id"),
                                resultSet.getTime("start_at").toLocalTime()
                        )
                )
        );
    }
}
