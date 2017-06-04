package septagram.Theomachy.Ability.HUMAN;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import septagram.Theomachy.Ability.Ability;

public class Goldspoon extends Ability{

	private final static String[] des= {"금수저를 물고 태어난 능력입니다.",
			"안타깝게도, 금수저는 못 드리고 금레깅스를 드리겠습니다.",
			"리스폰될 때마다 금레깅스를 받습니다."};
	
	public Goldspoon(String playerName) {
		super(playerName, "금수저", 123, false, true, false, des);
		
		this.rank=2;
	}
	
	public void T_Passive(PlayerRespawnEvent event){
		Player p=event.getPlayer();
		p.getInventory().addItem(new ItemStack(Material.GOLD_LEGGINGS));
	}
	
}
