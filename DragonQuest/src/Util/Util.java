package Util;

import java.util.Random;
import java.util.Scanner;

public class Util {
  private static final Util instance = new Util();
  private final Scanner sc = new Scanner(System.in);
  private final Random rd = new Random();

  private Util() {
  }

  public static Util getInstance() {
    return instance;
  }

  public Random getRd() {
    return rd;
  }

  public String getValue(String msg) {
    System.out.print(msg);
    return sc.next();
  }//eom

  public int getValue(String msg, int start, int end) {
    while (true) {
      try {
        System.out.print(msg);
        int val = sc.nextInt();
        if (val < start || val > end) {
          System.out.printf("[%d ~ %d]사이의값 입력%n", start, end);
          continue;
        }//if()
        return val;
      } catch (Exception e) {
        System.out.println("정수값을 입력하세요");
        sc.nextLine();
      }// try catch
    }// while(true)
  }// eom

  public void setDelay(int time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }// eom


}
