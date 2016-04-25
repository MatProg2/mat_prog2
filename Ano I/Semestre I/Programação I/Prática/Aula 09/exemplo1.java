import java.util.Scanner;
import java.io.*;

public class exemplo1 {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int cont[] = new int[26];

        String frase = new String();

        System.out.print("texto: ");
        frase = kb.nextLine();
        for(int i = 0 ; i < frase.length() ; i++) {
            char letra = frase.charAt(i);
            if(Character.isLetter(letra)) {
                letra = Character.toLowerCase(letra);
                int pos = (int)(letra - 'a');
                cont[pos]++;
            }
        }

        for(int i = 0 ; i < cont.length ; i++) {
            if(cont[i] > 0) {
                char l = (char)('a' + i);
                System.out.printf("%c -> %d\n", l, cont[i]);
            }
        }
    }
}
