package mint.thaumicmanagement;

import java.util.logging.Level;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumcraft.api.EnumTag;
import thaumcraft.api.ObjectTags;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aura.AuraNode;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNodeCorruptor extends ItemTM
{	
	World TriggeredWorld = null;
	
	public ItemNodeCorruptor(int id)
	{
		super(id);
		this.setCreativeTab(CreativeTabs.tabTools);
	}
	
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
    	if (player.isSneaking() == false)
    	{
    		itemstack.setTagCompound(new NBTTagCompound());
    		itemstack.stackTagCompound.setInteger("NodeKey", ThaumcraftApi.getClosestAuraWithinRange(world, player.posX, player.posY, player.posZ, ConfigHelper.ToolRange));
    		
        	if (itemstack.stackTagCompound.getInteger("NodeKey") != -1)
        	{
        		player.addChatMessage("Seting Tool to use node key " + itemstack.stackTagCompound.getInteger("NodeKey"));
        	}
    		
        	else
        	{
        		player.addChatMessage("Finding node...");
        	}
    	}
    	
    	else
    	{
    		TriggeredWorld = world;
    		player.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
    	}
        
    	return itemstack;
    }
    
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 75000;
    }

    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.block;
    }

    public void onUsingItemTick(ItemStack itemstack, EntityPlayer player, int count)
    {    	  	
    	AuraNode KeyedNode = ThaumcraftApi.getNodeCopy(itemstack.stackTagCompound.getInteger("NodeKey"));
    	
    	int TicksUsed = 0;

    	//Subtracts the max used count from the current used count
    	//gives a number of ticks used instead of a count down from the max number of ticks useable
    	TicksUsed = this.getMaxItemUseDuration(itemstack) - count;
    	
    	//checks to see if the number of ticks used [above] is a multiple of the configured number times 20 [20 ticks in a second]
    	if (TicksUsed % (ConfigHelper.CorruptorTimer*20) == 0)
    	{	
    		if (KeyedNode != null)
    		{
    			int RandomTagID = TriggeredWorld.rand.nextInt(48);
    			EnumTag SelectedTag = EnumTag.get(RandomTagID);
    			
    			if (SelectedTag != EnumTag.FLUX)
    			{
    				player.addChatMessage("Your wand befouls the node with some " + SelectedTag.name + " aspect flux!");
    				ThaumcraftApi.queueNodeChanges(0,0,0,false, new ObjectTags().add(SelectedTag, ConfigHelper.CorruptorFluxAmount), 0 ,0, 0);
    			}
    			
    			else {player.addChatMessage("Your wand does nothing...");}
    			
    		}
    		
    		else {player.addChatMessage("You can't corrupt a nonexistant node...");}
    		
    	}
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconregister)
    {
    	this.itemIcon = iconregister.registerIcon("thaumicmanagement:NodeCorruptor");
    }
    
    public boolean isFull3D()
    {
        return true;
    }

}
