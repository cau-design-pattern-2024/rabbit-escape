package rabbitescape.engine;

import static rabbitescape.engine.ChangeDescription.State.*;

public class DelicateRabbitEntrance extends RabbitEntrance {
    public DelicateRabbitEntrance(int x, int y) {
        super(x, y, DELICATE_ENTRANCE);
    }

    @Override
    protected Rabbit createRabbit(int x, int y, Direction dir) {
        return new DelicateRabbit(x, y, dir, Rabbit.Type.RABBIT);
    }
}

