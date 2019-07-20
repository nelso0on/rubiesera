package net.mcreator.ruby_mod;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.Item;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

public class MCreatorRubyArmor extends ruby_mod.ModElement {

	@GameRegistry.ObjectHolder("ruby_mod:rubyarmorhelmet")
	public static final Item helmet = null;
	@GameRegistry.ObjectHolder("ruby_mod:rubyarmorbody")
	public static final Item body = null;
	@GameRegistry.ObjectHolder("ruby_mod:rubyarmorlegs")
	public static final Item legs = null;
	@GameRegistry.ObjectHolder("ruby_mod:rubyarmorboots")
	public static final Item boots = null;

	public MCreatorRubyArmor(ruby_mod instance) {
		super(instance);
		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("RUBYARMOR", "ruby_mod:ruby_", 50, new int[]{4, 7, 8, 4}, 9, null, 2.5f);
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.HEAD).setUnlocalizedName("rubyarmorhelmet")
				.setRegistryName("rubyarmorhelmet").setCreativeTab(CreativeTabs.COMBAT));
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.CHEST).setUnlocalizedName("rubyarmorbody")
				.setRegistryName("rubyarmorbody").setCreativeTab(CreativeTabs.COMBAT));
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.LEGS).setUnlocalizedName("rubyarmorlegs")
				.setRegistryName("rubyarmorlegs").setCreativeTab(CreativeTabs.COMBAT));
		instance.items.add(() -> new ItemArmor(enuma, 0, EntityEquipmentSlot.FEET).setUnlocalizedName("rubyarmorboots")
				.setRegistryName("rubyarmorboots").setCreativeTab(CreativeTabs.COMBAT));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(helmet, 0, new ModelResourceLocation("ruby_mod:rubyarmorhelmet", "inventory"));
		ModelLoader.setCustomModelResourceLocation(body, 0, new ModelResourceLocation("ruby_mod:rubyarmorbody", "inventory"));
		ModelLoader.setCustomModelResourceLocation(legs, 0, new ModelResourceLocation("ruby_mod:rubyarmorlegs", "inventory"));
		ModelLoader.setCustomModelResourceLocation(boots, 0, new ModelResourceLocation("ruby_mod:rubyarmorboots", "inventory"));
	}
}
