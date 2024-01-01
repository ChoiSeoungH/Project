package Stage;

public interface Stage {

  boolean update();

  default void init() {

  }
}