package com.joymain.jecs.util.ectable;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.cell.AbstractCell;
import org.extremecomponents.table.core.TableModel;

public class EcTableCell extends AbstractCell  {

	public String getExportDisplay(TableModel model, Column column) {
		// TODO Auto-generated method stub
		if(column.getValue()==null){
			return "";
		}
		return column.getValue().toString();
		//return column.getPropertyValueAsString();
	}

	@Override
	protected String getCellValue(TableModel model, Column column) {
		// TODO Auto-generated method stub
		return column.getValueAsString();
	}

}
