package Map;

public interface GameMap {
  int SIZE = 7;

  void init();

  void PrintMap();

  String movePlayer(String move);
}
