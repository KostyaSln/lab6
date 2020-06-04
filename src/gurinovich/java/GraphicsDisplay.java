package gurinovich.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GraphicsDisplay extends JPanel
{
    private ArrayList<Ball> Balls = new ArrayList<Ball>();
    private Wall wall = new Wall(0, 0);

    private MyMouseAdapter adapter;

    private Timer repaintTimer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent ev)
        {
            repaint();
        }
    });

    private boolean Stopped = false;
    private boolean WallIsHere = false;

    public synchronized void Start()
    {
        Stopped = false;
        notifyAll();
    }

    public synchronized void Stop()
    {
        Stopped = true;
    }

    public GraphicsDisplay()
    {
        adapter = new MyMouseAdapter();
        addMouseListener(adapter);
        addMouseMotionListener(adapter);
        setBackground(Color.WHITE);
        repaintTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D canvas = (Graphics2D)g;

        for (int i = 0; i < Balls.size(); i++)
        {
            Balls.get(i).paint(canvas);
            Balls.get(i).setWall(wall);
        }

        if (WallIsHere)
        {
            wall.SetSize(35, 135);
            wall.paint(canvas);
        }
        else
        {
            wall.SetSize(0, 0);
            wall.SetPosition(-2, -2);
        }
    }

    public void AddBalls(int count)
    {
        for (int i = 0; i < count; i++)
            Balls.add(new Ball(this));
    }

    public synchronized void CanMove()
    {
        try
        {
            if (Stopped)
            {
                wait();
            }
        }
        catch(InterruptedException e)
        {}
    }

    public void setWallIsHere(boolean wallIsHere)
    {
        WallIsHere = wallIsHere;
    }

    private class MyMouseAdapter extends MouseAdapter
    {
        @Override
        public void mouseMoved(MouseEvent e)
        {
            if (WallIsHere)
            {
                wall.SetPosition(e.getX(), e.getY());
                repaint();
            }

        }
    }
}
