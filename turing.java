import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


class turing{

    public static void main(String[] args) {
        
    }


    public void input(){

        Scanner scan = new Scanner(System.in);

        int numTuringMachines = 0;
        numTuringMachines = scan.nextInt();

        for(int i = 0; i < numTuringMachines; i++){

            int numStates = 0;
            numStates = scan.nextInt();

            int numRules = 0;
            numRules = scan.nextInt();


            for(int j = 0; j < numRules; j++){

                int inputState, outputState;
                String charToRead, charToWrite, tapeHeadMovement;

                inputState = scan.nextInt();
                charToRead = scan.next();
                outputState = scan.nextInt();
                charToWrite = scan.next();
                tapeHeadMovement = scan.next();

            }

            int numInputStrings = scan.nextInt();
            int maxNumSimSteps = scan.nextInt();

            for(int k = 0; k < numInputStrings; k++){

                String test = scan.next();
                
            }
        }
    }

}