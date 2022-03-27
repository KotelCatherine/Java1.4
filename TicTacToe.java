import java.util.Random;
import java.util.Scanner;


public class TicTacToe {
    private final static int SIZE_MAP = 3;
    private static char[][] map = new char[SIZE_MAP][SIZE_MAP];
    private final static char DOT_X = 'X';
    private final static char DOT_O = 'O';
    private final static char DOT_EMPTY = '•';
    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        play();
    }

    private static void play() {
        init();
        print();

        while(true) {
            humanTure();
            print();
            if (checkWin(DOT_X)) {
                System.out.println("YOU WIN");
                break;
            }
            if (checkDraw()) {
                System.out.println("DRAW");
                break;
            }
            computerTure();
            print();
            if (checkWin(DOT_O)) {
                System.out.println("COMPUTER WIN");
                break;
            }
            if (checkDraw()) {
                System.out.println("DRAW");
                break;
            }
        }
    }
    private static void init() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void print() {
        for (int i = 0; i < map.length; i++) {
            if (i == 0) {
                System.out.print("  " + (i + 1) + " ");
            } else {
                System.out.print((i + 1) + " ");
            }
        }
        System.out.println();

        for (int i = 0; i < map.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void humanTure() {
        int x, y;

        do {
            while (true) {
                System.out.println("Please input coordinate in format 'x y'");
                if (scanner.hasNextInt()) {
                    x = scanner.nextInt();
                } else {
                    System.out.println("You input wrong X coordinate.");
                    scanner.nextLine();
                    continue;
                }

                if (scanner.hasNextInt()) {
                    y = scanner.nextInt();
                    break;
                } else {
                    System.out.println("You input wrong Y coordinate.");
                    scanner.nextLine();
                }
            }
        }while (!cellValidation(x, y));
        map[x - 1][y - 1] = DOT_X;
    }

    private static void computerTure() {
        int x, y;
        do {
            x = random.nextInt(SIZE_MAP);
            y = random.nextInt(SIZE_MAP);
        }while (!cellValidation(x + 1, y + 1));
        map[x][y] = DOT_O;
    }

    private static boolean checkWin(char dot) {
        int count = 0;
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++){
                if (map[i][j] == dot){
                    count++;
                } else {
                    break;
                }
            }
            if (count == 3){
                return true;
            }
            count = 0;
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[j][i] == dot) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == 3){
                return true;
            }
            count = 0;
        }

        for (int i = 0; i < map.length; i++) {
            if (map[i][i] == dot) {
                count++;
            }
            if (count == 3){
                return true;
            }
        }
        count = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i][map.length - i - 1] == dot) {
                count++;
            }
            if (count == 3){
                return true;
            }
        }
        return false;
    }


    private static boolean checkDraw() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean cellValidation(int x, int y) {
        if (x < 1 || x > SIZE_MAP || y < 1 || y > SIZE_MAP) {
            System.out.println("Exit map size.");
            return false;
        }
        boolean checkEmpty = map[x - 1][y - 1] == DOT_EMPTY; //true

        if (checkEmpty) {
            return true;
        } else {
            return false;
        }
    }
}
