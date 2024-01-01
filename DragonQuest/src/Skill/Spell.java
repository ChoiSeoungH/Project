package Skill;

import PlayerClass.Magician;
import Unit.Player;
import Unit.Unit;
import Util.Util;

public class Spell implements Skill, Magician {
  @Override
  public void castSkill(Player player, Unit target) {
    System.out.println(player.getName() + "가 메라를 시전했다!");
    int damage = 10;
    target.setCurhp(target.getCurhp() - damage);
    System.out.printf("[%s] 이 [%s] 에게 총 %d 포인트의 데미지!%n", player.getName(), target.getName(), damage);
    if (target.getCurhp() <= 0) {
      System.out.printf("[%s] 을 쓰러트렸다!%n", target.getName());
      target.setCurhp(0);
      target.setDead(true);
    }
    Util.getInstance().setDelay(500);
  }
}
