package septagram.Theomachy.Ability.GOD;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.Timer.Skill.WizardWindTimer;
import septagram.Theomachy.Utility.CoolTimeChecker;
import septagram.Theomachy.Utility.DirectionChecker;
import septagram.Theomachy.Utility.EventFilter;
import septagram.Theomachy.Utility.GetPlayerList;
import septagram.Theomachy.Utility.PlayerInventory;
import septagram.Theomachy.Utility.Skill;

public class Aeolus extends Ability{
	
	private final static String[] des= {"He is god of storm and wind.",
			"Normal ability can have your teammates who are in near by you",
			"to get some fresh air, and present SPEED and REGERATION to them.",
			"High ability can have enemy teammates who are in near by you",
			"present WEAKNESS and SLOW."};
	
	public Aeolus(String playerName)
	{
		super(playerName,"Aeolus", 16, true, false, false ,des);
		Theomachy.log.info(playerName+abilityName);
		
		
		this.cool1=60;
		this.sta1=10;
		this.cool2=180;
		this.sta2=32;
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
				p.sendMessage(ChatColor.AQUA+"A fresh breeze"+ChatColor.WHITE+"is blowing!");
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,20*15, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,20*15, 0));
			}
		}
		
	
	}
	
	private void rightAction(Player player) {
		
		if(CoolTimeChecker.Check(player, 2)&&PlayerInventory.ItemCheck(player, 4, sta2)){
			
			List<Player> entityList = GetPlayerList.getNearByNotTeamMembers(player, 10, 10, 10);
			ArrayList<Player> targetList = new ArrayList<Player>(); 
			for (Entity e : entityList)
				if (e instanceof Player)
					targetList.add((Player) e);
			if (!targetList.isEmpty())
			{
				Skill.Use(player, 4, sta2, 2, cool2);
				Timer t = new Timer();
				Vector v = new Vector(0,0.5,0);
				double vertical = 2.4d;
				double diagonal = vertical*1.4d;
				for (Player e : targetList)
				{
					e.setVelocity(v);
					e.sendMessage(ChatColor.DARK_AQUA+"You are bounced beacuse of STORM!!");
					e.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,20*5, 0));
					e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,20*5, 0));
				}
				switch(DirectionChecker.PlayerDirection(player))
				{
				case 0:
					v.add(new Vector(0,0,diagonal));
					break;
				case 1:
					v.add(new Vector(-vertical,0,vertical));
					break;
				case 2:
					v.add(new Vector(-diagonal,0,0));
					break;
				case 3:
					v.add(new Vector(-vertical,0,-vertical));
					break;
				case 4:
					v.add(new Vector(0,0,-diagonal));
					break;
				case 5:
					v.add(new Vector(vertical,0,-vertical));
					break;
				case 6:
					v.add(new Vector(diagonal,0,0));
					break;
				case 7:
					v.add(new Vector(vertical,0,vertical));
					break;
				}
				t.schedule(new WizardWindTimer(targetList, v), 200);
			}
			else
				player.sendMessage("There is no target who can use your ability...");
			
		}
		
		
	}
	
}
