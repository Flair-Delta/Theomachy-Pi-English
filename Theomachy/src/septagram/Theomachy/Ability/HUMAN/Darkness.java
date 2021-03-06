package septagram.Theomachy.Ability.HUMAN;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import septagram.Theomachy.Ability.Ability;

public class Darkness extends Ability{

	private final static String[] des= {"맞으면 맞을수록 좋습니다~!",
	   "가격 데미지를 1/10로 줄여받으며, 맞으면 10% 확률로",
	   "점프합니다. 단, 공격을 전혀 맞출 수 없습니다."};
	
	public Darkness(String playerName) {
		
		super(playerName, "다크니스", 130, false, true, false, des);
		
		this.rank=3;
		
	}
	
	public void T_Passive(EntityDamageByEntityEvent event){
		
		Player dark=(Player)event.getEntity();
		Player ness=(Player)event.getDamager();
		
		if(dark.getName().equals(playerName)){
			
			event.setDamage(event.getDamage()%10);
			
			Random r=new Random();
			if(r.nextInt(10)==0){
				World w=dark.getWorld();
				Vector v= dark.getEyeLocation().getDirection();
				v.setY(0.5);
				w.playEffect(dark.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
				
				dark.sendMessage(ChatColor.YELLOW+"얏호~!!!!!");
			}
			
		}
		
		if(ness.getName().equals(playerName)){
			event.setDamage(0);
		}
		
	}
	
}
