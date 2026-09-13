package edu.eci.dosw.tdd.skyrescue.center;

import edu.eci.dosw.tdd.skyrescue.drone.Drone;
import edu.eci.dosw.tdd.skyrescue.mission.Mission;
import edu.eci.dosw.tdd.skyrescue.mission.MissionStatus;
import edu.eci.dosw.tdd.skyrescue.operator.RescueOperator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    public Mission assignMission(
            String operatorId,
            String droneId,
            String location,
            int distanceKm) {
        RescueOperator operator = findOperator(operatorId);
        if (operator == null) {
            throw new IllegalArgumentException("Operator does not exist");
        }

        Drone drone = drones.get(droneId);
        if (distanceKm > drone.getMaxRangeKm()) {
            throw new IllegalArgumentException("Distance exceeds drone autonomy");
        }

        if (hasActiveMission(operatorId)) {
            throw new IllegalStateException("Operator already has an active mission");
        }

        Mission mission = new Mission(
                UUID.randomUUID().toString(),
                location,
                distanceKm,
                drone,
                operator,
                LocalDateTime.now(),
                MissionStatus.ACTIVE);

        drone.setAvailable(false);
        missions.add(mission);
        return mission;
    }

    private RescueOperator findOperator(String operatorId) {
        for (RescueOperator operator : operators) {
            if (operator.getId().equals(operatorId)) {
                return operator;
            }
        }
        return null;
    }

    private boolean hasActiveMission(String operatorId) {
        for (Mission mission : missions) {
            if (mission.getOperator().getId().equals(operatorId)
                    && mission.getStatus() == MissionStatus.ACTIVE) {
                return true;
            }
        }
        return false;
    }

    public Mission completeMission(String missionId) {
        Mission mission = findMission(missionId);
        mission.setStatus(MissionStatus.COMPLETED);
        mission.setEndDate(LocalDateTime.now());
        mission.getDrone().setAvailable(true);
        return mission;
    }

    private Mission findMission(String missionId) {
        for (Mission mission : missions) {
            if (mission.getId().equals(missionId)) {
                return mission;
            }
        }
        return null;
    }

    public boolean addOperator(RescueOperator operator) {
        return operators.add(operator);
    }
}
