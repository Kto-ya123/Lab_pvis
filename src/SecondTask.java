import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SecondTask {
    SecondTask(Shell shell){
        composite = new Composite(shell, SWT.NULL);
        RowLayout rowLayoutComposite = new RowLayout(SWT.HORIZONTAL);
        rowLayoutComposite.spacing = 10;
        composite.setLayout(rowLayoutComposite);

        textForButton = new Text(composite, SWT.BORDER);
        RowData layoutDataTextForButton = new RowData();
        layoutDataTextForButton.width = 150;
        textForButton.setLayoutData(layoutDataTextForButton);

        buttonTextToButton = new Button(composite, SWT.PUSH);
        RowData layoutDataButtonTextToButton = new RowData();
        layoutDataButtonTextToButton.width = 100;
        layoutDataButtonTextToButton.height = 40;
        buttonTextToButton.setLayoutData(layoutDataButtonTextToButton);

        buttonTextChange = new Button(composite, SWT.PUSH);
        buttonTextChange.setLayoutData(layoutDataButtonTextToButton);

        buttonTextToButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                // TODO Auto-generated method stub
                String stringOutput = textForButton.getText();
                buttonTextChange.setText(stringOutput);
            }
        });
        buttonTextChange.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                // TODO Auto-generated method stub
                String m = buttonTextToButton.getText();
                String n = buttonTextChange.getText();
                buttonTextToButton.setText(n);
                buttonTextChange.setText(m);
            }
        });

    }


    private Composite composite;
    private Text textForButton;
    private Button buttonTextToButton;
    private Button buttonTextChange;
}
