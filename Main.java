import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int jumlah, level;
        Map map;
        System.out.println("  _____ _            _____             _");
        System.out.println(" |_   _| |__   ___  | ____|_ __   __ _(_)_ __ ___   ___  _ __");
        System.out.println("   | | | '_ \\ / _ \\ |  _| | '_ \\ / _` | | '_ ` _ \\ / _ \\| '_ \\");
        System.out.println("   | | | | | |  __/ | |___| | | | (_| | | | | | | | (_) | | | |");
        System.out.println("  _|_|_|_| |_|\\___| |_____|_| |_|\\__, |_|_| |_| |_|\\___/|_| |_|");
        System.out.println(" |  ___|_ _  ___| |_ ___  _ __ _ |___/");
        System.out.println(" | |_ / _` |/ __| __/ _ \\| '__| | | |");
        System.out.println(" |  _| (_| | (__| || (_) | |  | |_| |");
        System.out.println(" |_|  \\__,_|\\___|\\__\\___/|_|   \\__, |");
        System.out.println("                               |___/");
        System.out.println();
        // input
        System.out.println("Masukkan jumlah maksimum Engimon pada peta:");
        jumlah = sc.nextInt();
        System.out.println("Masukkan level pembeda tampilan Engimon:");
        level = sc.nextInt();
        System.out.println();
        System.out.println("Selamat bermain!\n");
        // jalan game
        map = new Map(level, "Map.txt", jumlah);
        map.gameFlow();
        System.out.println()
        System.out.println("  _____ _                 _           __");
        System.out.println(" |_   _| |__   __ _ _ __ | | _____   / _| ___  _ __");
        System.out.println("   | | | '_ \\ / _` | '_ \\| |/ / __| | |_ / _ \\| '__|");
        System.out.println("   | | | | | | (_| | | | |   <\\__ \\ |  _| (_) | |   ");
        System.out.println("  _|_| |_| |_|\\__,_|_|_|_|_|\\_\\___/ |_|  \\___/|_|");
        System.out.println(" |  _ \\| | __ _ _   _(_)_ __   __ _");
        System.out.println(" | |_) | |/ _` | | | | | '_ \\ / _` |");
        System.out.println(") |  __/| | (_| | |_| | | | | | (_| |_ _ _");
        System.out.println(" |_|   |_|\\__,_|\\__, |_|_| |_|\\__, (_|_|_)");
        System.out.println()
    }
}
