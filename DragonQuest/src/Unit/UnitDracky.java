package Unit;


import Util.Util;

public class UnitDracky extends Unit implements Monster {

  public UnitDracky() {
    super(Util.getInstance().getRd().nextInt(3) + 8, 3, 1, 5, "드라키");
  }

  @Override
  public void skill(Unit target) {
    System.out.println("드라키의 흡혈 공격!");
    int damage = attack(target);
    int hp = curhp + damage / 2;
    if (hp > maxhp) {
      hp = maxhp;
    }
    System.out.printf("드라키가 체력을 [%d]만큼 회복 [%d]->[%d]%n", damage, curhp, hp);
  }


}