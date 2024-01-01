package _Main;

import Manager.GameManager;

public class _MainGame {

  public static void main(String[] args) {

    GameManager gm = GameManager.getInstance();
    boolean run;
    do {
      run = gm.changeStage();
    } while (run);
    System.out.println("게임오버");
  }
}