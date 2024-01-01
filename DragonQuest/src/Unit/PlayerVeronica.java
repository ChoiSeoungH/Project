package Unit;

import PlayerClass.Magician;
import Skill.Skill;

public class PlayerVeronica extends Player implements Magician {
  public PlayerVeronica(String name, int level, int curhp, int maxhp, int att, int def, int exp, int maxExp, boolean dead, boolean party) {
    super(name, level, curhp, maxhp, att, def, exp, maxExp, dead, party);
  }

  public PlayerVeronica() {
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
        if (skill instanceof Magician) {
          skillList.put(skils[i], skill);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
