package simulation.objects;

public class LightsSingleLoop extends LightsLoop {

    private double delta;
    private double duration;
    private boolean cycleOn;

    public LightsSingleLoop(double duration) {
        this.duration = duration;
        this.cycleOn = true;
        this.delta = 0;
        this.lastTime = System.currentTimeMillis();
    }

    @Override
    public void tick(long currentTime) {
        delta = currentTime - lastTime;
        if (delta >= duration * 1000) {
            if (cycleOn) {
                openRoads();
            } else {
                closeRoads();
            }
            cycleOn = !cycleOn;
            lastTime = currentTime;
        }
    }

    private void closeRoads() {
        this.intersection.getWestRoad().closeRoad();
        this.intersection.getSouthRoad().closeRoad();
        this.intersection.getNorthRoad().closeRoad();
        this.intersection.getEastRoad().closeRoad();
       // System.out.println("closing roads");
    }

    private void openRoads() {
        this.intersection.getWestRoad().openRoad();
        this.intersection.getSouthRoad().openRoad();
        this.intersection.getNorthRoad().openRoad();
        this.intersection.getEastRoad().openRoad();

       // System.out.println("opening roads");
    }


}

