package com.kvart;

public class Main {

    public static void main(String[] args) {

        DataStorage dataStorage = new DataStorage();

        dataStorage.addUser(new Users("Alex", 25));
        dataStorage.addUser(new Users("Dmitry", 35));
        dataStorage.addUser(new Users("Ivan", 28));
        dataStorage.addUser(new Users("Alina", 17));
        dataStorage.addUser(new Users("Ira", 38));

        System.out.println(dataStorage.getAllUsers());

        dataStorage.removeUser(2);
        dataStorage.removeUserByName("Dmitry");
        dataStorage.updateUser(new Users("Alina", 19));

        System.out.println(dataStorage.getUser(5));
        System.out.println(dataStorage.getAllUsers());

        dataStorage.removeAll();

    }
}
