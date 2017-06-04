package septagram.Theomachy.Ability.GOD;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.Utility.CoolTimeChecker;
import septagram.Theomachy.Utility.EventFilter;
import septagram.Theomachy.Utility.GetPlayerList;
import septagram.Theomachy.Utility.PlayerInventory;
import septagram.Theomachy.Utility.Skill;

public class Aprodite extends Ability{
	
	private final static String[] des= {
				"She is goddess of beauty.",
			   "Your ability can grap others.",
			   "If you use ability, enemy team memebers who are",
			   "in near by you are grabbed to your location.",
			   "When using it, If there is no block bloew you or ",
			   "you are sneaking, you can use it."};
	private final int coolTime0=500;
	private final int stack0=64;
	
	public Aprodite(String playerName)
	{
		super(playerName, "Aprodite", 13, true, false, false, des);
		Theomachy.log.info(playerName+"Aprodite");
		
		this.cool1=coolTime0;
		this.sta1=stack0;
		
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
			}
		}
	}

	private void leftAction(Player player) {
		if (CoolTimeChecker.Check(player, 1)&&PlayerInventory.ItemCheck(player, 4, stack0)) {
			if(!player.isSneaking() && !player.getLocation().add(0, -1, 0).getBlock().getType().equals(Material.AIR)) {
				Skill.Use(player, 4, stack0, 0, coolTime0);
				try {
					List<Player> list=GetPlayerList.getNearByNotTeamMembers(player, 20, 20, 20);
				
					for(Player e:list) {
						e.teleport(player);
						e.sendMessage(ChatColor.YELLOW+"You are grabbed by APRODITE!");
					}
				}catch(Exception e) {}
				
				player.sendMessage("You tempted others for your beauty.");
			}else {
				player.sendMessage(ChatColor.RED+"You are sneaking or there is no block bloew you.");
			}
		}

	}
	
}
