package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Node root = new Node("root");
        Node child1 = new Node("uniq");
        Node child2 = new Node("dupl");
        Node child3 = new Node("uniq 2");
        Node child4 = new Node("dupl");
        Node child11 = new Node("dupl");
        Node child44 = new Node("dupl");

        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);
        root.addChild(child4);
        child1.addChild(child11);
        child4.addChild(child44);

        System.out.println(root.findChildByName("dupl").getId());
    }
}