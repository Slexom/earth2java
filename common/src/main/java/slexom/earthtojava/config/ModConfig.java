package slexom.earthtojava.config;


import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import slexom.earthtojava.Earth2JavaMod;

@Config(name = Earth2JavaMod.MOD_ID)
public class ModConfig implements ConfigData {

	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig albinoCow = new EntityConfig(8);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig amberChicken = new EntityConfig(10);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig ashenCow = new EntityConfig(8);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig boneSpider = new EntityConfig(10);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig boldStripedRabbit = new EntityConfig(8);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig boulderingZombie = new EntityConfig(15);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig bronzedChicken = new EntityConfig(10);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig cluckshroom = new EntityConfig(10);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig cookieCow = new EntityConfig(8);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig creamCow = new EntityConfig(8);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig dairyCow = new EntityConfig(8);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig fancyChicken = new EntityConfig(10);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig fleckedSheep = new EntityConfig(12);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig freckledRabbit = new EntityConfig(4);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig fuzzySheep = new EntityConfig(12);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig goldCrestedChicken = new EntityConfig(10);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig harelequinRabbit = new EntityConfig(4);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig hornedSheep = new EntityConfig(12);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig inkySheep = new EntityConfig(12);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig jollyLlama = new EntityConfig(10);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig jumboRabbit = new EntityConfig(4);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig lobberZombie = new EntityConfig(15);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig longNosedSheep = new EntityConfig(12);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig midnightChicken = new EntityConfig(10);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig moobloom = new EntityConfig(8);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig moolip = new EntityConfig(8);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig mottledPig = new EntityConfig(10);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig muddyPig = new EntityConfig(10);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig muddyFootRabbit = new EntityConfig(4);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig palePig = new EntityConfig(10);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig patchedSheep = new EntityConfig(12);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig piebaldPig = new EntityConfig(10);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig pinkFootedPig = new EntityConfig(10);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig pintoCow = new EntityConfig(8);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig rainbowSheep = new EntityConfig(12);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig rockySheep = new EntityConfig(12);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig skeletonWolf = new EntityConfig(8);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig skewbaldChicken = new EntityConfig(10);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig sootyPig = new EntityConfig(10);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig spottedPig = new EntityConfig(10);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig stormyChicken = new EntityConfig(10);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig sunsetCow = new EntityConfig(8);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig tropicalSlime = new EntityConfig(10);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig umbraCow = new EntityConfig(8);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig vestedRabbit = new EntityConfig(4);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig vilerWitch = new EntityConfig(1, 1, 1);
	@ConfigEntry.Category("entities")
	@ConfigEntry.Gui.CollapsibleObject
	public final EntityConfig woolyCow = new EntityConfig(8);


	public static class EntityConfig {
		public final boolean spawn;
		@ConfigEntry.BoundedDiscrete(min = 1, max = 100)
		public final int weight;
		@ConfigEntry.BoundedDiscrete(min = 1, max = 10)
		public final int groupMin;
		@ConfigEntry.BoundedDiscrete(min = 1, max = 10)
		public final int groupMax;

		EntityConfig(int weight, int groupMin, int groupMax) {
			spawn = true;
			this.weight = weight;
			this.groupMin = groupMin;
			this.groupMax = groupMax;
		}

		EntityConfig(int weight) {
			this(weight, 2, 4);
		}
	}

}