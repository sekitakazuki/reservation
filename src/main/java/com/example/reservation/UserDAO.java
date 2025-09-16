package com.example.reservation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String DATA_FILE = "users.dat";
    private static List<User> users = new ArrayList<>();

    static {
        loadUsers();
        if (getUserByName("admin") == null) {
            users.add(new User("admin", "adminpass", "admin"));
            saveUsers();
        }
    }

    public static User getUserByName(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public static boolean registerUser(String username, String password) {
        if (getUserByName(username) != null) {
            return false; 
        }
        users.add(new User(username, password, "user"));
        saveUsers();
        return true;
    }

    private static void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (User u : users) {
                writer.write(u.getUsername() + "," + u.getPassword() + "," + u.getRole());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadUsers() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            users.clear();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    users.add(new User(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
