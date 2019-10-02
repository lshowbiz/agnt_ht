package com.joymain.jecs.util.tree;

import java.util.ArrayList;
import java.util.List;

public abstract class TreeBranch implements TreeNode {

	private List<TreeNode> leafs;

	private List<TreeBranch> subBranchs;

	public void addSubBranch(TreeBranch subNode) {
		if (this.subBranchs == null) {
			this.subBranchs = new ArrayList<TreeBranch>();
		}
		this.subBranchs.add(subNode);
	}

	public void addLeaf(TreeNode node) {
		if (this.leafs == null) {
			this.leafs = new ArrayList<TreeNode>();
		}
		this.leafs.add(node);
	}

	public List<TreeNode> getLeafs() {
		return leafs;
	}

	public void setLeafs(List<TreeNode> leafList) {
		this.leafs = leafList;
	}

	public List<TreeBranch> getSubBranchs() {
		return subBranchs;
	}

	public void setSubBranchs(List<TreeBranch> subBranchList) {
		this.subBranchs = subBranchList;
	}
}
