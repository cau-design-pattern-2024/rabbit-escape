package rabbitescape.engine.textworld;

import static rabbitescape.engine.Direction.*;

import java.util.List;

import rabbitescape.engine.ChangeDescription.State;
import rabbitescape.engine.DelicateRabbit;
import rabbitescape.engine.FragileRabbit;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.WeakRabbit;

public class RabbitRenderer
{
    public static void render( 
        Chars chars, 
        List<Rabbit> rabbits,
        boolean runtimeMeta 
    )
    {
        for ( Rabbit rabbit : rabbits )
        {
            if ( State.RABBIT_OUT_OF_BOUNDS == rabbit.state )
            {
                continue;
            }
            chars.set(
                rabbit.x,
                rabbit.y,
                charForRabbit( rabbit ),
                rabbit.saveState( runtimeMeta )
            );
        }
    }

    private static char charForRabbit( Rabbit rabbit )
    {
        if ( rabbit instanceof WeakRabbit )
        {
            return 'w';
        }else if ( rabbit instanceof FragileRabbit )
        {
            return 'f';
        }else if ( rabbit instanceof DelicateRabbit )
        {
            return 'e';
        }
        if ( rabbit.dir == RIGHT )
        {
            if ( rabbit.type == Rabbit.Type.RABBIT )
            {
                return 'r';
            }
            else
            {
                return 't';
            }
        }
        else
        {
            if ( rabbit.type == Rabbit.Type.RABBIT )
            {
                return 'j';
            }
            else
            {
                return 'y';
            }
        }
    }
}
