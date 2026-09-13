package edu.eci.dosw.tdd.skyrescue.center;

import edu.eci.dosw.tdd.skyrescue.drone.Drone;
import edu.eci.dosw.tdd.skyrescue.mission.Mission;
import edu.eci.dosw.tdd.skyrescue.mission.MissionStatus;
import edu.eci.dosw.tdd.skyrescue.operator.RescueOperator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Coordinates drones, operators and emergency missions.
 */
public class RescueCenter {

    private final List<RescueOperator> operators;
    private final Map<String, Drone> drones;
    private final List<Mission> missions;

    public RescueCenter() {
        this.operators = new ArrayList<>();
        this.drones = new HashMap<>();
        this.missions = new ArrayList<>();
    }

    /**
     * Registers a drone in the rescue center.
     *
     * Rules:
     * - The drone cannot be null.
     * - The drone id cannot be null or blank.
     * - Two drones cannot have the same id.
     * - A valid drone is stored as available.
     *
     * @param drone drone to register.
     * @return true if it was registered; false otherwise.
     */
    public boolean addDrone(Drone drone) {
        if (!isValidDrone(drone)) {
            return false;
        }
        drones.put(drone.getId(), drone);
        return true;
    }

    private boolean isValidDrone(Drone drone) {
        if (drone == null) {
            return false;
        }
        String id = drone.getId();
        if (id == null || id.isBlank()) {
            return false;
        }
        return !drones.containsKey(id);
    }


    /**
     * Assigns an emergency mission to an operator and an available drone.
     *
     * Rules:
     * - operatorId, droneId and location must be valid.
     * - The operator must exist.
     * - The drone must exist and be available.
     * - distanceKm must be greater than zero.
     * - distanceKm cannot exceed the drone maxRangeKm.
     * - The same operator cannot have two ACTIVE missions.
     * - On success, create an ACTIVE mission with the current date.
     * - On success, the selected drone becomes unavailable.
     * - The created mission must be stored in the center.
     *
     * Suggested error policy:
     * - Invalid/nonexistent data -> IllegalArgumentException.
     * - Valid resource but invalid state -> IllegalStateException.
     *
     * @param operatorId operator identifier.
     * @param droneId drone identifier.
     * @param location emergency location description.
     * @param distanceKm mission distance in kilometers.
     * @return created mission.
     */
    public Mission assignMission(
            String operatorId,
            String droneId,
            String location,
            int distanceKm) {
        RescueOperator operator = findOperatorById(operatorId);
        Drone drone = findDroneById(droneId);

        Mission mission = new Mission(
                java.util.UUID.randomUUID().toString(),
                location,
                distanceKm,
                drone,
                operator,
                java.time.LocalDateTime.now(),
                MissionStatus.ACTIVE);

        drone.setAvailable(false);
        missions.add(mission);
        return mission;
    }

    private RescueOperator findOperatorById(String operatorId) {
        return operators.stream()
                .filter(op -> op.getId().equals(operatorId))
                .findFirst()
                .orElse(null);
    }

    private Drone findDroneById(String droneId) {
        return drones.get(droneId);
    }
     
     /* @param missionId mission identifier.
     * @return completed mission.
     */
    public Mission completeMission(String missionId) {
        // TODO Implement using TDD.
        return null;
    }

    public boolean addOperator(RescueOperator operator) {
        return operators.add(operator);
    }
}
