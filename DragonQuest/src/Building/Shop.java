package Building;

import Item.Inventory;
import Item.Item;
import Item.ItemSymbol;
import Manager.UnitManager;
import Util.Util;

import java.util.ArrayList;

public class Shop implements Building {
  private final ArrayList<Item> itemList = new ArrayList<>();

  public Shop() {
    itemList.add(new Item(ItemSymbol.WEAPON.getSymbol(), "노송나무막대", 2, 500));
    itemList.add(new Item(ItemSymbol.WEAPON.getSymbol(), "구리검", 4, 1000));
    itemList.add(new Item(ItemSymbol.WEAPON.getSymbol(), "철검", 6, 2000));
    itemList.add(new Item(ItemSymbol.ARMOR.getSymbol(), "가죽 갑옷", 1, 400));
    itemList.add(new Item(ItemSymbol.ARMOR.getSymbol(), "체인 메일", 2, 800));
    itemList.add(new Item(ItemSymbol.ARMOR.getSymbol(), "청동 갑옷", 3, 1600));
    itemList.add(new Item(ItemSymbol.RING.getSymbol(), "청동반지", 10, 1000));
    itemList.add(new Item(ItemSymbol.RING.getSymbol(), "은반지", 20, 2500));
    itemList.add(new Item(ItemSymbol.RING.getSymbol(), "금반지", 30, 4000));
  }

  @Override
  public void PrintBuildingMenu() {
    System.out.println("여기는 상점입니다.");
    while (true) {
      System.out.println("무슨일로 오셨나요?");
      int menu = Util.getInstance().getValue("[1:구매] [2:판매] [0:뒤로가기]", 0, 2);
      switch (menu) {
        case 0:
          System.out.println("다음에 또 들르세요!");
          return;
        case 1:
          printBuyMenu();
          break;
        case 2:
          printSellMenu();
          break;
      }

    }
  }

  private void printBuyMenu() {
    System.out.println("무엇을 사시겠습니까?");
    int menu = Util.getInstance().getValue("[1.무기] [2.갑옷] [3.반지] [0.뒤로가기] : ", 0, 3);
    if (menu == 0) {
      return;
    }
    Item i = new Item();
    BuyItem(menu, i);
  }

  private void BuyItem(int menu, Item i) {

    while (true) {
      System.out.println("돈 : " + UnitManager.getMoney());
      switch (menu) {
        case 0:
          return;
        case 1:
          System.out.println("=========== [무기] ============");
          i = SelectItem(ItemSymbol.WEAPON.getSymbol());
          break;
        case 2:
          System.out.println("=========== [방어구] ============");
          i = SelectItem(ItemSymbol.ARMOR.getSymbol());
          break;
        case 3:
          System.out.println("=========== [반지] ============");
          i = SelectItem(ItemSymbol.RING.getSymbol());
          break;
      }
      if (i == null) {
        return;
      }
      if (UnitManager.getMoney() < i.getPrice()) {
        System.out.println("골드가 부족합니다");
        continue;
      }

      if (itemList.contains(i)) {
        System.out.printf("[%s] 을 구입했습니다.%n", i.getName());
        Util.getInstance().setDelay(500);
        Inventory.getInstance().getItemList().add(i);
        UnitManager.setMoney(UnitManager.getMoney() - i.getPrice());
      }
    }
  }

  private void printSellMenu() {
    while (true) {
      if (!Inventory.getInstance().hasItem()) {
        return;
      }
      Inventory.getInstance().printItemList();
      System.out.println("[골드 : " + UnitManager.getMoney() + "]");
      int item = Util.getInstance().getValue("판매할 아이템을 선택하세요. 매입은 50% 금액으로 합니다. [0.뒤로가기] >> ", 0, itemList.size()) - 1;
      if (item == -1) {
        return;
      }
      System.out.println(Inventory.getInstance().getItemList().get(item).getName() + "을 판매합니다.");
      Util.getInstance().setDelay(500);
      UnitManager.setMoney(UnitManager.getMoney() + Inventory.getInstance().getItemList().get(item).getPrice() / 2);
      Inventory.getInstance().getItemList().remove(item);
    }//eow
  }//eom

  private Item SelectItem(String kind) {
    int idx = 0;
    for (Item item : itemList) {
      if (item.getKind().equals(kind)) {
        System.out.printf("[%d번]", ++idx);
        System.out.printf("[이름 : %s] [능력 : %d] [가격 : %d]%n", item.getName(), item.getPower(), item.getPrice());
      }
    }
    int sel = Util.getInstance().getValue("구입할 아이템을 선택하세요 [0.뒤로가기] : ", 0, idx) - 1;
    if (sel == -1) return null;

    return findItem(sel, kind);
  }//eom

  private Item findItem(int sel, String kind) {
    int idx;
    idx = 0;
    for (Item item : itemList) {
      if (item.getKind().equals(kind)) {
        if (idx == sel) {
          return item;
        }
        idx += 1;
      }
    }
    return null;
  }

}

