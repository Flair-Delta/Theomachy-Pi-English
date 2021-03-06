package septagram.Theomachy.Ability.HUMAN;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import septagram.Theomachy.Ability.Ability;

public class Gasolin extends Ability{
	
	private final static String[] des= { "불에 타면 동력이 생겨 빨라집니다.",
		"능력에 의한 가속은 다른 가속 효과와 중첩되지 않습니다.",
		"제트 기관보다 가속력이 좋지 않습니다."
	};
	
	public Gasolin(String playerName) {
		super(playerName, "가솔린기관", 131, false, true, false, des);
		
		this.rank=3;

	}
	
	public void T_Passive(EntityDamageEvent event){
		Player p=(Player)event.getEntity();
		boolean has=false;
		
		for(PotionEffect e:p.getActivePotionEffects()){
			if(e.getType().equals(PotionEffectType.SPEED)){
				has=true;
			}
		}
		
		if(!has){
			if(event.getCause().equals(DamageCause.FIRE)||event.getCause().equals(DamageCause.FIRE_TICK)||event.getCause().equals(DamageCause.LAVA)){
				
				p.sendMessage("동력이 생겨 빨라집니다!");
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*5, 0));
				
			}
		}
			
	}
	
}
