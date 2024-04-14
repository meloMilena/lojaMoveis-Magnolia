/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.domain;

import java.util.Random;

/**
 *
 * @author DevChefMio
 */
public class CodigoBarrasGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int MAX_LENGTH = 5;

    public static String generateCodigoBarras() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < MAX_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }

        return sb.toString();
    }
}
