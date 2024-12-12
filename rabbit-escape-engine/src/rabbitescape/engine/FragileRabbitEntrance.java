package rabbitescape.engine;

import static rabbitescape.engine.ChangeDescription.State.*;

public class FragileRabbitEntrance extends RabbitEntrance {
    public FragileRabbitEntrance(int x, int y) {
        super(x, y, FRAGILE_ENTRANCE);
    }

    @Override
    protected Rabbit createRabbit(int x, int y, Direction dir) {
        return new FragileRabbit(x, y, dir, Rabbit.Type.RABBIT);
    }
}
