package com.keletu.arcane_arteries;

import WayofTime.bloodmagic.core.RegistrarBloodMagicItems;
import com.keletu.arcane_arteries.block.AABlocks;
import com.keletu.arcane_arteries.item.AAItems;
import com.keletu.arcane_arteries.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import vazkii.botania.api.BotaniaAPI;

@Mod(
        modid = ArcaneArteriesReborn.MOD_ID,
        name = ArcaneArteriesReborn.MOD_NAME,
        version = ArcaneArteriesReborn.VERSION,
        dependencies = "required-after:bloodmagic; required-after:thaumcraft; required-after:botania"
)
public class ArcaneArteriesReborn {

    public static final String MOD_ID = "arcane_arteries";
    public static final String MOD_NAME = "Arcane Arteries Reborn";
    public static final String VERSION = "1.0.0";

    @SidedProxy(clientSide = "com.keletu.arcane_arteries.proxy.ClientProxy", serverSide = "com.keletu.arcane_arteries.proxy.CommonProxy")
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);

        // Thaumcraft only parses research files whose locations were registered;
        // must happen before its loader runs, so do it in preInit.
        ThaumcraftApi.registerResearchLocation(new ResourceLocation(MOD_ID, "research/research"));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        proxy.init(event);

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MOD_ID, "thaumic_dagger"),
                new CrucibleRecipe(/*"DAGGER"*/"", new ItemStack(AAItems.thaumic_sacrificial_dagger), RegistrarBloodMagicItems.SACRIFICIAL_DAGGER, new AspectList().add(Aspect.MAGIC, 20).add(Aspect.EARTH, 20)));

        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(MOD_ID, "thaumic_sacrificial_dagger"),
                new InfusionRecipe(/*"SACRIFICIAL_DAGGER"*/"", new ItemStack(AAItems.thaumic_dagger_of_sacrifice, 1), 7, (new AspectList()).add(Aspect.MAGIC, 30).add(Aspect.LIFE, 30), new ItemStack(AAItems.thaumic_sacrificial_dagger), "ingotThaumium", "ingotThaumium"));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(MOD_ID, "thaumic_slate"),
                new CrucibleRecipe(/*"RUNE"*/"", new ItemStack(AAItems.thaumic_slate), Blocks.STONE, new AspectList().add(Aspect.MAGIC, 5).add(Aspect.EARTH, 5)));

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(MOD_ID, "thaumic_rune"),
                new ShapedArcaneRecipe(
                        new ResourceLocation(MOD_ID, "thaumic_rune"),
                        /*"RUNE"*/"",
                        50,
                        new AspectList().add(Aspect.FIRE, 2).add(Aspect.ORDER, 2),
                        new ItemStack(AABlocks.thaumic_rune, 1),
                        " S ",
                        "ABA",
                        " S ",
                        'A', "ingotThaumium",
                        'S', new ItemStack(AAItems.thaumic_slate),
                        'B', new ItemStack(Blocks.STONE)));

        BotaniaAPI.registerRuneAltarRecipe(new ItemStack(AAItems.botany_sacrificial_dagger), 2000, "ingotElvenElementium", "ingotElvenElementium", new ItemStack(RegistrarBloodMagicItems.SACRIFICIAL_DAGGER));
        BotaniaAPI.registerManaAlchemyRecipe(new ItemStack(AAItems.botany_dagger_of_sacrifice), new ItemStack(AAItems.botany_sacrificial_dagger), 6000);

        BotaniaAPI.registerManaInfusionRecipe(new ItemStack(AAItems.mana_slate, 1), new ItemStack(Blocks.STONE), 3000);

    }

    public static final CreativeTabs creativeTab = new CreativeTabs(MOD_ID) {
        @SideOnly(Side.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(RegistrarBloodMagicItems.DAGGER_OF_SACRIFICE);
        }
    };
}
