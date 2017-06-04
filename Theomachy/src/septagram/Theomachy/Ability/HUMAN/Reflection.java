package septagram.Theomachy.Ability.HUMAN;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;

public class Reflection extends Ability
{	
	private final static String[] des= {"공격받은 데미지를 공격자에게 반사하는 능력입니다.",
			   "33% 확률로 자신이 받은 데미지의 반을 상대방에게 반사합니다." ,
			   "화살 등 간접적으로 받는 데미지는 반사하지 못합니다.",
			   "이 능력은 방어를 무시합니다."};
	
	public Reflection(String playerName)
	{
		super(playerName,"반사", 109, false, true, false, des);
		Theomachy.log.info(playerName+abilityName);
		
		this.rank=2;
	}
	
	public void T_Passive(EntityDamageByEntityEvent event)
	{
		Player player = (Player) event.getEntity();
		if (player.getName().equals(playerName))
		{
			int rn = (int) (Math.random()*3);
			if (rn == 0)
			{
				Player damager = (Player) event.getDamager();
				damager.damage(event.getDamage()>>1);
			}
		}
	}
	
}
