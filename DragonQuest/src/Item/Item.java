package Item;

public class Item {

  private String kind;
  private String name;
  private int power;
  private int price;

  public Item(String kind, String name, int power, int price) {
    this.kind = kind;
    this.name = name;
    this.power = power;
    this.price = price;
  }

  public Item() {
  }

  public String getKind() {
    return kind;
  }

  public String getName() {
    return name;
  }

  public int getPower() {
    return power;
  }

  public int getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return String.format("[%s][이름 : %s] [능력 : %s] [가격 : %s]%n", kind, name, power, price);
  }

}
