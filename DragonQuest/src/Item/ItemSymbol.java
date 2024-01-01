package Item;

public enum ItemSymbol {
  WEAPON("⚔︎"), ARMOR("🧥"), RING("💍");
  private final String symbol;

  ItemSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }
}
