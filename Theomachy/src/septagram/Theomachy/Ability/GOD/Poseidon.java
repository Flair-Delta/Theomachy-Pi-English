package septagram.Theomachy.Ability.GOD;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import septagram.Theomachy.Theomachy;
import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.DB.GameData;
import septagram.Theomachy.Message.T_Message;
import septagram.Theomachy.Timer.CoolTime;
import septagram.Theomachy.Utility.CoolTimeChecker;
import septagram.Theomachy.Utility.EventFilter;
import septagram.Theomachy.Utility.PlayerInventory;
import septagram.Theomachy.Utility.Skill;

public class Poseidon extends Ability
{
	private boolean flag = true;
	private final int coolTime0=240;
	private final int material=4;
	private final int stack0=5;
	private final static String[] des= {
				"He is god of sea and water.",
			   "If you are in water, you can avoid attack by 10% chance.",
			   "Above passive ability will continue after leaving water by 7 sec.",
			   "By using ability, you can spawn water-wall.",
			   "During 7 sec, people who are in water-wall will be bounced.",
			   "Water-wall can destroy cobblestones."};
	
	public Poseidon(String playerName)
	{
		super(playerName,"Poseidon", 2, true, true, false, des);
		Theomachy.log.info(playerName+abilityName);
		
		this.cool1=coolTime0;
		this.sta1=stack0;
		
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
			}
		}
	}
	
	private void leftAction(Player player)
	{
		if (CoolTimeChecker.Check(player, 0)&&PlayerInventory.ItemCheck(player, material, stack0))
		{
			if (flag)
			{
				Skill.Use(player, material, stack0, 0, coolTime0);
				Location location = player.getLocation();
				Vector v = player.getEyeLocation().getDirection();
				v.setX(Math.round(v.getX()));
				v.setY(0);
				v.setZ(Math.round(v.getZ()));
				KnockBack  k = new KnockBack(player,v); 
				Wave w = new Wave(player, location, v);
				k.start();
				w.start();
			}
			else
				player.sendMessage("You can't use it beacuse sustainment time of skill isn't finished.");
		}
	}
	class KnockBack extends Thread
	{
		final Player player;
		Vector v;
		
		KnockBack(Player player, Vector v)
		{
			this.player=player;
			this.v=v.clone();
			this.v.multiply(10);
			this.v.setY(10);
		}

		public void run()
		{
			flag = false;
			Player[] players = Bukkit.getOnlinePlayers();
			for (int i=0; i<5; i++)
			{
				for (Player player : players)
					if (player != this.player && (player.getLocation().getBlock().getTypeId() == 9 ||
							player.getLocation().getBlock().getTypeId() == 8))
						player.setVelocity(v);
				try {
					sleep(1500);
				} catch (InterruptedException e) {
				}
			}
			flag = true;
		}
	}
	class Wave extends Thread
	{
		final Player player;
		final Location location;
		final Location remove;
		final Vector v;
		
		
		
		Wave(Player player, Location location, Vector v)
		{
			this.player = player;
			this.location = location.add(0,2,0);
			this.remove = location.clone();
			this.v=v;
		}
		
		public void run()
		{
			try
			{
				for (int i=0; i<9; i++)
				{
					Block b = location.add(v).getBlock();
					if (b.getTypeId() == 0 || b.getTypeId() == 4)
						b.setTypeId(8);
				}
				sleep(3000);
				for (int i=0; i<9; i++)
				{
					Block b = remove.add(v).getBlock();
					if (b.getTypeId() == 8 || b.getTypeId() == 9)
						b.setTypeId(0);
				}
			}
			catch(Exception e)
			{}
		}
	}


	public void T_Passive(EntityDamageEvent event)
	{
		Player player = (Player) event.getEntity();
		if (event.getCause() == DamageCause.DROWNING)
		{
			event.setCancelled(true);
			CoolTime.COOL0.put(playerName+"0", 7);
			T_Message.PassiveEnable(player, 0);
		}
		else if (CoolTime.COOL0.containsKey(player.getName()+"0"))
		{
			int rn = (int) (Math.random()*3);
			if (rn == 0)
			{
				event.setCancelled(true);
				player.sendMessage("AVOIDING!!");
			}
		}
	}
	public void conditionSet()
	{
		Player player = GameData.OnlinePlayer.get(playerName);
		player.setMaximumAir(0);
		player.setRemainingAir(0);
	}
	
	public void conditionReSet()
	{
		Player player = GameData.OnlinePlayer.get(playerName);
		player.setMaximumAir(300);
		player.setRemainingAir(300);
	}
}
