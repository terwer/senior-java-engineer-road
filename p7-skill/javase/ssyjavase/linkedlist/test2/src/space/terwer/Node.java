package space.terwer;

/**
 * @author terwer
 * @name Node
 * @date 2022-12-30 22:08
 **/
public class Node {
    /**
     * 存放节点数据本身
     */
    String data;

    /**
     * 存放指向下一个节点的引用
     */
    Node next;

    @Override
    public String toString() {
        return "Node{" +
                "data='" + data + '\'' +
                ", next=" + next +
                '}';
    }
}
