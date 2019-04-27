import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class ThirdTask {
    ThirdTask(Shell shell){
        compositeAllTask = new Composite(shell, SWT.NULL);
        RowLayout rowLayoutCompositeAllTask = new RowLayout(SWT.HORIZONTAL);
        rowLayoutCompositeAllTask.spacing = 80;
        compositeAllTask.setLayout(rowLayoutCompositeAllTask);

        compositeButtonText = new Composite(compositeAllTask, SWT.NULL);
        compositeButtonText.setLayout(new RowLayout(SWT.VERTICAL));

        compositeRadioButton = new Composite(compositeAllTask, SWT.NULL);
        compositeRadioButton.setLayout(new RowLayout(SWT.VERTICAL));

        textChoose = new Text(compositeButtonText, SWT.BORDER);
        RowData layoutDataTextChoose = new RowData();
        layoutDataTextChoose.width = 150;
        textChoose.setLayoutData(layoutDataTextChoose);

        buttonChoose = new Button(compositeButtonText, SWT.PUSH);
        buttonChoose.setText("");
        RowData layoutDataButtonChoose = new RowData();
        layoutDataButtonChoose.width = 100;
        buttonChoose.setLayoutData(layoutDataButtonChoose);

        firstRadio = new Button(compositeRadioButton, SWT.RADIO);
        firstRadio.setText("firstRadio");

        secondRadio = new Button(compositeRadioButton, SWT.RADIO);
        secondRadio.setText("secondRadio");

        thirdRadio = new Button(compositeRadioButton, SWT.RADIO);
        thirdRadio.setText("thirdRadio");

        buttonChoose.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                // TODO Auto-generated method stub
                String chooseString = textChoose.getText();

                if (chooseString.equals(firstRadio.getText())) {
                    firstRadio.setSelection(true);
                    secondRadio.setSelection(false);
                    thirdRadio.setSelection(false);
                    return;
                }

                if (chooseString.equals(secondRadio.getText())) {
                    firstRadio.setSelection(false);
                    secondRadio.setSelection(true);
                    thirdRadio.setSelection(false);
                    return;
                }

                if (chooseString.equals(thirdRadio.getText())) {
                    firstRadio.setSelection(false);
                    secondRadio.setSelection(false);
                    thirdRadio.setSelection(true);
                    return;
                }

                MessageBox messageNotFound = new MessageBox(shell, SWT.OK);
                messageNotFound.setMessage("Кнопка с таким названием не найдена");
                messageNotFound.setText("Ошибка");
                messageNotFound.open();
            }
        });
    }
    private Composite compositeAllTask;
    private Composite compositeButtonText;
    private Composite compositeRadioButton;
    private Text textChoose;
    private Button buttonChoose;
    private Button firstRadio;
    private Button secondRadio;
    private Button thirdRadio;
}
