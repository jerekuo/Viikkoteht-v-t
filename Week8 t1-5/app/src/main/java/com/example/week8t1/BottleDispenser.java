package com.example.week8t1;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class BottleDispenser {


    private static int bottles;
    private static double money;
    private static ArrayList<Bottle> bottlelist;

    private static final BottleDispenser instance = new BottleDispenser();

    public static BottleDispenser getInstance() {
        return instance;
    }

    private BottleDispenser() {
        bottles = 5;
        money = 0;
        ArrayList<Bottle> bottlelist = new ArrayList<Bottle>();
        bottlelist.add(new Bottle("Pepsi Max", "Pepsi", 400, 0.5, 1.80));
        bottlelist.add(new Bottle("Pepsi Max", "Pepsi", 1200, 1.5, 2.20));
        bottlelist.add(new Bottle("Coca-Cola Zero", "Coke", 0, 0.5, 2.00));
        bottlelist.add(new Bottle("Coca-Cola Zero", "Coke", 0, 1.5, 2.50));
        bottlelist.add(new Bottle("Fanta Zero", "Pepsi", 0, 0.5, 1.95));
        /*
         * for (int i = 0; i < bottles; i++) { Bottle newBottle = new Bottle();
         * bottlelist.add(newBottle); }
         */
        setBottlelist(bottlelist);
    }

    public String printList() {
        Bottle currentBottle;
        String lista = "";

        ArrayList<Bottle> bottlelista = getBottlelist();
        for (int i = 0; i < bottlelista.size(); i++) {
            currentBottle = bottlelista.get(i);
            lista += "\n" + currentBottle.getName();
            lista += "\nSize: " + currentBottle.getSize() + "  Price: " + currentBottle.getPrice();

            System.out.println(i + 1 + ". Name: " + currentBottle.getName());
            System.out.println("\tSize: " + currentBottle.getSize() + "\tPrice: " + currentBottle.getPrice());
        }
        return lista;
    }

    public void setBottlelist(ArrayList<Bottle> lista) {
        bottlelist = lista;

    }

    public int getBottles() {
        return bottles;
    }

    public ArrayList<Bottle> getBottlelist() {
        return bottlelist;
    }

    public double getMoney() {
        return money;
    }

    public String addMoney(int amount) {
        String s;
        money += amount;
        s ="Klink! Added more money!" ;
        return s;
    }

    public String buyBottle(Bottle choice) {
        ArrayList<Bottle> bottleList = getBottlelist();
        Bottle chosenBot;
        chosenBot = choice;
        if (getMoney() < chosenBot.getPrice()) {
            return ("Add money first!");
        } else {
            if (getBottles() < 1) {
                return ("Out of bottles.");
            } else {
                bottlelist.remove(chosenBot);
                bottles -= 1;
                money -= chosenBot.getPrice();
                setBottlelist(bottleList);
                return ("KACHUNK! " + chosenBot.getName() + " came out of the dispenser!");
            }
        }

    }

    public String returnMoney() {
        String s = "";
        String result = String.format("%.2f", money);
        s += ("Klink klink. Money came out! You got ");
        s += result;
        s += ("€ back");
        money = 0;
        return s;
    }


}
