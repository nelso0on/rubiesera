package net.mcreator.ruby_mod;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.Item;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import java.util.Set;
import java.util.HashMap;

public class MCreatorRubyshovel extends ruby_mod.ModElement {

	@GameRegistry.ObjectHolder("ruby_mod:rubyshovel")
	public static final Item block = null;

	public MCreatorRubyshovel(ruby_mod instance) {
		super(instance);
		instance.items.add(() -> new ItemSpade(EnumHelper.addToolMaterial("RUBYSHOVEL", 1, 900, 10f, 2.5f, 2)) {

			public Set<String> getToolClasses(ItemStack stack) {
				HashMap<String, Integer> ret = new HashMap<String, Integer>();
				ret.put("spade", 1);
				return ret.keySet();
			}
		}.setUnlocalizedName("rubyshovel").setRegistryName("rubyshovel").setCreativeTab(CreativeTabs.TOOLS));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("ruby_mod:rubyshovel", "inventory"));
	}
}
