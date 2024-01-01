package Skill;

import PlayerClass.Thief;
import Unit.Player;
import Unit.Unit;
import Util.Util;

public class Dagger implements Skill, Thief {
  @Override
  public void castSkill(Player player, Unit target) {
    System.out.println(player.getName() + "의 어쌔신 어택!");
    if (Util.getInstance().getRd().nextInt(10) + 1 <= 2) {
      System.out.printf("[%s] 을 쓰러트렸다!%n", target.getName());
      target.setCurhp(0);
      target.setDead(true);
      Util.getInstance().setDelay(500);
    } else {
      System.out.println("하지만 빗나갓다!");
    }
  }
}
