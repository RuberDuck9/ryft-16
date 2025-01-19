## About  
The [name in progress] is a 16 bit cpu/computer that can do basic functions and math. The schematic for it is written in **logisim evolution**, but could theoretically be built in real life if anyone has the time and money to do so. All information posted below is designed to make it simpler to use for the "average" person, but please note that information here may have typos and other mistakes (in which case please report them) so don't rule out any one point of error if you are having problems (at least for the time being). Enjoy!  

## Usage Instructions  
*PLEASE* read the text below to understand how this computer works, if even a single instruction is formated wrong the program will break. To program the computer, first write out what you would like to do in the assembly code listed below. Then, convert (you will need to do this yourself, sorry) the assembly into binary, which is stored in rom as a hex value. So, write code, turn that into hex, record it into rom, hopefully it doesn't break. Listed below are a few examples of what lines of this custom assembly would look like, and the binary conversion charts for each instruction listed next to the name.   
   
### Architure Style:   
4x16 bit input   
Input 1: 16 bit opticode - What to do with the following 3 arguments   
Input 2:  16 bit argument 1     
Input 3:  16 bit argument 2     
Input 4:  16 bit argument 3     

### Components:

- Instruction Interpreter: reads argument one and determines which instruction to execute
- Registers: hold temporary values that the cpu needs in the very short term
- RAM: holds more long term data for the cpu
- The Stack: stores data sequentially for the cpu, cannot be accessed out of outer
- ROM: stored instructions for the cpu to carry out once turned on, stores data as binary hex values, reads from address specified by counter
- Counter: increases by one each tick unless modified by an instruction, controls read address of ROM

### Instructions:  
  
- IMM [0000000000000000] [0000] : immediately store the value typed out in argument 1 to the address specified in argument 3, argument 2 should have NULL as its value    
- CPY [0000000000000001] [0001] : copy from the address specified in argument 1, argument 2 should be NULL, to that specified in argument in 3  
- ADD [0000000000000010] [0002] : add the values held at the addresses specified in arguments 1 and 2 and save it to the address specified in argument 3       
- AD1 [0000000000000011] [0003] : add the value typed out in argument 1 and the value held at the address specified in arguments 2, and and save it to the address specified in argument 3   
- AD2 [0000000000000100] [0004] : add the value typed out in argument 2 and the value held at the address specified in arguments 1, and and save it to the address specified in argument 3    
- SUB [0000000000000101] [0005] : subtract the value held at the address specified in argument 1 from that at argument 2 and save it to the address specified in argument 3   
- MLT [0000000000000110] [0006] : multiply the values held at the addresses specified in arguments 1 and 2 and save it to the address specified in argument 3   
- DIV [0000000000000111] [0007] : divide the value held at the address specified in argument 1 by that at argument 2 and save it to the address specified in argument 3  
- AND [0000000000001000] [0008] : and each bit of the values held at the addresses specified in arguments 1 and 2 and save it to the address specified in argument 3   
- ORR [0000000000001001] [0009] : or each bit of the values held at the addresses specified in arguments 1 and 2 and save it to the address specified in argument 3   
- NOR [0000000000001010] [000a] : nor each bit of the values held at the addresses specified in arguments 1 and 2 and save it to the address specified in argument 3   
- STR [0000000000001011] [000b] : store the value held at the address specified in arguments 1 to the address specified in arguments 2 in ram, argument 3 should be NULL
- PSH [0000000000001100] [000c] : push the value stored at the address in either argument 1 to the stack, argument 2 should be NULL and argument 3 should be NULL   
- POP [0000000000001101] [000d] : pop the top value from the stack and save it to the address specified in argument 3, arguments 1 and 2 should be NULL   
- RST [0000000000001110] [000e] : set the value of counter to zero, all other arguments are NULL for this instruction         
- GTO [0000000000001111] [000f] : set the value of counter to the value typed in argument 3, argument 1 and 2 should be NULL   
- EQL [0000000000010000] [0010] : compare the values stored the at the addresses specified by arguments 1 and 2, if they are equal, sets the counter value to the one typed out in argument 3   
- NEQ [0000000000010001] [0011] : compare the values stored the at the addresses specified by arguments 1 and 2, if they are not equal, sets the counter value to the one typed out in argument 3    
- GRT [0000000000010010] [0012] : compare the values stored the at the addresses specified by arguments 1 and 2, if the value of argument 1 is greater than that of argument 2, sets the counter value to the one typed out in argument 3    
- LES [0000000000010011] [0013] : compare the values stored the at the addresses specified by arguments 1 and 2, if the value of argument 1 is less than that of argument 2, sets the counter value to the one typed out in argument 3    
- CAL [0000000000010100] [0014] : push the current counter value + 4 to the stack (to prevent infinite loops) and then jump to the address typed out in argument 3, arguments 1 and 2 should be NULL for this instruction     
- RET [0000000000010101] [0015] : pop the top value off the stack and set the counter equal to that value, all other arguments are NULL for this instruction
- LOR [0000000000010110] [0016] : load the value held at the address specified in arguments 2 from ram and save it at the address specified in argument 3, argument 1 should be NULL  
- HLT [1111111111111111] [ffff] : stop the system clock, all other arguments are NULL for this instruction   

### Example Assembly Commands:  
Subject to change in the future    

(Removed for now while command are reworked)   

### Register Addresses:   

- reg0 [0000000000000000] [0000]     
- reg1 [0000000000000001] [0001]       
- reg2 [0000000000000010] [0002]   
- reg3 [0000000000000011] [0003]      
- reg4 [0000000000000100] [0004]   
- reg5 [0000000000000101] [0005]   
- reg6 [0000000000000110] [0006]   
- reg7 [0000000000000111] [0007]   
- reg8 [0000000000001000] [0008]    
- reg9 [0000000000001001] [0009]
- reg10 [0000000000001010] [000a]
- reg11 [0000000000001011] [000b]
- reg12 [0000000000001100] [000c]
- reg13 [0000000000001101] [000d]
- reg14 [0000000000001110] [000e]
- reg15 [0000000000001111] [000f]
- reg16 [0000000000010000] [0010]
- reg17 [0000000000010001] [0011]
- reg18 [0000000000010010] [0012]
- reg19 [0000000000010011] [0013]
- null [1111111111111111] [ffff]

## TODO 
Write an assembler to turn the assembly into hex.
