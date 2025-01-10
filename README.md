# About  
The [name in progress] is a 16 bit cpu/computer that can do basic functions and math. The schematic for it is written in logisim evolution, but could theoretically be build in real life if anyone has the time and money to do so. All information posted below is designed to make it simpler to use for the "average" person, but please not that information here may have typos and other mistakes, in which case please report them, so don't rule any one point of error if you are having problems (at least for the time being). Enjoy!  

# Usage Instructions  
PLEASE read the text below to understand how this computer works, it should help you a lot (if i did a good job writing it). To program the computer, first write out what you would like to do in the assembly code listed below. Then, compile (you will need to do this yourself, sorry) the assembly into binary, which is stored in rom as a hex value. So, write code, turn it into binary, to that into hex, record it into rom, hopefully it doesn't break. Listed below are a few examples of what lines of this custom assembly would look like, and below that the binary conversion charts for each instruction listed next to the name.   
   
Architure Style:   
4x16 bit input   
Input 1: 16 bit opticode - What to do with values 2 & 3   
Input 2:  16 bit argument 1 - Value 1   
Input 3:  16 bit argument 2 - Value 2   
Input 4:  16 bit argument 3 - Save location  

Components:

- Instruction Interpreter: reads argument one and determines which instruction to execute
- Registers: hold temporary values that the cpu needs in the very short term
- RAM: holds more long term data for the cpu
- The Stack: stores data sequentially for the cpu, cannot be accessed out of outer
- ROM: stored the instruction for the cpu to carry out once turned on, stored data as binary hex values, reads from address specified by counter
- Counter: increases by one each tick unless modified by instruction, controls read address of ROM

Instructions:  
  
- IMM [0000000000000000] : immediately store the value typed out in argument 1 or 2 to the address specified in argument 3    
- CPY [0000000000000001] : copy from address specified in argument 1 or 2 (the argument you chose to not copy from should have NULL as its value) to that specified in argument in 3  
- ADD [0000000000000010] : add the values held at the addresses specified in arguments 1 and 2 and save it to the address specified in argument 3   
- SUB [0000000000000011] : subtract the value held at the address specified in argument 1 from that at argument 2 and save it to the address specified in argument 3   
- MLT [0000000000000100] : multiply the values held at the addresses specified in arguments 1 and 2 and save it to the address specified in argument 3   
- DIV [0000000000000101] : divide the value held at the address specified in argument 1 by that at argument 2 and save it to the address specified in argument 3  
- AND [0000000000000110] : and each bit of the values held at the addresses specified in arguments 1 and 2 and save it to the address specified in argument 3   
- ORR [0000000000000111] : or each bit of the values held at the addresses specified in arguments 1 and 2 and save it to the address specified in argument 3   
- NOR [0000000000001000] : nor each bit of the values held at the addresses specified in arguments 1 and 2 and save it to the address specified in argument 3   
- STR [0000000000001001] : store the value held at the address specified in arguments 1 or 2 (the argument you chose to not copy from should have NULL as its value) at the address typed in argument 3 in ram
- PSH [0000000000001010] : push the value stored at the address in either argument 1 or 2 to the stack, the argument not used should be NULL and argument 3 should be NULL   
- POP [0000000000001011] : pop the top value from the stack and save it to the address specified in argument 3, arguments 1 and 2 should be NULL   
- RST [0000000000001100] : set the value of counter to zero, all other arguments are NULL for this instruction         
- GTO [0000000000001101] : set the value of counter to the value typed in argument 3, argument 1 and 2 should be NULL   
- EQL [0000000000001110] : compare the values stored the at the addresses specified by arguments 1 and 2, if they are equal, sets the counter value to the one typed out in argument 3   
- NEQ [0000000000001111] : compare the values stored the at the addresses specified by arguments 1 and 2, if they are not equal, sets the counter value to the one typed out in argument 3    
- GRT [0000000000010000] : compare the values stored the at the addresses specified by arguments 1 and 2, if the value of argument 1 is greater than that of argument 2, sets the counter value to the one typed out in argument 3    
- LES [0000000000010001] : compare the values stored the at the addresses specified by arguments 1 and 2, if the value of argument 1 is less than that of argument 2, sets the counter value to the one typed out in argument 3    
- CAL [0000000000010001] : push the current counter value to the stack and then jump to the address typed out in argument 3, arguments 1 and 2 should be NULL for this instruction     
- RET [0000000000010001] : pop the top value off the stack and set the counter equal to that value, all other arguments are NULL for this instruction     
- HLT [1111111111111111] : stop the system clock, all other arguments are NULL for this instruction   

Example Instruction Commands:  
  
- imm 437 NULL reg4  
- cpy reg3 NULL reg2  
- add reg0 reg1 reg2      
- sub reg0 reg1 reg2     
- mlt reg0 reg1 reg2    
- div reg0 reg1 reg2   
- and reg0 reg1 reg2   
- orr reg0 reg1 reg2   
- nor reg0 reg1 reg2   
- str reg2 NULL 5   
- psh reg3 NULL NULL   
- pop NULL NULL reg5   
- rst NULL NULL NULL   
- gto NULL NULL 27   
- eql reg0 reg1 4   
- neq reg0 reg1 4   
- grt reg0 reg1 4   
- les reg0 reg1 4   
- cal NULL NULL 7  
- ret NULL NULL NULL   
- hlt NULL NULL NULL   

Register Addresses:   

- reg0 [0000000000000000]     
- reg1 [0000000000000001]       
- reg2 [0000000000000010]    
- reg3 [0000000000000011]       
- reg4 [0000000000000100]    
- reg5 [0000000000000101]    
- reg6 [0000000000000110]    
- reg7 [0000000000000111]    
- reg8 [0000000000001000]      
- reg9 [0000000000001001]       
