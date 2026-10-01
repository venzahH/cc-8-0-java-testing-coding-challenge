import enums.Direction;
import enums.TurnDirection;
import model.AutonomousVehicle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AutonomousVehicleTest {
    AutonomousVehicle vehicle;

    /**
     * Sets up an AutonomousVehicle object for later use
     *
     * Result: Successfully creates a Tesla
     */
    @Before
    public void setup() {
        vehicle = new AutonomousVehicle(3, "Tesla", "Model Y", "Black");
    }

    /**
     * Calls finalDirection with an empty list
     *
     * Result: Direction should be default (North)
     */
    @Test
    public void testFinalDirectionNoDirections() {
        TurnDirection[] turnDirections = {};
        assertEquals(Direction.NORTH, vehicle.finalDirection(turnDirections));
    }

    /**
     * Calls finalDirection with valid directions
     *
     * Result: Direction should be East
     */
    @Test
    public void testFinalDirectionWithDirections() {
        TurnDirection[] turnDirections = {TurnDirection.RIGHT, TurnDirection.RIGHT, TurnDirection.RIGHT, TurnDirection.LEFT, TurnDirection.RIGHT, TurnDirection.RIGHT, TurnDirection.RIGHT, TurnDirection.RIGHT, TurnDirection.LEFT};
        assertEquals(Direction.EAST, vehicle.finalDirection(turnDirections));
    }

    /**
     * Calls finalDirection with 4 lefts (360 clockwise)
     *
     * Result: Direction should be North
     */
    @Test
    public void testFinalDirectionFullClockwise(){
        TurnDirection[] turnDirections = {TurnDirection.RIGHT, TurnDirection.RIGHT, TurnDirection.RIGHT, TurnDirection.RIGHT};
        assertEquals(Direction.NORTH, vehicle.finalDirection(turnDirections));
    }

    /**
     * Calls finalDirection with 4 lefts (360 counterclockwise)
     *
     * Result: Direction should be North
     */
    @Test
    public void testFinalDirectionFullCounterClockwise(){
        TurnDirection[] turnDirections = {TurnDirection.LEFT, TurnDirection.LEFT, TurnDirection.LEFT, TurnDirection.LEFT};
        assertEquals(Direction.NORTH, vehicle.finalDirection(turnDirections));
    }
}
