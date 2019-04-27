import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;




public class Main {
    private static Thread thread;
    private static boolean stopThreadMotion = false;
    private static boolean threadIsAlive = false;

    public static PositionXY getPosition(int degree){
        int radius = 420;
        int centreX = 760;
        int centreY = 440;
        int part;
        if(degree < 90){
            part = 1;
        }else if(degree < 180){
            part = 2;
        }else if(degree < 270){
            part = 3;
        }else {
            part = 4;
        }
        double sin = Math.sin(Math.toRadians(degree));
        int biasY = (int)(Math.abs(Math.sin(Math.toRadians(degree))) * radius);
        int biasX = (int)Math.sqrt((radius*radius) - (biasY * biasY));
        switch (part){
            case 1:
                biasY = - biasY;
                break;
            case 2:
                biasX = -biasX;
                biasY = -biasY;
                break;
            case 3:
                biasX = -biasX;
                break;
            case 4:
                break;
        }
        return new PositionXY(centreX + biasX, centreY + biasY);

    }
    public static void setComponentsMainShell(Shell shell){

    }
    public static void main(String[] args) {


        Display display = new Display();
        Shell shell = new Shell(display);
        //setComponentsMainShell(shell);
        shell.setText("Лабораторная работа 1");
        shell.setSize(450, 900);
        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        rowLayout.marginLeft = 10;
        rowLayout.marginTop = 10;
        rowLayout.spacing = 40;
        shell.setLayout(rowLayout);
        new FirstTask(shell);
        new SecondTask(shell);
        new ThirdTask(shell);
        new FourthTask(shell);
        new FifthTask(shell);

        Button startMainShell = new Button(shell, SWT.PUSH);
        startMainShell.setText("Start");
        RowData rd_startMainShell = new RowData();
        rd_startMainShell.width = 100;
        startMainShell.setLayoutData(rd_startMainShell);
        Button stopMainShell = new Button(shell, SWT.PUSH);
        stopMainShell.setText("Stop");
        stopMainShell.setLayoutData(rd_startMainShell);

        Shell shellStartStopMotion = new Shell(display);

        Button startMotion = new Button(shellStartStopMotion, SWT.PUSH);
        startMotion.setText("Start");
        RowData styleStartMotion = new RowData();
        styleStartMotion.width = 100;
        startMotion.setLayoutData(styleStartMotion);
        Button stopMotion = new Button(shellStartStopMotion, SWT.PUSH);
        stopMotion.setText("Stop");
        RowData styleStopMotion = new RowData();
        styleStopMotion.width = 100;
        stopMotion.setLayoutData(styleStopMotion);
        RowLayout styleShellInCentre = new RowLayout(SWT.HORIZONTAL);
        shellStartStopMotion.setLayout(styleShellInCentre);


        PositionXY CentreOfCircle = new PositionXY(760, 440);
        Shell[]shells = new Shell[5];
        for(int initIndex = 0; initIndex < shells.length; initIndex++){
            shells[initIndex] = new Shell(display);
            shells[initIndex].setLayout(rowLayout);
            shells[initIndex].setSize(450, 200);
        }
        new FirstTask(shells[0]);
        new SecondTask(shells[1]);
        new ThirdTask(shells[2]);
        new FourthTask(shells[3]);
        new FifthTask(shells[4]);

        int diffOfDegree = 360 / shells.length;
        int degreeOfShells [] = new int[shells.length];

        for(int positionIndex = 0; positionIndex < shells.length; positionIndex++){
            degreeOfShells[positionIndex] = positionIndex*diffOfDegree;
            PositionXY posOfShell = getPosition(degreeOfShells[positionIndex]);
            shells[positionIndex].setBounds(posOfShell.getX(), posOfShell.getY(), 450, 200);
        }

        startMainShell.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                stopThreadMotion = true;

                shell.setVisible(false);
                for(int openIndex = 0; openIndex < shells.length; openIndex++){
                    shells[openIndex].open();
                }
                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Display.getDefault().asyncExec(new Runnable() {
                            @Override
                            public void run() {
                                shellStartStopMotion.setBounds(CentreOfCircle.getX(), CentreOfCircle.getY(), 250, 100);
                                shellStartStopMotion.open();
                            }
                        });
                        Display.getDefault().syncExec(new Runnable() {
                            @Override
                            public void run() {
                                while (!shell.isDisposed() && !stopThreadMotion) {
                                    for(int positionIndex = 0; positionIndex < shells.length; positionIndex++){
                                        if(++degreeOfShells[positionIndex] == 360){
                                            degreeOfShells[positionIndex] = 0;
                                        }
                                        PositionXY posOfShell = getPosition(degreeOfShells[positionIndex]);
                                        shells[positionIndex].setBounds(posOfShell.getX(), posOfShell.getY(), 450, 200);
                                    }
                                    try{
                                        Thread.sleep(10);
                                    }
                                    catch(Exception ex)
                                    {
                                        System.err.println("ex");
                                    }
                                    if(degreeOfShells[0] == 0){
                                        shell.open();
                                        shellStartStopMotion.setVisible(false);
                                        for (int closeShell = 0; closeShell < shells.length; closeShell++) {
                                            shells[closeShell].setVisible(false);
                                        }

                                        int timeOfWait = 0;
                                        while (!shell.isDisposed() && timeOfWait++ != 600 && !stopThreadMotion) {
                                            System.out.println(""+timeOfWait);
                                            try {
                                                Thread.sleep(5);
                                            } catch (Exception ex) {
                                                System.err.println("ex");
                                            }
                                            if (display.readAndDispatch()) {

                                            }
                                        }if(!stopThreadMotion) {
                                            shell.setVisible(false);
                                            shellStartStopMotion.setVisible(true);
                                            for (int closeShell = 0; closeShell < shells.length; closeShell++) {
                                                shells[closeShell].setVisible(true);
                                            }
                                        }
                                    }
                                    if (display.readAndDispatch()) {
                                    }
                                }
                            }
                        });
                    }
                });
                if(!threadIsAlive) {
                    stopThreadMotion = false;
                    threadIsAlive = true;
                    thread.start();
                }else if(stopThreadMotion == true){
                    stopThreadMotion = false;
                    thread.start();
                }
            }
        });

        stopMotion.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                stopThreadMotion = true;
                threadIsAlive = false;
            }
        });
        stopMainShell.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(threadIsAlive == false){
                    return;
                }
                stopThreadMotion = true;
                threadIsAlive = false;
            }
        });
        startMotion.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.setVisible(false);
                for(int openIndex = 0; openIndex < shells.length; openIndex++){
                    shells[openIndex].open();
                }

                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Display.getDefault().asyncExec(new Runnable() {
                            @Override
                            public void run() {
                                shellStartStopMotion.setBounds(CentreOfCircle.getX(), CentreOfCircle.getY(), 250, 100);
                                shellStartStopMotion.open();
                            }
                        });
                        Display.getDefault().syncExec(new Runnable() {
                            @Override
                            public void run() {
                                while (!shell.isDisposed() && !stopThreadMotion) {
                                    for(int positionIndex = 0; positionIndex < shells.length; positionIndex++){
                                        if(++degreeOfShells[positionIndex] == 360){
                                            degreeOfShells[positionIndex] = 0;
                                        }
                                        PositionXY posOfShell = getPosition(degreeOfShells[positionIndex]);
                                        shells[positionIndex].setBounds(posOfShell.getX(), posOfShell.getY(), 450, 200);
                                    }
                                    try{
                                        Thread.sleep(10);
                                    }
                                    catch(Exception ex)
                                    {
                                        System.err.println("ex");
                                    }
                                    if(degreeOfShells[0] == 0){
                                        shell.open();
                                        shellStartStopMotion.setVisible(false);
                                        for (int closeShell = 0; closeShell < shells.length; closeShell++) {
                                            shells[closeShell].setVisible(false);
                                        }

                                        int timeOfWait = 0;
                                        while (!shell.isDisposed() && timeOfWait++ != 600 && !stopThreadMotion) {
                                            System.out.println(""+timeOfWait);
                                            try {
                                                Thread.sleep(5);
                                            } catch (Exception ex) {
                                                System.err.println("ex");
                                            }
                                            if (display.readAndDispatch()) {

                                            }
                                        }if(!stopThreadMotion) {
                                            shell.setVisible(false);
                                            shellStartStopMotion.setVisible(true);
                                            for (int closeShell = 0; closeShell < shells.length; closeShell++) {
                                                shells[closeShell].setVisible(true);
                                            }
                                        }
                                    }
                                    if (display.readAndDispatch()) {

                                    }
                                }
                            }
                        });
                    }
                });
                if(!threadIsAlive) {
                    stopThreadMotion = false;
                    threadIsAlive = true;
                    thread.start();
                }
            }
        });


        shell.open();

        while (!shell.isDisposed()) {
            if (display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
}

