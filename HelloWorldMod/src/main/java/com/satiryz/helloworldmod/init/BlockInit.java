package com.satiryz.helloworldmod.init;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.satiryz.helloworldmod.HelloWorldMod;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HelloWorldMod.MODID);
	
	public static final RegistryObject<Block> HELLO_WORLD_BLOCK = register("hello_world_block", 
			() -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST)
					.strength(3.0f).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()),
			object -> () -> new BlockItem(object.get(), new Item.Properties()));
    
	private static <T extends Block> RegistryObject<T> register(final String name, final Supplier<? extends T> block,
			Function<RegistryObject<T>, Supplier<? extends Item>> item) {
		RegistryObject<T> obj = registerBlock(name, block);
		ItemInit.ITEMS.register(name, item.apply(obj));
		return obj;
	}

	private static <T extends Block> RegistryObject<T> registerBlock(final String name,
			final Supplier<? extends T> block) {
		return BLOCKS.register(name, block);
	}
}