package net.mcreator.ruby_mod;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.BiomeDictionary;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.biome.Biome;

import java.util.Random;

public class MCreatorRubybiome extends ruby_mod.ModElement {

	@GameRegistry.ObjectHolder("ruby_mod:rubybiome")
	public static final BiomeGenCustom biome = null;

	public MCreatorRubybiome(ruby_mod instance) {
		super(instance);
		instance.biomes.add(() -> new BiomeGenCustom());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.FOREST);
	}

	static class BiomeGenCustom extends Biome {

		public BiomeGenCustom() {
			super(new Biome.BiomeProperties("ruby").setRainfall(0.5F).setBaseHeight(0.1F).setHeightVariation(0.2F).setTemperature(0.5F));
			setRegistryName("rubybiome");
			topBlock = MCreatorRubydirt.block.getDefaultState();
			fillerBlock = MCreatorRubydirt.block.getDefaultState();
			decorator.generateFalls = false;
			decorator.treesPerChunk = 3;
			decorator.flowersPerChunk = 10;
			decorator.grassPerChunk = 10;
			decorator.deadBushPerChunk = 0;
			decorator.mushroomsPerChunk = 0;
			decorator.bigMushroomsPerChunk = 0;
			decorator.reedsPerChunk = 0;
			decorator.cactiPerChunk = 0;
			decorator.sandPatchesPerChunk = 0;
			decorator.gravelPatchesPerChunk = 0;
		}

		@Override
		public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
			return super.getRandomTreeFeature(rand);
		}
	}
}
