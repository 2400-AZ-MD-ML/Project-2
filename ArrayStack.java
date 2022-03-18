package Project2;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.lang.Math;

/**
    A class of stacks whose entries are stored in an array.
    @author Frank M. Carrano and Timothy M. Henry
    @version 5.0
*/
public final class ArrayStack<T> implements StackInterface<T>
{
	private T[] stack;    // Array of stack entries
	private int topIndex; // Index of top entry
   private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
  
   public ArrayStack()
   {
      this(DEFAULT_CAPACITY);
   } // end default constructor
  
   public ArrayStack(int initialCapacity)
   {
      checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempStack = (T[])new Object[initialCapacity];
      stack = tempStack;
		topIndex = -1;
      integrityOK = true;
  } // end constructor
  
//  < Implementations of the stack operations go here. >
public void push(T newEntry){
checkIntegrity();
ensureCapacity();
stack[topIndex+1] = newEntry;
topIndex++;
}
public T peek(){
   checkIntegrity();
   if(isEmpty()){
      throw new EmptyStackException();
   }
   else{
      return stack[topIndex];
   }
}
public boolean isEmpty(){
   return topIndex< 0;
}
public T pop(){
   T result = stack[topIndex];
   stack[topIndex] = null;
   topIndex--;
   return result;
}
public void clear(){
checkIntegrity();
while(topIndex>-1){
   stack[topIndex]=null;
   topIndex--;
}
}
/**
 * Evaulates a string postfix expression with variables already substituted
 * @param postfix the postfix string to be solved
 * @return the solution of the postfix expression
 */
 public double evaluatePostfix(String postfix){
    ArrayStack<Double> operandStack = new ArrayStack<Double>();
   checkIntegrity();
   //checks if postfix is blank
   if(postfix.isBlank()){
      throw new IllegalStateException("Empty string");
   }
   //Trim the input for the empty spaces in the beginning and end if there is any
   postfix = postfix.trim();
   double result = 0; //holds the total int amount after operations
   //begin looping through the sanitized postfix
   for(int i = 0; i < postfix.length(); i++){
      //Make the current character a T type, so that we can push it onto the stack if it's a operator
      char current = postfix.charAt(i);
      //move forward in the loop if there is an empty space
      if(current == ' '){
         continue;
      }
      //pushes the character as a string to the stack if it's a digit
      else if(Character.isDigit(current)){
         operandStack.push(Double.parseDouble(String.valueOf(postfix.charAt(i))));
         continue;
      }
      //pops the 2 most recent operands, converts them to double, and performs the respective +,-,*,/,^ operation
      //pushes the result of the operation as a string back onto the stack
      else{
         double operandTwo = operandStack.pop();
         double operandOne = operandStack.pop();
         switch (current){
            case '+':
               result = operandOne + operandTwo;
               operandStack.push(result);
               break;
            case '-':
               result = operandOne - operandTwo;
               operandStack.push(result);
               break;
            case '*':
               result = operandOne * operandTwo;
               operandStack.push(result);
               break;
            case '/':
               result = operandOne / operandTwo;
               operandStack.push(result);
               break;
            case '^':
               result = (double)Math.pow(operandOne, operandTwo);
               operandStack.push(result);
               break;
         }
      }
   }
   return operandStack.pop();
}
//  < Implementations of the private methods go here; checkCapacity and checkIntegrity
//    are analogous to those in Chapter 2. >
private void ensureCapacity(){
   if(topIndex == stack.length-1){
      int newLength = 2*stack.length;
      checkCapacity(newLength);
      stack = Arrays.copyOf(stack,newLength);
   }
}
private void checkCapacity(int length){
   if(length>MAX_CAPACITY){
      throw new IllegalStateException("Capacity is over the limit");
   }
}
private void checkIntegrity()
	{
	   if (!integrityOK)
		  throw new SecurityException ("ArrayStack is corrupt.");
	} // end checkintegrity
//  . . .
} // end ArrayStack
