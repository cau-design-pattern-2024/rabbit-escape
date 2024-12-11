package rabbitescape.engine;

public class OnState extends OnOffState {
	public OnState(World world)
	{
		super(world);
	}
	
	@Override
	public void switchOnOff()
	{
		world.setState(world.getOffState());
		
		for ( Block block : world.blockTable )
    	{
    		if ( block instanceof OnOffBlock )
    		{
    			if ( ((OnOffBlock) block).onOffBlockType == OnOffBlock.OnOffBlockType.ACTIVE_AT_ON )
    			{
    				block.material = Block.Material.ONOFF_DEACTIVE;
    			}
    			else
    			{
    				block.material = Block.Material.ONOFF_ACTIVE;
    			}
    		}
    	}
	}
}
