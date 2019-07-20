package net.mcreator.ruby_mod;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.potion.Potion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.block.Block;

import java.util.function.Supplier;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

@Mod(modid = ruby_mod.MODID, version = ruby_mod.VERSION)
public class ruby_mod implements IFuelHandler, IWorldGenerator {

	public static final String MODID = "ruby_mod";
	public static final String VERSION = "0.0.8";
	@SidedProxy(clientSide = "net.mcreator.ruby_mod.ClientProxyruby_mod", serverSide = "net.mcreator.ruby_mod.CommonProxyruby_mod")
	public static CommonProxyruby_mod proxy;
	@Instance(MODID)
	public static ruby_mod instance;
	public final List<ModElement> elements = new ArrayList<>();
	public final List<Supplier<Block>> blocks = new ArrayList<>();
	public final List<Supplier<Item>> items = new ArrayList<>();
	public final List<Supplier<Biome>> biomes = new ArrayList<>();
	public final List<Supplier<EntityEntry>> entities = new ArrayList<>();
	public final List<Supplier<Potion>> potions = new ArrayList<>();

	public ruby_mod() {
		FluidRegistry.enableUniversalBucket();
		elements.add(new MCreatorRuby(this));
		elements.add(new MCreatorRubyore(this));
		elements.add(new MCreatorRubysword(this));
		elements.add(new MCreatorRubypickaxe(this));
		elements.add(new MCreatorRubyshovel(this));
		elements.add(new MCreatorRubyhoe(this));
		elements.add(new MCreatorRubyaxe(this));
		elements.add(new MCreatorRubyswordcrafting(this));
		elements.add(new MCreatorRubypickaxecrafting(this));
		elements.add(new MCreatorRubyaxecrafting(this));
		elements.add(new MCreatorRubyshovelcrafting(this));
		elements.add(new MCreatorRubyhoecrafting(this));
		elements.add(new MCreatorRubyblock(this));
		elements.add(new MCreatorRubyArmor(this));
		elements.add(new MCreatorRubybattleaxe(this));
		elements.add(new MCreatorRubybattleaxecrafting(this));
		elements.add(new MCreatorRubybettersword(this));
		elements.add(new MCreatorRubynugget(this));
		elements.add(new MCreatorRubynuggercrafting(this));
		elements.add(new MCreatorRubycrafting(this));
		elements.add(new MCreatorRubyblockcrafting(this));
		elements.add(new MCreatorRubycrafting2(this));
		elements.add(new MCreatorHelmetcrafting(this));
		elements.add(new MCreatorBodycrafting(this));
		elements.add(new MCreatorLeggingscrafting(this));
		elements.add(new MCreatorBootscrafting(this));
		elements.add(new MCreatorBootscrafting2(this));
		elements.add(new MCreatorStonewithruby(this));
		elements.add(new MCreatorRubydirt(this));
		elements.add(new MCreatorRubybiome(this));
		elements.add(new MCreatorRubydimension(this));
		elements.add(new MCreatorRubybow(this));
	}

	@Override
	public int getBurnTime(ItemStack fuel) {
		for (ModElement element : elements) {
			int ret = element.addFuel(fuel);
			if (ret != 0)
				return ret;
		}
		return 0;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator cg, IChunkProvider cp) {
		elements.forEach(element -> element.generateWorld(random, chunkX * 16, chunkZ * 16, world, world.provider.getDimension(), cg, cp));
	}

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(blocks.stream().map(Supplier::get).toArray(Block[]::new));
	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(items.stream().map(Supplier::get).toArray(Item[]::new));
	}

	@SubscribeEvent
	public void registerBiomes(RegistryEvent.Register<Biome> event) {
		event.getRegistry().registerAll(biomes.stream().map(Supplier::get).toArray(Biome[]::new));
	}

	@SubscribeEvent
	public void registerEntities(RegistryEvent.Register<EntityEntry> event) {
		event.getRegistry().registerAll(entities.stream().map(Supplier::get).toArray(EntityEntry[]::new));
	}

	@SubscribeEvent
	public void registerPotions(RegistryEvent.Register<Potion> event) {
		event.getRegistry().registerAll(potions.stream().map(Supplier::get).toArray(Potion[]::new));
	}

	@SubscribeEvent
	public void registerSounds(RegistryEvent.Register<net.minecraft.util.SoundEvent> event) {
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		elements.forEach(element -> element.registerModels(event));
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		GameRegistry.registerFuelHandler(this);
		GameRegistry.registerWorldGenerator(this, 5);
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		elements.forEach(element -> element.preInit(event));
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		elements.forEach(element -> element.init(event));
		proxy.init(event);
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		elements.forEach(element -> element.serverLoad(event));
	}

	public static class GuiHandler implements IGuiHandler {

		@Override
		public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
			return null;
		}

		@Override
		public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
			return null;
		}
	}

	public static class ModElement {

		public static ruby_mod instance;

		public ModElement(ruby_mod instance) {
			this.instance = instance;
		}

		public void init(FMLInitializationEvent event) {
		}

		public void preInit(FMLPreInitializationEvent event) {
		}

		public void generateWorld(Random random, int posX, int posZ, World world, int dimID, IChunkGenerator cg, IChunkProvider cp) {
		}

		public void serverLoad(FMLServerStartingEvent event) {
		}

		public void registerModels(ModelRegistryEvent event) {
		}

		public int addFuel(ItemStack fuel) {
			return 0;
		}
	}
}
