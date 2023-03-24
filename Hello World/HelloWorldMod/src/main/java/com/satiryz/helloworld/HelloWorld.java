package com.satiryz.helloworld;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("helloworld")
public class HelloWorld {
	
	public static final String MOD_ID = "helloworld";
	
	public HelloWorld(){
		MinecraftForge.EVENT_BUS.register(this);
	};
};
