import java.util.*;


class turing{

    static int inputState; //Start State
    static int outputState; //Ending state
    static String charToRead; //Character we are reading
    static String charToWrite; // Character we are writing to the tape
    static String tapeHeadMovement; //What direction we move on the tape
    static int numStates = 0; //Total number of states
    static int numRules = 0; //Number of rules to read
    static int numInputStrings; //Number of input strings we want to read
    static int maxNumSimSteps; 
    static String test; //Holder for each test string
    public static final int tapeAlphabetSize = 4; //Size of the tape alphabet
    public static final int acceptState = 1; 
    public static final int rejectState = 2;
    public static final int tapeSize = 1000;
    public static char[] tape = new char[tapeSize];

    

    public static void main(String[] args) {
        
        input();
        
    }


    public static void input(){

        Scanner scan = new Scanner(System.in);
        ArrayList<String> testStrings = new ArrayList<String>();
        ArrayList<Integer> testStringOutcomes = new ArrayList<Integer>();
        
        int numTuringMachines = 0;

        System.out.println("Number of TMs: ");
        numTuringMachines = scan.nextInt();
        

        for(int i = 0; i < numTuringMachines; i++){

            System.out.println("TM " + (i+1));
            
            System.out.println("Format: numStates numRules");
            numStates = scan.nextInt();

            //How we store the rules for each turing machine (rows x columns)
            Rule[][] rules = new Rule[numStates][tapeAlphabetSize];

            numRules = scan.nextInt();

            System.out.println("Format: ");
            System.out.println("InputState charToRead endState charToWrite tapeMovement");

            for(int j = 0; j < numRules; j++){

                
                inputState = scan.nextInt();

                charToRead = scan.next();

                int readCharValue = charToReadValueAssigner(charToRead);
                outputState = scan.nextInt();
                charToWrite = scan.next();   
                
                //Parse and store the tape movement for this rule; R = 0, L = 1
                tapeHeadMovement = scan.next();
                int tapeHeadMoveValue = tapeMovementParser(tapeHeadMovement);
                

                rules[inputState][readCharValue] = new Rule(inputState, outputState, charToRead, charToWrite, tapeHeadMoveValue);

            }

            System.out.println("Format: numInputStrings maxSimulationSteps");
            numInputStrings = scan.nextInt();
            maxNumSimSteps = scan.nextInt(); //Number of times to run the simulation

            for(int k = 0; k < numInputStrings; k++){

                System.out.println("String: ");
                test = scan.next();
                testStrings.add(test); //Store the current test string
                int pass = machineProcess(test, maxNumSimSteps, rules); //Get whether the test string passes, not, or fails to halt in 10 steps
                System.out.println("Pass: " + pass);
                testStringOutcomes.add(pass);
            }

            machineOutput(testStrings, testStringOutcomes, i);

            //Empty the test strings so the new machines do not print out any old test strings
            testStringOutcomes.clear();
            testStrings.clear();
        }

        scan.close();
    }


    public static int machineProcess(String test, int maxNumSimSteps, Rule[][] rules){

        //Things to keep track of
        int tapeHead = 0;
        int currentState = 0;

        int aValue = 0, bValue = 1, blankValue = 2, poundValue = 3;
        int i, j, numSteps = 0;


        //Copy the string into the tape character by character
        for(i = 0; i < test.length(); i++){

            tape[i] = test.charAt(i);

        }

        //begin parsing the string that is now in the tape
        for(j = 0; j < maxNumSimSteps; j++){

            //Read in a character from the tape
            char currentChar = tape[tapeHead];

            //Determine what character we have
            if(currentChar == 'a'){
                //Get the rule from the rules array and first write the rule's character to the tape
                tape[tapeHead] = rules[currentState][aValue].charToWrite.charAt(0);
                
                //Store the state we are going to move to
                int newState = rules[currentState][aValue].endState;

                if(rules[currentState][aValue].tapeMovement == 0){ //Left movement
                    
                    //Check if we are already at the leftmost position
                    if(tapeHead == 0){

                        tapeHead = 0;

                    } else { //We can move left without going out of bounds

                        tapeHead--;

                    }

                } else if(rules[currentState][aValue].tapeMovement == 1){ //Right movement

                    tapeHead++;
                    if(tape[tapeHead] == ' '){

                        tape[tapeHead] = 'B';
                    }

                }

                //Finally, move to the new state
                currentState = newState;


            } else if(currentChar == 'b'){

                //Write the new character to the tape
                tape[tapeHead] = rules[currentState][bValue].charToWrite.charAt(0);

                //Store the new state
                int newState = rules[currentState][bValue].endState;

                //Check for the tape movement
                if(rules[currentState][bValue].tapeMovement == 0){ //left movement

                    //Check if we're at the beginning of the tape
                    if(tapeHead == 0){

                        tapeHead = 0;

                    } else {

                        tapeHead--;

                    }
                
                } else if(rules[currentState][bValue].tapeMovement == 1){ //Right movement

                    tapeHead++;
                    if(tape[tapeHead] == ' '){

                        //Need this so the machine recognizes that it's a blank space and can handle that
                        tape[tapeHead] = 'B';

                    }

                }

                //Get the next state
                currentState = newState;


            } else if(currentChar == 'B'){

                //Write the new character to the tape
                tape[tapeHead] = rules[currentState][blankValue].charToWrite.charAt(0);

                //Store the new state
                int newState = rules[currentState][blankValue].endState;

                //Check for the tape movement
                if(rules[currentState][blankValue].tapeMovement == 0){ //left movement

                    //Check if we're at the beginning of the tape
                    if(tapeHead == 0){

                        tapeHead = 0;

                    } else {

                        tapeHead--;

                    }
                
                } else if(rules[currentState][blankValue].tapeMovement == 1){ //Right movement

                    tapeHead++;
                    if(tape[tapeHead] == ' '){

                        tape[tapeHead] = 'B';

                    }

                }

                //Get the next state
                currentState = newState;

            } else if(currentChar == '#'){

                //Write the new character to the tape
                tape[tapeHead] = rules[currentState][poundValue].charToWrite.charAt(0);

                //Store the new state
                int newState = rules[currentState][poundValue].endState;

                //Check for the tape movement
                if(rules[currentState][poundValue].tapeMovement == 0){ //left movement

                    //Check if we're at the beginning of the tape
                    if(tapeHead == 0){

                        tapeHead = 0;

                    } else {

                        tapeHead--;

                    }
                
                } else if(rules[currentState][poundValue].tapeMovement == 1){ //Right movement

                    tapeHead++;
                    if(tape[tapeHead] == ' '){

                        tape[tapeHead] = 'B';
                    }

                }

                //Get the next state
                currentState = newState;

            } else {

                System.out.println("Invalid character for string.");

                //Invalid string character
                return 2;

            }

            //Keep track of the number of steps
            numSteps++;

            //If we go over the boundaries of the number of simulation steps, stop
            if(numSteps >= maxNumSimSteps){

                return 3; //Does not halt

            }


            //If we reach an accept state at any point, accept the string
            if(currentState == acceptState){

                return 1;

            } else if(currentState == rejectState){ //Otherwise, reject the string

                return 2;

            }            
        }
        return 0;

    }

    //Outputs the result of each string in the requested format
    public static void machineOutput(ArrayList<String> testStrings, ArrayList<Integer> testStringOutcomes, int machineNum){

        System.out.println("Machine #" + (machineNum+1) + ":");

        for(int i = 0; i <  testStrings.size(); i++){

            if(testStringOutcomes.get(i) == 1){

                System.out.println(testStrings.get(i) + ": YES");
                
            } else if(testStringOutcomes.get(i) == 2) {

                System.out.println(testStrings.get(i) + ": NO");

            } else if(testStringOutcomes.get(i) == 3){

                System.out.println(testStrings.get(i) + ": DOES NOT HALT IN " + maxNumSimSteps + " STEPS");

            }
        }
    }

    public static class Rule{

        int startState;
        String charToRead;
        int endState;
        int tapeMovement;
        String charToWrite;

        public Rule(int start, int end, String readChar, String writeChar, int tapeMove){

            //Input info
            this.startState = start;
            this.charToRead = readChar;

            //Output info
            this.endState = end;
            this.tapeMovement = tapeMove;
            this.charToWrite = writeChar;
            

        }
    }

    /* 
    This method parses the tape movement string and converts it into an integer value
    */
    public static int tapeMovementParser(String tapeHeadMovement){

        if(tapeHeadMovement.contains("R")){

            //Store a right move, represented by 0
            return 0;

        } else if(tapeHeadMovement.contains("L")){

            //Store a left move, represented by 1
            return 1;

        } else {

            System.out.println("Inavlid tape movement.");
            System.exit(0);

        }

        return 2;
    }

    
    /*Parses the character we are reading and returns an assigned integer
        a = 0
        b = 1
        B = 2
        # = 3
    This is how each tape alphabet character will be stored in the rules array*/
    public static int charToReadValueAssigner(String charToRead){

        if(charToRead.contains("a")){

            return 0;

        } else if(charToRead.contains("b")){

            return 1;

        } else if(charToRead.contains("B")){
            
            return 2;

        } else if(charToRead.contains("#")){

            return 3;

        } else {

            System.out.println("Invalid character read.");
            System.exit(0);
        }


        return 5;

    }

}