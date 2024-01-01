package Unit;

import Util.Util;

public class UnitNeedler extends Unit implements Monster {
  public UnitNeedler() {
    super(Util.getInstance().getRd().nextInt(3) + 10, 4, 0, 6, "몽실키");
  }

  @Override
  public void skill(Unit target) {
    System.out.println("몽실키의 강타 공격!");
    att *= 1.5;
    attack(target);
    att /= 1.5;
  }
}

