package septagram.Theomachy.Ability.GOD;

import java.util.Timer;
import java.util.TimerTask;

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

	private final static String[] des= {"잠의 신입니다.",
			"일반능력으로 목표 지정한 적을 1분간 잠들게 합니다.",
			"목표 지정: /x <대상>"};
 	
	private final int coolTime1=180;
	private final int stack1=32;

	private String abilitytarget;
	
	public Morpious(String playerName) {
		super(playerName, "모르피우스", 15, true, false, false, des);
		
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
					player.sendMessage(ChatColor.RED+"목표는 본인이 아니어야 합니다.");
				}
				
				else{
					Player target = GameData.OnlinePlayer.get(abilitytarget);
					Skill.Use(player, 4, stack1, 1, coolTime1);
					player.sendMessage(ChatColor.GRAY+"목표를 잠재웠습니다!");
					target.sendMessage(ChatColor.GRAY+"착한 어른이는 일찍 자고 일찍 일어나야 해요~");
					target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1200,0), true);
					target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1200, 3), true);
					Timer t = new Timer();
					t.schedule(new M(player, target), 57000, 1000);
				}
				
			}
			else{
				player.sendMessage("목표를 설정해주십시오. (목표 설정법: /x <목표>)");
			}
			}
			else{
				player.sendMessage(ChatColor.GRAY+"본인의 팀이므로 잠을 재울 수 없습니다!");
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
}

class M extends TimerTask{

	private int count=3;
	private Player p;
	private Player t;
	
	public M (Player p, Player t){
		this.p=p;
		this.t=t;
	}
	
	@Override
	public void run() {
		if(count==0){
			t.sendMessage("잠에서 깨어났습니다!");
			p.sendMessage("마법을 걸었던 상대가 잠에서 깨어났습니다!");
			this.cancel();
		}
		else{
			t.sendMessage(ChatColor.DARK_AQUA+"잠에서 깨어나기 전까지 "+ChatColor.WHITE+count+ChatColor.DARK_AQUA+"초 전입니다.");
			p.sendMessage(ChatColor.DARK_AQUA+"상대가 잠에서 깨어나기 전까지 "+ChatColor.WHITE+count+ChatColor.DARK_AQUA+"초 전입니다.");
		}
		count--;
	}

}
