package septagram.Theomachy.Ability.HUMAN;

import org.bukkit.event.entity.FoodLevelChangeEvent;

import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.DB.GameData;

public class Anorexia extends Ability{

	private final static String[] des= { "신경성 식욕부진증이라고도 합니다.",
			"배고픔이 절반으로 유지됩니다."
	};
	
	public Anorexia(String playerName) {
		super(playerName, "거식증", 120, false, true, false, des);
		
		this.rank=2;

	}
	
	public void conditionSet(){
		GameData.OnlinePlayer.get(playerName).setFoodLevel(10);
	}
	
	public void T_Passive(FoodLevelChangeEvent event) {
		event.setFoodLevel(10);
	}
	
}
