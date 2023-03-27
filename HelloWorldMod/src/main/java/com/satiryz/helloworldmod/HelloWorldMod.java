package com.satiryz.helloworldmod;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.satiryz.helloworldmod.init.BlockInit;
import com.satiryz.helloworldmod.init.ItemInit;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HelloWorldMod.MODID)
public class HelloWorldMod
{
    public static final String MODID = "helloworldmod";
    // Directly reference a slf4j logger
    @SuppressWarnings("unused")
	private static final Logger LOGGER = LogUtils.getLogger();
    
    public HelloWorldMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        BlockInit.BLOCKS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::buildTabs);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        // LOGGER.info("HELLO FROM COMMON SETUP");
        // LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }
    
    @SubscribeEvent
    public void buildTabs(CreativeModeTabEvent.Register event) {
    	
        event.registerCreativeModeTab(new ResourceLocation(MODID, "items"),
        		(builder) -> {
                    builder.icon(() -> new ItemStack(ItemInit.HELLO_WORLD_ITEM.get()))
                    .title(Component.translatable("item_group." + MODID + ".items"))
                    .displayItems((enabledFlags, populator) -> {
                    	populator.accept(ItemInit.HELLO_WORLD_ITEM.get());
                    	populator.accept(BlockInit.HELLO_WORLD_BLOCK.get());
                    });
                }
        );
    }
}