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
       	if(itemstack.stackTagCompound == null)
    	{
    		itemstack.setTagCompound(new NBTTagCompound() );
    	}

    	if (player.isSneaking() == false)
    	{
        	if (ThaumcraftApi.getClosestAuraWithinRange(world, player.posX, player.posY, player.posZ, ConfigHelper.ToolRange) != -1)
        	{
        		itemstack.stackTagCompound.setInteger("NodeKey", ThaumcraftApi.getClosestAuraWithinRange(world, player.posX, player.posY, player.posZ, ConfigHelper.ToolRange) );
        		player.addChatMessage("Wand locked on to node " + itemstack.stackTagCompound.getInteger("NodeKey"));
        	}
    		
        	else
        	{
                if (getSide() == Side.CLIENT)
                {
                	player.addChatMessage("Finding node...");
                }
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
    	//Subtracts the max used count from the current used count
    	//gives a number of ticks used instead of a count down from the max number of ticks usable
    	int ticksUsed = this.getMaxItemUseDuration(itemstack) - count;
    	
    	//checks to see if the number of ticks used [above] is a multiple of the configured number times 20 [20 ticks in a second]
    	if (ticksUsed % (ConfigHelper.CorruptorTimer*20) == 0)
    	{	
    		int nodeKey = itemstack.stackTagCompound.getInteger("NodeKey");
    		AuraNode keyedNode = ThaumcraftApi.getNodeCopy(nodeKey);
    		
    		if (keyedNode != null)
    		{
    			int randomTagID = TriggeredWorld.rand.nextInt(48);
    			EnumTag selectedTag = EnumTag.get(randomTagID);
    			
    			if (selectedTag != EnumTag.FLUX)
    			{
    				if (getSide() == Side.SERVER)
    				{
    					player.addChatMessage("Your wand befouls the node with some " + selectedTag.name + " aspect flux!");
    				}
    				
    				ThaumcraftApi.queueNodeChanges(nodeKey , 0, 0, false, new ObjectTags().add(selectedTag, ConfigHelper.CorruptorFluxAmount), 0 ,0, 0);
    			}
    			
    			else
    			{
    				player.addChatMessage("Your wand fizzles and does nothing...");
    			}
    		}
    		
    		else
    		{
         		if (getSide() == Side.SERVER)
        		{
        			if (keyedNode == null) {player.addChatMessage("Wand not set to a node id!");}
        		}
    		}
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
