package Building;

import Files.FileManager;
import Manager.UnitManager;
import Unit.Player;
import Util.Util;

public class Church implements Building {
  @Override
  public void PrintBuildingMenu() {
    System.out.println("신의 인도를 받은 길 잃은 어린양이여.\n저희 교회에 잘 오셨습니다.");
    while (true) {

      int menu = Util.getInstance().getValue("[1:기도하기] [2:되살리기] [3:스킬리셋] [0:그만두기]", 0, 3);
      switch (menu) {
        case 0:
          System.out.println("신은 언제나 우리를 지켜보고 계신답니다.\n 또 들러주세요.");
          return;
        case 1: //기도
          pray();
          break;
        case 2: //부활
          revive();
          break;
        case 3: //리셋
          resetSkill();
          break;
      }
      System.out.println("그 밖에 다른 용건은 없으신가요?");
    }
  }

  private void pray() {
    System.out.println("신 앞에서 지금까지의 행실을 고백하고 모험의 서에 기록합니다");
    FileManager.getInstance().saveFile(UnitManager.getInstance().getPlayer());
  }

  private void revive() {
    int i = 0;
    for (Player player : UnitManager.getInstance().getPlayerList()) {
      if (player.isDead()) {
        System.out.printf("[%d] %s%n", ++i, player.getName());
      }
    }
    if (i == 0) {
      System.out.println("부활시킬 파티원이 없습니다.");
      return;
    }
    String name = Util.getInstance().getValue("부활시킬 파티원의 이름을 알려주세요 : ");
    int idx = findMember(name);
    if (idx == -1) {
      System.out.println("이름을 다시 확인해주세요");
      return;
    }
    UnitManager.getInstance().getPlayerList().get(idx).setDead(false);
    System.out.printf("전지전능하신 신이시여, %s에게 다시 힘을! %n", UnitManager.getInstance().getPlayerList().get(idx).getName());
    Util.getInstance().setDelay(500);
    System.out.println("부활이 완료 되었습니다.");
  }

  private int findMember(String name) {
    int idx = 0;
    for (Player player : UnitManager.getInstance().getPlayerList()) {
      if (player.getName().equals(name)) {
        return idx;
      }
      idx++;
    }
    return -1;
  }

  private void resetSkill() {
    int i = 0;
    for (Player player : UnitManager.getInstance().getPlayerList()) {
      System.out.printf("[%d] %s%n", ++i, player.getName());
    }
    System.out.println("초기화할 스킬 1포인트당 20골드가 필요합니다.");
    int num = Util.getInstance().getValue("어느 분의 스킬을 초기화하시겠어요?", 1, i) - 1;
  }
}
