package mint.thaumicmanagement;

import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mint.thaumicmanagement.ConfigHelper;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumcraft.api.EnumTag;
import thaumcraft.api.ObjectTags;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aura.AuraNode;

public class ItemNodePurifier extends ItemTM
{	
	public ItemNodePurifier(int id)
	{
		super(id);
		setCreativeTab(CreativeTabs.tabTools);
	}
	
	public boolean getShareTag()
    {
        return true;
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
    		int nodeKey = itemstack.stackTagCompound.getInteger("NodeKey");
    		AuraNode keyedNode = ThaumcraftApi.getNodeCopy(nodeKey);
    		
    		if (keyedNode != null)
    		{
    			purify(nodeKey, keyedNode);
    			ThaumcraftApi.queueNodeChanges(nodeKey, (keyedNode.baseLevel - keyedNode.level), 0, false, null, 0, 0, 0);
    			if (getSide() == Side.SERVER)
    			{
    				player.addChatMessage("Node purified!");
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
    	
    	return itemstack;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconregister)
    {
    	this.itemIcon = iconregister.registerIcon("thaumicmanagement:NodePurifier");
    }
    
    public boolean isFull3D()
    {
        return true;
    }
    
    //fucking ugly as shit. tried putting this in a loop but it didnt work! DONT JUDGE ME!
    public void purify(int nodekey, AuraNode keyednode)
    {
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.ARMOR, keyednode.flux.getAmount(EnumTag.ARMOR)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.BEAST, keyednode.flux.getAmount(EnumTag.BEAST)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.CLOTH, keyednode.flux.getAmount(EnumTag.CLOTH)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.COLD, keyednode.flux.getAmount(EnumTag.COLD)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.CONTROL, keyednode.flux.getAmount(EnumTag.CONTROL)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.CRAFT, keyednode.flux.getAmount(EnumTag.CRAFT)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.CROP, keyednode.flux.getAmount(EnumTag.CROP)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.CRYSTAL, keyednode.flux.getAmount(EnumTag.CRYSTAL)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.DARK, keyednode.flux.getAmount(EnumTag.DARK)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.DESTRUCTION, keyednode.flux.getAmount(EnumTag.DESTRUCTION)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.EARTH, keyednode.flux.getAmount(EnumTag.EARTH)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.ELDRITCH, keyednode.flux.getAmount(EnumTag.ELDRITCH)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.EVIL, keyednode.flux.getAmount(EnumTag.EVIL)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.EXCHANGE, keyednode.flux.getAmount(EnumTag.EXCHANGE)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.FIRE, keyednode.flux.getAmount(EnumTag.FIRE)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.FLESH, keyednode.flux.getAmount(EnumTag.FLESH)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.FLIGHT, keyednode.flux.getAmount(EnumTag.FLIGHT)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.FLOWER, keyednode.flux.getAmount(EnumTag.FLOWER)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.FLUX, keyednode.flux.getAmount(EnumTag.FLUX)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.FUNGUS, keyednode.flux.getAmount(EnumTag.FUNGUS)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.HEAL, keyednode.flux.getAmount(EnumTag.HEAL)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.INSECT, keyednode.flux.getAmount(EnumTag.INSECT)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.KNOWLEDGE, keyednode.flux.getAmount(EnumTag.KNOWLEDGE)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.LIFE, keyednode.flux.getAmount(EnumTag.LIFE)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.LIGHT, keyednode.flux.getAmount(EnumTag.LIGHT)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.MAGIC, keyednode.flux.getAmount(EnumTag.MAGIC)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.MECHANISM, keyednode.flux.getAmount(EnumTag.MECHANISM)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.METAL, keyednode.flux.getAmount(EnumTag.METAL)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.MOTION, keyednode.flux.getAmount(EnumTag.MOTION)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.PLANT, keyednode.flux.getAmount(EnumTag.PLANT)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.POISON, keyednode.flux.getAmount(EnumTag.POISON)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.POWER, keyednode.flux.getAmount(EnumTag.POWER)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.PURE, keyednode.flux.getAmount(EnumTag.PURE)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.ROCK, keyednode.flux.getAmount(EnumTag.ROCK)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.SOUND, keyednode.flux.getAmount(EnumTag.SOUND)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.SPIRIT, keyednode.flux.getAmount(EnumTag.SPIRIT)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.TIME, keyednode.flux.getAmount(EnumTag.TIME)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.TOOL, keyednode.flux.getAmount(EnumTag.TOOL)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.TRAP, keyednode.flux.getAmount(EnumTag.TRAP)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.VALUABLE, keyednode.flux.getAmount(EnumTag.VALUABLE)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.VISION, keyednode.flux.getAmount(EnumTag.VISION)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.VOID, keyednode.flux.getAmount(EnumTag.VOID)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.WATER, keyednode.flux.getAmount(EnumTag.WATER)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.WEAPON, keyednode.flux.getAmount(EnumTag.WEAPON)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.WEATHER, keyednode.flux.getAmount(EnumTag.WEATHER)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.WIND, keyednode.flux.getAmount(EnumTag.WIND)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(nodekey, 0, 0, false, new ObjectTags().remove(EnumTag.WOOD, keyednode.flux.getAmount(EnumTag.WOOD)), 0 ,0, 0);
    }
}
