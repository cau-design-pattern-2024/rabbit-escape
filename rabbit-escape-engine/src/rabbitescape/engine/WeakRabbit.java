package rabbitescape.engine;

import rabbitescape.engine.behaviours.*;

// WeakRabbit은 힘이 약해서 bridging을 할 수 없음
public class WeakRabbit extends Rabbit {

    public WeakRabbit( int x, int y, Direction dir, Type type )
    {
        super(x, y, dir, type);
    }

    @Override
    protected void createBehaviours()
    {
        Climbing climbing = new Climbing();
        Digging digging = new Digging();
        Exploding exploding = new Exploding();
        Burning burning = new Burning();
        OutOfBounds outOfBounds = new OutOfBounds();
        Drowning drowning = new Drowning();
        Exiting exiting = new Exiting();
        Brollychuting brollychuting = new Brollychuting( climbing, digging );
        falling = new Falling( climbing, brollychuting, getFatalHeight() );
        Bashing bashing = new Bashing();
        Blocking blocking = new Blocking();
        Walking walking = new Walking();
        RabbotCrash rabbotCrash = new RabbotCrash();
        RabbotWait rabbotWait = new RabbotWait();

        behavioursTriggerOrder.add( exploding );
        behavioursTriggerOrder.add( outOfBounds );
        behavioursTriggerOrder.add( burning );
        behavioursTriggerOrder.add( drowning );
        behavioursTriggerOrder.add( rabbotCrash );
        behavioursTriggerOrder.add( falling );
        behavioursTriggerOrder.add( exiting );
        behavioursTriggerOrder.add( brollychuting );
        behavioursTriggerOrder.add( climbing );
        behavioursTriggerOrder.add( bashing );
        behavioursTriggerOrder.add( digging );
        behavioursTriggerOrder.add( blocking );
        behavioursTriggerOrder.add( rabbotWait );
        behavioursTriggerOrder.add( walking );

        behaviours.add( exploding );
        behaviours.add( outOfBounds );
        behaviours.add( burning );
        behaviours.add( drowning );
        behaviours.add( rabbotCrash );
        behaviours.add( falling );
        behaviours.add( exiting );
        behaviours.add( brollychuting );
        behaviours.add( bashing );
        behaviours.add( digging );
        behaviours.add( blocking );
        behaviours.add( climbing );
        behaviours.add( rabbotWait );
        behaviours.add( walking );

        assert behavioursTriggerOrder.size() == behaviours.size();
    }
}
