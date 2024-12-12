package rabbitescape.engine;

import static rabbitescape.engine.ChangeDescription.State.*;

public class WeakRabbitEntrance extends RabbitEntrance {
    public WeakRabbitEntrance(int x, int y) {
        super(x, y, WEAK_ENTRANCE);
    }

    @Override
    protected Rabbit createRabbit(int x, int y, Direction dir) {
        return new WeakRabbit(x, y, dir, Rabbit.Type.RABBIT);
    }
}