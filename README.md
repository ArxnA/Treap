
# Treap Implementation with Java GUI

This project implements a **Treap** (a randomized binary search tree) with a Java-based graphical user interface (GUI) for visualization. The GUI allows you to insert and delete nodes, as well as view the structure of the Treap.

## Project Structure

- **TNode.java**: Defines the Treap node structure and contains methods for insertion, deletion, rotations, and other operations such as finding the k-th smallest element.
- **Treap.java**: Implements the main Treap structure, including the entry point for running the project and generating the Treap. The `main()` method can be used to generate a Treap and test its height.
- **TreapVisualizer.java**: Provides the GUI to visualize the Treap structure, allowing the user to insert, delete, and view the tree in real-time.

## Features

### Treap Operations:
1. **Insert**: Adds a new node with a given key. The priority of each node is generated randomly.
2. **Delete**: Removes a node with the specified key, maintaining the Treap's heap property.
3. **Rotations**: Supports both left and right rotations to balance the tree based on priority.
4. **In-order Traversal**: Prints the keys in the Treap in ascending order.
5. **Find K-th Smallest**: Retrieves the k-th smallest node in the Treap.

### Visualization Features:
- **Insert Node**: Adds a new node to the Treap and updates the graphical display.
- **Delete Node**: Removes an existing node and updates the Treap visualization.
- **Display**: Shows the current structure of the Treap in the form of a tree, with keys and priorities.

## GUI Usage

- **Insert**: Enter a key in the text field and click "Insert" to add a new node.
- **Delete**: Enter a key in the text field and click "Delete" to remove a node.
- **Display**: Click the "Display" button to refresh and view the current state of the Treap.

## Code Overview

### TNode.java

- Defines the structure of a Treap node.
- Contains methods like `insert`, `delete`, `leftRotate`, `rightRotate`, and utility methods such as `getHeight` and `getChildes`.

### Treap.java

- Manages the Treap operations and handles interactions with the GUI.
- Contains the `generateTreap()` function for generating a test Treap.
- Implements the main logic for `insert`, `delete`, and tree balancing.

### TreapVisualizer.java

- A Swing-based Java GUI for visualizing the Treap structure.
- Uses `paintComponent()` to draw the tree and represent nodes as circles with their keys and priorities.
- Allows user interaction through buttons for inserting and deleting nodes.

## Performance Analysis

The Treap maintains an average height of O(log n) for n nodes, ensuring efficient search, insertion, and deletion operations. This is validated by the output of average heights over multiple Treap generations, as shown in the console during execution.


