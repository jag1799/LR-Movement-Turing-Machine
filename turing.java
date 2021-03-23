import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


class turing{

    static int inputState; //Start State
    static int outputState; //Ending state
    static String charToRead; //Character we are reading
    static String charToWrite; //
    static String tapeHeadMovement;
    static int numStates = 0;
    static int numRules = 0;
    static int numInputStrings;
    static int maxNumSimSteps;
    static String test;
    static ArrayList<Transition> machine = new ArrayList<Transition>();
    static ArrayList<String> machineTape = new ArrayList<String>();

    public static void main(String[] args) {
        
        input();
        
    }


    public static void input(){

        Scanner scan = new Scanner(System.in);
        ArrayList<String> testStrings = new ArrayList<String>();
        ArrayList<Integer> testStringOutcomes = new ArrayList<Integer>();
        
        int numTuringMachines = 0;
        numTuringMachines = scan.nextInt();

        for(int i = 0; i < numTuringMachines; i++){

            
            numStates = scan.nextInt();

            
            numRules = scan.nextInt();


            for(int j = 0; j < numRules; j++){

                

                inputState = scan.nextInt();
                charToRead = scan.next();
                outputState = scan.nextInt();
                charToWrite = scan.next(); //Character you're writing to the tape
                tapeHeadMovement = scan.next();

            }

            numInputStrings = scan.nextInt(); 
            maxNumSimSteps = scan.nextInt();

            for(int k = 0; k < numInputStrings; k++){

                test = scan.next();
                testStrings.add(test); //Store the current test string
                int pass = machineProcess(test); //Get whether the test string passes, not, or fails to halt in 10 steps

                testStringOutcomes.add(pass);
            }

            machineOutput(testStrings, testStringOutcomes, i);
        }
    }

    public static int machineProcess(String test){

        int tapeHead = 0;
        int numSteps = 0;

        //Compute to the end of the test string
        for(int i = 0; i < test.length(); i++){


        }

    }


    public static void machineOutput(ArrayList<String> testStrings, ArrayList<Integer> testStringOutcomes, int machineNum){

        System.out.println("Machine #" + machineNum + ":");

        for(int i = 0; i <  testStrings.size(); i++){

            if(testStringOutcomes.get(i) == 1){

                
                System.out.println(testStrings.get(i) + ": YES");
                
            } else if(testStringOutcomes.get(i) == 2) {

                System.out.println(testStrings.get(i) + ": NO");

            } else {

                System.out.println(testStrings.get(i) + ": DOES NOT HALT IN 10 STEPS");
            }
        }
    }




    class Transition{

        int startState;
        int endState;
        String charToWrite;

        public Transition(int start, int end, String writeChar){

            this.startState = start;
            this.endState = end;
            this.charToWrite = writeChar;

        }
    }

}