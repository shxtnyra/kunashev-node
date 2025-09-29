package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Node {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    private final int id;
    private String name;
    private List<Node> children = new ArrayList<>();

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
        for (Node child : children) {
            if (child.getName().equals(name)) {
                return child;
            }
        }

        for (Node child : children) {
            Node found = child.findChildByName(name);

            if (found != null) {
                return found;
            }
        }

        return null;
    }

    public boolean removeChildByName(String name) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).getName().equals(name)) {
                Node toRemove = children.get(i);
                recursiveRemove(toRemove); // Удаляем всех потомков
                children.remove(i); // Удаляем сам узел из списка детей
                return true;
            }
        }

        for (Node child : children) {
            if (child.removeChildByName(name)) {
                return true;
            }
        }

        return false;
    }

    public boolean removeChildById(int id) {
        // Сначала ищем среди прямых детей
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).getId() == id) {
                Node toRemove = children.get(i);
                recursiveRemove(toRemove); // Удаляем всех потомков
                children.remove(i); // Удаляем сам узел из списка детей
                return true;
            }
        }

        for (Node child : children) {
            if (child.removeChildById(id)) {
                return true;
            }
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
