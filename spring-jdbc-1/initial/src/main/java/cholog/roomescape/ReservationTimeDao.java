package cholog.roomescape;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationTimeDao {

    private final JdbcTemplate jdbcTemplate;

    public ReservationTimeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ReservationTime findById(Long id) {
        String sql = "select id, start_at from reservation_time where id = ?";

        return jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> new ReservationTime(
                        resultSet.getLong("id"),
                        resultSet.getTime("start_at").toLocalTime()
                ),
                id
        );
    }
}
