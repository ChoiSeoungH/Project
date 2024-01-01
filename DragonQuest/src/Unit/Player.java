package Unit;

import Item.Item;
import Item.ItemSymbol;
import Skill.Skill;

import java.util.HashMap;
import java.util.Map;

public abstract class Player extends Unit {
  protected static String path = Skill.class.getPackageName() + ".";
  protected String[] skils = {"Hand", "Heal", "Spell", "Sword", "Acrobatic", "Dagger"};
  protected Map<String, Skill> skillList = new HashMap<>();
  private boolean party;
  private Item weapon;
  private Item armor;
  private Item ring;
  private String className;
  private boolean weaponCheck;
  private boolean armorCheck;
  private boolean ringCheck;

  public Player(String name, int level, int curhp, int maxhp, int att, int def, int exp, int maxExp, boolean dead, boolean party) {
    super(name, level, curhp, maxhp, att, def, exp, maxExp, dead);
    this.party = party;
  }

  public Player() {
  }

  public Item getWeapon() {
    return weapon;
  }

  public Item getArmor() {
    return armor;
  }

  public Item getRing() {
    return ring;
  }

  public Map<String, Skill> getSkillList() {
    return skillList;
  }

  public boolean isParty() {
    return party;
  }

  public void setParty(boolean party) {
    this.party = party;
  }

  public Item getItem(String kind) {
    Item item = null;
    if (kind.equals(ItemSymbol.WEAPON.getSymbol())) {
      item = weapon;
    } else if (kind.equals(ItemSymbol.ARMOR.getSymbol())) {
      item = armor;
    } else if (kind.equals(ItemSymbol.RING.getSymbol())) {
      item = ring;
    }
    return item;
  }

  public void setItem(Item item) {
    if (item.getKind().equals(ItemSymbol.WEAPON.getSymbol())) {
      if (this.weapon == null) {
        att += item.getPower();
      } else {
        att -= this.weapon.getPower();
        att += item.getPower();
      }
      this.weapon = item;
    } else if (item.getKind().equals(ItemSymbol.ARMOR.getSymbol())) {
      if (this.armor == null) {
        def += item.getPower();
      } else {
        def -= this.armor.getPower();
        def += item.getPower();
      }
      this.armor = item;
    } else if (item.getKind().equals(ItemSymbol.RING.getSymbol())) {
      if (this.ring == null) {
        curhp += item.getPower();
        maxhp += item.getPower();
      } else {
        curhp -= this.ring.getPower();
        curhp += item.getPower();
        maxhp -= this.ring.getPower();
        maxhp += item.getPower();
      }
      this.ring = item;

    }
  }

  public void setItem(String symbol) {
    if (symbol.equals(ItemSymbol.WEAPON.getSymbol())) {
      att -= this.weapon.getPower();
      this.weapon = null;
    } else if (symbol.equals(ItemSymbol.ARMOR.getSymbol())) {
      def -= this.weapon.getPower();
      this.armor = null;
    } else if (symbol.equals(ItemSymbol.RING.getSymbol())) {
      curhp -= this.ring.getPower();
      maxhp -= this.ring.getPower();
      this.ring = null;
    }
  }

  @Override
  public String toString() {
    String data = String.format("[이름 : %s] [레벨 : %d]", name, level);
    data += ring == null ? String.format("[체력 : %d / %d]%n", curhp, maxhp)
        : String.format("[체력 :%d(%d + %d) / %d(%d + %d)%n", curhp, curhp - ring.getPower(), ring.getPower(), maxhp, maxhp - ring.getPower(), ring.getPower());
    data += weapon == null ? String.format("[공격력 : %d]", att)
        : String.format("[공격력 : %d(%d + %d)]", att, att - weapon.getPower(), weapon.getPower());
    data += armor == null ? String.format("[방어력 : %d]", def)
        : String.format("[방어력 : %d(%d + %d)]", def, def - armor.getPower(), armor.getPower());
    data += String.format("[경험치 : %d / %d] [파티중 : %b]%n", exp, maxExp, party);
    data += weapon == null ? "[무기 : 없음 ]" : "[무기 : " + weapon.getName() + "]";
    data += armor == null ? "[방어구 : 없음 ]" : "[방어구 : " + armor.getName() + "]";
    data += ring == null ? "[반지 : 없음 ]" : "[반지 : " + ring.getName() + "]";
    return data;
  }

  abstract public void skill(Unit target);

  abstract public void learnSkill();

  @Override
  public void printData() {
    String data = "[%s] [❤%d/%d]".formatted(name, curhp, maxhp);
    data += " 🔪[%d] ".formatted(att);
    data += " \uD83D\uDD18[%d]".formatted(def);
    System.out.println(data);
  }
}
