package main;

import database.DatabaseInitializer;
import controller.MenuController;

public class SistemaLoja {

    public static void main(String[] args) {

        DatabaseInitializer.initialize();

        MenuController controller = new MenuController();
        controller.iniciar();
    }
}