package mint.thaumicmanagement;

import java.util.Random;
import java.util.logging.Level;

import thaumcraft.api.EnumTag;
import thaumcraft.api.ObjectTags;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aura.AuraNode;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockNodePurifier extends Block
{
	public static Icon TopTexture;
	public static Icon SideTexture;
	public static Icon BottomTexture;
	public static Icon UnderTexture;
	
	public BlockNodePurifier(int id, Material material)
	{
		super(id, material);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setTickRandomly(true);
		this.setHardness(-1);
	}
	
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		int NodeKey = ThaumcraftApi.getClosestAuraWithinRange(world, x, y, z, ConfigHelper.ToolRange);
		AuraNode KeyedNode = null;
		
		if (KeyedNode == null)
		{
			KeyedNode = ThaumcraftApi.getNodeCopy(NodeKey);
		}
		
		if (KeyedNode != null)
		{
			this.purify(NodeKey, KeyedNode);
			ThaumcraftApi.queueNodeChanges(NodeKey, (KeyedNode.baseLevel - KeyedNode.level), 0, false, null, 0, 0, 0);
		}
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconregister)
    {
    	TopTexture = iconregister.registerIcon("thaumicmanagement:BlockPurifierTop");
    	SideTexture = iconregister.registerIcon("thaumicmanagement:BlockPurifierSide");
    	BottomTexture = iconregister.registerIcon("thaumicmanagement:BlockPurifierBottom");
    	UnderTexture = iconregister.registerIcon("thaumicmanagement:BlockPurifierUnder");
    }
    
    public Icon getIcon(int side, int meta)
    {
    	switch (side)
    	{
    		case 1: return TopTexture;
    		case 2: return SideTexture;
    		case 3: return SideTexture;
    		case 4: return SideTexture;
    		case 5: return SideTexture;
    		case 6: return BottomTexture;
    	}
    	
    	return BottomTexture;
    }
    
    public boolean renderAsNormalBlock()
    {
      return false;
    }

    public int getRenderType()
    {
      return ClientProxy.PurifierBlockRenderID;
    }

    //fucking ugly as shit. tried putting this in a loop but it didnt work!
    public void purify(int NodeKey, AuraNode keyednode)
    {
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.ARMOR, keyednode.flux.getAmount(EnumTag.ARMOR)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.BEAST, keyednode.flux.getAmount(EnumTag.BEAST)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.CLOTH, keyednode.flux.getAmount(EnumTag.CLOTH)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.COLD, keyednode.flux.getAmount(EnumTag.COLD)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.CONTROL, keyednode.flux.getAmount(EnumTag.CONTROL)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.CRAFT, keyednode.flux.getAmount(EnumTag.CRAFT)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.CROP, keyednode.flux.getAmount(EnumTag.CROP)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.CRYSTAL, keyednode.flux.getAmount(EnumTag.CRYSTAL)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.DARK, keyednode.flux.getAmount(EnumTag.DARK)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.DESTRUCTION, keyednode.flux.getAmount(EnumTag.DESTRUCTION)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.EARTH, keyednode.flux.getAmount(EnumTag.EARTH)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.ELDRITCH, keyednode.flux.getAmount(EnumTag.ELDRITCH)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.EVIL, keyednode.flux.getAmount(EnumTag.EVIL)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.EXCHANGE, keyednode.flux.getAmount(EnumTag.EXCHANGE)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.FIRE, keyednode.flux.getAmount(EnumTag.FIRE)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.FLESH, keyednode.flux.getAmount(EnumTag.FLESH)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.FLIGHT, keyednode.flux.getAmount(EnumTag.FLIGHT)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.FLOWER, keyednode.flux.getAmount(EnumTag.FLOWER)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.FLUX, keyednode.flux.getAmount(EnumTag.FLUX)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.FUNGUS, keyednode.flux.getAmount(EnumTag.FUNGUS)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.HEAL, keyednode.flux.getAmount(EnumTag.HEAL)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.INSECT, keyednode.flux.getAmount(EnumTag.INSECT)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.KNOWLEDGE, keyednode.flux.getAmount(EnumTag.KNOWLEDGE)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.LIFE, keyednode.flux.getAmount(EnumTag.LIFE)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.LIGHT, keyednode.flux.getAmount(EnumTag.LIGHT)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.MAGIC, keyednode.flux.getAmount(EnumTag.MAGIC)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.MECHANISM, keyednode.flux.getAmount(EnumTag.MECHANISM)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.METAL, keyednode.flux.getAmount(EnumTag.METAL)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.MOTION, keyednode.flux.getAmount(EnumTag.MOTION)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.PLANT, keyednode.flux.getAmount(EnumTag.PLANT)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.POISON, keyednode.flux.getAmount(EnumTag.POISON)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.POWER, keyednode.flux.getAmount(EnumTag.POWER)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.PURE, keyednode.flux.getAmount(EnumTag.PURE)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.ROCK, keyednode.flux.getAmount(EnumTag.ROCK)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.SOUND, keyednode.flux.getAmount(EnumTag.SOUND)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.SPIRIT, keyednode.flux.getAmount(EnumTag.SPIRIT)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.TIME, keyednode.flux.getAmount(EnumTag.TIME)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.TOOL, keyednode.flux.getAmount(EnumTag.TOOL)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.TRAP, keyednode.flux.getAmount(EnumTag.TRAP)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.VALUABLE, keyednode.flux.getAmount(EnumTag.VALUABLE)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.VISION, keyednode.flux.getAmount(EnumTag.VISION)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.VOID, keyednode.flux.getAmount(EnumTag.VOID)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.WATER, keyednode.flux.getAmount(EnumTag.WATER)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.WEAPON, keyednode.flux.getAmount(EnumTag.WEAPON)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.WEATHER, keyednode.flux.getAmount(EnumTag.WEATHER)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.WIND, keyednode.flux.getAmount(EnumTag.WIND)), 0 ,0, 0);
		ThaumcraftApi.queueNodeChanges(NodeKey,0,0,false, new ObjectTags().remove(EnumTag.WOOD, keyednode.flux.getAmount(EnumTag.WOOD)), 0 ,0, 0);
    }
}
