package com.pewa.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by phonik on 2017-07-25.
 */
public class PewaSystemTray {
    private static TrayIcon trayIcon = null;
    public static final Logger log = LogManager.getLogger(PewaSystemTray.class);
    private static final String trayError = "Tray icon not supported";

    // TODO - menu tray, jakie opcje?

    public static void loadTray() {
        if (SystemTray.isSupported()) {
            final SystemTray tray = SystemTray.getSystemTray();
            final Image image = Toolkit.getDefaultToolkit().getImage("src/main/resources/bulb16x16.gif");
            final String trayName = "Pewa";

            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("domyślna akcja");
                }
            };

            trayIcon = new TrayIcon(image, trayName);
            trayIcon.addActionListener(listener);
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                log.error(e.getMessage());
            }
        } else {
            log.error(trayError);
        }
    }



}
