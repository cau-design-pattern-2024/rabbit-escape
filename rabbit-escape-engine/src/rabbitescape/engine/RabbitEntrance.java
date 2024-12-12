package rabbitescape.engine;

import static rabbitescape.engine.Direction.*;

import java.util.HashMap;
import java.util.Map;

public abstract class RabbitEntrance extends Thing {
    private int[] delay = new int[1];
    private int timeToNextRabbit;
    private int rabbitEntranceCount = 0;
    protected World world;

    public RabbitEntrance(int x, int y, ChangeDescription.State appearance) {
        super(x, y, appearance);
        delay[0] = -1;
        timeToNextRabbit = 0;
        rabbitEntranceCount = 0;
    }

    @Override
    public void calcNewState(World world) {
        if (delay[0] == -1) {
            delay = world.rabbit_delay;
        }
    }

    @Override
    public void step(World world) {
        this.world = world;
        if (world.num_waiting <= 0) {
            return;
        }

        if (timeToNextRabbit == 0) {
            birthRabbit(world);
        }
        --timeToNextRabbit;
    }

    private void birthRabbit(World world) {
        int delayIndex = rabbitEntranceCount >= delay.length
            ? delay.length - 1
            : rabbitEntranceCount;

        rabbitEntranceCount++;
        timeToNextRabbit = delay[delayIndex];

        // 하위 클래스에서 구체적인 Rabbit 생성
        Rabbit rabbit = createRabbit(x, y + 1, RIGHT);
        world.changes.enterRabbit(rabbit);
        world.rabbitIndex(rabbit);
    }

    // 하위 클래스에서 구체적인 Rabbit을 생성하도록 강제
    protected abstract Rabbit createRabbit(int x, int y, Direction dir);

    @Override
    public Map<String, String> saveState(boolean runtimeMeta) {
        Map<String, String> ret = new HashMap<>();
        BehaviourState.addToStateIfGtZero(
            ret, "Entrance.timeToNextRabbit", timeToNextRabbit
        );
        return ret;
    }

    @Override
    public void restoreFromState(Map<String, String> state) {
        timeToNextRabbit = BehaviourState.restoreFromState(
            state, "Entrance.timeToNextRabbit", timeToNextRabbit
        );
    }

    @Override
    public String overlayText() {
        return (world == null)
            ? "Entrance"
            : "Entrance\n" + world.num_waiting + " to come";
    }
}
