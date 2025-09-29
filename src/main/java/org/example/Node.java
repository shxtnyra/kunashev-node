package org.example;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Node {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    private final int id;
    private String name;
    private final List<Node> children = new ArrayList<>();

    public Node(String name) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getId() {
        return id;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public Node findChildByName(String name) {
        Queue<Node> queue = new ArrayDeque<>(children);

        for (Node child : queue) {
            if (child.getName().equals(name)) {
                return child;
            }

            queue.addAll(child.getChildren());
        }

        return null;
    }

    public boolean removeChildByName(String name) {
        Queue<Node> queue = new ArrayDeque<>(children);

        for (Node child : queue) {
            if (child.getName().equals(name)) {
                recursiveRemove(child);
                children.remove(child);
                return true;
            }

            queue.addAll(child.getChildren());
        }

        return false;
    }

    public boolean removeChildById(int id) {
        Queue<Node> queue = new ArrayDeque<>(children);

        for (Node child : queue) {
            if (child.getId() == id) {
                recursiveRemove(child);
                children.remove(child);
                return true;
            }

            queue.addAll(child.getChildren());
        }

        return false;
    }


    public void removeAllChildren() {
        recursiveRemove(this);
    }

    private void recursiveRemove(Node node) {
        for (Node child : node.children) {
            recursiveRemove(child);
        }

        node.children.clear();
    }
}
