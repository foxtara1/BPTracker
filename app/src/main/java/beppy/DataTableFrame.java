package beppy;

import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

final class DataTableFrame
{
    static JScrollPane create(String[][] data)
    {
        final UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        defaults.putIfAbsent("Table.alternateRowColor", Color.LIGHT_GRAY);

        final JTable table = new JTable(data, DataFile.HEADER_STRINGS.toArray());
        final JScrollPane scrollPane = new JScrollPane(table);
        table.setDefaultEditor(Object.class, null);
        table.setFillsViewportHeight(true);
        table.setGridColor(Color.GRAY);
        table.setShowGrid(true);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scrollPane;
    }
}
