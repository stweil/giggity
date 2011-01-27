/*
 * Giggity -- Android app to view conference/festival schedules
 * Copyright 2008-2011 Wilmer van der Gaast <wilmer@gaast.net>
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of version 2 of the GNU General Public
 * License as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301, USA.
 */

package net.gaast.giggity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SearchActivity extends ScheduleListActivity {
	Schedule sched;
	Giggity app;

	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
        if (!getIntent().getAction().equals(Intent.ACTION_SEARCH)) {
        	finish();
        	return;
        }
		
		/* Doesn't seem to work..
        TextView tv = new TextView(this);
		tv.setText("No results.");
		this.getListView().setEmptyView(tv);
		*/
        
    	app = (Giggity) getApplication();
        sched = app.getLastSchedule();
    	if (sched == null) {
    		finish(); /* WTF */
    		return;
    	}
    	
    	sched.setDay(-1);
    	String query = getIntent().getStringExtra(SearchManager.QUERY);
		setList(sched.searchItems(query));
		setTitle("Results for \"" + query + "\" in " + sched.getTitle());
	}
}