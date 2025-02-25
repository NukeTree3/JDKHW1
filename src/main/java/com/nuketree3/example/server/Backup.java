package com.nuketree3.example.server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Backup implements FIleWriteable{

    private final String filename;

    @Override
    public void doBackup(String message) {

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
