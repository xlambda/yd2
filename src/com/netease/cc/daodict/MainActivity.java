package com.netease.cc.daodict;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        ActionBar actionbar = getActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayShowHomeEnabled(false);
        actionbar.setDisplayUseLogoEnabled(false);
        
        Tab queryTab = actionbar.newTab();
        queryTab.setText(R.string.query_tab_title)
        //	.setContentDescription(R.string.query_tab_title_desc)
        	.setTabListener(new TabListener<QueryFragment>(this, 
        			R.id.fragment_container, QueryFragment.class));
        
        Tab otherTab = actionbar.newTab();
        otherTab.setText(R.string.other_tab_title);
       // otherTab.setContentDescription(R.string.other_tab_title_desc);
        otherTab.setTabListener(new TabListener<OtherFragment>(this,
        		R.id.fragment_container, OtherFragment.class));
        
        actionbar.addTab(queryTab);
        actionbar.addTab(otherTab);
    }
    
    public static class TabListener<T extends Fragment>
    		implements ActionBar.TabListener {
    	private Fragment fragment;
    	private Activity activity;
    	private Class <T> fragmentClass;
    	private int fragmentContainer;
    	
    	public TabListener(Activity activity, int fragmentContainer, Class<T> fragmentClass){
    		this.activity = activity;
    		this.fragmentContainer = fragmentContainer;
    		this.fragmentClass = fragmentClass;
    	}
    	@Override
    	public void onTabSelected(Tab tab, FragmentTransaction ft){
    		if (this.fragment == null){
    			String fragmentName = fragmentClass.getName();
    			this.fragment = Fragment.instantiate(activity, fragmentName);
    			ft.add(fragmentContainer, this.fragment, null);
    		}
    		else {
    			ft.attach(this.fragment);
    		}
    	}
    	
    	@Override
    	public void onTabUnselected(Tab tab, FragmentTransaction ft){
    		if (fragment != null){
    			ft.detach(fragment);
    		}
    	}
    	
    	@Override
    	public void onTabReselected(Tab tab, FragmentTransaction ft){
    		
    	}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
