package com.joymain.jecs.webapp.taglib;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

import com.joymain.jecs.util.tree.TreeNode;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;

/**
 * @author Asii
 * @jsp.tag name="writeTree" bodycontent="empty"
 * 
 */
public class WriteTreeTag extends TagSupport {
	
	// 当树没有顶部节点即没有parentId=0时，需虚拟一个顶部节点。
	private boolean noTop = false;

	private String branchs;

	private String leafs;

	private String curLeafs;

	private String type;

	public int doStartTag() {

		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();

		Map curLeafsMap;
		StringBuffer sb = new StringBuffer();

		sb.append("<script type=\"text/javascript\">");
		sb.append("tree = new MzTreeView(\"tree\");");
		List<TreeNode> curLeafsList = (List) request.getAttribute(this.curLeafs);
		curLeafsMap = new HashMap();
		if ("roleUserManage".equals(type)) {
			noTop = true;
			for (TreeNode t : curLeafsList) {
				curLeafsMap.put(t.getParentKey(), t);
			}
			writeTreeBranch(request, curLeafsMap, sb);
		}

		if ("userRoleManage".equals(type)) {
			noTop = true;
			for (TreeNode t : curLeafsList) {
				curLeafsMap.put(t.getKey(), t);
			}

			writeTreeBranch(request, curLeafsMap, sb);
		}

		if ("rolePowerManage".equals(type)) {
			for (TreeNode t : curLeafsList) {
				curLeafsMap.put(t.getKey(), t);
			}

			writeTreeBranch(request, curLeafsMap, sb);
		}
		if ("roleMenuManage".equals(type)) {
			for (TreeNode t : curLeafsList) {
				curLeafsMap.put(t.getKey(), t);
			}

			writeTreeLeafs(request, curLeafsMap, sb);
		}
		sb.append("tree.wordLine = false;");
		sb.append("tree.setIconPath(\"/wecs/images/treeimages/\");");
		sb.append("tree.setTarget(\"listFrame\");");
		sb.append("document.write(tree.toString());");
		sb.append("tree.expandAll();");
		sb.append("</script>");
		try {
			this.pageContext.getOut().write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.SKIP_BODY;
	}

	private void writeTreeBranch(ServletRequest request, Map curLeafsMap, StringBuffer sb) {
		List<TreeNode> branchList = (List) request.getAttribute(this.branchs);
		if (noTop && branchList.size() > 0) {
			TreeNode t = branchList.get(0);
			sb.append("tree.N[\"0_" + t.getKey() + "\"]=\"T:" + t.getEntityName() + "\";");
			branchList.remove(0);
		}
		for (TreeNode t : branchList) {
			sb.append("tree.N[\"" + t.getParentKey() + "_" + t.getKey() + "\"]=\"T:" + t.getEntityName() + "\";");
		}
		writeTreeLeafs(request, curLeafsMap, sb);
	}

	private void writeTreeLeafs(ServletRequest request, Map curLeafsMap, StringBuffer sb) {
		List<TreeNode> leafsList = (List) request.getAttribute(this.leafs);
		for (TreeNode t : leafsList) {
			String checked = "0";
			if (curLeafsMap.containsKey(t.getKey())) {
				checked = "1";
			}
			sb.append("tree.N[\"" + t.getParentKey() + "_" + t.getKey().toString().replace("_", ".") + "\"]=\"ctrl:sel;checked:" + checked + ";t:" + t.getEntityName() + "\";");
		}
	}

	public String getBranchs() {
		return branchs;
	}

	/**
	 * @jsp.attribute required="true" rtexprvalue="true"
	 * 
	 */
	public void setBranchs(String branchs) {
		this.branchs = branchs;
	}

	public String getCurLeafs() {
		return curLeafs;
	}

	/**
	 * @jsp.attribute required="false" rtexprvalue="true"
	 * 
	 */
	public void setCurLeafs(String curLeafs) {
		this.curLeafs = curLeafs;
	}

	public String getLeafs() {
		return leafs;
	}

	/**
	 * @jsp.attribute required="true" rtexprvalue="true"
	 * 
	 */
	public void setLeafs(String leafs) {
		this.leafs = leafs;
	}

	public String getType() {
		return type;
	}

	/**
	 * @jsp.attribute required="true" rtexprvalue="true"
	 * 
	 */
	public void setType(String type) {
		this.type = type;
	}
}
