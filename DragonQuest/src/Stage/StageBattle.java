package Stage;

import Manager.GameManager;
import Manager.UnitManager;
import Unit.Monster;
import Unit.Player;
import Unit.Unit;
import Util.Util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class StageBattle implements Stage {
  private static int exp;

  public static <O extends Unit, T extends Unit> void attackOp(LinkedList<O> list, T t) {
    while (true) {
      int rdNum = Util.getInstance().getRd().nextInt(list.size());
      if (!list.get(rdNum).isDead()) {
        if (t instanceof Monster) {
          int rdNum2 = Util.getInstance().getRd().nextInt(10) + 1;
          if (rdNum2 <= 4) {
            t.skill(list.get(rdNum));
          } else {
            t.attack(list.get(rdNum));
          }
        } else {
          t.attack(list.get(rdNum));
        }
        if (list.get(rdNum).isDead()) {
          exp += list.get(rdNum).getExp();
          list.remove(rdNum);
        }
        break;
      }
    }
  }

  @Override
  public void init() {
    UnitManager.getInstance().getMonsterList().clear();
    UnitManager.getInstance().generateRdMonster(Util.getInstance().getRd().nextInt(4) + 1);

  }

  @Override
  public boolean update() {
    boolean turn = true;
    boolean run = true;
    LinkedList<Player> pList = new LinkedList<>(UnitManager.getInstance().getPartyList());
    LinkedList<Unit> mList = new LinkedList<>(UnitManager.getInstance().getMonsterList());
    System.out.println("======[BATTLE]======");
    while (run) {
      if (turn) {
        run = playerAttack(pList, mList);
      } else {
        run = monsterAttack(pList, mList);
      }
      turn = !turn;
    }
    return false;
  }

  public void print_character() {

    System.out.println("======[PLAYER]======");
    for (Player player : UnitManager.getInstance().getPartyList()) {
      player.printData();
    }
    System.out.println("======[MONSTER]======");
    for (Unit monster : UnitManager.getInstance().getMonsterList()) {
      monster.printData();
    }
  }

  private boolean playerAttack(LinkedList<Player> pList, LinkedList<Unit> mList) {
    for (Player player : pList) {
      print_character();
      if (isAllDead(mList, player)) {
        int exp;
        for (Player p : pList) {
          if (StageBattle.exp % pList.size() != 0) {
            exp = StageBattle.exp / pList.size() + 1;
            StageBattle.exp -= 1;
          } else {
            exp = StageBattle.exp / pList.size();
          }
          System.out.printf("%s가 %d의 경험치를 획득했습니다.\n", p.getName(), exp);
          p.setExp(exp);
          Util.getInstance().setDelay(500);
        }
        StageBattle.exp = 0;
        return false;
      }
      if (player.getCurhp() <= 0) continue;
      System.out.println("======[메뉴 선택]=====");
      String msg = "[%s] [1.어택] [2.스킬] [3.파티원 변경] : ".formatted(player.getName());
      int sel = Util.getInstance().getValue(msg, 1, 3);
      switch (sel) {
        case 1:
          attackOp(mList, player);
          break;
        case 2:
          castSkill(mList, player);
          break;
        case 3:
          pList = UnitManager.getInstance().changePartyMember();
          break;
      }
    }

    return true;
  }

  private void castSkill(LinkedList<Unit> mList, Player player) {
    Set set = player.getSkillList().entrySet();
    Iterator it = set.iterator();
    int idx = 0;
    while (it.hasNext()) {
      Map.Entry e = (Map.Entry) it.next();
      System.out.printf("[%d : %s] ", ++idx, e.getKey());
    }
    int num = Util.getInstance().getValue("사용할 스킬 : ", 0, set.size()) - 1;

    it = set.iterator();
    idx = 0;
    while (it.hasNext()) {
      Map.Entry e = (Map.Entry) it.next();
      if (idx == num) {
        while (true) {
          int rdNum = Util.getInstance().getRd().nextInt(mList.size());
          if (mList.get(rdNum).isDead()) {
            continue;
          }
          player.getSkillList().get(e.getKey()).castSkill(player, mList.get(rdNum));
          if (mList.get(rdNum).isDead()) {
            exp += mList.get(rdNum).getExp();
            mList.remove(rdNum);
          }
          break;
        }
      }
      idx += 1;
    }
  }

  private boolean monsterAttack(LinkedList<Player> pList, LinkedList<Unit> mList) {
    for (Unit monster : mList) {
      if (monster.isDead()) continue;

      attackOp(pList, monster);
      if (isAllDead(pList, monster)) return false;
    }
    Util.getInstance().setDelay(500);
    return true;
  }

  private <O extends Unit, T extends Unit> boolean isAllDead(LinkedList<O> list, T t) {
    if (list.isEmpty()) {
      if (!(t instanceof Player)) {
        System.out.println("게임오버");
        GameManager.getInstance().setNextStage("TITLE");
      } else {
        System.out.println("전투종료!");
        GameManager.getInstance().setNextStage("PLAY");
      }
      return true;
    }
    return false;
  }
}