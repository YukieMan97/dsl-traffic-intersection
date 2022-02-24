package simulation.objects;


public abstract class LaneLoop extends LoopObject {

    protected LaneObject laneObject;
    protected long lastTime;

    public LaneLoop(LaneObject lane) {
        this.laneObject = lane;
    }

}
