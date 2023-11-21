package TicTacToe;

import java.util.Random;
import java.util.Scanner;

public class Version2 {
    public static char[][] map;
    public final static int SIZE = 3;
    public final static int WIN = 3;
    public final static char DOT_EMPTY = '•';
    public final static char DOT_USER = 'X';
    public final static char DOT_PC = 'O';


    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            moveUser();
            printMap();
            if (checkWin(DOT_USER)) {
                System.out.println("Вы выиграли!");
                System.out.println();
                break;
            }
            if (fullMap()) {
                System.out.println("Ничья!");
                System.out.println();
                break;
            }
            movePC();
            printMap();
            if (checkWin(DOT_PC)) {
                System.out.println("Вы проиграли!");
                System.out.println();
                break;
            }
            if (fullMap()) {
                System.out.println("Ничья!");
                System.out.println();
                break;
            }
        }
        printMap();
        System.out.println("Игра окончена!");
    }
    public static void initMap () {
        map = new char [SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap () {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i+1 + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void moveUser () {
        int x;
        int y;
        Scanner sc = new Scanner(System.in);
        OUT: do {
            System.out.println("Введите координаты точки X и Y:");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
            if (!checkBox(x, y)) {
                System.out.println("Введите другие координаты");
                continue OUT;
            } else {
                map[x][y] = DOT_USER;
                break;
            }
        } while (true);

    }

    public static boolean checkBox (int x, int y) {
        if (x < 0 || y < 0 || x > SIZE || y > SIZE || map[x][y] == DOT_PC || map[x][y] == DOT_USER) return false;
        return true;
    }

    public static void movePC () {
        int x;
        int y;
        Random rand = new Random();
        do {
            x = rand.nextInt(3);
            y = rand.nextInt(3);
            if (checkBox(x, y)) {
                map[x][y] = DOT_PC;
                System.out.println("Компьтер сделал ход в точку " + (x + 1) + " " + (y + 1));
                break;
            }
        } while (true);
    }

    public static boolean fullMap () {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkWin (char sym) {
        int sum = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == sym) sum++;
            }
        }
        return sum == WIN;
    }
}
