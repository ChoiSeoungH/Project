package Skill;

import Manager.UnitManager;
import PlayerClass.Healer;
import Unit.Player;
import Unit.Unit;
import Util.Util;

public class Heal implements Skill, Healer {

  @Override
  public void castSkill(Player player, Unit target) {
    UnitManager.getInstance().printPartyMember();
    int num = Util.getInstance().getValue("회복할 파티원을 선택 : ", 1, UnitManager.getInstance().getPartyList().size()) - 1;
    target = UnitManager.getInstance().getPartyList().get(num);
    System.out.println(player.getName() + "가 호이미를 시전했다!");
    int heal = 10;
    System.out.printf("[%s]가 [%s]의 체력을 [%d]만큼 회복!%n", player.getName(), target.getName(), heal);
    target.setCurhp(target.getCurhp() + heal);
    if (target.getCurhp() > target.getMaxhp()) {
      target.setCurhp(target.getMaxhp());
    }
    Util.getInstance().setDelay(500);
  }
}
