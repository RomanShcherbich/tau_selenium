package validation;

public class WebDriverLog {
  public static String log;

  public WebDriverLog() {
    if (log == null) {
      log = "";
    }
  }

  public static void addLog(String message) {
    log += "\n" + message;
  }
}
