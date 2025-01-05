
public class TNode {
    int key;
    int priority;
    int childes=0;
    int height=1;
    TNode right,left;
    TNode predecessor,successor;


    public TNode(int key) {
        this.key = key;
        this.priority =RandomGenerator.generateRandom();
        //this.priority=RandomGenerator.randomGenerator();
    }

    public static int getHeight(TNode node){
        if(node==null){
            return 0;
        }
        else {
            return node.height;
        }
    }
    public static int getChildes(TNode node){
        if(node==null){
            return 0;
        }
        else {
            return node.childes;
        }
    }

    public static void hightNumOfchilde(TNode root){
        int rootLC,rootRC;
        int rootLH,rootRH;
        if (root.left==null){
            rootLC=0;
            rootLH=0;
        }
        else {
            rootLC=getChildes(root.left)+1;
            rootLH=getHeight(root.left);
        }
        if (root.right==null){
            rootRC=0;
            rootRH=0;
        }else {
            rootRC=getChildes(root.right)+1;
            rootRH=getHeight(root.right);
        }
        root.childes =  rootLC+ rootRC;

        root.height=Math.max(rootLH,rootRH)+1;
    }

    public static TNode find(TNode root, int key){

        if (root == null || root.key == key)
            return root;
        TNode result;
        if (root.key < key) {
            result=find(root.right, key);
            return result;
        }
        result=find(root.left, key);
        return result;
    }

    public static TNode leftRotate(TNode root){
        // have child right
        TNode newRoot ;
        if(root==null){
            return null;
        }if (root.right==null){
            return null;
        }
        newRoot =root.right;
        root.right=newRoot.left;
        newRoot.left=root;

        hightNumOfchilde(root);
        hightNumOfchilde(newRoot);
         return newRoot;

    }

    public static TNode rightRotate(TNode root){
        //have left child
        TNode newRoot ;
        if(root==null){
            return null;
        }
        if (root.left==null){
            return null;
        }

        newRoot=root.left;
        root.left=newRoot.right;
        newRoot.right=root;

        hightNumOfchilde(root);
        hightNumOfchilde(newRoot);

        return newRoot;
    }

    public static TNode insert(TNode root,int key){
        root= insert(root,key,null,null);
        return root;
    }

    private static TNode insert(TNode root,int key,TNode predecessor,TNode successor){

        if (root==null){
            TNode newNode= new TNode(key);
            if (Treap.max==null){
                Treap.max=newNode;
            }if (Treap.min==null){
                Treap.min=newNode;
            }
            if (key>Treap.max.key){
                Treap.max=newNode;
            } else if (key<Treap.min.key) {
                Treap.min=newNode;
            }
            if (predecessor!=null){
                predecessor.successor=newNode;
               newNode.predecessor=predecessor;
            }
            if (successor!=null){
                successor.predecessor=newNode;
                newNode.successor=successor;
            }
            return newNode;
        }
        if (key<root.key){
            successor=root;
            root.left=insert(root.left,key,predecessor,successor);
            if (root.left.priority>root.priority){
                root=rightRotate(root);
            }
        }
        else {
            predecessor=root;
            root.right=insert(root.right,key,predecessor,successor);
            if (root.right.priority> root.priority){
                root=leftRotate(root);
            }
        }hightNumOfchilde(root);
       return root;
    }

    private static TNode delete(TNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.key) {
            root.left = delete(root.left, key);
        } else if (key > root.key) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null) {
                if (root.predecessor!=null){
                    root.predecessor.successor=root.successor;
                }
                if (root.successor!=null){
                    root.successor.predecessor=root.predecessor;
                }
                return root.right;
            } else if (root.right == null) {
                if (root.predecessor!=null){
                    root.predecessor.successor=root.successor;
                }
                if (root.successor!=null){
                    root.successor.predecessor=root.predecessor;
                }
                return root.left;
            }
            if (root.left.priority > root.right.priority) {
                root = rightRotate(root);
                root.right = delete(root.right, key);
            } else {
                root = leftRotate(root);
                root.left = delete(root.left, key);
            }
        }

        hightNumOfchilde(root);
        return root;
    }

    public static TNode delete(int key,TNode root) {
        if (root!=null){
            if (key==Treap.max.key){
                Treap.max=Treap.max.predecessor;
            } else if (key==Treap.min.key) {
                Treap.min=Treap.min.successor;
            }
            root = delete(root, key);
        }

        return root;
    }

    public static TNode kthSmallest(int k,TNode root){
        if (root==null){
            return null;
        }
        int rootLC;
        if (root.left==null){
            rootLC=0;
        }else {
            rootLC=getChildes(root.left)+1;
        }
        if (rootLC >= k) {
            return kthSmallest(k, root.left);
        } else if (rootLC + 1 == k) {
            return root;
        } else {
            return kthSmallest(k - (rootLC+1), root.right);
        }
    }

    public static void inOrder(TNode root){
        if (root==null){
            return;
        }
        inOrder(root.left);
        System.out.println(root.key+"  ");
        inOrder(root.right);

    }
    public static void testSuccPreMinMax(TNode node){
        if (node!=null){
            TNode temp=node;
            if (temp.successor==null){//max
                while (temp!=null){
                    System.out.println(temp.key);
                    temp=temp.predecessor;
                }
            }
            else if (temp.predecessor==null){//min
                while (temp!=null){
                    System.out.println(temp.key);
                    temp=temp.successor;
                }
            }
        }

    }
    public static void display(TNode root) {
        TreapVisualizer visualizer = new TreapVisualizer(root);
        visualizer.setVisible(true);
    }


}
