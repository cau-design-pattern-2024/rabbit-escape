package rabbitescape.engine.logic;

import static org.hamcrest.MatcherAssert.*;
import static rabbitescape.engine.Tools.*;
import static rabbitescape.engine.textworld.TextWorldManip.*;

import org.junit.Test;

import rabbitescape.engine.World;

public class TestOnOff
{
	@Test
	public void Can_not_pass_the_active_block()
	{
		World world = createWorld(
			" r   V    ",
			"##########"
		);
			
		world.step();
			
		assertThat(
			renderWorld( world, true, false ),
			equalTo(
				"  r> V    ",
				"##########"
			)
		);
		
		world.step();
		
		assertThat(
			renderWorld( world, true, false ),
			equalTo(
				"   r>V    ",
				"##########"
			)
		);
		
		world.step();
		
		// Rabbit can not pass the active block
		assertThat(
			renderWorld( world, true, false ),
			equalTo(
				"    ?V    ",
				"##########"
			)
		);
		
		world.step();
		
		assertThat(
			renderWorld( world, true, false ),
			equalTo(
				"   <jV    ",
				"##########"
			)
		);
		
		world.step();
		
		assertThat(
			renderWorld( world, true, false ),
			equalTo(
				"  <j V    ",
				"##########"
			)
		);
	}
	
	@Test
	public void On_off_switches_by_bash_pass_the_deactive_block()
	{
		World world = createWorld(
			"ubj  V    ",
		    "##########"
		);
		
		world.step();
		
		// When rabbit bashes to on/off button, on/off switches.
		assertThat(
			renderWorld( world, true, false ),
			equalTo(
				"Jj   V    ",
				"##########"
			)
		);
		
		world.step();
		
		// Active block becomes deactive block.
		assertThat(
			renderWorld( world, true, false ),
			equalTo(
				"u|   v    ",
				"##########"
			)
		);
		
		world.step();
		
		assertThat(
			renderWorld( world, true, false ),
			equalTo(
				"ur>  v    ",
				"##########"
			)
		);
		
		world.step();
		
		assertThat(
			renderWorld( world, true, false ),
			equalTo(
				"u r> v    ",
				"##########"
			)
		);
		
		world.step();
		
		assertThat(
			renderWorld( world, true, false ),
			equalTo(
				"u  r>v    ",
				"##########"
			)
		);
		
		world.step();
		
		assertThat(
			renderWorld( world, true, false ),
			equalTo(
				"u   r>    ",
				"##########"
			)
		);
		
		world.step();
		
		// Rabbit can pass the deactive block
		assertThat(
			renderWorld( world, true, false ),
			equalTo(
				"u    r>   ",
				"##########"
			)
		);
		
		world.step();
		
		assertThat(
			renderWorld( world, true, false ),
			equalTo(
				"u    vr>  ",
				"##########"
			)
		);
		
		world.step();
		
		assertThat(
			renderWorld( world, true, false ),
			equalTo(
				"u    v r> ",
				"##########"
			)
		);
	}
}
