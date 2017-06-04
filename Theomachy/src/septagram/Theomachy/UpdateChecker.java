package septagram.Theomachy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class UpdateChecker {
	
	public static void check(String thisVersion){
		try{
			URL u=new URL("https://raw.githubusercontent.com/plo-delta/PLODELTA/master/README.md");
			InputStreamReader isr=new InputStreamReader(u.openStream());
			BufferedReader br=new BufferedReader(isr);
			
			String Line;
			
			while((Line=br.readLine())!=null){
				if(Line.startsWith("Theomachy:")){
					Bukkit.getServer().getConsoleSender().sendMessage("[W.O.G] "+ChatColor.AQUA+"The newest version is "+Line.replace("Theomachy:", "")+".");
					if(Line.replace("Theomachy:", "").equals(thisVersion)){
						Bukkit.getServer().getConsoleSender().sendMessage("[W.O.G] "+ChatColor.AQUA+"This plugin is the newest!!");
					}else{
						Bukkit.getServer().getConsoleSender().sendMessage("[W.O.G] "+ChatColor.RED+"Ouch.. This plugin is old version. Please get the newest version in author's blog.");
						Bukkit.getConsoleSender().sendMessage("[W.O.G] Author's blog: http://http://ploriasplugin.tistory.com/");
					}
				}
			}
		}catch(Exception e){
			Bukkit.getServer().getConsoleSender().sendMessage("[W.O.G] "+ChatColor.RED+"Failed to checking update...");
		}
	}	
}