package Unit;


import Manager.UnitManager;
import Util.Util;

public class UnitSlime extends Unit implements Monster {
  public UnitSlime() {
    super(Util.getInstance().getRd().nextInt(2) + 5, 2, 0, 4, "슬라임");
  }

  @Override
  public void skill(Unit target) {
    System.out.println("슬라임은 지원을 요청했다!");
    int rdNum = Util.getInstance().getRd().nextInt(10) + 1;
    if (rdNum <= 2) {
      System.out.println("새로운 슬라임 출현!");
      UnitManager.getInstance().getMonsterList().add(this);
    } else {
      System.out.println("아무런 반응이 없다!");
    }
  }
}