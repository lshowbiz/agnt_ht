package com.joymain.jecs.util.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WTree {

	private List<TreeBranch> roots;

	private List branchList;

	private List leafList;

	private WTree() {
	};

	public static WTree buildWTree(List branchList, List leafList) {
		if (branchList == null || branchList.size() == 0) {
			return null;
		}

		WTree tree = new WTree();
		tree.roots = new ArrayList<TreeBranch>();
		Map branchMap;

		tree.setBranchList(branchList);
		tree.setLeafList(leafList);
		// 生成树的枝组织
		branchMap = new HashMap();
		for (int i = 0; i < branchList.size(); i++) {
			TreeBranch treeBranch = (TreeBranch) branchList.get(i);
			branchMap.put(treeBranch.getKey(), treeBranch);
		}

		for (int i = 0; i < branchList.size(); i++) {
			TreeBranch treeBranch = (TreeBranch) branchList.get(i);
			if (treeBranch.getParentKey().equals(new Long("-1"))) {
				tree.roots.add(treeBranch);
				continue;
			}
			TreeBranch parentNode = (TreeBranch) branchMap.get(treeBranch.getParentKey());
			parentNode.addSubBranch(treeBranch);
		}

		// 生成树的叶组织
		for (int i = 0; i < leafList.size(); i++) {
			TreeNode leaf = (TreeNode) leafList.get(i);
			TreeBranch branch = (TreeBranch) branchMap.get(leaf.getParentKey());
			branch.addLeaf(leaf);
		}

		return tree;

	}

	public List getBranchList() {
		return branchList;
	}

	public void setBranchList(List branchList) {
		this.branchList = branchList;
	}

	public List getLeafList() {
		return leafList;
	}

	public void setLeafList(List leafList) {
		this.leafList = leafList;
	}

	public List<TreeBranch> getRoots() {
		return roots;
	}

	public void setRoots(List<TreeBranch> root) {
		this.roots = root;
	}

}
