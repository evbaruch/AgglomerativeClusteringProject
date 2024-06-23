package Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

public class MainPanel extends JPanel implements ActionListener
{
    private  int scale;
    Timer timer;
     int x0,y0;
    private int index=0;
    Set<Set<TwoDPoint>> pointClusters;
    JButton endButt;
    Color[] basicColors = {Color.RED, Color.GREEN, Color.BLUE, Color.BLACK, Color.CYAN, Color.DARK_GRAY, Color.GRAY,
        Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.WHITE, Color.YELLOW};

    public MainPanel(Set<Set<TwoDPoint>> pointsClusterSet)
    {
        super();
        pointClusters = pointsClusterSet;
        setLayout(null);
        setPreferredSize(new Dimension(600,600));
        endButt=new JButton("סיים");
        endButt.addActionListener(this);
        endButt.setBounds(200,550,200,50);

        add(endButt);

        index=0;
        x0=20;
        y0=50;
        scale=15;
        timer=new Timer(10,this);
        timer.start();
    }

    public void paintComponent (Graphics page) {
        super.paintComponent(page);
        int colorIndex = 0;
        for (Set<TwoDPoint> points : pointClusters) {
            page.setColor(basicColors[colorIndex%basicColors.length]);
            colorIndex++;
            for (TwoDPoint p : points) {
                page.fillOval((int) (p.x * scale) + x0, (int) (p.y * scale) + y0, 10, 10);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==endButt)
        {
            System.exit(0);
        }
        index=(index+1)% pointClusters.size();
        repaint();
    }
}
