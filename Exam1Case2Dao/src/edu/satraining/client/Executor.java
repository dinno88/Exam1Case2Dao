package edu.satraining.client;

import edu.satraining.controller.Controller;

public class Executor {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.readData();
        controller.showData();
        controller.addData();
        controller.editData();
        controller.deleteData();
    }
}
