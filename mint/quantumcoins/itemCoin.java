package mint.quantumcoins;

import java.util.List;
import java.util.logging.Level;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class itemCoin extends Item
{	
	private boolean rang = false;
	
	private int converting;
	
	private float time = (float)ConfigHelper.convertTime;
	
	public itemCoin(int ID)
	{
		super(ID);
		setHasSubtypes(true);
		setMaxStackSize(64);
		setMaxDamage(0);
		setCreativeTab(CreativeTabs.tabMisc);
	}

	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 75000;
    }

    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }
    
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
    	player.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));

        return itemstack;
    }
    
    public void onUsingItemTick(ItemStack itemstack, EntityPlayer player, int count)
    {
    	//takes the max use number and subtracts it from the current in use count
    	//gives a ticks held instad of a ticks count down
    	int var6 = this.getMaxItemUseDuration(itemstack) - count;
        
        //turns var6 into seconds used instead of ticks used [i think]
        float var7 = (float)var6 / 20.0F; var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;
        
        FMLLog.log(Level.INFO, ""+var6);
        
        converting = itemstack.getItemDamage();
        
        if ((var7 >= time) && (rang == false))
        {
        	player.worldObj.spawnParticle("smoke", player.posX, player.posY, player.posZ, 0.0D, 0.0D, 0.0D);
        	rang = true;
        }
    }
    
    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int count)
    {
        //subtracts the itemInUseCount [that i dont fully understand at this point] from the max use duration//
    	int var6 = this.getMaxItemUseDuration(itemstack) - count;
        
        //all i know is it turns the result from var6 into a float//
        float var7 = (float)var6 / 20.0F;
        var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;
        
        if (var7 >= time)
        {
        	if (player.isSneaking())
        	{       	
        		if (itemstack.getItemDamage() != 0)
        		{
        			itemstack.stackSize--;
        			
        			world.playSoundAtEntity(player, "mint.quantumcoins.conversion", 1.0F, 1.0F);
            	
        			if (player.inventory.addItemStackToInventory(new ItemStack(QuantumCoins.coin, ConfigHelper.convertAmount, itemstack.getItemDamage() - 1)) == false)
        			{
        				player.dropPlayerItem(new ItemStack(QuantumCoins.coin, ConfigHelper.convertAmount, itemstack.getItemDamage() - 1));
        			}
        		}
        	}
        		 
        	else
        	{
        		if ((itemstack.stackSize >= ConfigHelper.convertAmount) && (itemstack.getItemDamage() != ConfigHelper.coinNum - 1))
        		{
        			itemstack.stackSize -= ConfigHelper.convertAmount; 
        			
        			world.playSoundAtEntity(player, "mint.quantumcoins.conversion", 1.0F, 0.0F);

        			if (player.inventory.addItemStackToInventory(new ItemStack(QuantumCoins.coin, 1, itemstack.getItemDamage() + 1)) == false)
        			{
        				player.dropPlayerItem(new ItemStack(QuantumCoins.coin, 1, itemstack.getItemDamage() +  1));
        			}
        		}
        	}
        }
           
        else
        {
        	player.worldObj.spawnParticle("smoke", player.posX, player.posY, player.posZ, 0.0D, 0.0D, 0.0D);
        }
        
        rang = false;
        player.inventoryContainer.detectAndSendChanges();
    }
   
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconregister)
    {
    	if (ConfigHelper.useAnim == true) {itemIcon = iconregister.registerIcon("quantumcoins:animCoin");}
    	else {itemIcon = iconregister.registerIcon("quantumcoins:coin");}
    }
    
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemstack, int par2)
    {
    	int color = Integer.parseInt(ConfigHelper.coinColor[itemstack.getItemDamage()], 16);
		return color;
    }
    
    public String getItemDisplayName(ItemStack itemstack)
    {
    	String name = "\u00A7" + ConfigHelper.coinNameColor[itemstack.getItemDamage()] +
    				  ConfigHelper.coinTypeName[itemstack.getItemDamage()] + " " + ConfigHelper.coinName;
    	return name;
    }
    
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		if (ConfigHelper.coinTypeInfo[itemstack.getItemDamage()].equals("none") == false)
		{list.add(ConfigHelper.coinTypeInfo[itemstack.getItemDamage()]);}
	}
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int var4 = 0; var4 < ConfigHelper.coinNum; ++var4)
        {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }
    
}
