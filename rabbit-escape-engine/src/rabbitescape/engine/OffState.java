package rabbitescape.engine;

public class OffState extends OnOffState {
	public OffState(World world)
	{
		super(world);
	}
	
	@Override
	public void switchOnOff()
	{
		world.setState(world.getOnState());
		
		for ( Block block : world.blockTable )
    	{
    		if ( block instanceof OnOffBlock )
    		{
    			if ( ((OnOffBlock) block).onOffBlockType == OnOffBlock.OnOffBlockType.ACTIVE_AT_ON )
    			{
    				block.material = Block.Material.ONOFF_ACTIVE;
    			}
    			else
    			{
    				block.material = Block.Material.ONOFF_DEACTIVE;
    			}
    		}
    	}
	}
}
