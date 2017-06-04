package septagram.Theomachy.Ability.HUMAN;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import septagram.Theomachy.Ability.Ability;
import septagram.Theomachy.Utility.BlockFilter;
import septagram.Theomachy.Utility.EventFilter;
import septagram.Theomachy.Utility.PlayerInventory;

public class Megumin extends Ability{

	private final static String[] des= { "이 능력은 메구밍!",
											   "아크 위저드이자, 최강의 공격 마법인",
											   "폭렬 마법을 다루는 능력!",
											   "그렇습니다. 게임에서 딱 한 번 영창 후",
											   "전방에 폭렬 마법을 날릴 수 있습니다.",
											   "블레이즈 로드로 선택한 블럭에 강력한 ",
											   "폭발을 날릴 수 있습니다. 단, 사용 후 쓰러집니다."};
	
	public Megumin(String playerName) {
		super(playerName, "메구밍", 128, true, false, false, des);
		
		this.rank=4;
		
		this.sta1=32;
		this.cool1=0;
	}

	public static boolean cancel=false;
	
	public void T_Active(PlayerInteractEvent event){
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

	private void leftAction(Player player) {
		if(PlayerInventory.ItemCheck(player, 4, sta1)) {
			if(!cancel) {
				Block block=player.getTargetBlock(null, 25);
				if (BlockFilter.AirToFar(player, block))
				{
					
					
					
					cancel=true;
				}
			}
			else {
				player.sendMessage("더 이상 쓸 수 없습니다...");
			}
		}
	}
	
	public void conditionSet(){
		cancel=false;
	}
	
}
