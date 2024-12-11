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
	
	public OnOffBlock(int x, int y, Material material, Shape shape, int variant, OnOffBlockType onOffBlockType)
	{
		super(x, y, material, shape, variant);
		
		this.onOffBlockType = onOffBlockType;
	}

	public OnOffBlock(Block block, OnOffBlockType onOffBlockType)
	{
		super(block.x, block.y, block.material, block.shape, block.variant);
		
		this.onOffBlockType = onOffBlockType;
	}
}
