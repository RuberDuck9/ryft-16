![1737254692_grim](https://github.com/user-attachments/assets/a459817b-d718-4ad2-854e-2d234ae0d7e5)    


## About  
The ryft-16 is a 16 bit cpu/computer that can do basic functions and math. The schematic for it is written in **logisim evolution**, but could theoretically be built in real life if anyone had the time and money to do so. All information posted below is designed to make it simpler to use for the "average" person, but please note that information here may have typos and other mistakes (in which case please report them) so don't rule out any one point of error if you are having problems (at least for the time being). Enjoy!  

## Usage Instructions  
*PLEASE* read the text below to understand how this computer works, if even a single instruction is formatted wrong the program will break. To program the computer, first write out what you would like to do in the assembly code listed below. Then, convert (you will need to do this yourself, sorry) the assembly into binary, which is stored in rom as a hex value. So, write code, turn that into hex, record it into rom, hopefully it doesn't break. Listed below are a few examples of what lines of this custom assembly would look like, and the binary conversion charts for each instruction listed next to the name.   

## Common Traps
There are a few things that no matter the circumstance should pretty much never be done. The first is setting the counting to a non-multiple of 4. Doing so will cause the cpu to crash and burn miserably as arguments and opicodes will not be in sync. Rather than seeing "opticode arg1 arg2 arg3" the cpu may see "arg3 opticode arg1 arg2" which would be catastrophic. Another trap is pushing a value to stack and not popping it off before returning from a function. This will cause the ret instruction to attempt to jump to the value you pushed to the stack and forgot about. For this reason, when inside functions, it is best to try to use registers because if you forget to pop all your values off the stack, things will fall apart quickly. I'm sure there are more things I'm forgetting about at the moment, so be careful when doing things not generally intended by this architecture. 
   
### Architecture Style:   

![architure](https://github.com/user-attachments/assets/da8653cb-1d8c-460a-8a7b-f7826a341588)       
The architecture this cpu is closely roughly after.   

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
  
- IMM [0000000000000000] [0000] : immediately store the value typed out in argument (IN HEX!) 1 to the address specified in argument 3, argument 2 should have NULL as its value    
- CPY [0000000000000001] [0001] : copy from the address specified in argument 1, argument 2 should be NULL, to that specified in argument in 3  
- ADD [0000000000000010] [0002] : add the values held at the addresses specified in arguments 1 and 2 and save it to the address specified in argument 3       
- AD1 [0000000000000011] [0003] : add the value typed out in argument 1 (IN HEX!) and the value held at the address specified in arguments 2, and save it to the address specified in argument 3   
- AD2 [0000000000000100] [0004] : add the value typed out in argument 2 (IN HEX!) and the value held at the address specified in arguments 1, and save it to the address specified in argument 3    
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
- EQL [0000000000010000] [0010] : compare the values stored the at the addresses specified by arguments 1 and 2, if they are equal, sets the counter value to the one typed out in argument 3(IN HEX!)      
- NEQ [0000000000010001] [0011] : compare the values stored the at the addresses specified by arguments 1 and 2, if they are not equal, sets the counter value to the one typed out in argument 3 (IN HEX!)          
- GRT [0000000000010010] [0012] : compare the values stored the at the addresses specified by arguments 1 and 2, if the value of argument 1 is greater than that of argument 2, sets the counter value to the one typed out in argument 3 (IN HEX!)      
- LES [0000000000010011] [0013] : compare the values stored the at the addresses specified by arguments 1 and 2, if the value of argument 1 is less than that of argument 2, sets the counter value to the one typed out in argument 3 (IN HEX!)         
- CAL [0000000000010100] [0014] : push the current counter value + 4 to the stack (to prevent infinite loops) and then jump to the address typed out in argument 3, arguments 1 and 2 should be NULL for this instruction     
- RET [0000000000010101] [0015] : pop the top value off the stack and set the counter equal to that value, all other arguments are NULL for this instruction
- LOR [0000000000010110] [0016] : load the value held at the address specified in arguments 2 from ram and save it at the address specified in argument 3, argument 1 should be NULL  
- HLT [1111111111111111] [ffff] : stop the system clock, all other arguments are NULL for this instruction   

### Example Assembly Commands:  
Subject to change in the future    

- imm 5 null a : save 5 to register 10   
- cpy a null b : copy the value in register 10 to register 11   
- add a b c : add register 10 and 11, save the result in register 12   
- ad1 5 a b : add 5 and register 10, save the result in register 11   
- ad2 a 5 b : add register 10 and 5, save the result in register 11   
- sub a b c : subtract register 10 and 11, save the result in register 12   
- mlt a b c : multiply register 10 and 11, save the result in register 12   
- div a b c : divide register 10 by 11, save the result in register 12   
- and a b c : bitwise and register 10 and 11, save the result in register 12   
- orr a b c : bitwise or register 10 and 11, save the result in register 12    
- nor a b c : bitwise nor register 10 and 11, save the result in register 12   
- str a b null : store the the value in register 11 at the value in register 10 in ram   
- psh a null null : push the value in register 10 to the stack   
- pop null null a : pop the top value off the stack and save it in register 10   
- rst null null null : set the counter to zero   
- gto null null d : set the counter to 14    
- eql a b 0 : if register 10 is equal to register 11 set the counter value to zero   
- neq a b 0 : if register 10 is not equal to register 11 set the counter value to zero   
- grt a b 0 : if register 10 is greater than register 11 set the counter value to zero   
- les a b 0 : if register 10 is less than register 11 set the counter value to zero    
- cal null null 10 : push the current counter value to the stack and jump to address 16   
- ret null null null : pop the top value off the stack and set the counter equal to it  
- lor null a b : load the value stored at address 10 in ram and save it in register 11   
- hlt null null null : halt the computer permanently   

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

## Debug Instructions:
First, make sure you didn't make any typos. If you did not, then follow the code until you see an instruction make a mistake. Try to see what caused that mistake by checking to see if each argument is doing what it should be for that instruction (easier said than done). It should be apparent by now whether you are dealing with a hardware bug, or a software bug. If it is a hardware bug or a software bug caused by me telling you to do something incorrect in the manual, report it to me please. If it is just a pure software bug caused by bad code, don't bother reporting it.    

## TODO 
Write an assembler to turn the assembly into hex.   
Come up with a better name
