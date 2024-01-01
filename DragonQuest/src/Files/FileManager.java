package Files;

import Manager.UnitManager;
import Unit.Player;
import Util.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
  final static private String CUR_PATH = System.getProperty("user.dir") + "\\DragonQuest\\src\\Files\\";
  private static final FileManager instance = new FileManager();

  private FileManager() {
  }

  public static FileManager getInstance() {
    return instance;
  }

  public void saveFile(Player player) {
    String gameData = UnitManager.getData();
    Util.getInstance().setDelay(500);
    saveData("gameData.txt", gameData);
  }//eom

  private void saveData(String fileName, String data) {
    try (FileWriter fw = new FileWriter(CUR_PATH + fileName)) {
      fw.write(data);
      System.out.println(fileName + "저장완료");
    } catch (IOException e) {
      System.out.print("입출력 에러");
    }
  }//eom

  public void loadFile(Player player) {
    String gameData = loadData("gameData.txt");
    UnitManager.addData(gameData);
    System.out.println("로드 완료");
    Util.getInstance().setDelay(500);
  }//eom

  private String loadData(String fileName) {
    String data = "";
    try (BufferedReader br = new BufferedReader(new FileReader(CUR_PATH + fileName))) {
      while (true) {
        String line = br.readLine();
        if (line == null) break;
        data += line;
        data += "\n";
      }
    } catch (Exception e) {
      System.out.println(fileName + "로드 실패");
    }
    return data;
  }
}
