package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.TreeViewAdapter.TreeNode;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;

public class SuperTreeViewAdapter extends BaseExpandableListAdapter {
	static public class SuperTreeNode {
		public Object parent;
		public List<TreeViewAdapter.TreeNode> childs = new ArrayList<TreeViewAdapter.TreeNode>();
	}

	private List<SuperTreeNode> superTreeNodes = new ArrayList<SuperTreeNode>();
	private Context parentContext;
	private OnChildClickListener stvClickEvent;

	public SuperTreeViewAdapter(Context view, OnChildClickListener stvClickEvent) {
		parentContext = view;
		this.stvClickEvent = stvClickEvent;
	}

	public List<SuperTreeNode> GetTreeNode() {
		return superTreeNodes;
	}

	public void UpdateTreeNode(List<SuperTreeNode> node) {
		superTreeNodes = node;
	}

	public void RemoveAll() {
		superTreeNodes.clear();
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return superTreeNodes.get(arg0).childs.get(arg1);
	}

	@Override
	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		return superTreeNodes.get(arg0).childs.size();
	}

	public ExpandableListView getExpandableListView() {
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, TreeViewAdapter.ItemHeight);
		ExpandableListView superTreeView = new ExpandableListView(parentContext);
		superTreeView.setLayoutParams(lp);
		return superTreeView;
	}

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
			ViewGroup arg4) {
		final ExpandableListView treeView = getExpandableListView();
		final TreeViewAdapter treeViewAdapter = new TreeViewAdapter(
				this.parentContext, 0);
		List<TreeNode> tmp = treeViewAdapter.GetTreeNode();
		final TreeNode treeNode = (TreeNode) getChild(arg0, arg1);
		tmp.add(treeNode);
		treeViewAdapter.UpdateTreeNode(tmp);
		treeView.setAdapter(treeViewAdapter);

		treeView.setOnChildClickListener(this.stvClickEvent);

		treeView.setOnGroupExpandListener(new OnGroupExpandListener() {

			public void onGroupExpand(int arg0) {
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT, (treeNode.childs
								.size() + 1) * TreeViewAdapter.ItemHeight + 10);
				treeView.setLayoutParams(lp);
			}
		});
		treeView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int arg0) {
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						TreeViewAdapter.ItemHeight);
				treeView.setLayoutParams(lp);
			}
		});
		treeView.setPadding(TreeViewAdapter.PaddingLeft, 0, 0, 0);
		return treeView;
	}

	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		TextView textView = TreeViewAdapter.getTextView(this.parentContext);
		textView.setText(getGroup(arg0).toString());
		textView.setPadding(TreeViewAdapter.PaddingLeft, 0, 0, 0);
		return textView;
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return arg1;
	}

	@Override
	public Object getGroup(int arg0) {
		// TODO Auto-generated method stub
		return superTreeNodes.get(arg0).parent;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return superTreeNodes.size();
	}

	@Override
	public long getGroupId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

}
