public class LinkedListNode {
    public int value;
    public LinkedListNode next;

    public LinkedListNode(int value) {
        this.value = value;
    }

    public static void main(String[] args) {
        LinkedListNode a = new LinkedListNode(1);
        LinkedListNode b = new LinkedListNode(2);
        LinkedListNode c = new LinkedListNode(3);

        a.next = b;
        b.next = c;

        deleteNode(b);
    }

    private static void deleteNode(LinkedListNode nodeToDelete) {
        LinkedListNode nextNode = nodeToDelete.next;

        if (nextNode != null) {

            // replace the input node's value and pointer with the next
            // node's value and pointer. the previous node now effectively
            // skips over the input node
            nodeToDelete.value = nextNode.value;
            nodeToDelete.next  = nextNode.next;

        } else {

            // eep, we're trying to delete the last node!
            throw new IllegalArgumentException("Can't delete the last node with this technique!");
        }

    }

    public static boolean containsCycle(LinkedListNode firstNode) {

        // start both runners at the beginning
        LinkedListNode slowRunner = firstNode;
        LinkedListNode fastRunner = firstNode;

        // until we hit the end of the list
        while (fastRunner != null && fastRunner.next != null) {
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;

            // case: fastRunner is about to "lap" slowRunner
            if (fastRunner == slowRunner) {
                return true;
            }
        }

        // case: fastRunner hit the end of the list
        return false;
    }

    public static LinkedListNode reverse(LinkedListNode headOfList) {
        LinkedListNode currentNode = headOfList;
        LinkedListNode previousNode = null;
        LinkedListNode nextNode = null;

        // until we have 'fallen off' the end of the list
        while (currentNode != null) {

            // copy a pointer to the next element
            // before we overwrite currentNode.next
            nextNode = currentNode.next;

            // reverse the 'next' pointer
            currentNode.next = previousNode;

            // step forward in the list
            previousNode = currentNode;
            currentNode = nextNode;
        }

        return previousNode;
    }

    public static LinkedListNode kthToLastNode(int k, LinkedListNode head) {

        if (k < 1) {
            throw new IllegalArgumentException(
                    "Impossible to find less than first to last node: " + k);
        }

        LinkedListNode leftNode  = head;
        LinkedListNode rightNode = head;

        // move rightNode to the kth node
        for (int i = 0; i < k - 1; i++) {

            // but along the way, if a rightNode doesn't have a next,
            // then k is greater than the length of the list and there
            // can't be a kth-to-last node! we'll raise an error
            if (rightNode.next == null) {
                throw new IllegalArgumentException(
                        "k is larger than the length of the linked list: " + k);
            }

            rightNode = rightNode.next;
        }

        // starting with leftNode on the head,
        // move leftNode and rightNode down the list,
        // maintaining a distance of k between them,
        // until rightNode hits the end of the list
        while (rightNode.next != null) {
            leftNode  = leftNode.next;
            rightNode = rightNode.next;
        }

        // since leftNode is k nodes behind rightNode,
        // leftNode is now the kth to last node!
        return leftNode;
    }
}


