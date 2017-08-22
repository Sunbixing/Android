package com.example.view;

import com.example.toorbar.R;

import android.app.Activity;
import android.content.Context;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

public class SearchView extends LinearLayout implements OnClickListener{
	private EditText searchedit;
	private ImageView delete;
	private Button search_button;
	private Context mcontext;
	private ListView search_listview;
	private ArrayAdapter<String> hintadapter;
	private ArrayAdapter<String> autoadapter;
	private SearchViewListener mlistener;
	public void setSearchViewListener(SearchViewListener listener){
		mlistener=listener;
	}
	public SearchView(Context context,AttributeSet set) {
		super(context,set);
		mcontext=context;
		LayoutInflater.from(context).inflate(R.layout.searchfirst, this);
		initViews();
		// TODO Auto-generated constructor stub
	}

	private void initViews() {
		searchedit=findViewById(R.id.searchedit);
		delete=findViewById(R.id.delete);
		search_button=findViewById(R.id.search_button);
		search_listview=findViewById(R.id.search_listview);
		search_listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String text=search_listview.getAdapter().getItem(arg2).toString();
				searchedit.setText(text);
				searchedit.setSelection(text.length());
				search_listview.setVisibility(View.GONE);
				notifyStartSearching(text);
			}
		});
		delete.setOnClickListener(this);
		search_button.setOnClickListener(this);
		searchedit.addTextChangedListener(new EditChangedListener());
		searchedit.setOnClickListener(this);
		searchedit.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId==EditorInfo.IME_ACTION_SEARCH) {
					search_listview.setVisibility(View.GONE);
					notifyStartSearching(searchedit.getText().toString());
				}
				return true;
			}
		});
		
	}
	protected void notifyStartSearching(String text) {
		if (mlistener!=null) {
			mlistener.onSearch(searchedit.getText().toString());
		}
		InputMethodManager imm=(InputMethodManager) mcontext.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}
	public void sethintadapter(ArrayAdapter<String> adapter){
		this.hintadapter=adapter;
		if (search_listview.getAdapter()==null) {
			search_listview.setAdapter(hintadapter);
		}
	}
	public void setautoadapter(ArrayAdapter<String> adapter){
		this.autoadapter=adapter;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.search_listview:
			search_listview.setVisibility(VISIBLE);
			break;
		case R.id.delete:
			searchedit.setText("");
			delete.setVisibility(GONE);
			break;
		case R.id.search_button:
			((Activity)mcontext).finish();
			break;
		}
	}
	private class EditChangedListener implements TextWatcher{

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if (!"".equals(s.toString())) {
				delete.setVisibility(VISIBLE);
				search_listview.setVisibility(VISIBLE);
				if (autoadapter!=null && search_listview.getAdapter()!=autoadapter) {
					search_listview.setAdapter(autoadapter);
				}
				if (mlistener!=null) {
					mlistener.onRefreshAutoComplete(s+"");
				}
			}else {
				delete.setVisibility(GONE);
				if (hintadapter!=null) {
					search_listview.setAdapter(hintadapter);
				}
				search_listview.setVisibility(GONE);
			}
			
		}
		
	}
	public interface SearchViewListener{
		void onRefreshAutoComplete(String text);
		void onSearch(String text);
	}

}
