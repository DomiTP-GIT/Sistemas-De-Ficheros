import Utils.ConsoleColors;

import java.io.File;
import java.nio.file.Files;
import java.util.Date;

public class main {
  // java SDF.jar "directorio" [l|c|t]
  public static void main(String[] args) {
    boolean check = comprobar(args);

    if (check) {
      File f = new File(args[0]);

      if (args[1].equalsIgnoreCase("l")) {
        lista(f);
      } else if (args[1].equalsIgnoreCase("c")) {
        columnas(f);
      } else if (args[1].equalsIgnoreCase("t")) {
        tabla(f);
      } else {
        System.out.println(ConsoleColors.RED + "ERROR MODOS");
      }
    }
  }

  public static boolean comprobar(String[] args) {
    if (args.length != 2) {
      System.out.println(ConsoleColors.RED + "¡Error!");
      System.out.println(ConsoleColors.PURPLE + "Ejecuta el programa con los argumentos: " + ConsoleColors.GREEN + "directorio [l|c|t]");
      return false;
    } else {
      File f = new File(args[0]);
      if (!f.exists() && f.isDirectory()) {
        System.out.println(ConsoleColors.RED + "¡Error!");
        System.out.println(ConsoleColors.PURPLE + "El directorio no existe.");
        return false;
      } else {
        if (!args[1].equalsIgnoreCase("l") && !args[1].equalsIgnoreCase("c") && !args[1].equalsIgnoreCase("t")) {
          System.out.println(ConsoleColors.PURPLE + "");
          return false;
        }
      }
    }
    return true;
  }

  public static void lista(File f) {
    for (File file : f.listFiles()) {
      if (file.isDirectory()) {
        System.out.println(ConsoleColors.BLUE + file.getName() + ConsoleColors.RESET);
      } else {
        System.out.println(file.getName());
      }
    }
  }

  public static void columnas(File f) {
    ListaColumnas(f.list());
  }


  public static void tabla(File f) {
    for (File file : f.listFiles()) {
      String DFRWH = "";

      if (file.isDirectory()) {
        DFRWH += ConsoleColors.BLUE + "D" + ConsoleColors.RESET;
      } else {
        DFRWH += ConsoleColors.GREEN + "F" + ConsoleColors.RESET;
      }

      if (file.canRead()) {
        DFRWH += ConsoleColors.YELLOW + "R" + ConsoleColors.RESET;
      } else {
        DFRWH += ConsoleColors.WHITE + "-" + ConsoleColors.RESET;
      }

      if (file.canWrite()) {
        DFRWH += ConsoleColors.CYAN + "W" + ConsoleColors.RESET;
      } else {
        DFRWH += ConsoleColors.WHITE + "-" + ConsoleColors.RESET;
      }

      if (file.isHidden()) {
        DFRWH += ConsoleColors.GREEN + "H" + ConsoleColors.RESET;
      } else {
        DFRWH += " ";
      }


      Date d = new Date(file.lastModified());
      System.out.println(DFRWH + "\t" + file.getName() + "\t" + String.format("%d", file.length() / 1024) + "kb\t" + d);
    }
  }


  public static void ListaColumnas(String[] filenames) {
    int MAX_FILES_BY_COLUMN = 5;
    int columnas = (filenames.length / MAX_FILES_BY_COLUMN) + 1;
    String[][] salida = new String[MAX_FILES_BY_COLUMN][columnas];
    for (int i = 0; i < filenames.length; i++) {
      salida[i % MAX_FILES_BY_COLUMN][i / MAX_FILES_BY_COLUMN] = filenames
          [i];
    }
//bucle para mostrar salidals
    for (int i = 0; i < MAX_FILES_BY_COLUMN; i++) {
      for (int j = 0; j < columnas; j++)
        System.out.print(salida[i][j] + " - ");
      System.out.println(" /");
    }
  }
}
