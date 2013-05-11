package mint.thaumicmanagement;

import static net.minecraftforge.common.Configuration.*;
import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.FMLLog;

public class ConfigHelper
{
	public static Configuration config;
	
	public static int NodePurifierID;
	public static int NodeCorruptorID;
	public static int NodeTransmutorID;
	public static int NodeMoverID;
	public static int ToolRange;
	public static int CorruptorTimer;
	public static int CorruptorFluxAmount;
	
	public static String NodeMoverDistance;

	public static void init(File coinConfig)
	{
		config = new Configuration(coinConfig);

		try
		{
			config.load();
			
			config.addCustomCategoryComment
			("Tool Configuration", "Thaumic Management tool configuration settings");

			// Generic config options
            Property nodePurifierP = config.get("Tool Configuration", "node_purifier", 25000);
            NodePurifierID = nodePurifierP.getInt();
			nodePurifierP.comment = "Node Purifier";
			
            Property nodeCorruptorP = config.get("Tool Configuration", "node_corruptor", 25001);
            NodeCorruptorID = nodeCorruptorP.getInt();
			nodeCorruptorP.comment = "Node Corruptor";
			
            Property nodeTransmutorP = config.get("Tool Configuration", "node_transmutor", 25002);
            NodeTransmutorID = nodeTransmutorP.getInt();
			nodeTransmutorP.comment = "Node Transmutor [Not working yet!]";
			
            Property nodeMoverP = config.get("Tool Configuration", "node_mover", 25003);
            NodeMoverID = nodeMoverP.getInt();
			nodeMoverP.comment = "Node Mover [Not working yet!]";
			
            Property toolRangeP = config.get("Tool Configuration", "tool_range", 32);
            ToolRange = toolRangeP.getInt();
			toolRangeP.comment = "Range in blocks for the tools to search for a node from the players position.";
			
			Property CorruptorTimerP = config.get("Tool Configuration", "corruptor_timer", 3);
			CorruptorTimer = CorruptorTimerP.getInt();
			CorruptorTimerP.comment = "Number in seconds that the corruptor puts a random aspect into the selected node.";
			
			Property CorruptorFluxAmountP = config.get("Tool Configuration", "corruptor_flux_amount", 1);
			CorruptorFluxAmount = CorruptorFluxAmountP.getInt();
			CorruptorFluxAmountP.comment = "Amount of random flux put into the node with each use of the wand.";
			
			Property NodeMoverDistanceP = config.get("Tool Configuration", "node_mover_distance", 0.1);
			NodeMoverDistance = NodeMoverDistanceP.getString();
			NodeMoverDistanceP.comment = "Amount of distance to move the node in the selected direction. Value should be set between 0.1 and 1";
		} 
		
		catch (Exception exception)
		{
			FMLLog.log(Level.SEVERE, exception, "Thaumic Management has had a problem loading its configuration!");
		} 
		
		finally
		{
			config.save();
		}
	}
}