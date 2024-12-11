package rabbitescape.engine;

public class OnOffButton extends Block {
	public OnOffButton(int x, int y, Material material, Shape shape, int variant)
	{
		super(x, y, material, shape, variant);
	}

	public OnOffButton(Block block)
	{
		super(block.x, block.y, block.material, block.shape, block.variant);
	}
}
