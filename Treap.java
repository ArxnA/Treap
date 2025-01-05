public class Treap {
    static TNode max,min ,root;
    public static void main(String[] args) {
        int sumHeight = 0, avgHeight;

        long startTime = System.nanoTime();

        for (int i = 0; i < 1; i++) {
            root = null;
            root = generateTreap(root);
            System.out.println(TNode.getHeight(root ));
            sumHeight += TNode.getHeight(root )- 1;
        }
        avgHeight = sumHeight / 1000;

        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime) / 1_000_000;

        System.out.println("Execution time: " + elapsedTime + " ms");
        System.out.println("Average height: " + avgHeight + " and log n which n is number of nodes (100000) is " + Math.log(100000)/Math.log(2));
    }

    public static TNode generateTreap(TNode root){
        for (int i = 0; i < 10; i++) {
            root=TNode.insert(root,i);
        }
//        System.out.println(TNode.getHeight(root));+
//        System.out.println(TNode.getHeight(root.left));
//        System.out.println(TNode.getChildes(root));
//        System.out.println(TNode.getChildes(root.left));
//        System.out.println(TNode.find(root,3).predecessor.successor.successor.key);
//        root=TNode.delete(2,root);
//        root=TNode.delete(4,root);
//        root=TNode.delete(0,root);
//        root=TNode.delete(9,root);
//        root=TNode.delete(8,root);
//        root=TNode.insert(root,2);
//        root=TNode.insert(root,1);
//        root=TNode.insert(root,1);
//        root=TNode.insert(root,2);
//        root=TNode.insert(root,2);
//        root=TNode.delete(1211,root);
//        root=TNode.insert(root,10001);
//
//        TNode kth=TNode.kthSmallest(11000090,root);
//        if (kth!=null){
//            System.out.println(kth.key);
//        }else {
//            System.out.println("Not Found !");
//        }
//        System.out.println(root.key);
//        System.out.println(root.childes +" "+root.left.childes+" "+root.right.childes);
//        System.out.println();
//        System.out.println(TNode.getChildes(root) +" "+TNode.getChildes(root.left)+" "+TNode.getChildes(root.right));
//        System.out.println(min.key+" "+max.key);
//        TNode.testSuccPreMinMax(max);
//        TNode.inOrder(root);
//        TNode.testSuccPreMinMax(min);
        TNode.display(root);
        return root;
    }

}