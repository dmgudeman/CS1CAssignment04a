
//CIS 1C Assignment #4
// Instructor Solution Client

import cs_1c.*;

class PrintObject<E> implements Traverser<E>
{
   public void visit(E x)
   {
      System.out.print( x + " ");
   }
};

//------------------------------------------------------
public class Foothill
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      int k;
      FHlazySearchTree<Integer> searchTree = new FHlazySearchTree<Integer>();
      PrintObject<Integer> intPrinter = new PrintObject<Integer>();

      searchTree.traverse(intPrinter);

      System.out.println( "\ninitial size: " + searchTree.size() );
      searchTree.insert(50);
      searchTree.insert(20);
      searchTree.insert(30);
      searchTree.insert(59);
      searchTree.insert(10);
      searchTree.insert(9);
      searchTree.insert(60);
      searchTree.insert(70);
      searchTree.insert(61);
      searchTree.insert(62);
     
      //TRAVERSE
      System.out.println( "\nTRAVERSE after populating: ");
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
//      SOFT REMOVAL
      System.out.println( "\n59 (one child) soft removal: ");
      if (searchTree.remove(59))
         System.out.println( "removed " + 59);
      System.out.println( "tree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      //SOFT REMOVAL
      System.out.println( "\n20 (two children) soft removal: ");
      if (searchTree.remove(20))
         System.out.println( "removed " + 20 );
      System.out.println( "tree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
    //SOFT REMOVAL
      System.out.println( "\n62 (leaf) soft removal: ");
      if (searchTree.remove(62))
         System.out.println( "removed " + 20 );
      System.out.println( "tree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      
      //TRAVERSE
      System.out.println( "\n59, 20, 62 soft removal.\n Size "
            + "should be 7, Hard Size should be 10");
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      //GARBAGE COLLECTION
      System.out.println( "\nCollecting garbage after soft remove.\n "
            + "Size should be 7 and Hard Size should be 7. " );
      searchTree.collectGarbage();
      System.out.println( "tree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      //TRAVERSE
      System.out.println( "\n Traverse after garbage collection");
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      //GARBAGE COLLECTION
      System.out.println( "\nCollecting garbage REPEAT. Should be no change" );
      searchTree.collectGarbage();
      System.out.println( "tree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      //TRAVERSE
      System.out.println( "\nTraverse after repeat garbage collection: ");
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      //SOFT REMOVAL
    System.out.println( "\n\nAttempting 9 soft removal: ");
    searchTree.remove(9);
       System.out.println( "removed " + 9 );
    System.out.println( "tree 1 size: " + searchTree.size() 
       + "  Hard size: " + searchTree.sizeHard() );
    
    //TRAVERSE
    System.out.println( "\nTRAVERSE after SOFT REMOVAL of 9: ");
    searchTree.traverse(intPrinter);
    System.out.println( "\ntree 1 size: " + searchTree.size() 
       + "  Hard size: " + searchTree.sizeHard() );
    
    System.out.println( "\nReinsert 9 which is in a soft delete state");
    searchTree.insert(9);
    System.out.println( "\ntree 1 size: " + searchTree.size() 
          + "  Hard size: " + searchTree.sizeHard() );
   
    
    //GARBAGE COLLECTION
    System.out.println( "\nCollecting garbage after soft removal 9" );
    searchTree.collectGarbage();
    System.out.println( "tree 1 size: " + searchTree.size() 
       + "  Hard size: " + searchTree.sizeHard() );
    
    //TRAVERSE
    System.out.println( "\nTRAVERSE after GARBAGE COLLECTION of 9 removal: ");
    searchTree.traverse(intPrinter);
    System.out.println( "\ntree 1 size: " + searchTree.size() 
       + "  Hard size: " + searchTree.sizeHard() );

     
      System.out.println( "\n60: Reinsert after garbage");
      searchTree.insert(60);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
            + "  Hard size: " + searchTree.sizeHard() );
     
      
//      
//      
//      System.out.println( "\nAfter garbage collecting 20");
//      searchTree.traverse(intPrinter);
//      System.out.println( "\ntree 1 size: " + searchTree.size() 
//         + "  Hard size: " + searchTree.sizeHard() );
//      
//      searchTree.insert(20);
//      
//      System.out.println( "After reinserting 20: ");
//      searchTree.traverse(intPrinter);
//      System.out.println( "\ntree 1 size: " + searchTree.size() 
//         + "  Hard size: " + searchTree.sizeHard() );
//      
      
      
      
      
      
      
//      System.out.println( "\n\n\n\nCollecting garbage on new tree - should be " +
//            "no garbage." );
//      searchTree.collectGarbage();
//      System.out.println( "tree 1 size: " + searchTree.size() 
//         + "  Hard size: " + searchTree.sizeHard() );
//
//      // test assignment operator
      FHlazySearchTree<Integer> searchTree2 
         = (FHlazySearchTree<Integer>)searchTree.clone();

      System.out.println( "\n\nAttempting 1 removal: ");
      if (searchTree.remove(20))
         System.out.println( "removed " + 20 );
      System.out.println( "tree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      System.out.println( "After removing 20 -- traversal and sizes: ");
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      System.out.println( "Collecting Garbage - should clean 1 item. " );
      searchTree.collectGarbage();
      System.out.println( "tree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      System.out.println( "Collecting Garbage again - no change expected. " );
      searchTree.collectGarbage();
      System.out.println( "tree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      // test soft insertion and deletion:

      System.out.println( "Adding 'hard' 22 - should see new sizes. " );
      searchTree.insert(22);
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      System.out.println( "\nAfter soft removal. " );
      searchTree.remove(22);
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      System.out.println( "Repeating soft removal. Should see no change. " );
      searchTree.remove(22);
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      System.out.println( "Soft insertion. Hard size should not change. " );
      searchTree.insert(22);
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      System.out.println( "\n\nAttempting 100 removals: " );
      for (k = 10; k > 0; k--)
      {
         if (searchTree.remove(k))
            System.out.println( "removed " + k );
      }
      searchTree.collectGarbage();
      
      System.out.println( "\nsearch_tree now:");
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      searchTree2.insert(500);
      searchTree2.insert(200);
      searchTree2.insert(300);
      searchTree2.insert(700);
      searchTree2.insert(100);
      searchTree2.insert(600);
      System.out.println( "\nsearchTree2:\n" );
      searchTree2.traverse(intPrinter);
      System.out.println( "\ntree 2 size: " + searchTree2.size() 
         + "  Hard size: " + searchTree2.sizeHard() );
    System.out.println(searchTree.findMax());
    System.out.println(searchTree.findMin());
   }
   
}
