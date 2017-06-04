package septagram.Theomachy.Ability.HUMAN;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.DB.GameData;
import septagram.Theomachy.Utility.CoolTimeChecker;
import septagram.Theomachy.Utility.EventFilter;
import septagram.Theomachy.Utility.PlayerInventory;
import septagram.Theomachy.Utility.Skill;

public class Bee extends Ability{

	public final static String[] des= {"벌들의 제왕입니다.",
			"자신을 공격해 온 적에게 75%의 확률로 독을 안겨줍니다.",
			"폼산을 이용해서 상대를 유혹할 수도 있습니다.",
			"능력 사용 시 목표로 지정해 둔 상대를 자신의 위치로 끌어옵니다.",
			"목표 지정: /x <대상>"};
	
	public final int coolTime0=180;
	public final int stack0=32;

	private String abilitytarget;
	
	public Bee(String playerName) {
		super(playerName, "여왕벌", 124, true, true, false, des);
		
		this.rank=3;
		
		this.cool1=coolTime0;
		this.sta1=stack0;
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

	private void leftAction(Player player)
	{
		if (CoolTimeChecker.Check(player, 1)&&PlayerInventory.ItemCheck(player, 4, stack0))
		{
			if(abilitytarget!=null){
				if(player.getName().equals(abilitytarget)){
					player.sendMessage(ChatColor.RED+"목표는 본인이 아니어야 합니다.");
				}
				
				else{
					Player target = GameData.OnlinePlayer.get(abilitytarget);
					Skill.Use(player, 4, stack0, 0, coolTime0);
					
					player.sendMessage(ChatColor.YELLOW+" 폼산 "+ChatColor.WHITE+"을 이용하여 목표를 유혹했습니다!");
					target.sendMessage(ChatColor.YELLOW+" 폼산 "+ChatColor.WHITE+"에 유혹당했습니다!");
					
					target.teleport(player);
				}
				
			}
			else{
				player.sendMessage("목표를 설정해주십시오. (목표 설정법: /x <목표>)");
			}
		}
	}
	
	public void targetSet(CommandSender sender, String targetName)
	{
			if (!playerName.equals(targetName))
			{
				this.abilitytarget = targetName;
				sender.sendMessage("타겟을 등록했습니다.   "+ChatColor.GREEN+targetName);
			}
			else
				sender.sendMessage("자기 자신을 목표로 등록 할 수 없습니다.");
	}
	
	public void T_Passive(EntityDamageByEntityEvent event) {
		Player p=(Player) event.getEntity();
		Player e=(Player) event.getDamager();
		
		if(p.getName().equals(this.playerName)) {
			Random r=new Random();
			
			if(r.nextInt(4)<=2) {
				e.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
				e.sendMessage(ChatColor.GOLD+"벌에게 쏘였습니다! 자나깨나 벌조심.");
			}
			
		}
		
	}
	
}
