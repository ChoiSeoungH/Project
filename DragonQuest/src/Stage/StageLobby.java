package Stage;


import Manager.GameManager;
import Util.Util;

public class StageLobby implements Stage {

  @Override
  public boolean update() {
    System.out.println("=====[LOBBY]=====");
    int menu = Util.getInstance().getValue("[1.시작] [2.종료] : ", 1, 2);
    switch (menu) {
      case 1:
        GameManager.getInstance().setNextStage("PLAY");
        break;
      case 2:
        GameManager.getInstance().setNextStage("");
        break;
    }
    return false;
  }

  @Override
  public void init() {

  }
}
