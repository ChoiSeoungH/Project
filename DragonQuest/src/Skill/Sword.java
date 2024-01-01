package Skill;

import PlayerClass.Hero;
import Unit.Player;
import Unit.Unit;
import Util.Util;

public class Sword implements Skill, Hero {
  @Override
  public void castSkill(Player player, Unit target) {
    System.out.println(player.getName() + "의 화염베기 공격!");
    int damage = (int) (player.getAtt() * 1.2 + 0.5) - target.getDef();
    if (damage <= 0) damage = 1;//최소데미지
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
