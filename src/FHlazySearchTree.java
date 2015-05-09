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
      mSize++;
      mSizeHard++;
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
         return new FHlazySTNode<E>(x, null, null, false);

      compareResult = x.compareTo(root.data); 
      if ( compareResult < 0 )
         root.lftChild = insert(root.lftChild, x);    
      else if ( compareResult > 0 )
         root.rtChild = insert( root.rtChild, x);
      else if ( compareResult == 0)
      {
         if(root.deleted == true)
         {
            root.deleted = false;
            mSizeHard--;
            return root;
           
         }
         mSize--;
         mSizeHard--;
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
      else if (root.deleted != true)
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
   
/////////////////////////////////////////////////////////////////////////// 
 

   protected FHlazySTNode<E> nodeReplacement(FHlazySTNode<E> root)
   {
     // int compareResult; // avoid multiple calls to compareTo()

      if (root == null)
      {
         return null;
      } 
      else if (root.rtChild != null && root.lftChild != null)
      {
         FHlazySTNode<E> tempNode;
         
         tempNode = findMin(root.rtChild);
         
         root.data = tempNode.data;
         if (tempNode.deleted == false)
         {
            tempNode.deleted = true;
            mSizeHard++;
         }       
         return root;
      } 
      else if (root.rtChild != null && root.lftChild == null)
      {
         root = root.rtChild;
         return root;
      } 
      else if (root.rtChild == null && root.lftChild != null)
      {
         root = root.lftChild;
         return root;
      } 
      else
         return root = null;
   }

//////////////////////////////////////////////////////////////////////
 
   protected FHlazySTNode<E> collectGarbage(FHlazySTNode<E> root)
   {
      if (root == null)
         return null;
      
      root = removeHard(root);
      root.lftChild = collectGarbage(removeHard(root.lftChild));
      root.rtChild = collectGarbage(removeHard(root.rtChild));
     
      return root;
   }
    
////////////////////////////////////////////////////////////////////////
   
   protected FHlazySTNode<E> removeHard (FHlazySTNode<E> root)
   {  
      if (root == null)
         return null; 
              
      if (root.lftChild != null && root.rtChild != null)
      {   
         if (root.deleted == true )
         {  
            
             root.data = nodeReplacement(root).data;
             mSizeHard--;
             root.deleted = false;
             return root;
         } 
       return root;           
      }
      else if(root.lftChild != null)     
      {   
         if (root.deleted == true)
         { 
            root.data = root.lftChild.data;
            root.deleted =  root.lftChild.deleted;
            root.lftChild.deleted = true;
            return root;          
         }
         else
            return root;
      }
      else if(root.rtChild != null)
      {    
         if (root.deleted == true)
         { 
            root.data = root.rtChild.data;
            root.deleted = root.rtChild.deleted;
            root.rtChild.deleted = true;
           
         return root;          
         }
         else
         return root;
      }
      else if (root.lftChild == null && root.rtChild == null)
      {  
         if (root.deleted == true)
         { 
            mSizeHard--;
            root = null;        
         }
         return root;
      }    
      return root;
   }
}

class FHlazySTNode<E extends Comparable< ? super E > >
{
   // use public access so the tree or other classes can access members 
   public FHlazySTNode<E> lftChild, rtChild;
   public E data;
   public FHlazySTNode<E> myRoot;  // needed to test for certain error
   public boolean deleted;

   public FHlazySTNode( E d, FHlazySTNode<E> lft, FHlazySTNode<E> rt, 
         boolean del )
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
