package septagram.Theomachy.Ability.HUMAN;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityExplodeEvent;

import septagram.Theomachy.Ability.Ability;

public class Bulter extends Ability{

	private final static String[] des= {"굉장히 젠틀한 능력입니다.",
			"모든 폭발을 억제할 수 있습니다."};
	
	public Bulter(String playerName) {
		super(playerName, "집사", 121, false, true, false, des);
		
		this.rank=4;
	}
	
	public void T_Passive(EntityExplodeEvent event){
		event.setCancelled(true);
		if(!event.getEntity().getType().equals(EntityType.FIREBALL))
			Bukkit.broadcastMessage(ChatColor.GREEN+"집사에 의해 폭발이 진정되었습니다.");
	}
	
}