package Manager;


import Item.Inventory;
import Item.Item;
import Item.ItemSymbol;
import Unit.Player;
import Unit.Unit;
import Util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class UnitManager {
  private static final UnitManager instance = new UnitManager();
  private static final String path = Unit.class.getPackageName() + ".";
  private static final String[] players = {"PlayerHero", "PlayerKamui", "PlayerVeronica", "PlayerSenya", "PlayerSilvia", "PlayerRow", "PlayerMartina"};
  private static int money;
  private static Collection<Player> playerList = new ArrayList<>();
  private static Collection<Player> partyList = new LinkedList<>();
  private final Collection<Unit> monsterList = new ArrayList<>();
  private final String[] mons = {"UnitDracky", "UnitNeedler", "UnitSlime"};
  private Player player;

  private UnitManager() {
  }

  public static int getMoney() {
    return money;
  }

  public static void setMoney(int money) {
    UnitManager.money = money;
  }

  public static UnitManager getInstance() {
    return instance;
  }

  public static void addData(String gameData) {
    ArrayList<Player> list = new ArrayList<>();
    String[] temp = gameData.split("\n");
    money = Integer.parseInt(temp[0]);
    int start = 1;
    int end = 1 + 2 * 7;
    int idx = 0;
    for (int i = start; i < end; i += 2) {
      String[] info = temp[i].split("/");
      try {
        Class<?> clazz = Class.forName(path + players[idx++]);
        Object obj = clazz.getDeclaredConstructor().newInstance();
        Player player = (Player) obj;
        player.setName(info[0]);
        player.setLevel(Integer.parseInt(info[1]));
        player.setCurhp(Integer.parseInt(info[2]));
        player.setMaxhp(Integer.parseInt(info[3]));
        player.setAtt(Integer.parseInt(info[4]));
        player.setDef(Integer.parseInt(info[5]));
        player.setMaxExp(Integer.parseInt(info[7]));
        player.setExp(Integer.parseInt(info[6]));
        player.setDead(Boolean.parseBoolean(info[8]));
        player.setParty(Boolean.parseBoolean(info[9]));
        list.add(player);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }

      info = temp[i + 1].split("/");
      for (int j = 0; j < info.length; j++) {
        String[] info2 = info[j].split(",");
        if (info2.length == 1) continue;
        list.get((i + 1) / 2 - 1).setItem(new Item(info2[0], info2[1]
            , Integer.parseInt(info2[2]), Integer.parseInt(info2[3])));
      }
    }
    start = end + 1;
    end = start + Integer.parseInt(temp[end]);
    Inventory.getInstance().getItemList().clear();
    for (int i = start; i < end; i++) {//아이템
      String[] info = temp[i].split("/");
      Inventory.getInstance().getItemList().add(new Item(info[0], info[1]
          , Integer.parseInt(info[2]), Integer.parseInt(info[3])));
    }
    playerList = list;
    partyList.clear();
    for (Player p : list) {
      if (p.isParty()) {
        partyList.add(p);
      }
    }
  }

  public static String getData() {
    String data = "";
    data += String.format("%s%n", "" + money);
    for (Player p : playerList) {
      data += String.format("%s/%d/%d/%d/%d/%d/%d/%d/%b/%b%n", p.getName(), p.getLevel(), p.getCurhp(), p.getMaxhp(), p.getAtt(), p.getDef(), p.getExp(), p.getMaxExp(), p.isDead(), p.isParty());
      data += getItemData(ItemSymbol.WEAPON.getSymbol(), p) + "/";
      data += getItemData(ItemSymbol.ARMOR.getSymbol(), p) + "/";
      data += getItemData(ItemSymbol.RING.getSymbol(), p) + "\n";
    }
    data += Inventory.getInstance().getItemList().size() + "\n";
    for (Item i : Inventory.getInstance().getItemList()) {
      data += getItemData(i);
    }
    return data;
  }

  private static String getItemData(String kind, Player p) {
    if (p.getItem(kind) == null) {
      return null;
    } else {
      return String.format("%s,%s,%d,%s", kind, p.getItem(kind).getName(), p.getItem(kind).getPower(), p.getItem(kind).getPrice());
    }
  }

  private static String getItemData(Item i) {
    return String.format("%s/%s/%d/%s%n", i.getKind(), i.getName(), i.getPower(), i.getPrice());
  }

  public Player getPlayer() {
    return player;
  }

  public ArrayList<Player> getPartyList() {
    ArrayList<Player> list = new ArrayList<>(partyList);
    return list;
  }

  public ArrayList<Player> getPlayerList() {
    ArrayList<Player> list = new ArrayList<>(playerList);
    return list;
  }

  public Collection<Unit> getMonsterList() {
    return monsterList;
  }

  public void generateRdMonster(int size) {
    for (int i = 0; i < size; i++) {
      int rdNum = Util.getInstance().getRd().nextInt(mons.length);
      try {
        Class<?> clazz = Class.forName(path + mons[rdNum]);
        Object obj = clazz.getDeclaredConstructor().newInstance();
        Unit temp = (Unit) obj;
        monsterList.add(temp);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public LinkedList<Player> changePartyMember() {
    printPartyMember();
    ArrayList<Player> list = (ArrayList<Player>) playerList;
    LinkedList<Player> list2 = (LinkedList<Player>) partyList;
    int now = Util.getInstance().getValue("교체할 번호를 입력하세요 >> ", 1, partyList.size()) - 1;
    int sub = Util.getInstance().getValue("참가할 번호를 입력하세요 >> ", 1, playerList.size()) - 1;
    for (Player p : partyList) {
      if (p.equals(list.get(sub))) {
        System.out.println("이미 파티중인 멤버입니다");
        Util.getInstance().setDelay(500);
        return list2;
      }
    }
    if (list.get(sub).isDead()) {
      System.out.println("사망한 멤버입니다");
      return list2;
    }
    if (list2.get(now).getName().equals(list.get(sub).getName())) {
      System.out.println("동일한 멤버입니다");
      Util.getInstance().setDelay(500);
      return list2;
    }
    System.out.println("====================================");
    System.out.print("[이름 : " + list.get(now).getName() + "]에서");
    System.out.print("[이름 : " + list.get(sub).getName() + "]으로 교체 합니다.\n");
    System.out.println("====================================");
    Util.getInstance().setDelay(500);
    list2.get(now).setParty(false);
    list.get(sub).setParty(true);
    list2.set(now, list.get(sub));
    playerList = list;
    partyList = list2;
    printPartyMember();
    return list2;
  }//eom

  public boolean printPartyMember() {

    System.out.println("================ [파티원] ===============");
    int idx = 0;
    for (Player p : partyList) {
      System.out.printf("[%d번]", ++idx);
      System.out.println(p + "\n");
      Util.getInstance().setDelay(500);
    }
    System.out.println("=====================================");
    return true;
  }

}