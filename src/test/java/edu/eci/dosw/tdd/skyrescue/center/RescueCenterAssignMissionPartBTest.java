package edu.eci.dosw.tdd.skyrescue.center;

import edu.eci.dosw.tdd.skyrescue.drone.Drone;
import edu.eci.dosw.tdd.skyrescue.operator.RescueOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RescueCenterAssignMissionPartBTest {

    private RescueCenter center;

    @BeforeEach
    void setUp() {
        center = new RescueCenter();
    }

    @Test
    void shouldNotAssignMissionWhenDistanceExceedsDroneAutonomy() {
        RescueOperator operator = new RescueOperator("OP1", "Laura");
        Drone drone = new Drone("D1", "Falcon", 10);
        center.addOperator(operator);
        center.addDrone(drone);

        assertThrows(IllegalArgumentException.class, () ->
                center.assignMission("OP1", "D1", "Zona Norte", 50));
    }

    @Test
    void shouldNotAssignMissionWhenOperatorDoesNotExist() {
        Drone drone = new Drone("D1", "Falcon", 100);
        center.addDrone(drone);

        assertThrows(IllegalArgumentException.class, () ->
                center.assignMission("OP-NOEXISTE", "D1", "Zona Norte", 20));
    }

    @Test
    void shouldNotAssignMissionWhenOperatorAlreadyHasActiveMission() {
        RescueOperator operator = new RescueOperator("OP1", "Laura");
        Drone drone1 = new Drone("D1", "Falcon", 100);
        Drone drone2 = new Drone("D2", "Hawk", 100);
        center.addOperator(operator);
        center.addDrone(drone1);
        center.addDrone(drone2);

        center.assignMission("OP1", "D1", "Zona Norte", 20);

        assertThrows(IllegalStateException.class, () ->
                center.assignMission("OP1", "D2", "Zona Sur", 20));
    }
}
