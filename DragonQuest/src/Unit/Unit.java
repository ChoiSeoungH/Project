package Unit;

import Util.Util;

public abstract class Unit {
  protected int curhp;
  protected int maxhp;
  protected int att;
  protected int def;
  protected int level;
  protected int exp;
  protected int maxExp;
  protected String name;
  protected boolean dead;
  private int levelCnt;

  public Unit() {
  }

  public Unit(int maxhp, int att, int def, int exp, String name) {
    this.curhp = maxhp;
    this.maxhp = maxhp;
    this.att = att;
    this.def = def;
    this.exp = exp;
    this.name = name;
    this.level = 1;
    this.maxExp = 10;
  }

  public Unit(String name, int level, int curhp, int maxhp, int att, int def, int exp, int maxExp, boolean dead) {
    this.curhp = curhp;
    this.maxhp = maxhp;
    this.att = att;
    this.def = def;
    this.level = level;
    this.exp = exp;
    this.maxExp = maxExp;
    this.name = name;
    this.dead = dead;
  }

  public int getCurhp() {
    return curhp;
  }

  public void setCurhp(int curhp) {
    this.curhp = curhp;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isDead() {
    return dead;
  }

  public void setDead(boolean dead) {
    this.dead = dead;
  }

  public int getExp() {
    return exp;
  }

  public void setExp(int exp) {
    this.exp = exp;
    if (this.exp >= maxExp) {
      System.out.printf("%s가 %d -> %d로 레벨업!", name, level, ++level);
      levelCnt += 1;
      maxhp += 2;
      curhp = maxhp;
      if (levelCnt / 2 == 0) {
        att += 1;
      }
      if (levelCnt / 3 == 0) {
        def += 1;
      }
      this.exp -= maxExp;
      maxExp += 2;
      Util.getInstance().setDelay(500);
    }
  }

  public int getMaxhp() {
    return maxhp;
  }

  public void setMaxhp(int maxhp) {
    this.maxhp = maxhp;
  }

  public int getAtt() {
    return att;
  }

  public void setAtt(int att) {
    this.att = att;
  }

  public int getDef() {
    return def;
  }

  public void setDef(int def) {
    this.def = def;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getMaxExp() {
    return maxExp;
  }

  public void setMaxExp(int maxExp) {
    this.maxExp = maxExp;
  }

  public int attack(Unit target) {
    int damage = att - target.def;
    if (damage <= 0) damage = 1;//최소데미지
    target.curhp -= (damage);
    System.out.printf("[%s] 이 [%s] 에게 총 %d 포인트의 데미지!%n", name, target.name, damage);
    if (target.curhp <= 0) {
      System.out.printf("[%s] 을 쓰러트렸다!%n", target.name);
      target.curhp = 0;
      target.setDead(true);
    }
    Util.getInstance().setDelay(500);
    return damage;
  }

  abstract public void skill(Unit target);

  public void printData() {
    String data = "[%s] [❤%d/%d]".formatted(name, curhp, maxhp);
    data += " 🔪[%d] ".formatted(att);
    data += " \uD83D\uDD18[%d]".formatted(def);
    System.out.println(data);
  }
}