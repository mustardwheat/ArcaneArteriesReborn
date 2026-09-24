package com.keletu.arcane_arteries.event;

import WayofTime.bloodmagic.client.IMeshProvider;
import com.keletu.arcane_arteries.item.AAItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(value = Side.CLIENT)
public class ModelEvents {
    @SubscribeEvent
    public static void regModels(ModelRegistryEvent event) {
        defaultModel(AAItems.thaumicRuneItem);
        defaultModel(AAItems.botanyRuneItem);

        defaultModel(AAItems.mana_slate);
        defaultModel(AAItems.thaumic_slate);

        defaultModel(AAItems.botany_dagger_of_sacrifice);
        defaultModel(AAItems.thaumic_dagger_of_sacrifice);

        meshModel(AAItems.botany_sacrificial_dagger);
        meshModel(AAItems.thaumic_sacrificial_dagger);
    }

    static void defaultModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    // Same registration scheme Blood Magic uses for IMeshProvider items:
    // variants are declared up-front, then the mesh definition picks one per stack.
    static void meshModel(Item item) {
        IMeshProvider mesh = (IMeshProvider) item;
        ResourceLocation loc = mesh.getCustomLocation();
        if (loc == null)
            loc = item.getRegistryName();

        Set<String> variants = new HashSet<>();
        mesh.gatherVariants(variants::add);
        for (String variant : variants)
            ModelLoader.registerItemVariants(item, new ModelResourceLocation(loc, variant));

        ModelLoader.setCustomMeshDefinition(item, mesh.getMeshDefinition());
    }
}
