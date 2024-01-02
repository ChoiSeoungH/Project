package Map;

import Building.Building;
import Building.Church;
import Building.Inn;
import Building.Shop;
import Manager.UnitManager;
import Util.Util;

import java.util.HashMap;
import java.util.Map;


public class Town implements GameMap {
  private final int MIDDLE = Math.round(SIZE / 2);
  private String[][] townMap;
  private int x;
  private int y;
  private Map<String, Building> buildingList;

  @Override
  public void init() {
    townMap = new String[SIZE][SIZE];
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (i == 0 || j == 0 || i == SIZE - 1 || j == SIZE - 1)
          townMap[i][j] = MapSymbol.WALL.getSymbol();
        else
          townMap[i][j] = MapSymbol.TILE.getSymbol();
      }
    }
    townMap[0][MIDDLE] = MapSymbol.DOOR.getSymbol();
    townMap[1][SIZE - 2] = MapSymbol.INN.getSymbol();
    townMap[SIZE - 2][1] = MapSymbol.SHOP.getSymbol();
    townMap[1][1] = MapSymbol.CHURCH.getSymbol();
    MapSymbol.PLAYER.setSymbol(UnitManager.getInstance().getPartyList().get(0).getName());
    townMap[y = 2][x = 1] = MapSymbol.PLAYER.getSymbol();

    buildingList = new HashMap<>();
    buildingList.put(MapSymbol.CHURCH.getSymbol(), new Church());
    buildingList.put(MapSymbol.INN.getSymbol(), new Inn());
    buildingList.put(MapSymbol.SHOP.getSymbol(), new Shop());
  }

  @Override
  public void PrintMap() {
    System.out.println("======== 이시마을 ========");
    townMap[y][x] = MapSymbol.PLAYER.getSymbol();
    for (String[] strings : townMap) {
      for (String string : strings) {
        System.out.print(string);
      }
      System.out.println();
    }
  }


  @Override
  public String movePlayer(String move) {
    if (!(move.equals("w") || move.equals("a") || move.equals("s") || move.equals("d"))) return "TOWN";
    int dx = x;
    int dy = y;
    switch (move) {
      case "w"://상
        dy -= 1;
        break;
      case "a"://좌
        dx -= 1;
        break;
      case "s"://하
        dy += 1;
        break;
      case "d"://우
        dx += 1;
        break;
    }//eos
    if (dy < 0 || dx < 0 || dy >= SIZE || dx >= SIZE
        || townMap[dy][dx].equals(MapSymbol.WALL.getSymbol())) return "TOWN";
    if (townMap[dy][dx] == MapSymbol.DOOR.getSymbol()) {
      return "DELKADAR";
    }
    if (townMap[dy][dx] != MapSymbol.TILE.getSymbol()) {
      Building building = buildingList.get(townMap[dy][dx]);
      building.PrintBuildingMenu();
      return "TOWN";
    }

    townMap[y][x] = MapSymbol.TILE.getSymbol();
    x = dx;
    y = dy;
    townMap[y][x] = MapSymbol.PLAYER.getSymbol();
    return "TOWN";
  }//eom

  public void printGameExitMenu() {
    String menu;
    menu = Util.getInstance().getValue("저장 하지않은 데이터가 날라갑니다. 종료하시겠습니까? [y:종료] [n:뒤로가기] >> ");
    switch (menu) {
      case "y":
        gameExit();
        break;
      case "n":
    }//eos
  }//eom

  private void gameExit() {
    System.out.println("게임을 종료합니다");
    System.exit(0);
  }//eom
}
