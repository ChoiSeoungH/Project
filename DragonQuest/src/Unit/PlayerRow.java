package Unit;

import PlayerClass.Fighter;
import PlayerClass.Healer;
import PlayerClass.Magician;
import Skill.Skill;

public class PlayerRow extends Player implements Healer, Magician, Fighter {
  public PlayerRow(String name, int level, int curhp, int maxhp, int att, int def, int exp, int maxExp, boolean dead, boolean party) {
    super(name, level, curhp, maxhp, att, def, exp, maxExp, dead, party);
  }

  public PlayerRow() {
    learnSkill();
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
        if (skill instanceof Healer || skill instanceof Magician || skill instanceof Fighter) {
          skillList.put(skils[i], skill);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
