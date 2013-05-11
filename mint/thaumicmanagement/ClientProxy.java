package mint.thaumicmanagement;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{
	public static int PurifierBlockRenderID;
	public static int BlockRenderStage = 0;
    
	@Override
    public void registerRenderThings()
    {
		PurifierBlockRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderPurifierBlock());
    }
	
	/*public void registerSoundHandler()
	{
		MinecraftForge.EVENT_BUS.register(new SoundHandler());
	}*/
   
}
