package epam.task1.stones.runner;

import epam.task1.stones.entity.Necklace;
import epam.task1.stones.enums.Color;
import epam.task1.stones.logic.Actions;
import epam.task1.stones.logic.Manager;

import java.io.Serializable;
import java.util.Scanner;


public class Runner implements Serializable {     //impement qldi (Serializable)


    private int counts;
    private String name;


    public Runner(int counts, String name) {
        this.counts = counts;
        this.name = name;

    }

    public int getCounts() {
        return counts;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return counts + "вес-" + name;
    }


    public static void main(String[] args) throws MyException {

        Scanner in = new Scanner(System.in);
        Necklace necklace = new Necklace();
        Manager manager = new Manager();
        int key = 0;
        int countStone = 0;
        String colour ="";


        while (true) {
            menu(manager,in, key, countStone, necklace, colour);
        }


    }

    private static void menu(Manager manager, Scanner in, int key, int countStone, Necklace necklace, String colour) {
        System.out.println(" \n\n 1-Создать ожерелье \n "
                + "2-Рассчитать общий вес \n "
                + "3-Рассчитать общую стоимость камней \n "
                + "4-Классифицировать камни по стоимости \n "
                + "5-Классифицировать камни по весу \n "
                + "6-Найти камень по цвету \n "
                + "7-Сохранить созданные камни\n "
                + "8-Извлечь список камней последнего ожерелья");
        //+ "7-Сохранить камни \n");
        System.out.print("Выбери: ");
        key = in.nextInt();
        switch (key) {
            case 1: {
                System.out.println("Введите количество камней в ожерелье");
                countStone = in.nextInt();
                if (countStone > 10) {
                    try {
                        throw new MyException("Слишком много камней!!!");
                    } catch (MyException e) {
                        System.out.println(e.getMessage());

                    }
                }
                else {
                    manager.createNecklace(necklace, countStone);
                    System.out.println(necklace);
                }
                break;
            }
            case 2: {
                System.out.println("Общий вес в каратах: "
                        + manager.calculateTotalCaratWeight(necklace
                        .getStonesList()));
                break;
            }
            case 3: {
                System.out.println("Общая стоимость камней: "
                        + manager.calculateTotalStonesCost(necklace
                        .getStonesList()));
                break;
            }
            case 4: {
                System.out.println("Отсортированные камни: ");
                Actions.stonesSortByPrice(necklace.getStonesList());
                System.out.print(necklace.getStonesList());
                break;
            }
            case 5: {
                System.out.println("Отсортированные камни: ");
                Actions.stonesSortByWeight(necklace.getStonesList());
                System.out.print(necklace.getStonesList());
                break;
            }
            case 6: {
                System.out.println("Введите критерии цвета: ");
                for (Color color : Color.values()) {
                    System.out.print(color.name() + " ");
                }
                colour = in.next();
                System.out.println("Найти камень по цвету: ");
                System.out.print(Actions.findStoneByColor(
                        colour.toUpperCase(), necklace.getStonesList()));
                break;
            }
            case 7: {
                System.out.println("\nСохраняю созданные камни...");
                necklace.saveStonesInFile();
                System.out.println("Камни сохранены\n");
                break;
            }
            case 8: {
                System.out.println("\nЗагрузка сохраненных камней...");
                necklace.loadFile();
                break;
            }
        }

    }
}