package septagram.Theomachy.Ability.GOD;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import septagram.Theomachy.Ability.Ability;

public class Eris extends Ability{

	private final static String[] des= {
			"She is goddess of discord.",
			"Someone that you are attacked by is bounced by 20% chance."};
	
	public Eris(String playerName) {
		
		super(playerName, "Eris", 14, false, true, false, des);
		
		this.rank=3;
		
	}

	public void T_Passive(EntityDamageByEntityEvent event) {
		Player eris = (Player)event.getEntity();
		Player damager = (Player) event.getDamager();
		Random random = new Random();
		int rn = random.nextInt(5);
		if(eris.getName().equals(playerName)){
			if(rn==0){
				Location psloc = eris.getLocation();
				Location daloc = psloc;
				daloc.setX(psloc.getX()+5);
				daloc.setZ(psloc.getZ()+5);
				damager.teleport(daloc);
				eris.sendMessage(ChatColor.RED+"Attacker was bounced!!");
				damager.sendMessage(ChatColor.RED+"You was bounced by ERIS!!");
			}
		}
	}
	
}
