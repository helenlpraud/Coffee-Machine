package com.company;

import java.util.Scanner;

public class CoffeeMachine {
    public static int mlWaterInMachine = 400;
    public static int mlMilkInMachine = 540;
    public static int gmCoffeeInMachine = 120;
    public static int  disposableCups = 9;
    public static int countOfMoney = 550;
    public static String action;
    public static boolean working = true;
    public static boolean purchaseOpportunity;
    public static boolean waterDeficiency;
    public static boolean milkDeficiency;
    public static boolean gmDeficiency;

    public static void main(String[] args) {

        start();
    }

    protected static void start() {
        while (working) {
            chooseAction();
            makeAction(action);
        }
    }

    public static void chooseAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        Scanner scanner1 = new Scanner(System.in);
        action = scanner1.next();
        System.out.println("");

    }
    public static void makeAction(String action) {
        switch (action) {
            case "buy":
                buy();
                break;
            case "fill":
                fill();
                break;
            case "take":
                take();
                break;
            case "remaining":
                remaining();
                break;
            case "exit":
                exit();
                break;
        }
    }

    public static void remaining() {
        printStocks();
    }

    public static void exit() {
        working = false;
    }

    public static void cheIngredient(int typeOfCoffee) {
        switch (typeOfCoffee) {
            case 1:
                purchaseOpportunity = mlWaterInMachine >= 250 && gmCoffeeInMachine >= 16;

                if (!purchaseOpportunity) {
                    waterDeficiency = mlWaterInMachine <= 250 && gmCoffeeInMachine >=16;
                    gmDeficiency = mlWaterInMachine >= 250 && gmCoffeeInMachine <= 16;
                }
                break;

            case 2:
                purchaseOpportunity = mlWaterInMachine >= 350 && mlMilkInMachine >= 75
                        && gmCoffeeInMachine >= 20;

                if (!purchaseOpportunity) {
                    waterDeficiency = mlWaterInMachine <= 350 && mlMilkInMachine >= 75
                    && gmCoffeeInMachine >= 20;
                    milkDeficiency = mlWaterInMachine >= 350 && mlMilkInMachine <= 75
                            && gmCoffeeInMachine >= 20;
                    gmDeficiency = mlWaterInMachine >= 350 && mlMilkInMachine <= 75
                            && gmCoffeeInMachine <= 20;
                }
                break;

            case 3:
                purchaseOpportunity = mlWaterInMachine >= 200 && mlMilkInMachine >= 100
                        && gmCoffeeInMachine >= 12;
                if (!purchaseOpportunity) {
                    waterDeficiency = mlWaterInMachine <= 200 && mlMilkInMachine >= 100
                            && gmCoffeeInMachine >= 12;
                    milkDeficiency = mlWaterInMachine >= 200 && mlMilkInMachine <= 100
                            && gmCoffeeInMachine >= 12;
                    gmDeficiency = mlWaterInMachine >= 200 && mlMilkInMachine <= 100
                            && gmCoffeeInMachine <= 12;
                }
                break;
        }
    }

    public static void getAmountCups() {
        System.out.println("Write how many cups of coffee you will need:");
        Scanner scanner2 = new Scanner(System.in);

        int amountCups = scanner2.nextInt();
        scanner2.close();

        int amountOfWater = amountCups * 200;
        int amountOfMilk = amountCups * 50;
        int amountOfCoffeeBeans = amountCups * 15;

        System.out.println("For " + amountCups + " cups of coffee you will need:");

        System.out.println(amountOfWater + " ml of water");
        System.out.println(amountOfMilk + " ml of milk");
        System.out.println(amountOfCoffeeBeans + " g of coffee beans");

        boolean waterCondition1 = mlWaterInMachine / amountOfWater >= 1;
        boolean milkCondition1 = mlMilkInMachine / amountOfMilk >= 1;
        boolean coffeeBeans1 = gmCoffeeInMachine / amountOfCoffeeBeans >= 1;

        boolean waterCondition2 = mlWaterInMachine % amountOfWater == 0;
        boolean milkCondition2 = mlMilkInMachine % amountOfMilk == 0;
        boolean coffeeBeans2 = gmCoffeeInMachine % amountOfCoffeeBeans == 0;

        if (waterCondition1 && milkCondition1 && coffeeBeans1) {
            if (waterCondition2 && milkCondition2 && coffeeBeans2) {
                System.out.println("Yes, I can make that amount of coffee");
            } else {
                int water1 = mlWaterInMachine / 200 - 1;
                int milk1 = mlMilkInMachine / 50 - 1;
                int coffee1 = gmCoffeeInMachine / 15 - 1;

                boolean minCups1 = water1 <= milk1 && water1 <= coffee1;
                boolean minCups2 = milk1 <= water1 && milk1 <= coffee1;
                boolean minCups3 = coffee1 <= water1 && coffee1 <= milk1;

                int countMoreCups = 0;

                if(minCups1) {
                    countMoreCups = water1;
                }
                else if(minCups2) {
                    countMoreCups = milk1;
                }
                else if(minCups3) {
                    countMoreCups = coffee1;
                }
                System.out.println("Yes, I can make that amount of coffee (and even " + countMoreCups + " more than that)");
            }
        } else {
            int water = mlWaterInMachine / 200;
            int milk = mlMilkInMachine / 50;
            int coffee = gmCoffeeInMachine / 15;

            boolean minCup1 = water <= milk && water <= coffee;
            boolean minCup2 = milk <= water && milk <= coffee;
            boolean minCup3 = coffee <= water && coffee <= milk;

            int countMoreCups1 = 0;

            if(minCup1) {
                countMoreCups1 = water;
            }
            else if(minCup2) {
                countMoreCups1 = milk;
            }
            else if (minCup3) {
                countMoreCups1 = coffee;
            }
            System.out.println("No, I can make only " + countMoreCups1 + " cup(s) of coffee");
        }

    }

    public static void setInput() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Input ml water");
        mlWaterInMachine = inputScanner.nextInt();
        System.out.println("Input ml milk");
        mlMilkInMachine = inputScanner.nextInt();
        System.out.println("Input gm coffee");
        gmCoffeeInMachine = inputScanner.nextInt();
        System.out.println("Input cups");
        disposableCups = inputScanner.nextInt();
        System.out.println("Input money");
        countOfMoney = inputScanner.nextInt();
    }

    public static void printStocks() {
        System.out.println("The coffee machine has:");
        System.out.println(mlWaterInMachine + " of water");
        System.out.println(mlMilkInMachine + " of milk");
        System.out.println(gmCoffeeInMachine + " of coffee beans");
        System.out.println(disposableCups + " disposable cups");
        System.out.println(countOfMoney + " of money");
        System.out.println("");
    }

    public static void buy() {
        Scanner scanner4 = new Scanner(System.in);
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String type = scanner4.next();
        System.out.println("");
        if (!type.equals("back")) {
            int typeOfCoffee = Integer.parseInt(type);
            madeCoffee(typeOfCoffee);
        }

    }
    public static void madeCoffee(int typeOfCoffee) {
        cheIngredient(typeOfCoffee);
        if (purchaseOpportunity) {
            switch (typeOfCoffee) {
                case 1:
                    mlWaterInMachine -= 250;
                    gmCoffeeInMachine -= 16;
                    countOfMoney += 4;
                    disposableCups -= 1;
                    break;
                case 2:
                    mlWaterInMachine -= 350;
                    mlMilkInMachine -= 75;
                    gmCoffeeInMachine -= 20;
                    countOfMoney += 7;
                    disposableCups -= 1;
                    break;
                case 3:
                    mlWaterInMachine -= 200;
                    mlMilkInMachine -= 100;
                    gmCoffeeInMachine -= 12;
                    countOfMoney += 6;
                    disposableCups -= 1;
                    break;
            }
        }
        else {
            if (milkDeficiency) {
                System.out.println("Sorry, not enough milk!");
            }
            else if (waterDeficiency){
                System.out.println("Sorry, not enough water!");
            }
            else if (gmDeficiency) {
                System.out.println("Sorry, not enough coffee beans!");
            }
            else {
                System.out.println("I have enough resources, making you a coffee!");
            }
        }
        /*
        System.out.println("Starting to make a coffee");
        System.out.println("Grinding coffee beans");
        System.out.println("Boiling water");
        System.out.println("Mixing boiled water with crushed coffee beans");
        System.out.println("Pouring coffee into the cup");
        System.out.println("Pouring some milk into the cup");
        System.out.println("Coffee is ready!");
         */
    }
    public static void fill() {
        Scanner scanner5 = new Scanner(System.in);
        System.out.println("Write how many ml of water do you want to add:");
        int addingWater = scanner5.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        int addingMilk = scanner5.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        int addingCoffee = scanner5.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int addingCups = scanner5.nextInt();
        System.out.println("");

        mlWaterInMachine += addingWater;
        mlMilkInMachine += addingMilk;
        gmCoffeeInMachine += addingCoffee;
        disposableCups += addingCups;

    }
    public static void take() {
        System.out.println("I gave you $" + countOfMoney);
        countOfMoney = 0;
    }
}