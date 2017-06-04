package septagram.Theomachy.Ability.GOD;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;

public class Ares extends Ability
{
	
	private final static String[] des= {
				"He is god of fighting.",
			   "All of attack damage increases 10%." ,
			   "And you can also avoid all of attack by 10% chance."};
	
	public Ares(String playerName)
	{
		super(playerName,"Ares", 8, false, true, false, des);
		Theomachy.log.info(playerName+abilityName);
		
		this.rank=3;
		
	}
	
	public void T_Passive(EntityDamageByEntityEvent event)
	{		
		Player player = (Player) event.getEntity();
		if (!player.getName().equals(playerName)) //공격
			event.setDamage((int) (event.getDamage()*1.5));
		else											//피격
		{
			Random random = new Random();
			if (random.nextInt(10) == 0) 	//1/2 확률
			{
				event.setCancelled(true);
				player.sendMessage("You avoided");
			}
		}
	}
}
