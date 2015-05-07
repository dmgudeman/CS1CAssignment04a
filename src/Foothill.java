
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
      searchTree.insert(70);
      searchTree.insert(10);
      searchTree.insert(9);
      searchTree.insert(60);
      searchTree.insert(59);
      searchTree.insert(61);
      searchTree.insert(62);
     
      //TRAVERSE
      System.out.println( "\nTRAVERSE after populating: ");
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      System.out.println( "\n60 soft removal: ");
      if (searchTree.remove(62))
         System.out.println( "removed " + 62 );
      System.out.println( "tree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      System.out.println( "\n20 soft removal: ");
      if (searchTree.remove(20))
         System.out.println( "removed " + 20 );
      System.out.println( "tree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      //TRAVERSE
      System.out.println( "\n50 and 20 soft removed");
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      System.out.println( "\n50 and 20 Collecting garbage after soft remove" );
      searchTree.collectGarbage();
      System.out.println( "tree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      //TRAVERSE
      System.out.println( "\n50 and 20: Traverse after garbage");
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      System.out.println( "\nCollecting garbage REPEAT" );
      searchTree.collectGarbage();
      System.out.println( "tree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      //TRAVERSE
      System.out.println( "\nTRAVERSE after SECOND GARBAGE: ");
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

     
//      System.out.println( "\n60: Reinsert after garbage");
//      searchTree.insert(60);
//      System.out.println( "\ntree 1 size: " + searchTree.size() 
//            + "  Hard size: " + searchTree.sizeHard() );
//     
//      
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
//      FHlazySearchTree<Integer> searchTree2 
//         = (FHlazySearchTree<Integer>)searchTree.clone();
//
//      System.out.println( "\n\nAttempting 1 removal: ");
//      if (searchTree.remove(20))
//         System.out.println( "removed " + 20 );
//      System.out.println( "tree 1 size: " + searchTree.size() 
//         + "  Hard size: " + searchTree.sizeHard() );
//      
//      System.out.println( "After removing 20 -- traversal and sizes: ");
//      searchTree.traverse(intPrinter);
//      System.out.println( "\ntree 1 size: " + searchTree.size() 
//         + "  Hard size: " + searchTree.sizeHard() );
//
//      System.out.println( "Collecting Garbage - should clean 1 item. " );
//      searchTree.collectGarbage();
//      System.out.println( "tree 1 size: " + searchTree.size() 
//         + "  Hard size: " + searchTree.sizeHard() );
//
//      System.out.println( "Collecting Garbage again - no change expected. " );
//      searchTree.collectGarbage();
//      System.out.println( "tree 1 size: " + searchTree.size() 
//         + "  Hard size: " + searchTree.sizeHard() );

      // test soft insertion and deletion:

//      System.out.println( "Adding 'hard' 22 - should see new sizes. " );
//      searchTree.insert(22);
//      searchTree.traverse(intPrinter);
//      System.out.println( "\ntree 1 size: " + searchTree.size() 
//         + "  Hard size: " + searchTree.sizeHard() );
//
//      System.out.println( "\nAfter soft removal. " );
//      searchTree.remove(22);
//      searchTree.traverse(intPrinter);
//      System.out.println( "\ntree 1 size: " + searchTree.size() 
//         + "  Hard size: " + searchTree.sizeHard() );
//
//      System.out.println( "Repeating soft removal. Should see no change. " );
//      searchTree.remove(22);
//      searchTree.traverse(intPrinter);
//      System.out.println( "\ntree 1 size: " + searchTree.size() 
//         + "  Hard size: " + searchTree.sizeHard() );
//
//      System.out.println( "Soft insertion. Hard size should not change. " );
//      searchTree.insert(22);
//      searchTree.traverse(intPrinter);
//      System.out.println( "\ntree 1 size: " + searchTree.size() 
//         + "  Hard size: " + searchTree.sizeHard() );
//
//      System.out.println( "\n\nAttempting 100 removals: " );
//      for (k = 100; k > 0; k--)
//      {
//         if (searchTree.remove(k))
//            System.out.println( "removed " + k );
//      }
//      searchTree.collectGarbage();
//      
//      System.out.println( "\nsearch_tree now:");
//      searchTree.traverse(intPrinter);
//      System.out.println( "\ntree 1 size: " + searchTree.size() 
//         + "  Hard size: " + searchTree.sizeHard() );
//
//      searchTree2.insert(500);
//      searchTree2.insert(200);
//      searchTree2.insert(300);
//      searchTree2.insert(700);
//      searchTree2.insert(100);
//      searchTree2.insert(600);
//      System.out.println( "\nsearchTree2:\n" );
//      searchTree2.traverse(intPrinter);
//      System.out.println( "\ntree 2 size: " + searchTree2.size() 
//         + "  Hard size: " + searchTree2.sizeHard() );
   }
   
}
