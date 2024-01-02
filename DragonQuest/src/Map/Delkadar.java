package Map;

import Manager.GameManager;
import Util.Util;

public class Delkadar implements GameMap {
  private final int MIDDLE = Math.round(SIZE / 2);
  private String[][] outskirtsMap;
  private int x;
  private int y;

  @Override
  public void init() {
    outskirtsMap = new String[SIZE][SIZE];
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        outskirtsMap[i][j] = MapSymbol.TILE.getSymbol();
      }
    }
    outskirtsMap[SIZE - 1][MIDDLE] = MapSymbol.DOOR.getSymbol();
    outskirtsMap[y = SIZE - 2][x = MIDDLE] = MapSymbol.PLAYER.getSymbol();
    SettingMonsterPos();

  }//eom

  private void SettingMonsterPos() {
    for (int i = 0; i < 4; ) {
      int monsterX = Util.getInstance().getRd().nextInt(SIZE);
      int monsterY = Util.getInstance().getRd().nextInt(SIZE);
      if (outskirtsMap[monsterY][monsterX] == MapSymbol.TILE.getSymbol()) {
        outskirtsMap[monsterY][monsterX] = MapSymbol.MONSTER.getSymbol();
        i++;
      }
    }
  }

  @Override
  public void PrintMap() {
    System.out.println("======== 델카다르 외곽 ========");
    outskirtsMap[y][x] = MapSymbol.PLAYER.getSymbol();
    for (String[] strings : outskirtsMap) {
      for (String string : strings) {
        System.out.print(string);
      }
      System.out.println();
    }
  }

  @Override
  public String movePlayer(String move) {
    if (!(move.equals("w") || move.equals("a") || move.equals("s") || move.equals("d"))) return "DELKADAR";
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
    if (dy < 0 || dx < 0 || dy >= SIZE || dx >= SIZE) return "DELKADAR";
    if (outskirtsMap[dy][dx] == MapSymbol.DOOR.getSymbol()) {
      return "TOWN";
    }
    if (outskirtsMap[dy][dx] == MapSymbol.MONSTER.getSymbol()) {
      GameManager.getInstance().setNextStage("BATTLE");
      GameManager.getInstance().changeStage();
    }
    outskirtsMap[y][x] = MapSymbol.TILE.getSymbol();
    x = dx;
    y = dy;
    outskirtsMap[y][x] = MapSymbol.PLAYER.getSymbol();
    return "DELKADAR";
  }//eom
}
