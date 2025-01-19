import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

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

        System.out.println(input);

        ArrayList<String> output = new ArrayList<String>(); //Create a new array list to hold the resultant hex values

        for (int i = 0; input.size() > i; i++ ) { //Match each instruction to its hex value

            if (input.get(i).equals("imm")) {
                output.add("0000");
            }
            else if (input.get(i).equals("cpy")) {
                output.add("0001");
            }
            else if (input.get(i).equals("add")) {
                output.add("0002");
            }
            else if (input.get(i).equals("ad1")) {
                output.add("0003");
            }
            else if (input.get(i).equals("ad2")) {
                output.add("0004");
            }
            else if (input.get(i).equals("sub")) {
                output.add("0005");
            }
            else if (input.get(i).equals("mlt")) {
                output.add("0006");
            }
            else if (input.get(i).equals("div")) {
                output.add("0007");
            }
            else if (input.get(i).equals("and")) {
                output.add("0008");
            }
            else if (input.get(i).equals("orr")) {
                output.add("0009");
            }
            else if (input.get(i).equals("nor")) {
                output.add("000a");
            }
            else if (input.get(i).equals("str")) {
                output.add("000b");
            }
            else if (input.get(i).equals("psh")) {
                output.add("000c");
            }
            else if (input.get(i).equals("pop")) {
                output.add("000d");
            }
            else if (input.get(i).equals("rst")) {
                output.add("000e");
            }
            else if (input.get(i).equals("gto")) {
                output.add("000f");
            }
            else if (input.get(i).equals("eql")) {
                output.add("0010");
            }
            else if (input.get(i).equals("neq")) {
                output.add("0011");
            }
            else if (input.get(i).equals("grt")) {
                output.add("0012");
            }
            else if (input.get(i).equals("les")) {
                output.add("0013");
            }
            else if (input.get(i).equals("cal")) {
                output.add("0014");
            }
            else if (input.get(i).equals("ret")) {
                output.add("0015");
            }
            else if (input.get(i).equals("lor")) {
                output.add("0016");
            }
            else if (input.get(i).equals("hlt")) {
                output.add("ffff");
            }
            else if (input.get(i).equals("null")) {
                output.add("ffff");
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