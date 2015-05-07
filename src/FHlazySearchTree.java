import cs_1c.*;

import java.util.*;

import cs_1c.Traverser;

public class FHlazySearchTree<E extends Comparable< ? super E > >
   implements Cloneable
{
   protected int mSize;
   protected int mSizeHard;
   protected FHlazySTNode<E> mRoot;
   
   
   public FHlazySearchTree() { clear(); }
   public boolean empty() { return (mSize == 0); }
   public int size() { return mSize; }
   public void clear() { mSize = 0; mRoot = null; }
   public int showHeight() { return findHeight(mRoot, -1); }
   
   public E findMin() 
   {
      if (mRoot == null)
         throw new NoSuchElementException();
      return findMin(mRoot).data;
   }
   
   public E findMax() 
   {
      if (mRoot == null)
         throw new NoSuchElementException();
      return findMax(mRoot).data;
   }
   
   public E find( E x )
   {
      FHlazySTNode<E> resultNode;
      resultNode = find(mRoot, x);
      if (resultNode == null)
         throw new NoSuchElementException();
      return resultNode.data;
   }
   public boolean contains(E x)  { return find(mRoot, x) != null; }
   
   public int sizeHard() 
   {
      return mSizeHard;
   }
   
   public boolean insert( E x )
   {
      int oldSize = mSize;
      mRoot = insert(mRoot, x);
      return (mSize != oldSize);
   }
   
   public boolean remove( E x )
   {
      int oldSize = mSize;
      remove(mRoot, x);
      return (mSize != oldSize);
   }
   
   public boolean collectGarbage(  )
   {
      int oldSize = mSizeHard;
      collectGarbage(mRoot);
      return (mSizeHard != oldSize);
   }
   
   public < F extends Traverser<? super E > > 
   void traverse(F func)
   {
      traverse(func, mRoot);
   }
   
   public Object clone() throws CloneNotSupportedException
   {
      FHlazySearchTree<E> newObject = (FHlazySearchTree<E>)super.clone();
      newObject.clear();  // can't point to other's data

      newObject.mRoot = cloneSubtree(mRoot);
      newObject.mSize = mSize;
      
      return newObject;
   }
   
   // private helper methods ----------------------------------------
   protected FHlazySTNode<E> findMin( FHlazySTNode<E> rooty ) 
   {    
      if (rooty.lftChild != null)
      {
         rooty = findMin(rooty.lftChild);        
      }
      else if (rooty.rtChild != null && rooty.lftChild == null)
      {
         rooty = findMin(rooty.rtChild);  
      }

      return rooty;
   }
   

   protected FHlazySTNode<E> findMax( FHlazySTNode<E> root ) 
   {
      if (root == null)
         return null;
      if (root.rtChild == null)
         return root;
      return findMax(root.rtChild);
   }
   
   protected FHlazySTNode<E> insert( FHlazySTNode<E> root, E x )
   {
      int compareResult;  // avoid multiple calls to compareTo()
      
      if (root == null)
      {
         mSize++;
         mSizeHard++;
         return new FHlazySTNode<E>(x, null, null, false);
      }
      
      compareResult = x.compareTo(root.data); 
      if ( compareResult < 0 && root.deleted != true)
         root.lftChild = insert(root.lftChild, x);
      else if ( compareResult > 0 && root.deleted != true)
         root.rtChild = insert( root.rtChild, x);
     
      else if (compareResult == 0 &&  root.deleted == true)
      {
         root.deleted = false;
         mSize++;
         mSizeHard++;
         return root;
      }

      return root;
   }

   protected FHlazySTNode<E> remove( FHlazySTNode<E> root, E x  )
   {
     
      int compareResult;  // avoid multiple calls to compareTo()
     
      if (root == null)
         return root;

      compareResult = x.compareTo(root.data); 
      if ( compareResult < 0)
         root.lftChild = remove(root.lftChild, x);
      else if ( compareResult > 0)
         root.rtChild = remove(root.rtChild, x);
      else
      {
        root.deleted = true;
      
         mSize--;
      }
         return root;
      
      
   }
   
   protected <F extends Traverser<? super E>> 
   void traverse(F func, FHlazySTNode<E> treeNode)
   {
      if (treeNode == null)
         return;

      traverse(func, treeNode.lftChild);
      if (treeNode.deleted != true) {func.visit(treeNode.data);};
      traverse(func, treeNode.rtChild);
   }
   
   protected FHlazySTNode<E> find( FHlazySTNode<E> root, E x )
   {
      int compareResult;  // avoid multiple calls to compareTo()

      if (root == null)
         return null;

      compareResult = x.compareTo(root.data); 
      if (compareResult < 0 && root.deleted != true)
         return find(root.lftChild, x);
      if (compareResult > 0 && root.deleted != true)
         return find(root.rtChild, x);
      return root;   // found
   }
   
   protected FHlazySTNode<E> cloneSubtree(FHlazySTNode<E> root)
   {
      FHlazySTNode<E> newNode;
      if (root == null)
         return null;

      // does not set myRoot which must be done by caller
      newNode = new FHlazySTNode<E>
      (
         root.data, 
         cloneSubtree(root.lftChild), 
         cloneSubtree(root.rtChild),
         root.deleted
         
      );
      return newNode;
   }
   
   protected int findHeight( FHlazySTNode<E> treeNode, int height ) 
   {
      int leftHeight, rightHeight;
      if (treeNode == null)
         return height;
      height++;
      leftHeight = findHeight(treeNode.lftChild, height);
      rightHeight = findHeight(treeNode.rtChild, height);
      return (leftHeight > rightHeight)? leftHeight : rightHeight;
   }
   
 
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
  
   boolean isItALeftChild = false;

   protected FHlazySTNode<E> findMinRemove(FHlazySTNode<E> parentNode, FHlazySTNode<E> root, E x)
   {
 
      int compareResult; // avoid multiple calls to compareTo()

      if (root == null)
         return null;

      compareResult = x.compareTo(root.data);

      if (compareResult < 0)
      {
         isItALeftChild = false;
         parentNode = root;
         root = root.rtChild;
         return findMinRemove(root, root.rtChild, x);
      }
      if (compareResult > 0)
      {
         isItALeftChild = true;
         parentNode = root;
         root = root.lftChild;
         return findMinRemove(root, root.rtChild, x);
      }
         if (isItALeftChild)
            parentNode.lftChild = null;
         if (!isItALeftChild)
            parentNode.rtChild = null;
     
      
      return root; // found
   }

//      if (rooty.rtChild != null && rooty.lftChild !=null)
//      { 
//         if (rooty.rtChild.data.equals(goal))
//         {
//            rooty.rtChild = null;       
//         } 
//         rooty = findMinRemove(rooty.rtChild, goal);
//      }
//      else if (rooty.rtChild != null && rooty.lftChild ==null)
//      { 
//         if (rooty.rtChild.data.equals(goal))
//         {
//            rooty.rtChild = null;       
//         } 
//         rooty = findMinRemove(rooty.rtChild, goal);
//      }
//      else if (rooty.rtChild == null && rooty.lftChild != null)
//      {      
//         if (rooty.lftChild.data.equals(goal))
//         {
//            rooty.lftChild = null;       
//         }
//         rooty = findMinRemove(rooty.lftChild, goal);
//      }
//      return rooty;
//   
 
   protected void collectGarbage(FHlazySTNode<E> root)
   {
      if (root == null)
         return;
      if (root.deleted == true)
      {
         removeHard(root);
         collectGarbage(root);
      }
      else if (root.lftChild != null && root.rtChild != null)
      {
         if (root.lftChild.deleted == true)
         { System.out.println("OOONNEEE");
            collectGarbage(removeHard(root.lftChild));
         }
      
         else if (root.rtChild.deleted == true)
         { System.out.println("TWWWOOO");
               collectGarbage(removeHard(root.rtChild));
          }
          
      }
      else if (root.lftChild != null && root.rtChild == null)
      {
         if (root.lftChild.deleted == true)
         {System.out.println("THREEE");
             collectGarbage(removeHard(root.lftChild));
          }
      }
      else if (root.rtChild != null && root.lftChild == null)
      {
         if (root.rtChild.deleted == true)
         { System.out.println("FOOOUUURRR");
            collectGarbage(removeHard(root.rtChild));
         }
      }
      else if (root.rtChild == null && root.lftChild == null)
      {
//         if (root.deleted == true)
//            root.lftChild = null;
//         if (root.lftChild.deleted == true)
//            root.rtChild = null;
         
         System.out.println("FIIIIVVVEE");
      }

      return;
   }
   
   protected FHlazySTNode<E> removeHard (FHlazySTNode<E> root)
   {  // found the node
      if (root == null)
         return root; 
               
     if (root.lftChild != null && root.rtChild != null)
      {  
         root = findMin(root.rtChild);
         root.deleted = false;
         mSizeHard--;
         findMinRemove(root, root.rtChild, root.data); 
         collectGarbage(removeHard(root));         
      }
      else if(root.lftChild != null)
      {     
          E tempData = root.lftChild.data;
          FHlazySTNode<E> tempRnode  = root.lftChild.rtChild;
          FHlazySTNode<E> tempLnode = root.lftChild.lftChild;
          root.lftChild = tempLnode ;
          root.rtChild = tempRnode;
          root.data = tempData;
          root.deleted = false;
          System.out.println("HEELLO LEFT");
          mSizeHard--;
          collectGarbage(removeHard(root));
       }
       else if(root.rtChild != null)
       {
          E tempData = root.rtChild.data;
          FHlazySTNode<E> tempRnode  = root.rtChild.rtChild;
          FHlazySTNode<E> tempLnode = root.rtChild.lftChild;
          root.lftChild = tempLnode ;
          root.rtChild = tempRnode;
          root.data = tempData;
          root.deleted = false;
          System.out.println("HEELLO RIGHT");
          mSizeHard--;
          collectGarbage(removeHard(root));

       } else {
//         
//         root =
//            (root.lftChild != null)? root.lftChild : root.rtChild;
//         
//         mSizeHard--;
         //collectGarbage(root);
      }
      
      return root;
   }
   public void printPreOrder(FHlazySTNode<E> root) 
   {
      printPreOrder(root);
      System.out.println("");      
   }
   private void printPreOrderRec(FHlazySTNode<E> currRoot)
   {
      if (currRoot == null)
         return;
      
      System.out.print(currRoot.data + ", ");
      printPreOrderRec(currRoot.lftChild);
      printPreOrderRec(currRoot.rtChild);
      
               
   }
}

class FHlazySTNode<E extends Comparable< ? super E > >
{
   // use public access so the tree or other classes can access members 
   public FHlazySTNode<E> lftChild, rtChild;
   public E data;
   public FHlazySTNode<E> myRoot;  // needed to test for certain error
   public boolean deleted;

   public FHlazySTNode( E d, FHlazySTNode<E> lft, FHlazySTNode<E> rt, boolean del )
   {
      lftChild = lft; 
      rtChild = rt;
      data = d;
      deleted = del;
   }
   
   public FHlazySTNode()
   {
      this(null, null, null, false);
   }
   
   // function stubs -- for use only with AVL Trees when we extend
   public int getHeight() { return 0; }
   boolean setHeight(int height) { return true; }
}
