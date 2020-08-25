package zhangjie.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * B+树
 * 1. m-way st  --> 节点有m-1个key
 *   1.1 m：表示有m个孩子节点
 * 2. 创建过程自下而上
 * @Author zhangjie
 * @Date 2020/6/30 11:11
 **/
public class BPlusTree<K extends Comparable<? super K>, V> {
    /**
     * 分支个数
     * 例如 branchingFactor =5，则节点的包含的key为branchingFactor-1（4）
     */
    private int branchingFactor;

    private Node root;

    public BPlusTree(int branchingFactor) {
        //root节点至少2个子节点
        if (branchingFactor <= 2){
            throw new IllegalArgumentException("Illegal branching factor: "
                    + branchingFactor);
        }
        this.branchingFactor = branchingFactor;
        this.root = new LeafNode();
    }

    public void insert(K key, V value) {
        root.insertValue(key, value);
    }

    //叶子节点
    private class LeafNode extends Node {
        List<V> values;
        LeafNode next;

        LeafNode() {
            keys = new ArrayList<K>();
            values = new ArrayList<V>();
        }

        @Override
        void insertValue(K key, V value) {
            //二分法搜索key的位置
            int loc = Collections.binarySearch(keys, key);
            //key在集合keys的位置，如果不存在返回应该插入的位置+1
            int valueIndex = loc >= 0 ? loc : -loc - 1;
            if (loc >= 0) {
                values.set(valueIndex, value);
            } else {//集合Keys中不存在，在指定位置插入
                keys.add(valueIndex, key);
                values.add(valueIndex, value);
            }
            /**
             * 节点中的数据不能超过m-1
             *  -->如果超过了，就要开始分割
             *  新建内部节点，children属性复制被分割的节点
             *      sibling.getFirstLeafKey()                                  [2]
             *      -                    -                                   -     -
             *     -                      -                                 -       -
             *   this                      sibling                        [0,1]     [2,3]
             */
            if (root.isOverflow()) {
                Node sibling = split();
                InternalNode newRoot = new InternalNode();
                newRoot.keys.add(sibling.getFirstLeafKey());
                newRoot.children.add(this);
                newRoot.children.add(sibling);
                root = newRoot;
            }
        }

        @Override
        boolean isOverflow() {
            return values.size() > branchingFactor - 1;
        }

        /**
         * 从中间开始截取直到末端
         * [0,1,2,3]
         *   [0,1] -> [2.3](next属性进行链接)
         * @return
         */
        @Override
        Node split() {
            LeafNode sibling = new LeafNode();
            int from = (keyNumber() + 1) / 2, to = keyNumber();
            sibling.keys.addAll(keys.subList(from, to));
            sibling.values.addAll(values.subList(from, to));

            keys.subList(from, to).clear();
            values.subList(from, to).clear();

            sibling.next = next;
            next = sibling;
            return sibling;
        }

        @Override
        K getFirstLeafKey() {
            return keys.get(0);
        }
    }

    private class InternalNode extends Node {

        List<Node> children;

        InternalNode() {
            this.keys = new ArrayList<K>();
            this.children = new ArrayList<Node>();
        }

        @Override
        void insertValue(K key, V value) {
            Node child = getChild(key);
            child.insertValue(key, value);
            if (child.isOverflow()) {
                Node sibling = child.split();
                insertChild(sibling.getFirstLeafKey(), sibling);
            }
            if (root.isOverflow()) {
                /**
                 *             [6]
                 *           -    -
                 *          -     -
                 *      [2,4]       [8]
                      ....          .....
                 */
                Node sibling = split();
                InternalNode newRoot = new InternalNode();
                newRoot.keys.add(sibling.getFirstLeafKey());
                newRoot.children.add(this);
                newRoot.children.add(sibling);
                root = newRoot;
            }
        }

        //根据key在父节点的位置，定位子节点
        Node getChild(K key) {
            int loc = Collections.binarySearch(keys, key);
            int childIndex = loc >= 0 ? loc + 1 : -loc - 1;
            return children.get(childIndex);
        }

        /**
         * 内部节点插入子节点
         *         2                                 (2, 3  )
         *       -   -             ====>            -   -    -
         *      -     -                           -      -     -
         *  (0,1)     (2,3,4,5)                (0,1)    (2,3)  (4,5)
         * @param key
         * @param child
         */
        void insertChild(K key, Node child) {
            int loc = Collections.binarySearch(keys, key);
            int childIndex = loc >= 0 ? loc + 1 : -loc - 1;
            if (loc >= 0) {
                children.set(childIndex, child);
            } else {
                keys.add(childIndex, key);
                children.add(childIndex + 1, child);
            }
        }

        @Override
        boolean isOverflow() {
            return children.size() > branchingFactor;
        }

        /**
         * 内部节点切分
         *        [2,     4,     6,    8]
         *      -    -      -      -     -
         *     -      -      -      -     -
           [0,1]     [2,3]   [4,5]  [6,7]  [8,9]

                  [8]                       [2,   4 ]
                 -  -                      -   -    -
                -    -                    -     -    -
            [6,7]    [8,9]           [0,1]     [2,3]  [4,5]
         * @return
         */
        @Override
        Node split() {
            int from = keyNumber() / 2 + 1, to = keyNumber();
            InternalNode sibling = new InternalNode();
            sibling.keys.addAll(keys.subList(from, to));
            sibling.children.addAll(children.subList(from, to + 1));

            keys.subList(from - 1, to).clear();
            children.subList(from, to + 1).clear();
            return sibling;
        }

        @Override
        K getFirstLeafKey() {
            return children.get(0).getFirstLeafKey();
        }
    }

    private abstract class Node {
        List<K> keys;

        int keyNumber() {
            return keys.size();
        }

        abstract void insertValue(K key, V value);

        abstract boolean isOverflow();

        abstract Node split();

        abstract K getFirstLeafKey();
    }
}
