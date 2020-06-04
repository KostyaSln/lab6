package gurinovich.java;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball implements Runnable
{
    private double MinRadius = 10, MaxRadius = 40;

    private int Radius;
    private double Angle;
    private double Speed, XSpeed, YSpeed, MaxSpeed = 15;
    private int x, y;

    private Color color;

    private GraphicsDisplay display;

    private Wall wall = new Wall(0, 0);

    public void setWall(Wall wall)
    {
        this.wall = wall;
    }

    public Ball(GraphicsDisplay display)
    {
        this.display = display;

        Radius = (int) (Math.random() * (MaxRadius - MinRadius) + MinRadius);

        Angle = Math.random() * (2 * Math.PI);

        Speed = MaxRadius / Radius * 2;
        if (Speed > MaxSpeed)
            Speed = MaxSpeed;
        XSpeed = Speed * Math.cos(Angle);
        YSpeed = Speed * Math.sin(Angle);

        x = (int) (Math.random() * (display.getSize().width - 2 * Radius) + Radius);
        y = (int) (Math.random() * (display.getSize().height - 2 * Radius) + Radius);

        color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());

        Thread ThisThread = new Thread(this);
        ThisThread.start();
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                display.CanMove();

                if(x + XSpeed <= Radius)//left
                {
                    x = Radius;
                    XSpeed = -XSpeed;
                }
                else if(y + YSpeed <= Radius)//up
                {
                    y = Radius;
                    YSpeed = -YSpeed;
                }
                else if(x + XSpeed >= display.getWidth() - Radius)//right
                {
                    x = display.getWidth() - Radius;
                    XSpeed = -XSpeed;
                }
                else if(y + YSpeed >= display.getHeight() - Radius)//down
                {
                    y = display.getHeight() - Radius;
                    YSpeed = -YSpeed;
                }
                else if(x + XSpeed >= wall.x - Radius && x <= wall.x + wall.Weight && y >= wall.y && y <= wall.y + wall.Height)//left wall
                {
                    x = wall.x - Radius;
                    XSpeed = -XSpeed;
                }
                else if(y + YSpeed >= wall.y - Radius && y <= wall.y + wall.Height && x >= wall.x && x <= wall.x + wall.Weight)//up wall
                {
                    y = wall.y - Radius;
                    YSpeed = -YSpeed;
                }
                else if(x + XSpeed <= wall.x + wall.Weight + Radius && x >= wall.x && y >= wall.y && y <= wall.y + wall.Height)//right wall
                {
                    x = wall.x + wall.Weight + Radius;
                    XSpeed = -XSpeed;
                }
                else if(y + YSpeed <= wall.y + wall.Height + Radius && y >= wall.y && x >= wall.x && x <= wall.x + wall.Weight)//down wall
                {
                    y = wall.y + wall.Height + Radius;
                    YSpeed = -YSpeed;
                }
                else
                {
                    x += XSpeed;
                    y += YSpeed;
                }

                Thread.sleep((long) (MaxSpeed + 1 - Speed));
            }
        }
        catch (InterruptedException e)
        {}
    }

    public void paint(Graphics2D canvas)
    {
        canvas.setColor(color);
        canvas.setPaint(color);
        Ellipse2D.Double Ball = new Ellipse2D.Double(x - Radius, y - Radius, 2 * Radius, 2 * Radius);
        canvas.draw(Ball);
        canvas.fill(Ball);
    }
}
