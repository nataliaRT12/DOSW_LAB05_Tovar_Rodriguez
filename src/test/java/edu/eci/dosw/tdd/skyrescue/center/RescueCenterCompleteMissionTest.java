package edu.eci.dosw.tdd.skyrescue.center;

import edu.eci.dosw.tdd.skyrescue.drone.Drone;
import edu.eci.dosw.tdd.skyrescue.mission.Mission;
import edu.eci.dosw.tdd.skyrescue.mission.MissionStatus;
import edu.eci.dosw.tdd.skyrescue.operator.RescueOperator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RescueCenterCompleteMissionTest {

    private RescueCenter center;

    @BeforeEach
    void setUp() {
        center = new RescueCenter();
        center.addOperator(new RescueOperator("op1", "Operador 1"));
        center.addDrone(new Drone("d1", "Modelo X", 100));
    }

    @Test
    void shouldCompleteActiveMission() {
        Mission mission = center.assignMission("op1", "d1", "Zona Norte", 50);

        Mission completed = center.completeMission(mission.getId());

        assertEquals(MissionStatus.COMPLETED, completed.getStatus());
        assertNotNull(completed.getEndDate());
        assertTrue(center.assignMission("op1", "d1", "Zona Sur", 20) != null);
    }

    @Test
    void shouldThrowExceptionWhenCompletingNonExistentMission() {
        assertThrows(IllegalArgumentException.class,
                () -> center.completeMission("mision-inexistente"));
    }

    @Test
    void shouldThrowExceptionWhenCompletingMissionTwice() {
        Mission mission = center.assignMission("op1", "d1", "Zona Norte", 50);
        center.completeMission(mission.getId());

        assertThrows(IllegalStateException.class,
                () -> center.completeMission(mission.getId()));
    }
}
