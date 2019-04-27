import org.eclipse.swt.widgets.*;
public class CircularMotion implements Runnable {
    private Shell shellMotion;
        public CircularMotion(Shell shell){
            this.shellMotion = shell;
            shellMotion.setSize(100, 100);
            shellMotion.open();
        }
        public void run(){
            Display.getDefault().asyncExec(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            shellMotion.setBounds(100, 200, 100, 100);
                            Thread.yield();
                            Thread.sleep(700);
                            shellMotion.setBounds(700, 700, 100, 100);
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
