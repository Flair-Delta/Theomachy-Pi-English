package septagram.Theomachy.Ability.GOD;

import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.DB.GameData;
import septagram.Theomachy.Timer.Skill.HermesFlying;
import septagram.Theomachy.Utility.CoolTimeChecker;
import septagram.Theomachy.Utility.EventFilter;
import septagram.Theomachy.Utility.PlayerInventory;
import septagram.Theomachy.Utility.Skill;

public class Hermes extends Ability
{
	private final int coolTime0=60;
	private final int material=4;
	private final int stack0=2;
	private final static String[] des= {"여행자의 신입니다.",
			   "기본적으로 이동속도가 빠르며 블레이즈 로드를",
			   "사용한 능력을 통해 비행 할 수 있습니다.",
			   "비행 시 점프하면서 쓰시면 바로 날 수 있습니다." ,
			   "비행 중에는 낙하 데미지를 받지 않습니다."};
	
	public Hermes(String playerName)
	{
		super(playerName,"헤르메스", 11, true, true, true, des);
		Theomachy.log.info(playerName+abilityName);
		
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

	private void leftAction(Player player)
	{
		if (CoolTimeChecker.Check(player, 0)&&PlayerInventory.ItemCheck(player, material, stack0))
		{
			Skill.Use(player, material, stack0, 0, coolTime0);
			player.setAllowFlight(true);
			player.setFlying(true);
			Timer t = new Timer();
			t.schedule(new HermesFlying(player),2000,1000);
		}
	}
	
	public void buff()
	{
		Player player = GameData.OnlinePlayer.get(playerName);
		if (player != null)
		{
			Timer t = new Timer();
			t.schedule(new buff(player), 1000);
		}
	}
	
	private class buff extends TimerTask
	{
		final Player player;
		
		buff(Player player)
		{
			this.player = player;	
		}
		public void run()
		{
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 0),true);
		}
	}
}
