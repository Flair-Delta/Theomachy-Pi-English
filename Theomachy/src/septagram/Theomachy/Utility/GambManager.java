package septagram.Theomachy.Utility;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GambManager {
	
	public static void Gamb(Player player) {
		Inventory inv = player.getInventory();
		if (inv.contains(4, 32)){
			Random r=new Random();
			player.getInventory().removeItem(new ItemStack(4, 32));
			int rn = (int)(r.nextInt(100));
			if(rn<=4){
				player.sendMessage(ChatColor.AQUA+"와우! 축하합니다! 다이아몬드 3개입니다!");
				player.getInventory().addItem(new ItemStack(264, 3));
			}
			else if(rn== 5 || rn <= 19){
				player.sendMessage(ChatColor.GOLD+"대박! 짜잔! 원목 3개 당첨 축하드립니다!");
				player.getInventory().addItem(new ItemStack(Material.LOG, 3));
			}
			else if(rn==20 || rn<=34){
				player.sendMessage(ChatColor.RED+"하하하하하하! 이 돌은 서버의 신이 가져간다! 하하하하하!");
				player.sendMessage(ChatColor.BLUE+"서버의 신의 자비로 능력의 막대를 드립니다.");
				player.getInventory().addItem(new ItemStack(369, 1));
			}
			else if(rn==35 || rn<=79){
				player.sendMessage ("당신의 운은 평범하군요! 철괴 4개를 드립니다.");
				player.getInventory().addItem(new ItemStack(265, 4));
			}
			else if(rn==80||rn<=98){
				player.sendMessage ("당신의 운은 평범하군요! 철괴 6개를 드립니다.");
				player.getInventory().addItem(new ItemStack(265, 6));
			}
			else if(rn==99){
				player.sendMessage(ChatColor.YELLOW+"헐... 대박, 당신의 운은 미쳤군요!");
				player.sendMessage(ChatColor.AQUA+"다이아몬드 22개를 드립니다!");
				player.sendMessage(ChatColor.GREEN+"2222222222222222222222 당신의 콩을 위하여!");
				player.getInventory().addItem(new ItemStack(264, 22));
		}
	}else{
		player.sendMessage(ChatColor.RED+"조약돌이 부족합니다! 정신차려임마.");
	}
	}
	
}
