package Map;

import Manager.UnitManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum MapSymbol {
  MONSTER("\uD83D\uDC7E"), TILE("🟩"), WALL("\uD83E\uDDF1"), DOOR("\uD83D\uDEAA"),
  PLAYER(""),
  INN("🏨"), SHOP("\uD83C\uDFDA\uFE0F"), CHURCH("⛪\uFE0F");
  private String symbol;

  MapSymbol(String symbol) {
    this.symbol = symbol;
  }


  public void setSymbol(String player) {
    ArrayList<String> list = new ArrayList<>(Arrays.asList("🤴", "🦹‍♂️", "🧙‍♂️", "🧙‍♀️", "🧙🏽‍♀️", "👸", "👨‍🎤"));
    String temp = null;
    if (player.equals("용사")) {
      temp = list.get(0);
    } else if (player.equals("카뮈")) {
      temp = list.get(1);
    } else if (player.equals("로우")) {
      temp = list.get(2);
    } else if (player.equals("세냐")) {
      temp = list.get(3);
    } else if (player.equals("베로니카")) {
      temp = list.get(4);
    } else if (player.equals("마르티나")) {
      temp = list.get(5);
    } else if (player.equals("실비아")) {
      temp = list.get(6);
    }
    this.symbol = temp;
  }

  public String getSymbol() {
    return symbol;
  }

}
