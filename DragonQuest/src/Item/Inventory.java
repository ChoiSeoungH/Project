package Item;

import Manager.UnitManager;
import Util.Util;

import java.util.ArrayList;

public class Inventory {
  private static final Inventory instance = new Inventory();
  private final ArrayList<Item> itemList = new ArrayList<>();

  private Inventory() {
  }

  public static Inventory getInstance() {
    return instance;
  }

  public ArrayList<Item> getItemList() {
    return itemList;
  }

  public void inventoryMenu() {
    while (true) {
      System.out.println("[1.착용] [2.해제] [0.뒤로가기]");
      int menu = Util.getInstance().getValue("메뉴 선택 >>", 0, 2);
      switch (menu) {
        case 0:
          return;
        case 1://착용
          equipMenu();
          break;
        case 2://해제
          unEquipMenu();
          break;
      }//eos
    }//eow
  }//eom

  private void equipMenu() {
    if (!hasItem()) return;
    int member = Util.getInstance().getValue("아이템을 착용할 파티원을 선택하세요 : ", 0, UnitManager.getInstance().getPlayerList().size()) - 1;
    while (true) {
      if (!hasItem()) return;
      System.out.println(UnitManager.getInstance().getPlayerList().get(member));
      Util.getInstance().setDelay(500);
      printItemList();
      int num = Integer.parseInt(Util.getInstance().getValue("착용할 아이템 번호를 입력하세요 [0.뒤로가기] : ")) - 1;
      if (num == -1) return;
      if (UnitManager.getInstance().getPlayerList().get(member).getItem(itemList.get(num).getKind()) != null) {
        itemList.add(UnitManager.getInstance().getPlayerList().get(member).getItem(itemList.get(num).getKind()));
      }
      System.out.println(itemList.get(num));
      UnitManager.getInstance().getPlayerList().get(member).setItem(itemList.get(num));
      itemList.remove(num);
    }//eow
  }//eom

  private void unEquipMenu() {
    String[] symbols = {ItemSymbol.WEAPON.getSymbol(), ItemSymbol.ARMOR.getSymbol(), ItemSymbol.RING.getSymbol()};

    int member = Util.getInstance().getValue("아이템을 해제할 파티원을 선택하세요 : ", 0, UnitManager.getInstance().getPlayerList().size()) - 1;
    while (true) {
      System.out.println(UnitManager.getInstance().getPlayerList().get(member));
      Util.getInstance().setDelay(200);
      int cnt = 0;
      for (String symbol : symbols) {
        if (UnitManager.getInstance().getPlayerList().get(member).getItem(symbol) == null) {
          cnt += 1;
        }
      }
      if (cnt == 3) {
        System.out.println("착용한 아이템이 없습니다");
        return;
      }
      int num = Integer.parseInt(Util.getInstance().getValue("[1:무기] [2:갑옷] [3:반지] [0.뒤로가기] : ")) - 1;
      if (num == -1) return;
      if (UnitManager.getInstance().getPlayerList().get(member).getItem(symbols[num]) == null) {
        System.out.println("착용하지 않은 부위입니다");
        continue;
      }
      itemList.add(UnitManager.getInstance().getPlayerList().get(member).getItem(symbols[num]));
      UnitManager.getInstance().getPlayerList().get(member).setItem(symbols[num]);
    }//eow
  }

  public void printItemList() {
    System.out.println("============ [아이템리스트] ==============");
    int idx = 0;
    for (Item i : itemList) {
      System.out.printf("[%d번]", ++idx);
      System.out.println(i);
    }
  }

  public boolean hasItem() {
    if (itemList.isEmpty()) {
      System.out.println("아이템이 없습니다.");
      return false;
    }
    return true;
  }

}//eoc
