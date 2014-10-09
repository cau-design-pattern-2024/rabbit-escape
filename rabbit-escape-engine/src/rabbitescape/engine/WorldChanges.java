package rabbitescape.engine;

import java.util.ArrayList;
import java.util.List;

import rabbitescape.engine.World.NoBlockFound;
import rabbitescape.engine.World.NoSuchAbilityInThisWorld;
import rabbitescape.engine.World.NoneOfThisAbilityLeft;
import rabbitescape.engine.World.UnableToAddToken;

public class WorldChanges
{
    private final World world;

    private final List<Rabbit> rabbitsToEnter = new ArrayList<Rabbit>();
    private final List<Rabbit> rabbitsToKill  = new ArrayList<Rabbit>();
    private final List<Rabbit> rabbitsToSave  = new ArrayList<Rabbit>();
    private final List<Token>  tokensToAdd    = new ArrayList<Token>();
    private final List<Token>  tokensToRemove = new ArrayList<Token>();
    private final List<Block>  blocksToAdd    = new ArrayList<Block>();
    private final List<Block>  blocksToRemove = new ArrayList<Block>();

    public WorldChanges( World world )
    {
        this.world = world;
    }

    public synchronized void apply()
    {
        // Add any new things
        for ( Rabbit rabbit : rabbitsToEnter )
        {
            rabbit.calcNewState( world );
        }
        world.rabbits.addAll( rabbitsToEnter );
        world.things.addAll( tokensToAdd );
        world.blocks.addAll( blocksToAdd );

        // Remove dead/saved rabbits, used tokens, dug out blocks
        world.rabbits.removeAll( rabbitsToKill );
        world.rabbits.removeAll( rabbitsToSave );
        world.things.removeAll(  tokensToRemove );
        world.blocks.removeAll(  blocksToRemove );

        rabbitsToEnter.clear();
        rabbitsToKill.clear();
        rabbitsToSave.clear();
        tokensToAdd.clear();
        tokensToRemove.clear();
        blocksToAdd.clear();
        blocksToRemove.clear();
    }

    public synchronized void enterRabbit( Rabbit rabbit )
    {
        --world.num_waiting;
        rabbitsToEnter.add( rabbit );
    }

    public synchronized void killRabbit( Rabbit rabbit )
    {
        ++world.num_killed;
        rabbitsToKill.add( rabbit );
    }

    public synchronized void saveRabbit( Rabbit rabbit )
    {
        ++world.num_saved;
        rabbitsToSave.add( rabbit );
    }

    public synchronized void addToken( int x, int y, Token.Type type )
    throws UnableToAddToken
    {
        Integer numLeft = world.abilities.get( type );

        if ( numLeft == null )
        {
            throw new NoSuchAbilityInThisWorld( type );
        }

        if ( numLeft == 0 )
        {
            throw new NoneOfThisAbilityLeft( type );
        }

        tokensToAdd.add( new Token( x, y, type ) );
        world.abilities.put( type, numLeft - 1 );
    }

    public synchronized void removeToken( Token thing )
    {
        tokensToRemove.add( thing );
    }

    public synchronized void addBlock( Block block )
    {
        blocksToAdd.add( block );
    }

    public synchronized void removeBlockAt( int x, int y )
    {
        Block block = world.getBlockAt( x, y );
        if ( block == null )
        {
            throw new NoBlockFound( x, y );
        }
        blocksToRemove.add( block );
    }

    public synchronized List<Thing> tokensAboutToAppear()
    {
        return new ArrayList<Thing>( tokensToAdd );
    }
}
