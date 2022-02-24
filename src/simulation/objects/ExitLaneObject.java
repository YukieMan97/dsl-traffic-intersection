package simulation.objects;

public class ExitLaneObject extends LaneObject {

    public ExitLaneObject(int xPos, int yPos, int length, Direction roadDir) {
        super(xPos, yPos, length, roadDir);
    }

    @Override
    public void tick() {
        //Does Nothing
    }
}
