package simulation.objects;

public class LaneSingleLoop extends LaneLoop {

    private double delta;
    private double duration;
    private boolean cycleOn;

    public LaneSingleLoop (LaneObject lane, double duration) {
        super(lane);
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
                laneObject.openLane();
            } else {
                laneObject.closeLane();
            }
            cycleOn = !cycleOn;
            lastTime = currentTime;
        }
    }

}