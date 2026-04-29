package cholog.roomescape;

import java.time.LocalDate;

public record ReservationReq(
        LocalDate date,
        String name,
        Long timeId
) {
}
