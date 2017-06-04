package septagram.Theomachy.Ability.GOD;

import java.util.Timer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.Timer.Skill.ApollonPlayerScorching;
import septagram.Theomachy.Utility.CoolTimeChecker;
import septagram.Theomachy.Utility.EventFilter;
import septagram.Theomachy.Utility.PlayerInventory;
import septagram.Theomachy.Utility.Skill;

public class Apollon extends Ability
{
	private final static int coolTime1=90;
	private final static int coolTime2=180;
	private final static int material=4;
	private final static int stack1=1;
	private final static int stack2=10;
	private final static String[] des= {
				"He is god of sun",
			   "Normal ability can turn night into dat.",
			   "High ability can turn night into day and remove snow or rain,",
			   "also can burn everyone(You aren't included here!)."};
	
	public Apollon(String playerName)
	{
		super(playerName, "Apollon", 6, true, false, false, des);
		Theomachy.log.info(playerName+abilityName);
		
		this.cool1=coolTime1;
		this.cool2=coolTime2;
		this.sta1=stack1;
		this.sta2=stack2;
		this.rank=2;
		
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

	private void leftAction(Player player)
	{
		if (CoolTimeChecker.Check(player, 1)&&PlayerInventory.ItemCheck(player, material, stack1))
		{
			Skill.Use(player, material, stack1, 1, coolTime1);
			World world = player.getWorld();
			world.setTime(6000);
			Bukkit.broadcastMessage(ChatColor.YELLOW+"APOLLON turned night into day.");
		}
	}
	
	private void rightAction(Player player)
	{
		if (CoolTimeChecker.Check(player, 2)&&PlayerInventory.ItemCheck(player, material, stack2))
		{
			Skill.Use(player, material, stack2, 2, coolTime2);
			World world = player.getWorld();
			world.setTime(6000);
			world.setStorm(false);
			Timer t = new Timer();
			t.schedule(new ApollonPlayerScorching(player, 15), 5000,2000);
			}
	}
}
