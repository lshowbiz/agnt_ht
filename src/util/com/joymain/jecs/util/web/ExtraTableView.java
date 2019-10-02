package com.joymain.jecs.util.web;

import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.AbstractHtmlView;
import org.extremecomponents.table.view.CompactToolbar;
import org.extremecomponents.util.HtmlBuilder;

public class ExtraTableView extends AbstractHtmlView {

	protected void beforeBodyInternal(TableModel model) {
        getTableBuilder().tableStart();

        getTableBuilder().theadStart();
        
        getTableBuilder().titleRowSpanColumns();
        
        getTableBuilder().filterRow();

        getTableBuilder().headerRow();

        getTableBuilder().theadEnd();

        getTableBuilder().tbodyStart();
    }

    protected void afterBodyInternal(TableModel model) {
        getCalcBuilder().defaultCalcLayout();
        
        getTableBuilder().tfooterStart();

        toolbar(getHtmlBuilder(), getTableModel());

        getTableBuilder().tbodyEnd();

        getTableBuilder().tableEnd();
    }
    
    protected void toolbar(HtmlBuilder html, TableModel model) {
        new CompactToolbar(html, model).layout();
    }

}
