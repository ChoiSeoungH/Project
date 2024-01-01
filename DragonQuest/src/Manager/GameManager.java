package Manager;


import Files.FileManager;
import Stage.*;
import Util.Util;

import java.util.HashMap;
import java.util.Map;

public class GameManager {
  private static final GameManager instance = new GameManager();
  private String nextStage;
  private String curStage;
  private Map<String, Stage> stageList;

  private GameManager() {
    init();
  }

  public static GameManager getInstance() {
    return instance;
  }

  public String getNextStage() {
    return nextStage;
  }

  public void setNextStage(String nextStage) {
    this.nextStage = nextStage;
  }

  public String getCurStage() {
    return curStage;
  }

  private void init() {
    FileManager.getInstance().loadFile(UnitManager.getInstance().getPlayer());
    stageList = new HashMap<>();
    nextStage = "TITLE";
    curStage = "";

    stageList.put("TITLE", new StageTitle());
    stageList.put("LOBBY", new StageLobby());
    stageList.put("PLAY", new StagePlay());
    stageList.put("BATTLE", new StageBattle());

  }

  public boolean changeStage() {
    Util.getInstance().setDelay(500);
    curStage = nextStage;
    Stage stage = stageList.get(curStage);
    stage.init();
    boolean run = true;
    do {
      run = stage.update();
    } while (run);

    return !nextStage.equals("");
  }// eom
}