package mint.thaumicmanagement;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderPurifierBlock implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
        Tessellator tessellator = Tessellator.instance;
        if(modelID == ClientProxy.PurifierBlockRenderID)
        {
        	BlockNodePurifier handlerBlock = (BlockNodePurifier)block;
            
        	block.setBlockBoundsForItemRender();
            
        	GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        	
            tessellator.startDrawingQuads();
            tessellator.setNormal(0F, +1.0F, 0F);
            renderer.setRenderBounds(0.0001, 0.0001, 0.0001, 0.9999, 0.9999, 0.9999);
            renderer.renderTopFace(block, 0.0D, 0.0D, 0.0D, handlerBlock.underTexture);
            renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
            renderer.renderTopFace(block, 0.0D, 0.0D, 0.0D, handlerBlock.topTexture);
            tessellator.draw();
            
            tessellator.startDrawingQuads();
            tessellator.setNormal(0F, -1.0F, 0F);
            renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
            renderer.renderBottomFace(block, 0.0D, 0.0D, 0.0D, handlerBlock.bottomTexture);
            tessellator.draw();
            
            tessellator.startDrawingQuads();
            tessellator.setNormal(-1.0F, 0F, 0F);
            renderer.setRenderBounds(0.0001, 0.0001, 0.0001, 0.9999, 0.9999, 0.9999);
            renderer.renderNorthFace(block, 0.0D, 0.0D, 0.0D, handlerBlock.underTexture);
            renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
            renderer.renderNorthFace(block, 0.0D, 0.0D, 0.0D, handlerBlock.sideTexture);
            tessellator.draw();
            
            tessellator.startDrawingQuads();
            tessellator.setNormal(0F, 0F, -1.0F);
            renderer.setRenderBounds(0.0001, 0.0001, 0.0001, 0.9999, 0.9999, 0.9999);
            renderer.renderEastFace(block, 0.0D, 0.0D, 0.0D, handlerBlock.underTexture);
            renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
            renderer.renderEastFace(block, 0.0D, 0.0D, 0.0D, handlerBlock.sideTexture);
            tessellator.draw();
            
            tessellator.startDrawingQuads();
            tessellator.setNormal(+1.0F, 0F, 0F);
            renderer.setRenderBounds(0.0001, 0.0001, 0.0001, 0.9999, 0.9999, 0.9999);
            renderer.renderSouthFace(block, 0.0D, 0.0D, 0.0D, handlerBlock.underTexture);
            renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
            renderer.renderSouthFace(block, 0.0D, 0.0D, 0.0D, handlerBlock.sideTexture);
            tessellator.draw();
            
            tessellator.startDrawingQuads();
            tessellator.setNormal(0F, 0F, +1.0F);
            renderer.setRenderBounds(0.0001, 0.0001, 0.0001, 0.9999, 0.9999, 0.9999);
            renderer.renderWestFace(block, 0.0D, 0.0D, 0.0D, handlerBlock.underTexture);
            renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
            renderer.renderWestFace(block, 0.0D, 0.0D, 0.0D, handlerBlock.sideTexture);
            tessellator.draw();
            
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        }
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		Tessellator tess = Tessellator.instance;
		
		BlockNodePurifier handlerBlock = (BlockNodePurifier)block;
		
		tess.setBrightness(225);
		tess.setColorOpaque_I(0xFFFFFF);
		
		renderer.renderNorthFace(block, x, y, z, ((BlockNodePurifier)block).underTexture);
		renderer.renderSouthFace(block, x, y, z, ((BlockNodePurifier)block).underTexture);
		renderer.renderEastFace(block, x, y, z, ((BlockNodePurifier)block).underTexture);
		renderer.renderWestFace(block, x, y, z, ((BlockNodePurifier)block).underTexture);
		renderer.renderTopFace(block, x, y, z, ((BlockNodePurifier)block).underTexture);
		renderer.setRenderBounds(0.001, 0.001, 0.001, 0.999, 0.999, 0.999);
		renderer.renderStandardBlock(block, x, y, z);
		renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
		renderer.clearOverrideBlockTexture();
		renderer.renderStandardBlock(block, x, y, z);
		
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return ClientProxy.PurifierBlockRenderID;
	}
}