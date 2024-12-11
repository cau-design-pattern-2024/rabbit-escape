package rabbitescape.engine;

public abstract class OnOffState {
	protected World world;
	
	protected OnOffState(World world)
	{
		this.world = world;
	}
	
	public abstract void switchOnOff();
}