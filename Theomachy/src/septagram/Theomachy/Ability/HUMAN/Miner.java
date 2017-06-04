package septagram.Theomachy.Ability.HUMAN;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;

public class Miner extends Ability
{
	
	private final static String[] des= {"곡괭이를 능숙하게 다룰 수 있습니다.",
			   "코블스톤을 캘 때 일정 3% 확률로 한 번에 10개를 얻을 수 있습니다.",
			   "금곡괭이를 제외한 곡괭이들의 데미지가 4로 고정됩니다."};
	
	public Miner(String playerName)
	{
		super(playerName,"광부", 102, false, true, false, des);
		Theomachy.log.info(playerName+abilityName);
		
		this.rank=2;
	}
	
	public void T_Passive(BlockBreakEvent event)
	{
		Block block = event.getBlock();
		if (block.getTypeId() == 4)
		{
			Location location = block.getLocation();
			World world = event.getPlayer().getWorld();
			Random random = new Random();
			if (random.nextInt(33) == 0)
			{
				Player player = event.getPlayer();
				player.sendMessage("손맛이 느껴집니다!");
				world.dropItemNaturally(location, new ItemStack(4, 9));
			}
		}
	}
	
	public void T_Passive(EntityDamageByEntityEvent event){
		Player p=(Player)event.getDamager();
		if(p.getName().equals(playerName)){
			Material m=p.getItemInHand().getType();
			if(m.equals(Material.WOOD_PICKAXE)||m.equals(Material.STONE_PICKAXE)||m.equals(Material.IRON_PICKAXE)||m.equals(Material.DIAMOND_PICKAXE)){
				event.setDamage(4);
			}
		}
	}
}
