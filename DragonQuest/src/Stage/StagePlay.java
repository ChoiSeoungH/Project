package Stage;

import Files.FileManager;
import Item.Inventory;
import Manager.UnitManager;
import Map.Delkadar;
import Map.GameMap;
import Map.Town;
import Unit.Player;
import Util.Util;

import java.util.HashMap;
import java.util.Map;

public class StagePlay implements Stage {
  private final Map<String, GameMap> mapList = new HashMap<>();
  private String nextMap;
  private String curMap = "TOWN";
  private GameMap gameMap;

  private static void printMember() {
    int i = 0;
    for (Player player : UnitManager.getInstance().getPlayerList()) {
      System.out.println("============" + ++i + "============");
      System.out.println(player);
      System.out.println("===========================");
    }
  }

  @Override
  public void init() {
    mapList.put("TOWN", new Town());
    mapList.put("DELKADAR", new Delkadar());
  }

  @Override
  public boolean update() {
    System.out.println("=====[PLAY]=====");
    gameMap = mapList.get(curMap);
    gameMap.init();
    while (true) {
      gameMap.PrintMap();
      String move = Util.getInstance().getValue("[w상] [a하] [s좌] [d우] [m메뉴] >> ");
      if (move.equals("m")) {
        printGameMenu(gameMap);
      }
      nextMap = gameMap.movePlayer(move);
      if (curMap.equals(nextMap)) continue;
      gameMap = mapList.get(nextMap);
      if (nextMap.equals("DELKADAR") && curMap.equals("TOWN")) {
        gameMap.init();
      }
      curMap = nextMap;
    }//eow
  }//eom

  private void printGameMenu(GameMap gameMap) {
    while (true) {
      System.out.println("돈 : " + UnitManager.getMoney());
      String menu = Util.getInstance().getValue("[p:파티원] [l:로드] [e:게임종료] [q:메뉴닫기]>> ");
      switch (menu) {
        case "p"://파티원
          printPartyMenu();
          break;
        case "l"://로드
          FileManager.getInstance().loadFile(UnitManager.getInstance().getPlayer());
          break;
        case "e"://게임종료
          Town t = (Town) gameMap;
          t.printGameExitMenu();
        case "q"://메뉴닫기
          return;
      }//eos
    }//eow
  }//eom

  private void printPartyMenu() {
    while (true) {
      printMember();
      int menu = Util.getInstance().getValue("[1:파티원 변경] [2:아이템 변경] [0:뒤로 가기]>> ", 0, 2);
      switch (menu) {
        case 0://뒤로가기
          return;
        case 1://파티원변경
          UnitManager.getInstance().changePartyMember();
          break;
        case 2://아이템장착
          Inventory.getInstance().inventoryMenu();
          break;
      }
    }
  }


}
