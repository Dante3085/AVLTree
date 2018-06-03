# AVLTree
AVLTree implementation in Java

Motivation: 
<br>A normal BinarySearchTree intrinsically organizes it's Nodes in terms of their size. When adding a new Node, first it's data is compared to the root Node's data. If the new Node's data is smaller than root Node's data, the new Node goes to the left. For the opposite outcome of that comparison it goes to the right (What exactly is meant by "smaller" and "bigger" has to be a decision made by each Object type. For that reason those Objects have to be "comparable"). This is done until an empty spot for the new Node is found.
<br>The advantage of this structure is, that searching, adding and deleting (also other operations) can be done significantly faster in comparison to a linearly structured List. In a BinarySearchTree I already know where I have to go. In a List, worst case is going through all Elements before finding what I am looking for.
<br>
<br>Now, for example if you were to add the numbers "1, 2, 3, 4, 5, 6" sequentially in an empty BinarySearchTree, what we would end up with is a linear List again. To avoid that AVLTrees are used. AVLTrees are self-balancing BinarySearchTrees. Self-balancing means that the height difference of every Subtree is at most "one". To achieve this, after every "insertion" and "deletion" of a Node, the balance of the tree is checked. If the tree needs to be rebalanced, Nodes are rotated to reduce the tree's / subtree's height to fulfill the before mentioned criterium.
