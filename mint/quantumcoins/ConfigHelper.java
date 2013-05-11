package mint.quantumcoins;

import static net.minecraftforge.common.Configuration.CATEGORY_GENERAL;
import static net.minecraftforge.common.Configuration.CATEGORY_ITEM;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.FMLLog;

public class ConfigHelper
{
	public static Configuration config;
	
	public static int coinNum;
	public static int coinID;
	public static int convertAmount;
	public static int convertTime;
	public static String coinName;
	public static boolean useAnim;

	public static String[] coinTypeName;
	public static String[] coinNameColor;
	public static String[] coinColor;
	public static String[] coinTypeInfo;

	// Because each config section's loading is the same, we can pull the
	// repeated code out into a function
	private static String[] populateFromConfig(String section, String fieldPrefix, String[] defaults, String fallback)
	{
		// Initialize the array to coinNum values-- arrays can be arbitarily
		// sized, so, we can dynamically size this.
		String[] list = new String[coinNum];
		for (int i = 0; i < coinNum; i++)
		{
			// Pick the default name. Fall back if you have more coins than the
			// number of default names
			String defaultValue;

			if (i >= defaults.length){defaultValue = fallback;}
			else {defaultValue = defaults[i];}

			// Set the proper value in the coin name array
			list[i] = config.get(section, fieldPrefix + (i + 1),
                    defaultValue).getString();
		}
		
		return list;
	}

	public static void init(File coinConfig)
	{
		config = new Configuration(coinConfig);

		try
		{
			config.load();
			
			config.addCustomCategoryComment
			("Coin_Types", "Names for the coin types. E.G. Copper, Iron, Gold");

			config.addCustomCategoryComment
			("Coin_Colors","Color of the different coin types in a hex color code. E.G. A2DF4D" + "\n" +
			 "I suggest http://http://www.colorpicker.com for finding your hex coolor codes.");

			config.addCustomCategoryComment
			("Coin_Type_Extra_Info", "Extra information on the tooltip of the coin types E.G. 'Worth 5 copper coins'"+ "\n" + 
			 "If you dont want tooltip info put 'none' without the quotation marks");

			config.addCustomCategoryComment
			("Coin_Name_Colors", "Color of the coin type names in 0-f. k, l, m, n, and o may also be used." + "\n" + 
			 "Please see http://www.minecraftwiki.net/wiki/Formatting_codes for more information.");

			// Generic config options
            Property useAnimP = config.get(CATEGORY_ITEM, "animated_icon", true);
            useAnim = useAnimP.getBoolean(true);
			useAnimP.comment = "Use the animated icon? May cause a bit of client lag if there are many stacks in players inventory.";
			
			Property coinNumP = config.get(CATEGORY_ITEM, "number_of_coins", 10);
			coinNum = coinNumP.getInt();
			coinNumP.comment = "Number of coins to use. NOTE: If you go higher then 10 you will need to launch minecraft" + "\n" + 
							   "and then close it to populate the config with the new variables for the extra coins.";

			Property coinIDp = config.get(CATEGORY_ITEM, "coin_item_ID", 31500);
			coinID = coinIDp.getInt();
			coinIDp.comment = "Item ID for the coins";

			Property coinNameP = config.get(CATEGORY_ITEM, "coin_name", "Coin");
			coinName = coinNameP.getString();
			coinNameP.comment = "Name for the coins";
			
			Property convertAmountP = config.get(CATEGORY_ITEM, "convert_amount", 5);
			convertAmount = convertAmountP.getInt();
			convertAmountP.comment = "Number of coins needed/gained from a conversion.";
			
			Property convertTimeP = config.get(CATEGORY_ITEM, "convert_time", 3);
			convertTime = convertTimeP.getInt();
			convertTimeP.comment = "Time in seconds the player needs to hold the conversion for it to work";

			
			
			// Load each config field
			coinTypeName = populateFromConfig("Coin_Types", "coin_type_",
					new String[] { "Copper", "Iron", "Gold", "Obsidian",
								   "Diamond", "Astral", "Cosmic", "Star", "Omega",
								   "Special" }, "[name]");
			
			coinNameColor = populateFromConfig("Coin_Name_Colors", "coin_name_color_",
					new String[] { "0", "1", "2", "3", "4",
								   "5", "6", "7", "8", "9" }, "0");
			
			coinColor = populateFromConfig("Coin_Colors", "coin_color_",
					new String[] { "D4BC6E", "CCCCCC", "FFFF40", "222222",
								   "40EAED", "F7ADFF", "CAFF7A", "FF4800", "469E49",
							       "123456" }, "FFFFFF");
			
			coinTypeInfo = populateFromConfig("Coin_Type_Extra_Info", "coin_type_extra_info_",
					new String[] { "none",
								   "Worth 5 Copper Coins", "Worth 5 Iron Coins",
								   "Worth 5 Gold Coins", "Worth 5 Obsidian Coins",
								   "Worth 5 Diamond Coins", "Worth 5 Astral Coins",
								   "Worth 5 Cosmic Coins", "Worth 5 Star Coins",
								   "Worth 5 Omega Coins" }, "none");
		} 
		
		catch (Exception exception)
		{
			FMLLog.log(Level.SEVERE, exception, "Quantum Coins has had a problem loading its configuration!");
		} 
		
		finally
		{
			config.save();
		}
	}
}