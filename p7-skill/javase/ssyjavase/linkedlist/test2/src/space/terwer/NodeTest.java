package space.terwer;

/**
 * @author terwer
 * @name NodeTest
 * @date 2022-12-30 22:11
 **/
public class NodeTest {
    public static void main(String[] args) {
        Node node1 = new Node();
        node1.data = "node1";
        Node node2 = new Node();
        node2.data = "node2";
        Node node3 = new Node();
        node3.data = "node3";

        node1.next = node2;
        node2.next = node3;

        System.out.println("node1=>" + node1);
        System.out.println("node2=>" + node2);
        System.out.println("node3=>" + node3);
        System.out.println("----------------------");

        System.out.println(node1.next.next.data);
        System.out.println("----------------------");

        // 在node1和node2之间插入node4
        Node node4 = new Node();
        node4.data = "node4";
        node4.next = node2;
        node1.next = node4;

        System.out.println(node1.next.next.next.data);
        System.out.println("----------------------");

        // 删除node4
        node1.next = node4.next;
        node4 = null;

        System.out.println(node1.next.next.data);
    }
}
