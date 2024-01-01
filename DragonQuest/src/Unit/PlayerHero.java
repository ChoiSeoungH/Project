package Unit;

import PlayerClass.Hero;
import Skill.Skill;

public class PlayerHero extends Player implements Hero {
  public PlayerHero() {
    learnSkill();
  }

  public PlayerHero(String name, int level, int curhp, int maxhp, int att, int def, int exp, int maxExp, boolean dead, boolean party) {
    super(name, level, curhp, maxhp, att, def, exp, maxExp, dead, party);
  }

  @Override
  public void skill(Unit target) {

  }

  @Override
  public void learnSkill() {
    try {
      for (int i = 0; i < skils.length; i++) {
        Class<?> clazz = Class.forName(path + skils[i]);
        Object obj = clazz.getDeclaredConstructor().newInstance();
        Skill skill = (Skill) obj;
        if (skill instanceof Hero) {
          skillList.put(skils[i], skill);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
