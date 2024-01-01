package Stage;


import Manager.GameManager;
import Util.Util;

public class StageTitle implements Stage {
  @Override
  public boolean update() {
    System.out.println("==== Dragon Quest ====");
    String input = Util.getInstance().getValue("시작하려면 아무버튼이나 입력하세요 : ");
    GameManager.getInstance().setNextStage("LOBBY");
    return false;
  }

}
