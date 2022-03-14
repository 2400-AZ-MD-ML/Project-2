package Project2;

import java.util.EmptyStackException;
/**
    A class of stacks whose entries are stored in a chain of nodes.
    @author Frank M. Carrano and Timothy M. Henry
    @version 5.0
*/
public final class LinkedStack<T> implements StackInterface<T>
{
	private Node topNode; // References the first node in the chain
  private boolean integrityOK = false;
   public LinkedStack()
   {
      topNode = null;
      integrityOK = true;
   } // end default constructor
  
//  < Implementations of the stack operations go here. >
public T peek(){
   if(isEmpty()){
      throw new EmptyStackException();
      
   }
   else{
      return topNode.getData();
   }
}
public boolean isEmpty(){
   return topNode == null;
}
public void clear(){
   topNode =null;
}
public void push(T newEntry){
   topNode = new Node(newEntry, topNode);

}
public T pop(){
 T pop = peek();
 topNode = topNode.getNextNode();
 return pop;
}
private void checkIntegrity(){
   {
	   if (!integrityOK)
		  throw new SecurityException ("ArrayStack is corrupt.");
	} // end checkintegrity
}
/** 
 * Converts an infix string to a postfix string
 * @param postFix is the infix string that will be inputted to the method to return a postFix string
 * @return the postFix version of the infix string
*/
public String converttoPostFix(String postFix){
   checkIntegrity();
   if(postFix.isBlank()){
      throw new IllegalStateException("Empty string");
   }
   //Trim the input for the empty spaces in the beginning and end if there is any
   postFix = postFix.trim();
   String result = "";  
  //Have the open parenthesis be an T item because we need to check it later
   @SuppressWarnings("unchecked")
   T open = (T)String.valueOf('(');
   for(int i =0; i<postFix.length(); i++){
      //move forward in the loop if there is an empty space
      if(postFix.charAt(i)== ' '){
         continue;
      }
      //Make the current character a T type, so that we can push it onto the stack if it's a operator
      @SuppressWarnings("unchecked")
      T current = (T)String.valueOf(postFix.charAt(i));
   //If it's a character just add it to result
   if(Character.isLetter(postFix.charAt(i))){
   
         result += postFix.charAt(i);
         continue;
      }
      //If it's the highest precedence oprerator of ^ then we just push it onto the stack
      else if(postFix.charAt(i)== '^'){
         push(current);
         continue;
      }
    // If it's a normal operator of +,-,/, or * then we need to check if the stack has elements and if the current operator is less than or equal 
    //in precedence then we pop it from the stack, keep looping until either is false
    //then add it to the result then we push the current operator to the stack
      else if(postFix.charAt(i)== '*' || postFix.charAt(i)== '+' || postFix.charAt(i) == '-' || postFix.charAt(i) == '/'  ){
      
         while((!isEmpty()) && precedence(current)<= precedence(peek()) ){
             result += pop();
         }
         push(current);

         continue;
      }
      //Add the operator ( to the stack
   else if(postFix.charAt(i) =='('){
      push(current);
      continue;
   }
   // Then pop all the operators and add them to the result when the ) comes, until the open operator
  else if(postFix.charAt(i)==')'){
     T topOperator = pop();
     while(!topOperator.equals(open)){
        result += topOperator;
        topOperator = pop();
     }
   }
  }
  // Add any remaining operators onto result after the loop has traversed through the string.
while(!isEmpty()){
  T top = pop();
   result += top;
}
return result;
}
/** 
 * Returns a number based on the operator's precedence, the higher the precedence the higher the number returned
 * @param operator is the operator that will be checked ofr the precedence
 * @return a integer number that is based off the operator given
*/
public int precedence(T operator){
  if(operator.equals("+") || operator.equals("-")){
     return 1;
  }
  else if(operator.equals("/")|| operator.equals("*")){
     return 2;
  }
  else if(operator.equals("^")){
     return 3;
  }
  return -1;
}
//  . . .

	private class Node
	{
      private T    data; // Entry in stack
      private Node next; // Link to next node
      
      private Node(T dataPortion)
      {
         this(dataPortion, null);
      } // end constructor
      
      private Node(T dataPortion, Node linkPortion)
      {
         data = dataPortion;
         next = linkPortion;
      } // end constructor
      
      private T getData()
      {
         return data;
      } // end getData
      
      private void setData(T newData)
      {
         data = newData;
      } // end setData
      
      private Node getNextNode()
      {
         return next;
      } // end getNextNode
      
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
	} // end Node
   public static void main(String[] args){
      LinkedStack<String> result =  new LinkedStack<String>();
      System.out.println(result.converttoPostFix(" a *b/( c - a)+d*e "));
   }
} // end LinkedStack
