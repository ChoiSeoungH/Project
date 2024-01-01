package Skill;

import PlayerClass.Fighter;
import Unit.Player;
import Unit.Unit;
import Util.Util;

public class Hand implements Skill, Fighter {
  @Override
  public void castSkill(Player player, Unit target) {
    System.out.println(player.getName() + "의 일섬 찌르기 공격!");
    int damage;
    if (Util.getInstance().getRd().nextInt(10) + 1 <= 4) {
      System.out.println("🩻 통한의 일격!");
      damage = (player.getAtt() * 3 - target.getDef());
    } else {
      System.out.println("하지만 빗나갓다!");
      return;
    }
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
