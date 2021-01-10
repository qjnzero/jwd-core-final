package factory;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CrewMemberFactoryTest {

    @Test
    void create() {
        CrewMember actual = CrewMemberFactory.INSTANCE.create("Kirill Ostapchuk", Role.COMMANDER, Rank.CAPTAIN);
        assertEquals("Kirill Ostapchuk", actual.getName());
        assertEquals(Role.COMMANDER, actual.getRole());
        assertEquals(Rank.CAPTAIN, actual.getRank());
    }
}