package com.liuyao.design_patterns.composite_flyweight;

import java.util.ArrayList;
import java.util.List;

public class MainComposite {

    /**
     * 组合模式
     *  树形 branch leaf
     *
     * 栈方式遍历二叉树？？
     *
     * @param args
     */
    public static void main(String[] args) {
        Branch root = new Branch("root");

        Branch b1 = new Branch("b1");
        Branch b2 = new Branch("b2");
        Branch b3 = new Branch("b3");

        Leaf f1 = new Leaf("f1");
        Leaf f2 = new Leaf("f2");
        Leaf f3 = new Leaf("f3");
        Leaf f4 = new Leaf("f4");

        root.add(b1);
        root.add(b2);

        b1.add(b3);
        b1.add(f1);

        b2.add(f2);
        b2.add(f4);

        b3.add(f3);

        printTree(root, 0);
    }

    private static void printTree(Node node, int i) {

        for (int j = 0; j < i; j++) {
            System.out.print("-");
        }
        node.print();

        if (node instanceof Branch) {
            for (Node n: ((Branch) node).nodes) {
                printTree(n, i+1);
            }
        }
    }
}

abstract class Node {
    abstract void print();
}

class Leaf extends Node {

    String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    void print() {
        System.out.println(this.name);
    }
}

class Branch extends Node {

    List<Node> nodes = new ArrayList<>();
    String name;

    public Branch(String name) {
        this.name = name;
    }

    public void add(Node n) {
        this.nodes.add(n);
    }

    @Override
    void print() {
        System.out.println(this.name);
    }
}
