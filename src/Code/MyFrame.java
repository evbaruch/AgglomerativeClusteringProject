import java.awt.*;
import java.util.Set;

public class MyFrame extends Frame
{
  public MyFrame(Set<Set<TwoDPoint>> pointsClusterSet) throws HeadlessException
  {
    super("Clusters");
    this.setUndecorated(true);
    Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
    setLocation(d.width/2-300,d.height/2-300);
    add(new MainPanel(pointsClusterSet));
    pack();
    setVisible(true);
  }

 }
