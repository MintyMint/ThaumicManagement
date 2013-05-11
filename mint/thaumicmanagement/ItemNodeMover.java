package mint.thaumicmanagement;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aura.AuraNode;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNodeMover extends ItemTM
{
	public String[] Directions = {"+X", "-X", "+Y", "-Y", "+Z", "-Z"};
	
	public ItemNodeMover(int id)
	{
		super(id);
		this.setCreativeTab(CreativeTabs.tabTools);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconregister)
    {
    	this.itemIcon = iconregister.registerIcon("thaumicmanagement:NodeMover");
    }

	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
        if(itemstack.stackTagCompound == null)
        {
            itemstack.setTagCompound(new NBTTagCompound());
            itemstack.stackTagCompound.setInteger("Direction", -1);
        }
    		
    	if (player.isSneaking() == false)
    	{
    		if (itemstack.stackTagCompound.getInteger("Direction") < 5)
    		{
    			itemstack.stackTagCompound.setInteger("Direction", (itemstack.stackTagCompound.getInteger("Direction") +1));
    		
    			if (this.getSide() == Side.CLIENT)
    			{
    				player.addChatMessage("Rune set to " + Directions[itemstack.stackTagCompound.getInteger("Direction")] + " mode.");
    			}
    		}
    		
    		else
    		{
    			itemstack.stackTagCompound.setInteger("Direction", -1);
    		}
    	}
    	
    	else
    	{
    		if (getKey(world, player) != -1)
    		{
    			if (this.getSide() == Side.SERVER)
    			{
    						 if (itemstack.stackTagCompound.getInteger("Direction") == 0) {ThaumcraftApi.queueNodeChanges(this.getKey(world, player), 0, 0, false, null, Float.parseFloat(ConfigHelper.NodeMoverDistance), 0, 0);}
    					else if (itemstack.stackTagCompound.getInteger("Direction") == 1) {ThaumcraftApi.queueNodeChanges(this.getKey(world, player), 0, 0, false, null, -Float.parseFloat(ConfigHelper.NodeMoverDistance), 0, 0);}
    					else if (itemstack.stackTagCompound.getInteger("Direction") == 2) {ThaumcraftApi.queueNodeChanges(this.getKey(world, player), 0, 0, false, null, 0, Float.parseFloat(ConfigHelper.NodeMoverDistance), 0);}
    					else if (itemstack.stackTagCompound.getInteger("Direction") == 3) {ThaumcraftApi.queueNodeChanges(this.getKey(world, player), 0, 0, false, null, 0, -Float.parseFloat(ConfigHelper.NodeMoverDistance), 0);}
    					else if (itemstack.stackTagCompound.getInteger("Direction") == 4) {ThaumcraftApi.queueNodeChanges(this.getKey(world, player), 0, 0, false, null, 0, 0, Float.parseFloat(ConfigHelper.NodeMoverDistance));}
    					else if (itemstack.stackTagCompound.getInteger("Direction") == 5) {ThaumcraftApi.queueNodeChanges(this.getKey(world, player), 0, 0, false, null, 0, 0, -Float.parseFloat(ConfigHelper.NodeMoverDistance));}
    			}
    		}
    		
    		else
    		{
        		if (this.getSide() == Side.CLIENT)
        		{
        			if (itemstack.stackTagCompound.getInteger("NodeKey") == -1) {player.addChatMessage("You can't move a nonexistant node...");}
        		}
    		}
    	}
    	
    	return itemstack;
    }
    	
    public int getKey(World world, EntityPlayer player)
    {
    	if (this.getSide() == Side.SERVER)
    	{
    		return ThaumcraftApi.getClosestAuraWithinRange(world, player.posX, player.posY, player.posZ, ConfigHelper.ToolRange);
    	}
    	
    	return -1;
    }
}
