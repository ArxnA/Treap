import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TreapVisualizer extends JFrame {
    private TNode root;
    private static final int NODE_SIZE = 50;
    private static final int LEVEL_HEIGHT = 100;
    private static final int HORIZONTAL_GAP = 50;

    private JPanel controlPanel;
    private JTextField keyField;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton displayButton;
    private JTextArea messageArea;
    private JPanel drawingPanel;

    public TreapVisualizer(TNode root) {
        this.root = root;
        setTitle("Treap Visualization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeComponents();
        pack();
    }

    private void initializeComponents() {
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JLabel keyLabel = new JLabel("Key:");
        keyField = new JTextField(10);
        insertButton = new JButton("Insert");
        deleteButton = new JButton("Delete");
        displayButton = new JButton("Display");

        controlPanel.add(keyLabel);
        controlPanel.add(keyField);
        controlPanel.add(insertButton);
        controlPanel.add(deleteButton);
        controlPanel.add(displayButton);

        messageArea = new JTextArea(20, 50);
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);

        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTree(g, root, getWidth() / 2, 50, getWidth() / 4);
            }
        };
        drawingPanel.setPreferredSize(new Dimension(1000, 800));

        JScrollPane drawingScrollPane = new JScrollPane(drawingPanel);
        drawingScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        drawingScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(controlPanel, BorderLayout.NORTH);
        add(drawingScrollPane, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int key = Integer.parseInt(keyField.getText());
                    root = TNode.insert(root, key);
                    messageArea.setText("Node with key " + key + " inserted.");
                    updatePanelSize();
                    drawingPanel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TreapVisualizer.this, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int key = Integer.parseInt(keyField.getText());
                    root = TNode.delete(key, root);
                    messageArea.setText("Node with key " + key + " deleted.");
                    updatePanelSize();
                    drawingPanel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TreapVisualizer.this, "Invalid input. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageArea.setText("Treap Visualization:\n");
                drawingPanel.repaint();
            }
        });
    }

    private void drawTree(Graphics g, TNode node, int x, int y, int xOffset) {
        if (node == null) {
            return;
        }

        g.setColor(Color.BLACK);
        g.fillOval(x - NODE_SIZE / 2, y - NODE_SIZE / 2, NODE_SIZE, NODE_SIZE);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(node.key), x - 10, y - 5);
        g.drawString(String.valueOf(node.priority), x - 10, y + 15);

        if (node.left != null) {
            int xLeft = x - xOffset;
            int yLeft = y + LEVEL_HEIGHT;
            g.setColor(Color.BLACK);
            g.drawLine(x, y, xLeft, yLeft);
            drawTree(g, node.left, xLeft, yLeft, xOffset / 2);
        }

        if (node.right != null) {
            int xRight = x + xOffset;
            int yRight = y + LEVEL_HEIGHT;
            g.setColor(Color.BLACK);
            g.drawLine(x, y, xRight, yRight);
            drawTree(g, node.right, xRight, yRight, xOffset / 2);
        }
    }

    private void updatePanelSize() {
        int treeWidth = calculateTreeWidth(root);
        int treeHeight = calculateTreeHeight(root);
        drawingPanel.setPreferredSize(new Dimension(treeWidth * (NODE_SIZE + HORIZONTAL_GAP), treeHeight * LEVEL_HEIGHT + 50));
        revalidate();
    }

    private int calculateTreeWidth(TNode node) {
        if (node == null) {
            return 0;
        }
        int leftWidth = calculateTreeWidth(node.left);
        int rightWidth = calculateTreeWidth(node.right);
        return Math.max(1, leftWidth + rightWidth + 1);
    }

    private int calculateTreeHeight(TNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(calculateTreeHeight(node.left), calculateTreeHeight(node.right)) + 1;
    }

}
