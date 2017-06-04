package septagram.Theomachy.Ability.GOD;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.Utility.CoolTimeChecker;
import septagram.Theomachy.Utility.EventFilter;
import septagram.Theomachy.Utility.PlayerInventory;
import septagram.Theomachy.Utility.Skill;

public class Demeter extends Ability
{
	private final int coolTime0=30;
	private final int material=4;//나무
	private final int stack0=10;
	private final static String[] des= {
				"She is goddess of grain.",
			   "You can exchange cobblestone into bread.",
			   "Your food level dosen't decrease and",
			   "regaining health is very fast."};
	
	public Demeter(String playerName)
	{
		super(playerName,"Demeter", 4, true, true, false, des);
		Theomachy.log.info(playerName+abilityName);
		
		this.cool1=coolTime0;
		this.sta1=stack0;
		
		this.rank=2;
	}

	public void T_Active(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		if (PlayerInventory.InHandItemCheck(player, 369))
		{
			switch(EventFilter.PlayerInteract(event))
			{
			case 0:case 1:case 2:case 3:
				Action(player);
				break;
			}
		}
	}

	private void Action(Player player)
	{
		if (CoolTimeChecker.Check(player, 0)&&PlayerInventory.ItemCheck(player, material, stack0))
		{
			Skill.Use(player, material, stack0, 0,coolTime0);
			Inventory inventory = player.getInventory();
			inventory.addItem(new ItemStack(Material.BREAD.getId(),stack0));
		}
	}
	
	public void T_Passive(FoodLevelChangeEvent event)
	{
		((Player)event.getEntity()).setFoodLevel(20);
		event.setCancelled(true);
	}
	
	public void T_Passive(EntityRegainHealthEvent event)
	{
		event.setAmount(event.getAmount()<<2);
	}
}
