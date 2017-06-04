package septagram.Theomachy.Ability.GOD;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.Utility.CoolTimeChecker;
import septagram.Theomachy.Utility.EventFilter;
import septagram.Theomachy.Utility.GetPlayerList;
import septagram.Theomachy.Utility.PlayerInventory;
import septagram.Theomachy.Utility.Skill;

public class Akasha extends Ability{

	private final static String[] des= {
			"She is goddess of pain and joy.",
			"Normal ability can have your team memebers who are in near by you",
			"present joy and give SPEED and REGERATION to them, so.",
			"High ability can have enemy team memebers who are in near by you",
			"present CONFUSION and decrease their health."};
	
	public Akasha(String playerName)
	{
		super(playerName,"Akasha", 17, true, false, false ,des);
		Theomachy.log.info(playerName+abilityName);
		
		
		this.cool1=60;
		this.sta1=10;
		this.cool2=120;
		this.sta2=20;
		this.rank=4;
	}
	
	public void T_Active(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		if (PlayerInventory.InHandItemCheck(player, 369))
		{
			switch(EventFilter.PlayerInteract(event))
			{
			case 0:case 1:
				leftAction(player);
				break;
			case 2:case 3:
				rightAction(player);
				break;
			}
		}
	}

	private void leftAction(Player player) {
		
		if(CoolTimeChecker.Check(player, 1)&&PlayerInventory.ItemCheck(player, 4, sta1)){
			
			Skill.Use(player, 4, sta1, 1, cool1);
			
			List<Player> nearp=GetPlayerList.getNearByTeamMembers(player, 20, 20, 20);
			
			for(Player p:nearp){
				p.sendMessage(ChatColor.DARK_PURPLE+"JOY OF AKASHA"+ChatColor.WHITE+"amuses you!!");
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,20*15, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,20*15, 0));
			}
		}
		
	}
	
	private void rightAction(Player player) {
		
		if(CoolTimeChecker.Check(player, 2)&&PlayerInventory.ItemCheck(player, 4, sta2)){
			
			List<Player> entityList = GetPlayerList.getNearByNotTeamMembers(player, 10, 10, 10);
			
			for(Player e:entityList){
				
				e.sendMessage(ChatColor.WHITE+"you fell in "+ChatColor.DARK_PURPLE+"PAIN OF AKASHA");
				
				e.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,20*6, 0));
				e.setHealth(e.getHealth()-4);
			}
			
		}
	}
	
}
