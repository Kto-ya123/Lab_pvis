
import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class FirstTask {
    FirstTask(Shell shell){
        composite = new Composite(shell, SWT.NULL);
        RowLayout rowLayoutComposite = new RowLayout(SWT.HORIZONTAL);
        rowLayoutComposite.spacing = 10;
        composite.setLayout(rowLayoutComposite);

        textInput = new Text(composite, SWT.BORDER);
        RowData layoutDataTextInput = new RowData();
        layoutDataTextInput.width = 150;
        textInput.setLayoutData(layoutDataTextInput);

        buttonInput = new Button(composite, SWT.PUSH);
        RowData layoutDataButtonInput = new RowData();
        layoutDataButtonInput.width = 80;
        layoutDataButtonInput.height = 30;
        buttonInput.setLayoutData(layoutDataButtonInput);
        buttonInput.setText("add text");

        textOutput = new Combo(composite, SWT.MULTI
            | SWT.READ_ONLY | SWT.BORDER);
        RowData layoutDataTextOutput = new RowData();
        layoutDataTextOutput.width = 100;
        textOutput.setLayoutData(layoutDataTextOutput);
        textOutput.setText("Text");

        buttonInput.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                // TODO Auto-generated method stub
                String stringInput = textInput.getText();

                for(int i = 0; i < textOutput.getItemCount(); i++) {
                    String stringOutput = textOutput.getItem(i);
                    if (stringInput.equals(stringOutput)) {
                        MessageBox messageEqualString = new MessageBox(shell, SWT.OK);
                        messageEqualString.setText("Ошибка");
                        messageEqualString.setMessage("Такая строка уже есть");
                        messageEqualString.open();
                        return;
                    }
                }
                textOutput.add(stringInput);
            }
        });
    }
    private Composite composite;
    private Text textInput;
    private Button buttonInput;
    private Combo textOutput;
}
