/**
 * Copyright (c) 2015, rpgtoolkit.net <help@rpgtoolkit.net>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of
 * the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.rpgtoolkit.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.opengl.Display;

/**
 *
 * @author Chris Hutchinson <chris@cshutchinson.com>
 */
public class Driver {
  private final static Logger LOGGER = Logger.getLogger(Driver.class.getName());

  public static LwjglApplicationConfiguration loadConfig(InputStream stream) throws IOException {
    Properties properties = new Properties();
    properties.load(stream);

    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

    config.width = Integer.parseInt(properties.getProperty("width", "1024"));
    config.height = Integer.parseInt(properties.getProperty("height", "768"));
    config.samples = Integer.parseInt(properties.getProperty("samples", "2"));
    config.title = properties.getProperty("title", "RPG Toolkit 4.x");

    return config;
  }

  public static void main(String[] args) {
    InputStream configStream = null;
    LwjglApplicationConfiguration config = null;

    try {
      //We loadScript a properties file from the classpath since gdx has not been setup yet
      configStream = Driver.class.getClassLoader().getResourceAsStream("config.properties");
      if(configStream == null) {
        throw new FileNotFoundException("config.properties");
      }

      //Parse the configuration
      config = loadConfig(configStream);

    } catch(IOException exception) {
      LOGGER.log(Level.SEVERE, "Could not load config.properties from classpath.", exception);
    } finally {
      if(configStream != null) {
        try {
          configStream.close();
        } catch (IOException exception) {
          LOGGER.log(Level.SEVERE, "Could not close config.properties.", exception);
        }
      }
    }

    if(config != null) {
      final LwjglApplication app = new LwjglApplication(new DesktopGame(), config);
      LOGGER.info("Game has started.");

      // Set window icon
      final Pixmap icon = new Pixmap(Gdx.files.internal("icon.png"));
      Display.setIcon(new ByteBuffer[]{icon.getPixels()});
    }
  }

}
