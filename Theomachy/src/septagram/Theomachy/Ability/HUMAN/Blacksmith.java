package septagram.Theomachy.Ability.HUMAN;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.Utility.CoolTimeChecker;
import septagram.Theomachy.Utility.EventFilter;
import septagram.Theomachy.Utility.PlayerInventory;
import septagram.Theomachy.Utility.Skill;

public class Blacksmith extends Ability
{
	private final int coolTime1=300;
	private final int coolTime2=600;
	private final int material1=4;
	private final int material2=265;//철괴
	private final int stack1=70;
	private final int stack2=20;
	private final static String[] des= {"철,다이아를 만들어 낼 수 있는 능력입니다.",
			   "일반능력으로 코블스톤을 소비하여 철괴 10개를 획득할 수 있습니다.",
			   "고급능력으로 철괴를 소비하여 다이아 5개를 얻을 수 있습니다."};
	
	public Blacksmith(String playerName)
	{
		super(playerName,"대장장이", 5, true, false, false, des);
		Theomachy.log.info(playerName+abilityName);
		
		this.cool1=coolTime1;
		this.cool2=coolTime2;
		this.sta1=stack1;
		this.sta2=stack2;
		
		this.rank=4;
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
		if (CoolTimeChecker.Check(player, 1)&&PlayerInventory.ItemCheck(player, material1, stack1))
		{
			Skill.Use(player, material1, stack1, 1, coolTime1);
			World world = player.getWorld();
			world.dropItem(player.getLocation().add(0,2,0), new ItemStack(Material.IRON_INGOT.getId(), 10));
		}
	}
	
	private void rightAction(Player player)
	{
		if (CoolTimeChecker.Check(player, 2)&&PlayerInventory.ItemCheck(player, material2, stack2))
		{
			Skill.Use(player, material2, stack2, 2, coolTime2);
			World world = player.getWorld();
			world.dropItem(player.getLocation().add(0,2,0), new ItemStack(Material.DIAMOND.getId(), 5));
		}
	}
}
