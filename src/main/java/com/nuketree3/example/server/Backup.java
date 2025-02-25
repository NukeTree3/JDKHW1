package com.nuketree3.example.server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Backup {

    private final String filename;

    public void backup(String message) {

        try (FileWriter fw = new FileWriter(filename, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.print(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Backup(String filename) {
        this.filename = filename;
    }
}
