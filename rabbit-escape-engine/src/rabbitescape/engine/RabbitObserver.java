package rabbitescape.engine;

public interface RabbitObserver {
    void onRabbitStateChanged(Rabbit rabbit, ChangeDescription.State newState);
}
