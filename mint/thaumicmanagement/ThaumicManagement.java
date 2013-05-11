package mint.thaumicmanagement;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
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
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "thaumicmanagement", name = "Thaumic Management", version = "1.0.0")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class ThaumicManagement
{
	public static Item NodePurifier;
	public static Item NodeCorruptor;
	public static Item NodeTransmutor;
	public static Item NodeMover;
	
	public static Block BlockNodePurifier;
	
	@SidedProxy(clientSide = "mint.thaumicmanagement.ClientProxy", serverSide = "mint.thaumicmanagement.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance("ThaumicManagement")
	public static ThaumicManagement instance;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigHelper.init(event.getSuggestedConfigurationFile());
		
		//proxy.registerSoundHandler();
	}

	@Init
	public void init(FMLInitializationEvent event)
	{
		proxy.registerRenderThings();
		
		NodePurifier = new ItemNodePurifier(ConfigHelper.NodePurifierID).setUnlocalizedName("NodePurifier");
		GameRegistry.registerItem(NodePurifier, "NodePurififyingWand");
		LanguageRegistry.addName(NodePurifier, "Node Purifying Wand");
		
		NodeCorruptor = new ItemNodeCorruptor(ConfigHelper.NodeCorruptorID).setUnlocalizedName("NodeCorruptor");
		GameRegistry.registerItem(NodeCorruptor, "NodeCorruptingWand");
		LanguageRegistry.addName(NodeCorruptor, "Node Corrupting Wand");
		
		//NodeTransmutor = new ItemNodeTransmutor(ConfigHelper.NodeTransmutorID).setUnlocalizedName("NodeTransmutor");
		//GameRegistry.registerItem(NodeTransmutor, "NodeTransmutingMirror");
		//LanguageRegistry.addName(NodeTransmutor, "Node Transmuting Mirror");
		
		NodeMover = new ItemNodeMover(ConfigHelper.NodeMoverID).setUnlocalizedName("NodeMover");
		GameRegistry.registerItem(NodeMover, "NodeMovingRune");
		LanguageRegistry.addName(NodeMover, "Node Moving Rune");
		
		BlockNodePurifier = new BlockNodePurifier(ConfigHelper.blockPurifierID, Material.cloth).setUnlocalizedName("BlockNodePurifier");
		GameRegistry.registerBlock(BlockNodePurifier, "BlockPurifier");
		LanguageRegistry.addName(BlockNodePurifier, "Node Purifying Block");	
	}

	@PostInit
	public static void postInit(FMLPostInitializationEvent event)
	{
		FMLLog.info("Thaumic Management: Looks like everything ran correctly!");
	}
}
