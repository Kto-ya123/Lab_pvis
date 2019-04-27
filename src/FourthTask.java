import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class FourthTask {
    FourthTask(Shell shell){
        compositeAllTask = new Composite(shell, SWT.NULL);
        RowLayout rowLayoutCompositeAllTask = new RowLayout(SWT.HORIZONTAL);
        rowLayoutCompositeAllTask.spacing = 80;
        compositeAllTask.setLayout(rowLayoutCompositeAllTask);

        compositeButtonText = new Composite(compositeAllTask, SWT.NULL);
        compositeButtonText.setLayout(new RowLayout(SWT.VERTICAL));

        Composite compositeCheckBox = new Composite(compositeAllTask, SWT.NULL);
        compositeCheckBox.setLayout(new RowLayout(SWT.VERTICAL));

        textChooseCheckBox = new Text(compositeButtonText, SWT.BORDER);
        RowData layoutDataTextChooseCheckBox = new RowData();
        layoutDataTextChooseCheckBox.width = 150;
        textChooseCheckBox.setLayoutData(layoutDataTextChooseCheckBox);

        buttonChooseCheckBox = new Button(compositeButtonText, SWT.PUSH);
        buttonChooseCheckBox.setText("Выбрать");
        RowData layoutDataButtonChooseCheckBox = new RowData();
        layoutDataButtonChooseCheckBox.height = 40;
        layoutDataButtonChooseCheckBox.width = 100;
        buttonChooseCheckBox.setLayoutData(layoutDataButtonChooseCheckBox);

        checkBoxFirst = new Button(compositeCheckBox, SWT.CHECK);
        checkBoxFirst.setText("first");

        checkBoxSecond = new Button(compositeCheckBox, SWT.CHECK);
        checkBoxSecond.setText("second");

        checkBoxThird = new Button(compositeCheckBox, SWT.CHECK);
        checkBoxThird.setText("third");

        buttonChooseCheckBox.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                String stringInput = textChooseCheckBox.getText();

                if (stringInput.equals(checkBoxFirst.getText())) {

                    if (checkBoxFirst.getSelection()) {
                        checkBoxFirst.setSelection(false);
                    } else {
                        checkBoxFirst.setSelection(true);
                    }
                    return;
                }

                if (stringInput.equals(checkBoxSecond.getText())) {

                    if (checkBoxSecond.getSelection()) {
                        checkBoxSecond.setSelection(false);
                    } else {
                        checkBoxSecond.setSelection(true);
                    }
                    return;
                }

                if (stringInput.equals(checkBoxThird.getText())) {

                    if (checkBoxThird.getSelection()) {
                        checkBoxThird.setSelection(false);
                    } else {
                        checkBoxThird.setSelection(true);
                    }
                    return;
                }
                MessageBox messageBoxError = new MessageBox(shell, SWT.OK);
                messageBoxError.setText("Ошибка");
                messageBoxError.setMessage("Кнопки с таким названием не найдено");
                messageBoxError.open();
            }
        });
    }
    private Composite compositeAllTask;
    private Composite compositeButtonText;
    private Text textChooseCheckBox;
    private Button buttonChooseCheckBox;
    private Button checkBoxFirst;
    private Button checkBoxSecond;
    private Button checkBoxThird;
}
