package com.example.toorbar;

import java.util.List;

import com.example.adapter.SuperTreeViewAdapter;
import com.example.adapter.TreeViewAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

@SuppressLint("InflateParams")
public class Tab13 extends View {
	static ExpandableListView expandableListView;
	static TreeViewAdapter adapter;
	static SuperTreeViewAdapter superAdapter;
	static Button second;
	static Button third;
	private static Context context;
	public String[] groups = { "好友", "同学", "女人" };
	public String[][] child = { { "A", "B", "C", "D" }, { "甲", "乙", "丙" },
			{ "御姐", "萝莉" } };
	public String[] parent = { "好友", "同学" };
	public String[][][] child_grandson = { { { "A" }, { "AA", "AAA" } },
			{ { "B" }, { "BB", "BBB", "BBBB" } }, { { "C" }, { "CC", "CCC" } },
			{ { "D" }, { "DD", "DDD", "DDDD" } }, };

	public Tab13(Context context) {
		super(context);
		Tab13.context = context;
	}

	public View getView(LayoutInflater inflater) {
		View view = inflater.inflate(R.layout.tab13, null);

		second = view.findViewById(R.id.second);
		second.setOnClickListener(new ClickEvent());
		third = view.findViewById(R.id.third);
		third.setOnClickListener(new ClickEvent());

		adapter = new TreeViewAdapter(context, TreeViewAdapter.PaddingLeft >> 1);
		superAdapter = new SuperTreeViewAdapter(context, stvClickEvent);

		expandableListView = view.findViewById(R.id.expandableListView);

		return view;
	}

	class ClickEvent implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			adapter.RemoveAll();
			adapter.notifyDataSetChanged();
			superAdapter.RemoveAll();
			superAdapter.notifyDataSetChanged();
			if (arg0 == second) {
				List<TreeViewAdapter.TreeNode> treeNode = adapter.GetTreeNode();
				for (int i = 0; i < groups.length; i++) {
					TreeViewAdapter.TreeNode node = new TreeViewAdapter.TreeNode();
					node.parent = groups[i];
					for (int j = 0; j < child[i].length; j++) {
						node.childs.add(child[i][j]);
					}
					treeNode.add(node);
				}
				adapter.UpdateTreeNode(treeNode);
				expandableListView.setAdapter(adapter);
				expandableListView
						.setOnChildClickListener(new OnChildClickListener() {

							@Override
							public boolean onChildClick(
									ExpandableListView arg0, View arg1,
									int arg2, int arg3, long arg4) {
								String str = "parent id:"
										+ String.valueOf(arg2)
										+ ",children id:"
										+ String.valueOf(arg3);
								Toast.makeText(context, str, Toast.LENGTH_SHORT)
										.show();
								return false;
							}
						});
			} else if (arg0 == third) {
				List<SuperTreeViewAdapter.SuperTreeNode> superTreeNode = superAdapter
						.GetTreeNode();
				for (int i = 0; i < parent.length; i++) {
					SuperTreeViewAdapter.SuperTreeNode superNode = new SuperTreeViewAdapter.SuperTreeNode();
					superNode.parent = parent[i];
					for (int j = 0; j < child_grandson.length; j++) {
						TreeViewAdapter.TreeNode node = new TreeViewAdapter.TreeNode();
						node.parent = child_grandson[j][0][0];
						for (int k = 0; k < child_grandson[j][1].length; k++) {
							node.childs.add(child_grandson[j][1][k]);
						}
						superNode.childs.add(node);
					}
					superTreeNode.add(superNode);
				}
				superAdapter.UpdateTreeNode(superTreeNode);
				expandableListView.setAdapter(superAdapter);
			}

		}

	}

	static OnChildClickListener stvClickEvent = new OnChildClickListener() {

		@Override
		public boolean onChildClick(ExpandableListView arg0, View arg1,
				int arg2, int arg3, long arg4) {
			String str = "parent id:" + String.valueOf(arg2) + ",children id:"
					+ String.valueOf(arg3);
			Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
			return false;
		}
	};

}
