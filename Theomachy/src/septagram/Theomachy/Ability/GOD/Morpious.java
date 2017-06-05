package septagram.Theomachy.Ability.GOD;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.DB.GameData;
import septagram.Theomachy.Utility.CoolTimeChecker;
import septagram.Theomachy.Utility.EventFilter;
import septagram.Theomachy.Utility.PlayerInventory;
import septagram.Theomachy.Utility.Skill;

public class Morpious extends Ability{

	private final static String[] des= {
			"He is god of sleeping.",
			"By using ability, target will be fell asleep by you.",
			"How to target: /x <TARGET>"};
 	
	private final int coolTime1=180;
	private final int stack1=32;

	private String abilitytarget;
	
	public Morpious(String playerName) {
		super(playerName, "Morpious", 15, true, false, false, des);
		
		this.rank=3;
		
		this.cool1=coolTime1;
		this.sta1=stack1;
	}
	
	public void T_Active(PlayerInteractEvent event){
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

	private void leftAction(Player player){
		if (CoolTimeChecker.Check(player, 1)&&PlayerInventory.ItemCheck(player, 4, stack1))
		{
			String[] team = new String[2];
			team[0]=GameData.PlayerTeam.get(player.getName());
			team[1]=GameData.PlayerTeam.get(abilitytarget);
					
			if(team[0]!=team[1]){
			if(abilitytarget!=null){
				if(player.getName().equals(abilitytarget)){
					player.sendMessage(ChatColor.RED+"Ouch. Target must not yourself.");
				}
				
				else{
					Player target = GameData.OnlinePlayer.get(abilitytarget);
					Skill.Use(player, 4, stack1, 1, coolTime1);
					player.sendMessage(ChatColor.GRAY+"Target was fell asleep!");
					target.sendMessage(ChatColor.GRAY+"You have to go to bed ealry~");
					target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1200,0), true);
					target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1200, 3), true);
				}
				
			}
			else{
				player.sendMessage("Please target. (How to target: /x <TARGET>)");
			}
			}
			else{
				player.sendMessage(ChatColor.GRAY+"Target is your team! You can't use ability to him.");
			}
		}
	}
	
	public void targetSet(CommandSender sender, String targetName)
	{
			if (!playerName.equals(targetName))
			{
				this.abilitytarget = targetName;
				sender.sendMessage("TARGET.   "+ChatColor.GREEN+targetName);
			}
			else
				sender.sendMessage("Ouch. Target must not yourself.");
	}
}
