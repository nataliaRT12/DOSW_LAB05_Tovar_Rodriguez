package edu.eci.dosw.tdd.skyrescue.center;

import edu.eci.dosw.tdd.skyrescue.drone.Drone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RescueCenterTest {

    @Test
    void shouldRegisterDroneWhenDataIsValid() {
        // Arrange
        RescueCenter center = new RescueCenter();
        Drone drone = new Drone("D1", "Matrice300", 15);

        // Act
        boolean result = center.addDrone(drone);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldNotRegisterDroneWhenDroneIsNull() {
        // Arrange
        RescueCenter center = new RescueCenter();

        // Act
        boolean result = center.addDrone(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldNotRegisterDroneWhenIdIsBlank() {
        // Arrange
        RescueCenter center = new RescueCenter();
        Drone drone = new Drone("   ", "Matrice300", 15);

        // Act
        boolean result = center.addDrone(drone);

        // Assert
        assertFalse(result);
    }

}