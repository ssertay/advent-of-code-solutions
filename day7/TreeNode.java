import java.util.ArrayList;

public class TreeNode {

	private String name;
	private int weight;
	private ArrayList<TreeNode> children;
	
	public TreeNode(String name, int weight) {
		this.name = name;
		this.weight = weight;
		this.children = new ArrayList<TreeNode>();
	}
	
	public TreeNode(String name) {
		this.name = name;
		this.weight = -1; // uninitialized
		this.children = new ArrayList<TreeNode>();
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void addChild(TreeNode newChild) {
		children.add(newChild);
	}
	
	public void printNodeInformation() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append(": ");
		builder.append(weight);
		
		System.out.println(builder.toString());
	}
	
	public void printWholeTree(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "└── " : "├── ") + name + " " + weight);
        for (int i = 0; i < children.size() - 1; i++) {
            children.get(i).printWholeTree(prefix + (isTail ? "    " : "│   "), false);
        }
        if (children.size() > 0) {
            children.get(children.size() - 1)
                    .printWholeTree(prefix + (isTail ?"    " : "│   "), true);
        }
    }
	
}