package com.kvart;

public class Main {

    public static void main(String[] args) {

        DataStorage dataStorage = new DataStorage();

        dataStorage.addUser(new User("Alex", 25));
        dataStorage.addUser(new User("Dmitry", 35));
        dataStorage.addUser(new User("Ivan", 28));
        dataStorage.addUser(new User("Alina", 17));
        dataStorage.addUser(new User("Ira", 38));

        System.out.println(dataStorage.getAllUsers());

        dataStorage.removeUser(2);
        dataStorage.removeUserByName("Dmitry");

        dataStorage.getUser(4).setAge(19);
        dataStorage.updateUser(dataStorage.getUser(4));

        System.out.println(dataStorage.getUser(5));
        System.out.println(dataStorage.getAllUsers());

        dataStorage.removeAll();
        System.out.println(dataStorage.getAllUsers());

    }
}
