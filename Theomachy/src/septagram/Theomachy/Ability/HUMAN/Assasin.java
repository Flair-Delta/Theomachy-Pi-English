package septagram.Theomachy.Ability.HUMAN;

import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.DB.GameData;
import septagram.Theomachy.Timer.CoolTime;
import septagram.Theomachy.Utility.CoolTimeChecker;
import septagram.Theomachy.Utility.DirectionChecker;
import septagram.Theomachy.Utility.EventFilter;
import septagram.Theomachy.Utility.PlayerInventory;
import septagram.Theomachy.Utility.Skill;

public class Assasin extends Ability
{
	private final int coolTime1=1;
	private final int coolTime2=20;
	private final int material=4;
	private final int stack1=0;
	private final int stack2=1;
	private final static String[] des= {
				"You has agile movement.",
			   "After jumping, if you use your normal ability, ",
			   "you can jump one more time.",
			   "By using high ability, you can teleport to a near player."};
	
	public Assasin(String playerName)
	{
		super(playerName,"Assasin", 108, true, false, false, des);
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
		Location temp = player.getLocation();
		Block b = temp.add(0,-1,0).getBlock();
		if ((b.getTypeId()==0) || (b.getTypeId()==78) || (b.getTypeId()==44))
		{	
			if ((!CoolTime.COOL0.containsKey(playerName+"0") && (PlayerInventory.ItemCheck(player, material, stack1))))
			{
			CoolTime.COOL0.put(playerName+"0", coolTime1);
			PlayerInventory.ItemRemove(player, material, stack1);
			World world = player.getWorld();
			Location location = player.getLocation();
			Vector v = player.getEyeLocation().getDirection();
			v.setY(0.5);			
			player.setVelocity(v);
			world.playEffect(location, Effect.ENDER_SIGNAL, 1);
			}
		}
	}
	
	private void rightAction(Player player)
	{
		if (CoolTimeChecker.Check(player, 2)&&PlayerInventory.ItemCheck(player, material, stack2))
		{
			boolean flag = true;
			List<Entity> entityList = player.getNearbyEntities(10, 10, 10);
			for (Entity e : entityList)
			{
				if (e instanceof Player)
				{
					Player target = (Player) e;
					
					String targetTeamName = GameData.PlayerTeam.get(target.getName());
					String playerTeamName = GameData.PlayerTeam.get(player.getName());
					if ((targetTeamName == null) || !(targetTeamName.equals(playerTeamName)))
					{
						Skill.Use(player, material, stack2, 2, coolTime2);
						Location fakeLocation = player.getLocation();
						Location location = target.getLocation();
						World world = player.getWorld();
						List<Player> playerlist = world.getPlayers();
						for (Player each : playerlist)
							each.hidePlayer(player);
						switch(DirectionChecker.PlayerDirection(target))
						{
						case 0:
							location.add(0,0,-1);
							break;
						case 1:
							location.add(0.7,0,-0.7);
							break;
						case 2:
							location.add(1,0,0);
							break;
						case 3:
							location.add(0.7,0,0.7);
							break;
						case 4:
							location.add(0,0,1);
							break;
						case 5:
							location.add(-0.7,0,0.7);
							break;
						case 6:
							location.add(-1,0,0);
							break;
						case 7:
							location.add(-0.7,0,-0.7);
							break;
						}
						player.teleport(location);
						world.dropItem(fakeLocation.add(0,1,0), new ItemStack(38, 1));
						for (Player each : playerlist)
							each.showPlayer(player);
						flag=false;
						break;
					}
				}
			}
			if (flag)
				player.sendMessage("스킬을 사용 할 수 있는 상대가 없습니다.");
		}
	}
}
