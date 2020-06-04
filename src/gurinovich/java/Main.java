package gurinovich.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame
{
    private boolean Stopped = false;

    private GraphicsDisplay display = new GraphicsDisplay();

    public Main()
    {
        super("idk");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        setSize(640, 480);
        setLocation((toolkit.getScreenSize().width - 640) / 2, (toolkit.getScreenSize().height - 480) / 2);
//////////

        JMenuBar Menu = new JMenuBar();

            JMenu BallsMenu = new JMenu("Balls");
            BallsMenu.setMaximumSize(BallsMenu.getPreferredSize());

                JMenuItem AddBalls = new JMenuItem("Add");////////////////////////////////////////////////////////Add
                AddBalls.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent)
                    {
                        String AddCountString = JOptionPane.showInputDialog(Main.this, "Balls count to add", "Adding", JOptionPane.QUESTION_MESSAGE);

                        try
                        {
                            int AddCount = Integer.parseInt(AddCountString);
                            display.AddBalls(AddCount);
                        }
                        catch (NumberFormatException e)
                        {
                            JOptionPane.showMessageDialog(Main.this, "you entered not a number", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });

            BallsMenu.add(AddBalls);
        Menu.add(BallsMenu);

            JMenu ManagementMenu = new JMenu("Management");
            ManagementMenu.setMaximumSize(ManagementMenu.getPreferredSize());

                JMenuItem StartStop = new JMenuItem("Stop");//////////////////////////////////////////////////////Start/Stop
                StartStop.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent)
                    {
                        if (!Stopped)
                        {
                            StartStop.setText("Start");
                            display.Stop();
                        }
                        else
                        {
                            StartStop.setText("Stop");
                            display.Start();
                        }

                        Stopped = !Stopped;
                    }
                });

            ManagementMenu.add(StartStop);

                JCheckBoxMenuItem WallMenu = new JCheckBoxMenuItem("Wall");///////////////////////////////////////////Wall
                WallMenu.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent)
                    {
                        display.setWallIsHere(WallMenu.isSelected());
                    }
                });

            ManagementMenu.add(WallMenu);
        Menu.add(ManagementMenu);

        setJMenuBar(Menu);

        getContentPane().add(display);
    }

    public static void main(String[] args)
    {
        Main Frame = new Main();
        Frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Frame.setVisible(true);
    }
}
