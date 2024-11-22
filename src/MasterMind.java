import java.util.Scanner;
import java.util.Random;

public class MasterMind {
    public static void main(String[] args){
        System.out.println("Witaj w grze MasterMind!");
        System.out.println("Zgadnij 4 cyfrowy kod składający się z liczb od 1 do 6.");
        
        Scanner scann = new Scanner(System.in);
        Random rand = new Random();
        
        int codeLength = 4;
        int maxDigit = 6;
        int[] secretCode = new int[codeLength];
        int[] userCode = new int[codeLength];
        boolean guessed = false;
        
        for(int i = 0; i < codeLength; i++){
            secretCode[i] = rand.nextInt(maxDigit) + 1;
        }
        
        while(guessed == false){
            System.out.println("Wprowadź swoją próbę: ");
            String guess = scann.nextLine();
            
            try{
                if(guess.length() != codeLength){
                    throw new NumberFormatException();
                }
                
                for( int i = 0; i < codeLength; i++){
                    userCode[i] = Character.getNumericValue( guess.charAt(i) );
                    if( userCode[i] < 1 || userCode[i] > maxDigit){
                        throw new NumberFormatException();
                    }
                }
                
            }catch(NumberFormatException e){
                System.out.println("Wprowadź liczbę od 1 do " + maxDigit + ".");
            }
            
            int identicalButNotInPlace = 0;
            int identicalAndInPlace = 0;
            boolean[] countedInUserCode = new boolean[codeLength];
            boolean[] countedInSecretCode = new boolean[codeLength];
            
            // Sprawdzanie poprawnych cyfr na właściwej pozycji
            for(int i = 0; i < codeLength; i++){
                if(userCode[i] == secretCode[i]){
                    identicalAndInPlace++;
                    countedInUserCode[i] = true;
                    countedInSecretCode[i] = true;
                }
            }
            
            // Sprawdzanie poprawnych cyfr na niewłaściwej pozycji
            for(int i = 0; i < codeLength; i++){
                if(countedInUserCode[i] != true){
                    for(int j = 0; j < codeLength; j++){
                        if(countedInSecretCode[j] == false && userCode[i] == secretCode[j]){
                            identicalButNotInPlace++;
                            countedInUserCode[i] = true;
                            countedInSecretCode[j] = true;
                        }
                    }
                }
            }
            
            if(identicalAndInPlace == codeLength){
                System.out.println("Gratulacje - Kod zosatł odgadnięty!");
                guessed = true;
            } else {
                System.out.println("Poprawne cyfry na właściwej pozycji: " + identicalAndInPlace);
                System.out.println("Poprawne cyfry na niewłaściwej pozycji: " + identicalButNotInPlace);
            }
        }

        scann.close();
    }
}