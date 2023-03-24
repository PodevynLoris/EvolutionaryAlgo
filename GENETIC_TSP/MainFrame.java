package GENETIC_TSP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JPanel mainPanel ;
    private final ImageIcon image ;
    private JLabel bg ;
    private JButton KS ;
    private JButton TS ;
    private KSframe kSframe ;
    private FramePoints tSframe ;

    private  MainFrame f ;
    public MainFrame(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() * 0.8);
        int height = (int) (screenSize.getHeight() * 0.8);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setTitle("Problems for GA");
        f = this ;


        image = new ImageIcon(new ImageIcon("GENETIC_KP/background.jpg").getImage().getScaledInstance(120*10, 80*10, Image.SCALE_DEFAULT)) ;
        bg= new JLabel(image);
        bg.setLayout(null);


        KS = new JButton("KnapStack Problem") ;
        KS.setHorizontalTextPosition(SwingConstants.CENTER);
        KS.setSize(250, 50);
        KS.setForeground(Color.BLACK);
        KS.setFont(new Font("Dialog", Font.PLAIN,15));
        KS.setLocation(getWidth()/2-250/2,getHeight()/2-70);

        KS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                kSframe = new KSframe(f) ;
            }
        });


        TS = new JButton("Travelling SalesMan Problem") ;
        TS.setHorizontalTextPosition(SwingConstants.CENTER);
        TS.setSize(250, 50);
        TS.setForeground(Color.BLACK);
        TS.setFont(new Font("Dialog", Font.PLAIN,15));
        TS.setLocation(getWidth()/2-250/2,(getHeight()/2+70));

        TS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                tSframe = new FramePoints(new Population(), f);

                // Trigger the start button click event.
                tSframe.start.doClick();
            }
        });


        bg.add(TS) ;
        bg.add(KS) ;
        this.add(bg) ;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public FramePoints getframePoints(){
        return tSframe;
    }

    public KSframe getKSframe(){
        return kSframe ;
    }
}
