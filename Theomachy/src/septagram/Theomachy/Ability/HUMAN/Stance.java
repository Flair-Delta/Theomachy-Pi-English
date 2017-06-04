package septagram.Theomachy.Ability.HUMAN;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;

public class Stance extends Ability
{
	
	private final static String[] des= {"강한 의지를 갖고 있는 능력입니다.",
			   "모든 데미지 증폭 효과를 무시하며,",
			   "모든 공격에 100% 확률로 밀려나지 않습니다." ,
			   "패널티로 모든 자신의 방어 효과는 무시됩니다."};
	
	public Stance(String playerName)
	{
		super(playerName,"스탠스", 103, false, true, false ,des);
		Theomachy.log.info(playerName+abilityName);
		
		this.rank=3;
	}
	
	public void T_Passive(EntityDamageEvent event)
	{
		if (event.getCause() == DamageCause.ENTITY_ATTACK || event.getCause() == DamageCause.PROJECTILE)
		{
			Player player = (Player)event.getEntity();
			int damage = event.getDamage();
			player.damage(damage);
			event.setCancelled(true);
		}
	}
	
	
}
