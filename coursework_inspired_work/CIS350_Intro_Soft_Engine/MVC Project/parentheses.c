/******************************************************************************

                            Online C Compiler.
                Code, Compile, Run and Debug C program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <stdio.h>
#include <stdlib.h>
int strLength(char*, int);
int main (void){

	char* string;
	int nread;
	int counter;
	int ret = EOF+1;
	size_t maxn=500000;
	int len;
	int inputs = 0;
	int attempts=1;

	
string=(char *) malloc(sizeof(char)*maxn);
		ret = scanf("%s%n", string, &len);
	strLength(string, len);
		inputs +=1;
	if(inputs > 1){
	len = len-1;
	}		
	
	
	free (string);	
	return 0;
}
int strLength(char* str, int amountOfCharacters){
 int ret = EOF+1;
        size_t maxn=10;
	int forCounter = 0;
	int charsInd[5000] = {};
	charsInd[0] = -1;
	int stackIndex = 0;
	int longestString=0;
	int longestStartingString=0;
	int starter = 0;
	int returnValue;	
	int tempStringLength = 0;	
if ( starter == 0 && str[starter] != (char)'('  && str[starter] != (char)'{' && str[starter] != (char) '[' && str[starter] != (char) ')' && str[starter] != (char) '}' && str[starter] != (char) ']') {
longestStartingString++;
starter++;
while (str[starter]!= (char)'(' && str[starter]!= (char) '{' && str[starter] != (char) '[' && str[starter]!=(char) ')' && str[starter]!= (char)'}' && str[starter]!= (char) ']' && starter < amountOfCharacters){
	
	longestStartingString++;
	starter++;
	if(starter == amountOfCharacters){
	return longestStartingString;
	}
}
}

for (forCounter=0; forCounter < amountOfCharacters; forCounter++){
		if(str[forCounter]==(char) '(' || str[forCounter]== (char) '{' || str[forCounter]== (char)'['){
			if(stackIndex ==0){
			  charsInd[stackIndex] = forCounter;  
			}
			else{
			 
			stackIndex++;
			charsInd[stackIndex] = forCounter;
			
			}
		}
		
		
		if(str[forCounter] == (char) ')' || str[forCounter]== (char)'}' || str[forCounter]== (char) ']'){
			
			if(str[charsInd[stackIndex]] == (char) '(' && str[forCounter]== (char) ')'){				
				
				tempStringLength = forCounter - charsInd[stackIndex] + 1;
											
					int tempCounter = forCounter+1;
					
					while ((str[tempCounter] != (char) ')' && str[tempCounter] != (char) '(' && str[tempCounter] != (char) ']' && str[tempCounter] != (char) '[' && str[tempCounter] !=  (char) '{' && str[tempCounter] !=  (char) '}') && tempCounter < amountOfCharacters) {
							
							tempStringLength++;
							tempCounter++;
							}
					
					tempCounter = charsInd[stackIndex]-1;
					
					while ((str[tempCounter] != (char) ')' && str[tempCounter] != (char) '(' && str[tempCounter] != (char) ']' && str[tempCounter] != (char) '[' && str[tempCounter] != (char) '{' && str[tempCounter] != (char) '}') && tempCounter >= 0) {
					
					
					tempStringLength++;
					tempCounter--;
					
					}
					 
					
					if(tempStringLength >longestString){
					longestString = tempStringLength;
																                                                                                                                                                                      }
			stackIndex--;
			}
			

					
					
			
		
		

		else if(str[charsInd[stackIndex]] != (char) '(' && str[forCounter]== (char) ')'){
		stackIndex--;
		}
			
		else if(str[charsInd[stackIndex]] == (char) '[' && str[forCounter]== (char) ']'){
			
			tempStringLength += forCounter - charsInd[stackIndex] + 1;
			int tempCounter = forCounter+1;
 	                while ((str[tempCounter] != (char) '}' && str[tempCounter] != (char) '(' && str[tempCounter] != (char) ']' && str[tempCounter] != (char) '[' && str[tempCounter] !=  (char) '{' && str[tempCounter] !=  (char) '}') && tempCounter < amountOfCharacters) {
				tempStringLength++;
			        tempCounter++;
			}
                             tempCounter = charsInd[stackIndex]-1;
                        while ((str[tempCounter] != (char) ')' && str[tempCounter] != (char) '(' && str[tempCounter] != (char) ']' && str[tempCounter] != (char) '[' && str[tempCounter] != (char) '{' && str[tempCounter] != (char) '}') && tempCounter >= 0) {
				tempStringLength++;
				tempCounter--;
                        }
                        if(tempStringLength >longestString){
				longestString = tempStringLength;
			}
			stackIndex--;
			}

			else if(str[charsInd[stackIndex]] != (char) '[' && str[forCounter]== (char) ']'){
				stackIndex--;
			}
	

		else if(str[charsInd[stackIndex]] == (char) '{' && str[forCounter]== (char) '}'){
			tempStringLength += forCounter - charsInd[stackIndex] + 1;
			int tempCounter = forCounter+1;
			while ((str[tempCounter] != (char) '}' && str[tempCounter] != (char) '(' && str[tempCounter] != (char) ']' && str[tempCounter] != (char) '[' && str[tempCounter] !=  (char) '{' && str[tempCounter] !=  (char) '}') && tempCounter < amountOfCharacters) {
				tempStringLength++;
				tempCounter++;
			}
			tempCounter = charsInd[stackIndex]-1;
			while ((str[tempCounter] != (char) ')' && str[tempCounter] != (char) '(' && str[tempCounter] != (char) ']' && str[tempCounter] != (char) '[' && str[tempCounter] != (char) '{' && str[tempCounter] != (char) '}') && tempCounter >= 0) {
				tempStringLength++;
				tempCounter--;
				}
			if(tempStringLength >longestString){																			   				longestString = tempStringLength;
			}
			stackIndex--;
			}

			else if(str[charsInd[stackIndex]] != (char) '{' && str[forCounter]== (char) '}'){
				stackIndex--;
				}
		}
}
					//calculate length (if any of string before)
					////calculate lenght (if any of string after
					//add in any remaining length
					//see if it is enough to be longest
			//decrease pointer indexes by one
			//check for next parenthesis
if(longestString < longestStartingString){
longestString = longestStartingString;
}

tempStringLength=0;
 printf("%d\n", longestString);
return longestString;
}