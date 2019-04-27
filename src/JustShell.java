import org.eclipse.swt.widgets.*;
public class JustShell implements Runnable {
    private Shell shellMotion;
    private Display display;
    public JustShell(Shell shell){
        this.shellMotion = shell;
        shellMotion.open();
    }
    public void run(){
        Display.getDefault().syncExec(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        shellMotion.setBounds(400, 200, 450, 800);
                        Thread.yield();
                        Thread.sleep(700);
                        shellMotion.setBounds(1000, 700, 450, 800);
                        Thread.yield();
                        Thread.sleep(700);

                    }
                }catch(InterruptedException e)
                {
                    System.err.println("Interrupted");
                }
            }
        });

    }
}
