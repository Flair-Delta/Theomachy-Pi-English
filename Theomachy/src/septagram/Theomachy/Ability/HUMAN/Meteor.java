package septagram.Theomachy.Ability.HUMAN;

import java.util.Timer;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.Timer.Skill.MeteorTimer;
import septagram.Theomachy.Utility.CoolTimeChecker;
import septagram.Theomachy.Utility.EventFilter;
import septagram.Theomachy.Utility.PlayerInventory;
import septagram.Theomachy.Utility.Skill;

public class Meteor extends Ability
{
	private final int coolTime0=110;
	private final int material=4;
	private final int stack0=5;
	private final static String[] des= {"유성을 소환하는 능력입니다.",
			   "블레이즈 로드를 사용한 능력으로 유성을 소환합니다." ,
			   "능력 사용시 자신의 위치를 기준으로 ",
			   "넓은 범위에 유성을 30번 떨어뜨립니다." ,
			   "블럭은 메테오의 폭발에 파괴되지 않습니다."};
	
	public Meteor(String playerName)
	{
		super(playerName,"메테오", 117, true, false, false, des);
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
			Location location = player.getLocation();
			Timer t = new Timer();
			t.schedule(new MeteorTimer(player, location, 30), 0,200);
		}
	}
}
