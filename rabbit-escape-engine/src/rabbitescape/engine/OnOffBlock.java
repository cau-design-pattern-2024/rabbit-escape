package rabbitescape.engine;

/** On-off block (with Decorator pattern) */
public class OnOffBlock extends Block
{
	public enum OnOffBlockType
	{
		ACTIVE_AT_ON,
		ACTIVE_AT_OFF,
	}

	public OnOffBlockType onOffBlockType;
	public boolean active;
	
	public OnOffBlock(int x, int y, Material material, Shape shape, int variant, OnOffBlockType onOffBlockType, boolean active)
	{
		super(x, y, material, shape, variant);
		
		this.onOffBlockType = onOffBlockType;
		this.active = active;
	}

	public OnOffBlock(Block block, OnOffBlockType onOffBlockType, boolean active)
	{
		super(block.x, block.y, block.material, block.shape, block.variant);
		
		this.onOffBlockType = onOffBlockType;
		this.active = active;
	}
}
