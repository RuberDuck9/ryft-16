# Custom 16 Bit Cpu   
ALL INFORMAION IN HERE IS MOST LIKELY CORRECT, BUT PLEASE LET ME KNOW IF THERE ARE INCONSISTENCIES THAT I MISSED   
   
Architure Style:   
4x16 bit input   
Input 1: 16 bit opticode - What to do with values 2 & 3   
Input 2:  16 bit argument 1 - Value 1   
Input 3:  16 bit argument 2 - Value 2   
Input 4:  16 bit argument 3 - Save location  

Instructions:

NOP [0000000000000000] : no operation, modify no values
CPY [0000000000000001] : copy from address specified in argument 1 or 2 (the argument you chose to not copy from should have NULL as its value) to that specified in argument in 3
ADD [0000000000000010] : add the values held at the addresses specified in arguments 1 and 2 and save it to the address specified in argument 3
SUB [0000000000000011] : subtract the value held at the address specified in argument 1 from that at argument 2 and save it to the address specified in argument 3
MLT [0000000000000100] : multiply the values held at the addresses specified in arguments 1 and 2 and save it to the address specified in argument 3
DIV [0000000000000101] : divide the value held at the address specified in argument 1 by that at argument 2 and save it to the address specified in argument 3
AND [0000000000000110] : and each bit of the values held at the addresses specified in arguments 1 and 2 and save it to the address specified in argument 3
ORR [0000000000000111] : or each bit of the values held at the addresses specified in arguments 1 and 2 and save it to the address specified in argument 3
NOR [0000000000001000] : nor each bit of the values held at the addresses specified in arguments 1 and 2 and save it to the address specified in argument 3
STR [0000000000001001] : store the value specified at the address in either argument 1 or 2 in ram, the argument not used should be NULL and argument 3 should be NULL
PSH [0000000000001010] : push the value stored at the address in either argument 1 or 2 to the stack, the argument not used should be NULL and argument 3 should be NULL
POP [0000000000001011] : pop the top value from the stack and save it to the address specified in argument 3, arguments 1 and 2 should be NULL
RST [0000000000001100] : set the value of counter to zero
GTO [0000000000001101] : set the value of counter to the value at the address specified in argument 3, argument 1 and 2 should be NULL
EQL [0000000000001110] : compare the values stored the at the addresses specified by arguments 1 and 2, if they are equal, sets the counter value to the one typed out in argument 3
NEQ [0000000000001111] : compare the values stored the at the addresses specified by arguments 1 and 2, if they are not equal, sets the counter value to the one typed out in argument 3
GRT [0000000000010000] : compare the values stored the at the addresses specified by arguments 1 and 2, if the value of argument 1 is greater than that of argument 2, sets the counter value to the one typed out in argument 3
LES [0000000000010001] : compare the values stored the at the addresses specified by arguments 1 and 2, if the value of argument 1 is less than that of argument 2, sets the counter value to the one typed out in argument 3
HLT [1111111111111111] : stop the system clock
