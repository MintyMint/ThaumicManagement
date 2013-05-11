package mint.quantumcoins;

import java.util.logging.Level;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;



@Mod(modid = "quantumcoins", name = "Quantum Coins", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class QuantumCoins
{	
	public static Item coin;
	
	@SidedProxy(clientSide = "mint.quantumcoins.ClientProxy", serverSide = "mint.quantumcoins.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance("Coins")
	public static QuantumCoins instance;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigHelper.init(event.getSuggestedConfigurationFile());
		
		proxy.registerSoundHandler();
	}

	@Init
	public void init(FMLInitializationEvent event)
	{
		coin = new itemCoin(ConfigHelper.coinID).setUnlocalizedName("coin");
		GameRegistry.registerItem(coin, "coin");
		
		proxy.registerRenderThings();
	}

	@PostInit
	public static void postInit(FMLPostInitializationEvent event)
	{
		FMLLog.info("Quantum Coins: Looks like everything ran correctly!");
	}
	
}