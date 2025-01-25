#include <stdio.h>
#include <string.h>
#include <stdlib.h>  

int main () {

        printf("Please enter the name of your file. \n"); // take in the file name to be used from the user

        char file_name[30];
        scanf("%s", file_name); //store the file name in file_name

        FILE* input_file; 
        input_file = fopen(file_name, "r"); //Try to open the file the user specified

	if (input_file == NULL) { //If the file didn't open successfully return an error
        	printf("File not found! \n");
        	return 1;
    	}

        int char_count = 0; //create the variable to use in the while loop below, this will count how many characters are in the assembly code
    
	while (getc(input_file) != EOF) { //count up the number of characters in the file
		char_count ++;
	} 

	char file_contents[char_count + 1]; //create a string to hold the data from the file
        fseek(input_file, 0, SEEK_SET); // go back to the beginning of the file
        fread(file_contents, 1, char_count + 1, input_file); //read from the file and store it in the string

        fclose(input_file); //close the file to free memory 

        char output[char_count * 4]; //insure the output string ill always have anough space to prevent a buffer overflow
        output[0] = '\0'; // Initialize the output string with an empty string

        char *token = strtok(file_contents, " ");

        int times_run = 0; //create an integer that will tick up every for times the while loop runs

        while (token != NULL) {

                if (strcmp(token, "nop") == 0) { //match the line to one of these
                        strcat(output, "0000 ");  
                }
                else if (strcmp(token, "imm") == 0) {
                        strcat(output, "0001 "); 
                }
                else if (strcmp(token, "cpy") == 0) {
                        strcat(output, "0002 "); 
                }
                else if (strcmp(token, "add") == 0) {
                        strcat(output, "0003 "); 
                }
                else if (strcmp(token, "ad1") == 0) {
                        strcat(output, "0004 ");
                }
                else if (strcmp(token, "ad2") == 0) {
                        strcat(output, "0005 "); 
                }
                else if (strcmp(token, "sub") == 0) {
                        strcat(output, "0006 "); 
                }
                else if (strcmp(token, "mlt") == 0) {
                        strcat(output, "0007 "); 
                }
                else if (strcmp(token, "div") == 0) {
                        strcat(output, "0008 ");
                }
                else if (strcmp(token, "and") == 0) {
                        strcat(output, "0009 "); 
                }
                else if (strcmp(token, "orr") == 0) {
                        strcat(output, "000a "); 
                }
                else if (strcmp(token, "nor") ==0) {
                        strcat(output, "000b "); 
                }
                else if (strcmp(token, "str") == 0) {
                        strcat(output, "000c ");
                }
                else if (strcmp(token, "lor") == 0) {
                        strcat(output, "0017 "); 
                }
                else if (strcmp(token, "psh") == 0) {
                        strcat(output, "000d "); 
                }
                else if (strcmp(token, "pop") == 0) {
                        strcat(output, "000e "); 
                }
                else if (strcmp(token, "rst") == 0) {
                        strcat(output, "000f ");
                }
                else if (strcmp(token, "gto") == 0) {
                        strcat(output, "0010 "); 
                }
                else if (strcmp(token, "eql") == 0) {
                        strcat(output, "0011 "); 
                }
                else if (strcmp(token, "neq") ==0) {
                        strcat(output, "0012 "); 
                }
                else if (strcmp(token, "grt") == 0) {
                        strcat(output, "0013 ");
                }
                else if (strcmp(token, "les") == 0) {
                        strcat(output, "0014 "); 
                }
                else if (strcmp(token, "cal") == 0) {
                        strcat(output, "0015 "); 
                }
                else if (strcmp(token, "ret") == 0) {
                        strcat(output, "0016 "); 
                }
                else if (strcmp(token, "hlt") == 0) {
                        strcat(output, "ffff ");
                }
                else if (strcmp(token, "null") == 0) {
                        strcat(output, "ffff "); 
                }
                else { // if the assembly token doesn't match with a term above, it must a number, so convert it into hex
                        int value = atoi(token); // convert the current token into an integer
                        char formated_hex_value[6]; // create a string to store the value that is about to be converted into hex
                        sprintf(formated_hex_value, "%04x ", value); // "%04X" formats the integer as a 4 digit string in lower case
                        strcat(output, formated_hex_value); // strcat concatonates output and the the new value we just formated
                }

                times_run ++; // every four times the while loop runs, add an extra indent
                if (times_run % 4 == 0) {
                        strcat(output, " \n");
                }

                token = strtok(NULL, " \n"); //move to the next line to decode
        }


        //print out the converted data with some nice formatting and stuff
        printf(" \n");
        printf("*************************************************");
        printf("\n%s", output);
        printf("************************************************* \n");

        return 0;

} 
