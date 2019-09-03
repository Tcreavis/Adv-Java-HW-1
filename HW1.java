// Tyler Reavis
// OCCC Fall 2019
// Advanced Java
// Demo Program V2

import java.math.BigInteger;
import java.util.Scanner;

public class HW1{

	public static void main (String[] args){

		String theValue = "";
		int theInitialBase = 0;
		int theFinalBase = 0;

		if (args.length == 0){
			Scanner s = new Scanner(System.in);
			System.out.print("Please enter a value: ");
			theValue = s.nextLine();
			System.out.print("Please enter initial base: ");
			theInitialBase = Integer.parseInt(s.nextLine());
			System.out.print("Please enter the final base: ");
			theFinalBase = Integer.parseInt(s.nextLine());
		}

		else if (args.length == 3){
			theValue = args[0];
			theInitialBase = Integer.parseInt(args[1]);
			theFinalBase = Integer.parseInt(args[2]);
		}

		else {
			System.out.println("Usage: Demo [value initBase finalbase]");
			System.exit(0);
		}

		if(isValidInteger(theValue, theInitialBase)) {			
			String finalValue = baseConverter(theValue, theInitialBase, theFinalBase);
			System.out.println(theValue + " in base " + theInitialBase + " is " + finalValue + " in base " + theFinalBase);
		}
		
		else {
			System.out.println("Please try again.");
		}

	}
		
	public static boolean isValidInteger(String theValue, int theBase) {
		
		String value = theValue.toUpperCase();
		int base = theBase;
		
	
		if(base < 2 || base > 36) {
			
			System.out.println("Base must be between 2 and 36.");
			return false;
		}
	
		else {
			for(int i = 0; i < value.length(); i++) {
				
				int requiredBase = 0;
				
				if(Character.isDigit(value.charAt(i))) {
					requiredBase = value.charAt(i) - 47;
					if (base < requiredBase) {
						System.out.println("Given base is not valid for given value.");
						return false;
					}
				}
				
				else {
					requiredBase = value.charAt(i) - 54;
					if(base < requiredBase) {
						System.out.println("Given base is not valid for given value.");
						return false;
					}
							
				}
			}
		
		}
				
		return true;
	}

	public static String baseConverter(String theValue, int theInitialBase, int theFinalBase) {
		
		String value = theValue.toUpperCase();
		BigInteger firstBase = BigInteger.valueOf(theInitialBase);
		BigInteger secondBase = BigInteger.valueOf(theFinalBase);

		
		BigInteger baseMultiplier = BigInteger.valueOf(1);
		BigInteger counter = BigInteger.valueOf(0);
		
		for (int i = value.length() - 1; i >= 0; i--) {
			
			char currentChar = value.charAt(i);
			BigInteger currentCharInDec = BigInteger.valueOf(0);
			
			if(Character.isAlphabetic(currentChar)) {				
				currentCharInDec = BigInteger.valueOf(currentChar - 55);				
			}
			
			else {
				currentCharInDec = BigInteger.valueOf(currentChar - 48);				
			}
			
			counter = counter.add( (currentCharInDec.multiply(baseMultiplier)) );
			baseMultiplier = baseMultiplier.multiply(firstBase);
		}

		BigInteger baseTenValue = counter;
		
		int k = 0;
		do {
			counter = counter.divide(secondBase);
			k++;
		}while(counter.floatValue() > 0);
		
		char[] finalValueInverted = new char[k];

			for(int i = 0; i < k; i++){
				BigInteger[] conversionArr = baseTenValue.divideAndRemainder(secondBase);
				baseTenValue = conversionArr[0];
				BigInteger finalValBit = conversionArr[1];

				if(finalValBit.intValue() > 9)
				{
					Character.toChars( finalValBit.intValue() + 55, finalValueInverted, i );
				}
				else
				{
					Character.toChars( finalValBit.intValue() + 48, finalValueInverted, i );
				}	
			}
			
			int x = 0;
			char[] finalValue = new char[k];
			for (int i = k-1; i >= 0; i--) {
				finalValue[x] = finalValueInverted[i];
				x++;
			}
			
		String finalValString = String.copyValueOf(finalValue);
		
		return finalValString;
	}
}
