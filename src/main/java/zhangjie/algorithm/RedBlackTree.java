package zhangjie.algorithm;

/**
 * 红黑树(参照TreeMap实现)
 * 规则
 * 1. 根始终是黑色
 * 2. 没有red -red 的父子关系
 * 3. 根到叶子节点的黑色节点数相同
 * @Author zhangjie
 * @Date 2020/7/5 10:01
 **/
public class RedBlackTree<K,V> {

    private transient RedBlackTree.Entry<K,V> root;


    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    /**
     * @param key
     * @param value
     * @return 与{@code key}相关联的前一个值，如果没有{@code key}的映射，则为{@code null}
     */
    public V put(K key,V value){
        Entry<K,V> t = root;
        if (t == null) {
            root = new Entry<>(key, value, null);
            return null;
        }
        //待插入的父节点
        Entry<K,V> parent;
        int cmp;


        if (key == null){throw new NullPointerException();}
        Comparable<? super K> k = (Comparable<? super K>) key;

        //循环直到插入叶子节点。将其设置为待插入节点的父节点
        do{
            parent = t;
            cmp = k.compareTo(t.key);
            if(cmp <0){
                t = t.left;
            }else if(cmp > 0){
                t = t.right;
            }else{
                return t.setValue(value);
            }

        }while (t != null);

        Entry<K,V> e = new Entry<>(key, value, parent);
        if (cmp < 0){
            parent.left = e;
        }else {
            parent.right = e;
        }
        fixAfterInsertion(e);
        return null;
    }

    /**
     * 红 - 红时要旋转、变色
     * 三种情况
     * 1. 叔叔节点为红色
     *   --> 重新着色（黑色下沉）
     * 2. 叔叔节点为黑色或者nil
     *   2.1 待插入节点、父节点、祖父节点呈 三角形
     *     --> 以父节点向右旋转
     *   2.2 待插入节点、父节点、祖父节点呈 直线
     *     --> 以祖父节点向左旋转
     */
    private void fixAfterInsertion(Entry<K,V> x) {
        x.color = RED;
        while (x != null && x != root && x.parent.color == RED) {
            //父节点在其祖父节点的左边
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                //叔叔节点
                Entry<K,V> y = rightOf(parentOf(parentOf(x)));
                /**
                 * 祖父节点黑色下沉
                 *        B                            R
                 *      -  -     ---->              -    -
                 *    -     -                      -      -
                 *   R       R                    B        B
                 */
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    //将待修复节点变为其祖父节点，进行下一次轮询
                    x = parentOf(parentOf(x));
                }else{
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            }else{  //父节点在其祖父节点的右边

                Entry<K,V> y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                }else{
                    //三角形
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.color = BLACK;
    }


    /**
     * 1.首先根据规则，画出对应旋转图
     * 2. 对有变动的节点的属性（parent、left、right）做相应的变化
     * 向左旋转
     * @param p 待插入节点祖父节点
     */
    private void rotateLeft(Entry<K,V> p) {
        if (p != null) {
            //待插入节点的父节点
            Entry<K,V> r = p.right;
            p.right = r.left;
            if (r.left != null) {
                r.left.parent = p;
            }
            r.parent = p.parent;
            if (p.parent == null) {
                root = r;
            }else if (p.parent.left == p) {
                p.parent.left = r;
            }else {
                p.parent.right = r;
            }
            r.left = p;
            p.parent = r;
        }
    }

    /**
     * 向右旋转
     * @param p 待插入节点的父节点
     */
    private void rotateRight(Entry<K,V> p) {
        if (p != null) {
            Entry<K,V> l = p.left;
            p.left = l.right;
            if (l.right != null) {
                l.right.parent = p;
            }
            l.parent = p.parent;
            if (p.parent == null) {
                root = l;
            }else if (p.parent.right == p) {
                p.parent.right = l;
            }else{
                p.parent.left = l;
            }
            l.right = p;
            p.parent = l;
        }
    }

    private static <K,V> Entry<K,V> parentOf(Entry<K,V> p) {
        return (p == null ? null: p.parent);
    }

    private static <K,V> Entry<K,V> leftOf(Entry<K,V> p) {
        return (p == null) ? null: p.left;
    }

    private static <K,V> Entry<K,V> rightOf(Entry<K,V> p) {
        return (p == null) ? null: p.right;
    }

    private static <K,V> boolean colorOf(Entry<K,V> p) {
        return (p == null ? BLACK : p.color);
    }

    private static <K,V> void setColor(Entry<K,V> p, boolean c) {
        if (p != null){
            p.color = c;
        }
    }




    static final class Entry<K, V> {
        K key;
        V value;
        RedBlackTree.Entry<K,V> left;
        RedBlackTree.Entry<K,V> right;
        RedBlackTree.Entry<K,V> parent;
        boolean color = BLACK;

        Entry(K key, V value, Entry<K,V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * 用给定的值替换当前与键相关联的值
         * @param value
         * @return 返回key关联的旧值
         */
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
