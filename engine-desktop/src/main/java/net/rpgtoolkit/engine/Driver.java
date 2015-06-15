/**
 * Copyright (c) 2015, rpgtoolkit.net <help@rpgtoolkit.net>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.rpgtoolkit.engine;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;
import java.nio.ByteBuffer;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Chris Hutchinson <chris@cshutchinson.com>
 */
public class Driver {

    public static class DesktopListener
            extends ApplicationAdapter {

        private final Engine engine;

        public DesktopListener(Engine engine) {
            this.engine = engine;
        }

        @Override
        public void render() {
            this.engine.render();
        }

    }

    public static void main(String[] args) {

        final Engine engine = new Engine();

        LwjglApplicationConfiguration config
                = new LwjglApplicationConfiguration();

        config.width = 1024;
        config.height = 768;
        config.samples = 2;
        config.title = "RPG Toolkit 4.x";

        final LwjglApplication app = new LwjglApplication(
                new DesktopListener(engine), config);

        // Set window icon

        final Pixmap icon = new Pixmap(
                Gdx.files.internal("icon.png"));
        
        Display.setIcon(new ByteBuffer[] {
            icon.getPixels()
        });

    }

}
