package spell;

public class Trie implements ITrie{

    public Node root = new Node();
    private StringBuilder sb1;
    private StringBuilder sb2;
    private int nodeCount = 1;
    private int wordCount = 0;


    @Override
    public void add(String word) {
        addHelper(root, word);



    }
    private void addHelper(Node parent, String word) {
        if (word.length() == 0) {
            parent.count += 1;
            if (parent.count == 1) {
                wordCount++;
            }
        } else {

                int tmp = word.charAt(0) - 97;
                if (parent.children[tmp] == null) {
                    parent.children[tmp] = new Node();
                    nodeCount++;
                }
                if (word.length() == 1) {
                    word = "";
                } else {
                    word = word.substring(1);
                }
                addHelper(parent.children[tmp], word);
        }
    }

    @Override
    public INode find(String word) {
        Node parent = root;
        for (int i = 0; i < word.length(); i++) {
            int tmp = word.charAt(i) - 97;
            if (parent.children[tmp] == null) {
                return null;
            } else {
                parent = parent.children[tmp];
            }
        }
        if(parent.count == 0) {
            return null;
        } else {
            return parent;
        }

    }

    @Override
    public int getWordCount() {
        return wordCount;
    }

    @Override
    public int getNodeCount() {
        return nodeCount;
    }

    @Override
    public String toString() {
        sb1 = new StringBuilder();
        sb2 = new StringBuilder();

        toStringHelper(root, sb1, sb2);

        return sb2.toString();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash = (hash + wordCount) * 31;
        hash = (hash + nodeCount) * 31;
        for (int i = 0; i < root.children.length; i++) {
            if (root.children[i] != null) {
                hash = (hash + i) * 31;
                break;
            }

        }

        return hash;
    }

    @Override
    public boolean equals(Object o) {
        try {
            if(wordCount == ((Trie) o).getWordCount() && nodeCount == ((Trie) o).getNodeCount()) {
                return equalsHelper(root, ((Trie) o).root);
            } else {
                return false;
            }
        } catch(Exception e) {
            return false;
        }

    }

    private boolean equalsHelper(Node node1, Node node2) {
        if(node1.count != node2.count) {
            return false;
        }
        for (int i = 0; i < node1.children.length; i++) {
            if (node1.children[i] == null && node2.children[i] == null) {

            } else if (node1.children[i] != null && node2.children[i] != null) {
                if (!equalsHelper(node1.children[i], node2.children[i])) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private void toStringHelper(Node parent, StringBuilder word, StringBuilder words) {
        if (parent.count > 0) {
            words.append(word.toString());
            words.append('\n');
        }
        for (int i = 0; i < parent.children.length; i++) {
            if(parent.children[i] != null) {
                word.append((char) (i + 97));
                toStringHelper(parent.children[i], word, words);
                word.deleteCharAt(word.length() - 1);

            }
        }
    }

}
