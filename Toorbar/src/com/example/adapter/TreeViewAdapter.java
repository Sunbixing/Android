package com.example.adapter;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

@SuppressLint("RtlHardcoded")
public class TreeViewAdapter extends BaseExpandableListAdapter {
	public static final int ItemHeight = 48;
	public static final int PaddingLeft = 36;
	private int myPaddingLeft = 0;

	static public class TreeNode {
		public Object parent;
		public List<Object> childs = new ArrayList<Object>();
	}

	List<TreeNode> treeNodes = new ArrayList<TreeNode>();
	Context parentContext;

	public TreeViewAdapter(Context view, int myPaddingLeft) {
		parentContext = view;
		this.myPaddingLeft = myPaddingLeft;
	}

	public List<TreeNode> GetTreeNode() {
		return treeNodes;
	}

	public void UpdateTreeNode(List<TreeNode> nodes) {
		treeNodes = nodes;
	}

	public void RemoveAll() {
		treeNodes.clear();
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return treeNodes.get(arg0).childs.get(arg1);
	}

	@Override
	public int getChildrenCount(int arg0) {
		// TODO Auto-generated method stub
		return treeNodes.get(arg0).childs.size();
	}

	static public TextView getTextView(Context context) {
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, ItemHeight);

		TextView textView = new TextView(context);
		textView.setLayoutParams(lp);
		textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		return textView;
	}

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
			ViewGroup arg4) {
		TextView textView = getTextView(this.parentContext);
		textView.setText(getChild(arg0, arg1).toString());
		textView.setPadding(myPaddingLeft + PaddingLeft, 0, 0, 0);
		return textView;
	}

	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
		TextView textView = getTextView(this.parentContext);
		textView.setText(getGroup(arg0).toString());
		textView.setPadding(myPaddingLeft + (PaddingLeft >> 1), 0, 0, 0);
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
		return treeNodes.get(arg0).parent;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return treeNodes.size();
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
