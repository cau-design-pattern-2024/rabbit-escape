package rabbitescape.engine;

public class OnOffButton extends Block {
	public boolean on;
	
	public OnOffButton(int x, int y, Material material, Shape shape, int variant, boolean on)
	{
		super(x, y, material, shape, variant);
		this.on = on;
	}

	public OnOffButton(Block block, boolean on)
	{
		super(block.x, block.y, block.material, block.shape, block.variant);
		this.on = on;
	}
}
