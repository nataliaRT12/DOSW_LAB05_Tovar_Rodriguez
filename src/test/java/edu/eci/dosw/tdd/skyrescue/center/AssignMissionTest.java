package edu.eci.dosw.tdd.skyrescue.center;

import edu.eci.dosw.tdd.skyrescue.drone.Drone;
import edu.eci.dosw.tdd.skyrescue.mission.Mission;
import edu.eci.dosw.tdd.skyrescue.mission.MissionStatus;
import edu.eci.dosw.tdd.skyrescue.operator.RescueOperator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssignMissionTest {

    @Test
    void shouldAssignMissionWhenDataIsValid() {
        // Arrange
        RescueCenter center = new RescueCenter();
        RescueOperator operator = new RescueOperator("OP1", "Carlos");
        Drone drone = new Drone("D1", "Matrice300", 20);
        center.addOperator(operator);
        center.addDrone(drone);

        // Act
        Mission mission = center.assignMission("OP1", "D1", "Bogota", 10);

        // Assert
        assertNotNull(mission);
        assertEquals(MissionStatus.ACTIVE, mission.getStatus());
        assertEquals("OP1", mission.getOperator().getId());
        assertEquals("D1", mission.getDrone().getId());
        assertFalse(drone.isAvailable());
    }

    @Test
    void shouldNotAssignMissionWhenDroneDoesNotExist() {
        // Arrange
        RescueCenter center = new RescueCenter();
        RescueOperator operator = new RescueOperator("OP1", "Carlos");
        center.addOperator(operator);

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> center.assignMission("OP1", "D-UNKNOWN", "Bogota", 10));
    }

    @Test
    void shouldNotAssignMissionWhenDroneIsBusy() {
        // Arrange
        RescueCenter center = new RescueCenter();
        RescueOperator operator1 = new RescueOperator("OP1", "Carlos");
        RescueOperator operator2 = new RescueOperator("OP2", "Ana");
        Drone drone = new Drone("D1", "Matrice300", 20);
        center.addOperator(operator1);
        center.addOperator(operator2);
        center.addDrone(drone);
        center.assignMission("OP1", "D1", "Bogota", 10);

        // Act & Assert
        assertThrows(IllegalStateException.class,
                () -> center.assignMission("OP2", "D1", "Medellin", 10));
    }
}