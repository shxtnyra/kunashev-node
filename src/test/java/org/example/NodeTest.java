package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {

    private Node root;
    private Node child1, child2, child3, child4;
    private Node child11, child44;

    @BeforeEach
    public void setUp() {
        root = new Node("root");
        child1 = new Node("uniq");
        child2 = new Node("dupl");
        child3 = new Node("uniq 2");
        child4 = new Node("dupl");
        child11 = new Node("dupl");
        child44 = new Node("dupl");

        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);
        root.addChild(child4);
        child1.addChild(child11);
        child4.addChild(child44);
    }

    @Test
    public void createNode() {
        Node node = new Node("root");

        assertNotNull(node);
        assertEquals("root", node.getName());
        assertTrue(node.getChildren().isEmpty());
    }

    @Test
    public void setNameNode() {
        Node node = new Node("root");
        node.setName("newName");

        assertEquals("newName", node.getName());
    }

    @Test
    public void addChildren() {
        Node root = new Node("root");
        Node child1 = new Node("child1");
        Node child2 = new Node("child2");

        root.addChild(child1);
        root.addChild(child2);

        assertEquals(2, root.getChildren().size());
        assertTrue(root.getChildren().contains(child1));
        assertTrue(root.getChildren().contains(child2));
    }

    @Test
    public void findChildByName_ExistingChild() {
        // Должен найти первого ребенка с именем "dupl" (child2)
        Node found = root.findChildByName("dupl");

        assertNotNull(found);
        assertEquals("dupl", found.getName());
        assertEquals(child2, found);
    }

    @Test
    public void findChildByName_NonExisting() {
        Node found = root.findChildByName("NonExisting");

        assertNull(found);
    }

    @Test
    public void removeChildByName_ExistingChild() {
        // При удалении по имени должен удалить первого ребенка с таким именем
        boolean removed = root.removeChildByName("dupl");

        assertTrue(removed);
        assertEquals(3, root.getChildren().size());

        assertFalse(root.getChildren().contains(child2));
        assertTrue(root.getChildren().contains(child4));

        // Теперь найдёт другой
        assertNotEquals(child2, root.findChildByName("dupl"));
    }

    @Test
    public void removeChildByName_NonExisting() {
        boolean removed = root.removeChildByName("NonExisting");

        assertFalse(removed);
        assertEquals(4, root.getChildren().size());
    }

    @Test
    public void removeChildByName_MultipleCalls() {
        // Первое удаление должно удалить child2
        boolean firstRemoval = root.removeChildByName("dupl");
        assertTrue(firstRemoval);
        assertEquals(3, root.getChildren().size());
        assertFalse(root.getChildren().contains(child2));

        // Второе удаление должно удалить child4
        boolean secondRemoval = root.removeChildByName("dupl");
        assertTrue(secondRemoval);
        assertEquals(2, root.getChildren().size());
        assertFalse(root.getChildren().contains(child4));
    }

    @Test
    public void removeChildById_ExistingChild() {
        boolean removed = root.removeChildById(child2.getId());

        assertTrue(removed);
        assertEquals(3, root.getChildren().size());
        assertFalse(root.getChildren().contains(child2));
        assertTrue(root.getChildren().contains(child4));
    }

    @Test
    public void removeChildById_NonExisting() {
        boolean removed = root.removeChildById(-1);

        assertFalse(removed);
        assertEquals(4, root.getChildren().size());
    }

    @Test
    public void removeAllChildren() {
        root.removeAllChildren();

        assertTrue( child1.getChildren().isEmpty());
        assertTrue( child4.getChildren().isEmpty());
        assertTrue( root.getChildren().isEmpty());
    }

    @Test
    public void nodeIdUniqueness() {
        Node node1 = new Node("node1");
        Node node2 = new Node("node2");
        Node node3 = new Node("node3");

        assertNotEquals(node1.getId(), node2.getId());
        assertNotEquals(node1.getId(), node3.getId());
        assertNotEquals(node2.getId(), node3.getId());
    }
}