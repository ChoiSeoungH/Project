package Unit;

import PlayerClass.Fighter;
import Skill.Skill;

public class PlayerMartina extends Player implements Fighter {
  public PlayerMartina() {
    learnSkill();
  }

  public PlayerMartina(String name, int level, int curhp, int maxhp, int att, int def, int exp, int maxExp, boolean dead, boolean party) {
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
        if (skill instanceof Fighter) {
          skillList.put(skils[i], skill);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
