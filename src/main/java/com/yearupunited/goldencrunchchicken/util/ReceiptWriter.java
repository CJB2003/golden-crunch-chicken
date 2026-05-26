package com.yearupunited.goldencrunchchicken.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ReceiptWriter {

    public static void writeToFile(String receiptBuilder, String filename) {
        // Stores filename
        File receiptFile = new File("src/main/resources/receipts/" + filename);

        try {

            BufferedWriter receiptWriter = new BufferedWriter(new FileWriter(receiptFile, true));

            receiptWriter.write(receiptBuilder);
            receiptWriter.newLine();
            receiptWriter.close();

        } catch(Exception e) {
            System.out.println("Could not write receipt to file.");
        }
    }
}
