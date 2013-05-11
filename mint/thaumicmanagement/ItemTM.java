package mint.thaumicmanagement;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.item.Item;
import cpw.mods.fml.common.FMLCommonHandler;

public class ItemTM extends Item
{

	public ItemTM(int id)
	{
		super(id);
	}
	
	public Side getSide()
	{
		return FMLCommonHandler.instance().getEffectiveSide();
	}

}
