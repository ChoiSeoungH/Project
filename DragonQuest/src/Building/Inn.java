package Building;

import Manager.UnitManager;
import Unit.Player;
import Util.Util;

public class Inn implements Building {
  private final int fee = 60;

  @Override
  public void PrintBuildingMenu() {
    System.out.println("어서 와. 여긴 여행자의 여관이야.?");
    while (true) {
      System.out.println("묵고 가겠어? 아니면 잠깐 쉬다 갈래");
      System.out.println("둘 다 가격은 " + fee + " 골드야");
      System.out.println("돈 : " + UnitManager.getMoney());
      int menu = Util.getInstance().getValue("[1:묵는다] [2:쉰다] [0:그만두기]", 0, 2);
      if (UnitManager.getMoney() < fee) {
        System.out.println("이런 소지금이 부족하네");
        continue;
      }
      switch (menu) {
        case 0:
          System.out.println("잘 가, 필요하면 다음에 또 들르고.");
          return;
        case 1: //묵는다
          stayAtInn();
          break;
        case 2: //쉰다
          restAtinn();
          break;
      }
    }
  }

  private void TakeRest() {
    for (Player player : UnitManager.getInstance().getPlayerList()) {
      if (!player.isDead()) {
        player.setCurhp(player.getMaxhp());
      }
    }
    UnitManager.setMoney(UnitManager.getMoney() - fee);
    System.out.println("그럼 편히 쉬도록 해");
    Util.getInstance().setDelay(500);
    System.out.println("그 밖에 다른 용건은 ?");
  }

  private void stayAtInn() {
    TakeRest();
  }

  private void restAtinn() {
    System.out.println("언제까지 쉴 거야?");
    int menu = Util.getInstance().getValue("[1:아침] [2:낮] [3:저녁] [4:밤] [0:그만두기]", 0, 4);
    switch (menu) {
      case 0:
        System.out.println("그만두겠어? 그럼 다시,");
        return;
      case 1: //아침
        break;
      case 2: //낮
        break;
      case 3: //저녁
        break;
      case 4: //밤
        break;
    }
    TakeRest();

  }
}
