
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
      
      //SOFT REMOVAL
      System.out.println( "\n59 (one child) soft removal: ");
      if (searchTree.remove(59))
         System.out.println( "removed " + 59);
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      //SOFT REMOVAL
      System.out.println( "\n20 (two children) soft removal: ");
      if (searchTree.remove(20))
         System.out.println( "removed " + 20 );
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      //SOFT REMOVAL
      System.out.println( "\n62 (leaf) soft removal: ");
      if (searchTree.remove(62))
         System.out.println( "removed " + 62 );
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
     
      //GARBAGE COLLECTION
      System.out.println( "\nCollecting garbage after soft remove of 3 items.\n"
            + "Size should be 7 and Hard Size should be 7. " );
      searchTree.collectGarbage();
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      //GARBAGE COLLECTION
      System.out.println( "\nCollecting garbage REPEAT. Should be no change" );
      searchTree.collectGarbage();
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      System.out.println("\n\nREINSERTING AFTER A SOFT REMOVAL");
      
      //SOFT REMOVAL
      System.out.println( "\nAttempting 9 soft removal: ");
      searchTree.remove(9);
      System.out.println( "removed " + 9 );
      System.out.println( "tree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
    
      System.out.println( "\nReinsert 9 which is in a soft delete state.\n"
         + "Should only increment the size not the hard size.");  
      searchTree.insert(9);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      System.out.println( "\ninsert 9 again.\n"
            + "N.");  
         searchTree.insert(9);
         System.out.println( "\ntree 1 size: " + searchTree.size() 
            + "  Hard size: " + searchTree.sizeHard() );
      
       // test assignment operator
      System.out.println("\nClone Search Tree (ST) into SEarch Tree 2 (ST2)\n");
      FHlazySearchTree<Integer> searchTree2 
         = (FHlazySearchTree<Integer>)searchTree.clone();
       
      //TRAVERSE
      System.out.println( "\nSearch Tree (ST) now: ");
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
    
      //TRAVERSE
      System.out.println( "\nSearch Tree 2 (ST2), a product of clone: ");
      searchTree2.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree2.size() 
         + "  Hard size: " + searchTree2.sizeHard() );
       
      System.out.println("Six insertions into ST2\n");
      searchTree2.insert(500);
      searchTree2.insert(200);
      searchTree2.insert(300);
      searchTree2.insert(700);
      searchTree2.insert(100);
      searchTree2.insert(600);
       
      //TRAVERSE
      System.out.println( "\nSearch Tree after 6 inserts into ST2: ");
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
     
      //TRAVERSE
      System.out.println( "\nSearch Tree now: ");
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree2.size() 
          + "  Hard size: " + searchTree2.sizeHard() );
      
      //SOFT REMOVAL 60 FROM ST
      System.out.println( "\n60 soft removal from ST: ");
      if (searchTree.remove(60))
      System.out.println( "removed " + 60);
      searchTree.traverse(intPrinter);
      System.out.println( "\nST size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      //SOFT REMOVAL 100 FROM ST2
      System.out.println( "\n100 soft removal from ST2: ");
      if (searchTree2.remove(100))
      System.out.println( "removed " + 100);
      searchTree.traverse(intPrinter);
      System.out.println( "\nST size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
       
      // test soft insertion and deletion:
      
      // INSERT
      System.out.println( "\nAdding 'hard' 22 - should see new sizes. " );
      searchTree.insert(22);
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      
      System.out.println( "\nAfter soft removal. " );
      searchTree.remove(22);
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      System.out.println( "\nRepeating soft removal. Should see no change. " );
      searchTree.remove(22);
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      System.out.println( "\nSoft insertion. Hard size should not change. " );
      searchTree.insert(22);
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      System.out.println( "\nAttempting 1 removal: ");
      if (searchTree.remove(20))
         System.out.println( "removed " + 20 );
      System.out.println( "tree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      System.out.println( "After removing 20 -- traversal and sizes: ");
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      System.out.println( "\nCollecting Garbage - should clean 1 item. " );
      searchTree.collectGarbage();
      System.out.println( "tree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      System.out.println( "\nCollecting Garbage again - no change expected. " );
      searchTree.collectGarbage();
      System.out.println( "tree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      // test soft insertion and deletion:

      System.out.println( "\nAdding 'hard' 22 - should see new sizes. " );
      searchTree.insert(22);
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      System.out.println( "\nAfter soft removal. " );
      searchTree.remove(22);
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      System.out.println( "\nRepeating soft removal. Should see no change. " );
      searchTree.remove(22);
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      System.out.println( "\nSoft insertion. Hard size should not change. " );
      searchTree.insert(22);
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      System.out.println( "\n\nAttempting 100 removals: " );
      for (k = 100; k > 0; k--)
      {
         if (searchTree.remove(k))
            System.out.println( "removed " + k );
      }
     
      
      
      System.out.println( "\nsearch_tree now:");
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
      
      System.out.println("GARBAGE COLLECTION");
      
      searchTree.collectGarbage();
      
      System.out.println( "\nsearch_tree2 now:");
      searchTree2.traverse(intPrinter);
      System.out.println( "\ntree 2 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );


      System.out.println(searchTree.findMax());
      System.out.println(searchTree.findMin());
      
      System.out.println( "\nRemove 50. " );
      searchTree.remove(50);
      searchTree.traverse(intPrinter);
      System.out.println( "\ntree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );

      System.out.println( "\nCollecting Garbage after remove 50. " );
      searchTree.collectGarbage();
      System.out.println( "tree 1 size: " + searchTree.size() 
         + "  Hard size: " + searchTree.sizeHard() );
   }
   
}
