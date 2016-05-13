package org.gwtbook.client.ui.flextableexample;

import org.gwtbook.client.ui.DashboardComposite;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

public class FlexTableExample extends DashboardComposite {
	
	FlexTable flextable;
	
	public FlexTableExample(){
		flextable = new FlexTable();
		FlexCellFormatter formatter = flextable.getFlexCellFormatter();
		formatter.setColSpan(0, 0, 2);
		formatter.setRowSpan(0,0,2);
		for(int loop1 = 0; loop1 < 6; loop1++) {
			for(int loop2 = 0; loop2 < 5; loop2++) {
				flextable.setText(loop1,loop2,"("+loop1+","+loop2+")");
			}
		}
		formatter.addStyleName(1,1,"flexTable");
		initWidget(flextable);
	}
}
