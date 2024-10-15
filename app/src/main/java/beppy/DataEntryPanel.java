package beppy;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;

final class DataEntryPanel
{
    final static JPanel create()
    {
        final JPanel buttonAndInputFieldsPane = new JPanel();

        final JButton addButton = new JButton("Add");

        addButton.setName("AddButton");

        final JFormattedTextField systolicEntryTextField = new JFormattedTextField(10);
        systolicEntryTextField.setName("SystolicEntryTextField");

        final JFormattedTextField diastolicEntryTextField = new JFormattedTextField(10);
        diastolicEntryTextField.setName("DiastolicEntryTextField");

        buttonAndInputFieldsPane.add(addButton);
        buttonAndInputFieldsPane.add(systolicEntryTextField);
        buttonAndInputFieldsPane.add(diastolicEntryTextField);

        return buttonAndInputFieldsPane;
    }
}
