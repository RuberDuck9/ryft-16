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
            opcodeMap.put("imm", "0000");
            opcodeMap.put("cpy", "0001");
            opcodeMap.put("add", "0002");
            opcodeMap.put("ad1", "0003");
            opcodeMap.put("ad2", "0004");
            opcodeMap.put("sub", "0005");
            opcodeMap.put("mlt", "0006");
            opcodeMap.put("div", "0007");
            opcodeMap.put("and", "0008");
            opcodeMap.put("orr", "0009");
            opcodeMap.put("nor", "000a");
            opcodeMap.put("str", "000b");
            opcodeMap.put("lor", "0016");
            opcodeMap.put("psh", "000c");
            opcodeMap.put("pop", "000d");
            opcodeMap.put("rst", "000e");
            opcodeMap.put("gto", "000f");
            opcodeMap.put("eql", "0010");
            opcodeMap.put("neq", "0011");
            opcodeMap.put("grt", "0012");
            opcodeMap.put("les", "0013");
            opcodeMap.put("cal", "0014");
            opcodeMap.put("ret", "0015");
            opcodeMap.put("hlt", "ffff");
            opcodeMap.put("null", "ffff");
            
            String opcode = opcodeMap.get(input.get(i));
            if (opcode != null) {
                output.add(opcode);
            }
            else { //If its just a number convert it into hex
                int iOut = Integer.parseInt(input.get(i));
                output.add(Integer.toHexString(iOut));
            }

        }

        for (int i = 0; input.size() > i; i++ ) { //Print out each hex value
            System.out.println(output.get(i));
        }

        console.close();

    }

}