package beppy;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.BoxLayout;

final class RootWindow implements Runnable
{
    @Override
    public void run()
    {
        final JFrame rootJFrame = new JFrame();

        rootJFrame.setLayout(new BoxLayout(rootJFrame.getContentPane(), BoxLayout.Y_AXIS));

        rootJFrame.setName("Root");
        rootJFrame.setTitle("Beppy");
        rootJFrame.setSize(500, 300);

        final String[][] data = DataFile.readTo2d().toArray(String[][]::new);

        final var table = DataTableFrame.create(data);

        final var addDataPanel = DataEntryPanel.create();

        rootJFrame.add(table);
        rootJFrame.add(addDataPanel);

        rootJFrame.setVisible(true);
    }
}

public class Beppy
{

    public static void main(String args[])
    {
        if( ! DataFile.exists())
        {
            System.out.println("Data file not found. Creating data file...");
            final var path = DataFile.create();
            System.out.printf("Data file created at: \"%s\"", path);
        }

        System.out.printf("Using data file: \"%s\"", DataFile.PATH);

        SwingUtilities.invokeLater(new RootWindow());
    }
}
