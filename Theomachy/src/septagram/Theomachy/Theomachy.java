package septagram.Theomachy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import septagram.Theomachy.DB.AbilityData;
import septagram.Theomachy.DB.GameData;
import septagram.Theomachy.DB.PluginData;
import septagram.Theomachy.Handler.CommandModule.Blacklist;
import septagram.Theomachy.Manager.CommandManager;
import septagram.Theomachy.Manager.EventManager;

public class Theomachy extends JavaPlugin
{
	public static boolean INVENTORY_CLEAR = true;
	public static boolean GIVE_ITEM = true;
	public static boolean IGNORE_BED = true;
	public static boolean ENTITIES_REMOVE = true;
	public static boolean AUTO_SAVE = false;
	public static boolean ANIMAL = true;
	public static boolean MONSTER = true;
	public static boolean FAST_START = false;
	public static int DIFFICULTY = 1;
	public static boolean GAMB = true;
	public static boolean RANDOMUP = true;
	
	public CommandManager cm;
	public static Logger log=Bukkit.getLogger();
	
	public File file=new File(getDataFolder(), "blacklist.yml");
	
	public void onEnable()
	{
		
		UpdateChecker.check(this.getDescription().getVersion());
		
		log.info("[W.O.G] Plugin Loaded!   "+PluginData.buildnumber+"  "+PluginData.version);
		log.info("[W.O.G] Basic settings of plugin are loading...");
		
		saveResource("blacklist.yml", true);
		
		cm=new CommandManager(this);
		ShapedRecipe recipe = new ShapedRecipe(new ItemStack(Material.BLAZE_ROD)).shape(new String[]{"|","|","|"}).setIngredient('|', Material.STICK);
		getServer().addRecipe(recipe);
		getServer().getPluginManager().registerEvents(new EventManager(), this);
		FileInputStream fis;
		InputStreamReader isr;
		BufferedReader br;
		try {
			fis = new FileInputStream(file);
			isr=new InputStreamReader(fis);
			br=new BufferedReader(isr);
			String line;
			while((line=br.readLine())!=null){
				Blacklist.Blacklist.add(Integer.parseInt(line));
			}
		}catch(FileNotFoundException e) {} catch (IOException e) {}
		
		for(int i=1;i<=AbilityData.GOD_ABILITY_NUMBER;i++) {
			if(!Blacklist.Blacklist.contains(i)) Blacklist.GodCanlist.add(i);
		}for(int i=101;i<=AbilityData.HUMAN_ABILITY_NUMBER+100;i++) {
			if(!Blacklist.Blacklist.contains(i)) Blacklist.HumanCanlist.add(i);
		}
		
		log.info("[W.O.G] Listed abilities");
		log.info("[W.O.G] God: "+Blacklist.GodCanlist.size()+", Human: "+Blacklist.HumanCanlist.size());
		log.info("[W.O.G] Total: "+String.valueOf(Blacklist.GodCanlist.size()+Blacklist.HumanCanlist.size()));
		
		log.info("[W.O.G] Settings of game are loading...");
		getConfig().options().copyDefaults(true);
		saveConfig();
		INVENTORY_CLEAR = getConfig().getBoolean("Clearing inventory");
		GIVE_ITEM = getConfig().getBoolean("Providing item in sky-block");
		ENTITIES_REMOVE = getConfig().getBoolean("Removing entities");
		IGNORE_BED = getConfig().getBoolean("Ignoring bed");
		AUTO_SAVE = getConfig().getBoolean("Auto save");
		ANIMAL = getConfig().getBoolean("Spawning animals");
		MONSTER = getConfig().getBoolean("Spawning monsters");
		DIFFICULTY = getConfig().getInt("Difficulty");
		FAST_START=getConfig().getBoolean("Fast start");
		GAMB=getConfig().getBoolean("Allowing gambling");
		RANDOMUP=getConfig().getBoolean("Increasing ability lottery number");
		
		log.info("[W.O.G] ========================================");
		log.info("[W.O.G] Clearing inventory when game starts : "+String.valueOf(INVENTORY_CLEAR));
		log.info("[W.O.G] Providing item in sky-block when game starts : "+String.valueOf(GIVE_ITEM));
		log.info("[W.O.G] Removing entities when game starts : "+String.valueOf(ENTITIES_REMOVE));
		log.info("[W.O.G] Ignoring bed when respawning : "+String.valueOf(IGNORE_BED));
		log.info("[W.O.G] Fast start : "+String.valueOf(FAST_START));
		log.info("[W.O.G] Allowing gambling : "+String.valueOf(GAMB));
		log.info("[W.O.G] Increasing ability lottery number : "+String.valueOf(GAMB));
		log.info("[W.O.G] Auto save : "+String.valueOf(AUTO_SAVE));
		log.info("[W.O.G] Spawning animals : "+String.valueOf(ANIMAL));
		log.info("[W.O.G] Spawning monsters : "+String.valueOf(MONSTER));
		log.info("[W.O.G] Difficulty : "+String.valueOf(DIFFICULTY));
		log.info("[W.O.G] ========================================");
	
		Bukkit.getConsoleSender().sendMessage("Original author: "+ChatColor.WHITE+"칠각별(septagram) \n"+ChatColor.GRAY+"Correted verson author: "+ChatColor.AQUA+"플로리아(humint2003)");
	
		try {
			for(Player p:Bukkit.getOnlinePlayers()) {
				GameData.OnlinePlayer.put(p.getName(), p);
			}
		}catch(NullPointerException e) {}
		
	}
	
	public void onDisable() {
		BufferedWriter bw;
		try {
			bw=new BufferedWriter(new FileWriter(file));
			
			for(int i:Blacklist.Blacklist) {
				bw.write(String.valueOf(i)); bw.newLine();
			}
			
			bw.close();
		}catch(FileNotFoundException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
		
		
		log.info("[W.O.G] Blacklist was saved to file. Don't touch 'Blacklist.yml'.");
		
	}
	
}
