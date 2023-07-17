/*
NOT Operator (~)
The NOT operator, represented by the tilde (~) symbol, flips the bits of a binary number. 
It changes each 0 to 1 and each 1 to 0. This operator is useful for inverting the bits of a number.

AND Operator (&)
The AND operator, denoted by the ampersand (&) symbol, compares two binary numbers bit by bit. 
It sets each bit to 1 only if both corresponding bits are also 1. Otherwise, it sets the bit to 0. 
The AND operator is commonly used for masking and checking the presence of specific bits.

OR Operator (|)
The OR operator, represented by the vertical bar (|) symbol, compares two binary numbers bit by bit. 
It sets each bit to 1 if either of the corresponding bits is 1. If both bits are 0, it sets the resulting bit 
to 0. The OR operator is useful for combining or merging bits.

XOR Operator (^)
The XOR operator, denoted by the caret (^) symbol, compares two binary numbers bit by bit. 
It sets each bit to 1 if exactly one of the corresponding bits is 1. If both bits are the same (either both 
0 or both 1), it sets the resulting bit to 0. The XOR operator is often used for toggling or swapping bits.

Left Shift Operator (<<)
The left shift operator (<<) shifts the bits of a binary number to the left by a specified number of positions. 
This operation is equivalent to multiplying the number by 2 raised to the power of the shift amount. It is 
frequently used for efficient multiplication or creating space for additional bits.

Right Shift Operator (>>)
The right shift operator (>>) shifts the bits of a binary number to the right by a specified number of 
positions. This operation is equivalent to dividing the number by 2 raised to the power of the shift amount. 
It is commonly used for efficient division or extracting specific bits

*/

// Printing Binary Representation of a number
public void printBinary(int num){
    for(int i=10; i>=0; i--) cout<<((num>>i)&1); 
}

// checking if ith Bit is Set or Not
public void ithBitSetOrNot(int num){
    if(num&(1<<i) != 0){
        System.out.println("Set");
    }else{
        System.out.println("Not Set");
    }    
}

// Counting Number of set Bits
public void countSetBits(int num){
    int count = 0;
    for(int i=31; i>=0; i--){
        if(num&(1<<i) != 0){
            count++; 
        }
    }
}

// How To check if number if power of 2 
/*
Properties for numbers which are powers of 2, is that they have one and only one bit set in their binary representation. 
If the number is neither zero nor a power of two, it will have 1 in more than one place. So if x is a power of 2 
then x & (x-1) will be 0.
*/

public void isPowerOfTwo(int num){
    return (num && !(num&(num-1)));
}

// Dividing a number or multiplying a number by 2
x = x>>1
x = x<<1

// Swap with XOR
int a,b;
a = a^b;
b = b^a;
a = a^b;


//