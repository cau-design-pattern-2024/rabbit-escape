package rabbitescape.ui.swing;

import static rabbitescape.ui.swing.SwingConfigSetup.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;

import javax.swing.*;

import rabbitescape.engine.config.Config;
import rabbitescape.engine.config.ConfigTools;
import rabbitescape.render.androidlike.Sound;

public class MainJFrame extends JFrame
{
    private static final long serialVersionUID = 1L;
    private Point normalLocation; // 원래 위치 기억용

    private class Listener extends EmptyListener
    {
        private final MainJFrame frame;

        public Listener( MainJFrame frame )
        {
            this.frame = frame;
        }

        @Override
        public void windowClosing( WindowEvent e )
        {
            exit();
        }

        @Override
        public void componentMoved( ComponentEvent e )
        {
            ConfigTools.setInt( uiConfig, CFG_GAME_WINDOW_LEFT, frame.getX() );
            ConfigTools.setInt( uiConfig, CFG_GAME_WINDOW_TOP,  frame.getY() );
            uiConfig.save();
        }

        @Override
        public void componentResized( ComponentEvent e )
        {
            if ( !frame.isActive() )
            { // awt.EDT fires off a couple of events before the
              // frame is active the second changes the frame's size slightly.
              // protect against this.
                return;
            }
            ConfigTools.setInt(
                uiConfig, CFG_GAME_WINDOW_WIDTH,  frame.getWidth() );

            ConfigTools.setInt(
                uiConfig, CFG_GAME_WINDOW_HEIGHT, frame.getHeight() );

            uiConfig.save();

            updateScreenIfPaused();
        }

        @Override
        public void componentShown( ComponentEvent arg0 )
        {
            updateScreenIfPaused();
        }

        @Override
        public void windowActivated( WindowEvent arg0 )
        {
            updateScreenIfPaused();
        }

        private void updateScreenIfPaused()
        {
            if ( null != swingGraphics )
            {
                if ( swingGraphics.world.paused )
                {
                    new Thread() {
                        @Override
                        public void run()
                        {
                            swingGraphics.redraw();
                        }
                    }.start();
                }
            }
        }
    }

    private final Config uiConfig;
    private final Sound sound;
    private SwingGraphics swingGraphics = null;

    public MainJFrame( Config uiConfig, Sound sound )
    {
        this.uiConfig = uiConfig;
        this.sound = sound;

        Listener listener = new Listener( this );
        addWindowListener( listener );
        addComponentListener( listener );

        setBoundsFromConfig();

        setIcon();
    }

    public void setGraphics( SwingGraphics swingGraphics )
    {
        this.swingGraphics = swingGraphics;
    }

    private void setIcon()
    {
        SwingBitmapLoader l = new SwingBitmapLoader();
        int s = l.sizeFor( 128 );
        SwingBitmap bmp = l.load( "ic_launcher", s );
        this.setIconImage(bmp.image);
    }

    public void setBoundsFromConfig()
    {
        int x      = ConfigTools.getInt( uiConfig, CFG_GAME_WINDOW_LEFT );
        int y      = ConfigTools.getInt( uiConfig, CFG_GAME_WINDOW_TOP );
        int width  = ConfigTools.getInt( uiConfig, CFG_GAME_WINDOW_WIDTH );
        int height = ConfigTools.getInt( uiConfig, CFG_GAME_WINDOW_HEIGHT );

        if ( x != Integer.MIN_VALUE && y != Integer.MIN_VALUE )
        {
            setLocation( x, y );
        }

        if ( width != Integer.MIN_VALUE && y != Integer.MIN_VALUE )
        {
            setPreferredSize( new Dimension( width, height ) );
        }
    }

    public void shakeWindow() {
        final int shakeDistance = 10; // 흔들 강도 (픽셀)
        final int shakeDuration = 200; // 전체 흔들 시간 (밀리초)
        final int shakeInterval = 10; // 흔들 주기 (밀리초)

        if (!isVisible()) return;

        // 원래 위치 저장
        normalLocation = getLocationOnScreen();

        final Timer timer = new Timer(shakeInterval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point current = getLocationOnScreen();
                int x = normalLocation.x + (int) (Math.random() * shakeDistance * 2 - shakeDistance);
                int y = normalLocation.y + (int) (Math.random() * shakeDistance * 2 - shakeDistance);
                setLocation(x, y);
            }
        });

        timer.setRepeats(true);
        timer.start();

        // shakeDuration 이후에 타이머 정지하고 원위치로 복귀
        new Timer(shakeDuration, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                setLocation(normalLocation);
            }
        }).start();
    }

    public void exit()
    {
        sound.dispose();
        dispose();
        System.exit( 0 );
    }
}
