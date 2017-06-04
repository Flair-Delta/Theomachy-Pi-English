package septagram.Theomachy.Ability.HUMAN;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.Utility.CoolTimeChecker;
import septagram.Theomachy.Utility.EventFilter;
import septagram.Theomachy.Utility.PlayerInventory;
import septagram.Theomachy.Utility.Skill;

public class Archer extends Ability
{
	private final int coolTime1=20;
	private final int coolTime2=60;
	private final int material=4;
	private final int stack1=7;
	private final int stack2=15;
	private final static String[] des= {"궁수입니다.",
			   "활 공격 데미지가 1.4배로 상승합니다.",
			   "좌클릭으로 화살을 얻을 수 있으며 우클릭으로 활을 얻을 수 있습니다."};
	
	public Archer(String playerName)
	{
		super(playerName,"아처", 101, true, true, false, des);
		Theomachy.log.info(playerName+abilityName);
		this.cool1=coolTime1;
		this.cool2=coolTime2;
		this.sta1=stack1;
		this.sta2=stack2;
		
		this.rank=2;
	}
	
	public void T_Active(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		if (PlayerInventory.InHandItemCheck(player, 369))
		{
			switch(EventFilter.PlayerInteract(event))
			{
			case 0:case 1:
				leftAction(player);
				break;
			case 2:case 3:
				rightAction(player);
				break;
			}
		}
	}

	private void leftAction(Player player)
	{
		if (CoolTimeChecker.Check(player, 1)&&PlayerInventory.ItemCheck(player, material, stack1))
		{
			Skill.Use(player, material, stack1, 1, coolTime1);
			World world = player.getWorld();
			Location location = player.getLocation();
			world.dropItem(location, new ItemStack(Material.ARROW.getId(), 1));
		}
	}
	
	private void rightAction(Player player)
	{
		if (CoolTimeChecker.Check(player, 2)&&PlayerInventory.ItemCheck(player, material, stack2))
		{
			Skill.Use(player, material, stack2, 2, coolTime2);
			World world = player.getWorld();
			Location location = player.getLocation();
			world.dropItem(location, new ItemStack(Material.BOW.getId(), 1));
		}
	}
	
	public void T_Passive(EntityDamageByEntityEvent event)
	{
		int damage = (int)(event.getDamage());
		event.setDamage((int) (damage*1.4));
	}
}
