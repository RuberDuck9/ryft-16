import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class assembler {

    public static void main(String[] args) {
        
        Scanner console = new Scanner(System.in);

        System.out.println("What is the name of your file?"); //Take in the name of the file to read from
        String fileName = console.nextLine();

        ArrayList<String> input = new ArrayList<String>(); //Create a new array list to hold all the inputs

        try { //Attempt to read from file and throw an error if you can't
            
            File file = new File(fileName);
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String rawInput = fileReader.nextLine();
                String[] spacedInput = rawInput.split("\\s+");
                for (String part : spacedInput) {
                    input.add(part); // Add each instruction to the input list
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
            e.printStackTrace();
        }

        ArrayList<String> output = new ArrayList<String>(); //Create a new array list to hold all the outputs

        for (int i = 0; input.size() > i; i++ ) { //Match each instruction to its hex value

            Map<String, String> opcodeMap = new HashMap<>();
            opcodeMap.put("nop", "0000");
            opcodeMap.put("imm", "0001");
            opcodeMap.put("cpy", "0002");
            opcodeMap.put("add", "0003");
            opcodeMap.put("ad1", "0004");
            opcodeMap.put("ad2", "0005");
            opcodeMap.put("sub", "0006");
            opcodeMap.put("mlt", "0007");
            opcodeMap.put("div", "0008");
            opcodeMap.put("and", "0009");
            opcodeMap.put("orr", "000a");
            opcodeMap.put("nor", "000b");
            opcodeMap.put("str", "000c");
            opcodeMap.put("lor", "0017");
            opcodeMap.put("psh", "000d");
            opcodeMap.put("pop", "000e");
            opcodeMap.put("rst", "000f");
            opcodeMap.put("gto", "0010");
            opcodeMap.put("eql", "0011");
            opcodeMap.put("neq", "0012");
            opcodeMap.put("grt", "0013");
            opcodeMap.put("les", "0014");
            opcodeMap.put("cal", "0015");
            opcodeMap.put("ret", "0016");
            opcodeMap.put("hlt", "ffff");
            opcodeMap.put("null", "ffff");
            
            String opcode = opcodeMap.get(input.get(i));
            if (opcode != null) {
                output.add(opcode);
            }
            else { //If its just a number convert it into hex
                int iOut = Integer.parseInt(input.get(i));
                output.add(String.format("%04X", iOut));
            }

        }

        System.out.println("******************************************************************");

        for (int i = 0; input.size() > i; i+=4 ) { //Print out each hex value
            System.out.println(output.get(i) + " " + output.get(i+1) + " " + output.get(i+2) + " " + output.get(i+3));
        }

        System.out.println("******************************************************************");

        console.close();

    }

}