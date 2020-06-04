package gurinovich.java;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Wall implements Runnable
{
    int Weight, Height;
    int x = -2, y = -2;

    public Wall()
    {
        Weight = 35;
        Height = 135;

        Thread thisThread = new Thread(this);
        thisThread.start();
    }

    public Wall(int Weight, int Height)
    {
        this.Weight = Weight;
        this.Height = Height;

        Thread thisThread = new Thread(this);
        thisThread.start();
    }

    @Override
    public void run()
    {
        while (true)
        {

        }
    }

    public void SetPosition(int x, int y)
    {
        this.x = x - Weight / 2;
        this.y = y - Height / 2;
    }

    public void SetSize(int Weight, int Height)
    {
        this.Weight = Weight;
        this.Height = Height;
    }

    public void paint(Graphics2D canvas)
    {
        canvas.setColor(Color.BLACK);
        canvas.setPaint(Color.BLACK);
        Rectangle2D.Double rectangle = new Rectangle2D.Double(x, y, Weight, Height);
        canvas.draw(rectangle);
        canvas.fill(rectangle);
    }
}
