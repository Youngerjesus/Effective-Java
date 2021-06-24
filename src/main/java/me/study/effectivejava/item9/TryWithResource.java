package me.study.effectivejava.item9;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResource {
    static String firstLineOfFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            return br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new RuntimeException();
    }
}
