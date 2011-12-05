package enigmaserie;

import java.io.PrintWriter;

public abstract class AppBase {

  protected abstract void execute(String[] aArgs) throws Exception;

  protected void runApp(String[] args) {
    try {
      execute(args);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  protected void printHelp(PrintWriter aSysout) throws Exception {
  }

}
