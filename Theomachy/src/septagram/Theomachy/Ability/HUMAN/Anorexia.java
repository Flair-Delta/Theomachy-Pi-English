package septagram.Theomachy.Ability.HUMAN;

import org.bukkit.event.entity.FoodLevelChangeEvent;

import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.DB.GameData;

public class Anorexia extends Ability{

	private final static String[] des= { 
			"Your food level will be always keep half."
	};
	
	public Anorexia(String playerName) {
		super(playerName, "Anorexia", 120, false, true, false, des);
		
		this.rank=2;

	}
	
	public void conditionSet(){
		GameData.OnlinePlayer.get(playerName).setFoodLevel(10);
	}
	
	public void T_Passive(FoodLevelChangeEvent event) {
		event.setFoodLevel(10);
	}
	
}
