package factory;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightMissionFactoryTest {

    @Test
    void create() {
        FlightMission actual = FlightMissionFactory.INSTANCE.create(
                "Flight228",
                LocalDate.of(2002, 1, 30),
                LocalDate.of(2021, 1, 30),
                123456L);
        assertEquals("Flight228", actual.getName());
        assertEquals(LocalDate.of(2002, 1, 30), actual.getStartDate());
        assertEquals(LocalDate.of(2021, 1, 30), actual.getEndDate());
        assertEquals(123456L, actual.getDistance());
    }
}