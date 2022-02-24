package simulation.objects;

public abstract class LightsLoop extends LoopObject{
    protected IntersectionObject intersection;
    protected long lastTime;

    public void setIntersection(IntersectionObject intersection) {
        this.intersection = intersection;
    }
}
