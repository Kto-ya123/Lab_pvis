import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class FifthTask {
    FifthTask(Shell shell){
        compositeAllTask = new Composite(shell, SWT.NULL);
        RowLayout rowLayoutCompositeFifthTask = new RowLayout();
        rowLayoutCompositeFifthTask.spacing = 30;
        compositeAllTask.setLayout(rowLayoutCompositeFifthTask);

        compositeAllButtonText = new Composite(compositeAllTask, SWT.NULL);
        compositeAllButtonText.setLayout(new RowLayout(SWT.VERTICAL));

        textToTable = new Text(compositeAllButtonText, SWT.BORDER);
        RowData rowLayoutTextToTable = new RowData();
        rowLayoutTextToTable.width = 150;
        textToTable.setLayoutData(rowLayoutTextToTable);

        buttonTextToTable = new Button(compositeAllButtonText, SWT.PUSH);
        buttonTextToTable.setText("В таблицу");
        RowData layoutForButtonFifthTask = new RowData();
        layoutForButtonFifthTask.width = 160;
        layoutForButtonFifthTask.height = 30;
        buttonTextToTable.setLayoutData(layoutForButtonFifthTask);

        buttonFromFirstToSecond = new Button(compositeAllButtonText, SWT.PUSH);
        buttonFromFirstToSecond.setText("Из первого во второй");
        buttonFromFirstToSecond.setLayoutData(layoutForButtonFifthTask);

        buttonFromSecondToFirst = new Button(compositeAllButtonText, SWT.PUSH);
        buttonFromSecondToFirst.setText("Из второго в первый");
        buttonFromSecondToFirst.setLayoutData(layoutForButtonFifthTask);

        tableFifthTask = new Table(compositeAllTask,/*SWT.MULTI
            | SWT.BORDER | SWT.NO_SCROLL |*/ SWT.FULL_SELECTION);
        tableFifthTask.setLinesVisible(true);
        RowData rowDataTableFifthTask = new RowData();
        rowDataTableFifthTask.width = 200;
        //tableFifthTask.setLayoutData(rowDataTableFifthTask);

        for(int i = 0; i < 2; i++){
            TableColumn column = new TableColumn(tableFifthTask, SWT.NONE);
            column.setWidth(100);
        }
        /*TableColumn [] tableColumns = tableFifthTask.getColumns();
        for(int i = 0; i < 5; i++){
            TableItem item = new TableItem(tableFifthTask, SWT.NONE);
        }*/

        buttonTextToTable.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String stringInput = textToTable.getText();
                new TableItem(tableFifthTask, SWT.NONE);
                tableFifthTask.getItem(tableFifthTask.getItemCount()-1).setText(stringInput);
            }
        });

        buttonFromFirstToSecond.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int index = tableFifthTask.getSelectionIndex();
                TableItem item = tableFifthTask.getItem(index);
                String stringFirst = item.getText(0);
                if(!stringFirst.equals("")) {

                    item.setText(1, item.getText(0));
                    item.setText(0, "");
                }
            }
        });

        buttonFromSecondToFirst.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int index = tableFifthTask.getSelectionIndex();
                TableItem item = tableFifthTask.getItem(index);
                String stringSecond = item.getText(1);
                if(!stringSecond.equals("")) {
                    item.setText(0, item.getText(1));
                    item.setText(1, "");
                }
            }
        });

    }
    private Composite compositeAllTask;
    private Composite compositeAllButtonText;
    private Text textToTable;
    private Button buttonTextToTable;
    private Button buttonFromFirstToSecond;
    private Button buttonFromSecondToFirst;
    private Table tableFifthTask;
}
