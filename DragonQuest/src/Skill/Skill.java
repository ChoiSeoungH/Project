package Skill;

import Unit.Player;
import Unit.Unit;

public interface Skill {
  void castSkill(Player player, Unit target);
}
