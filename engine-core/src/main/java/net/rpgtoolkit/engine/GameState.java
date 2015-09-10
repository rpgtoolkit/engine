/**
 * Copyright (c) 2015, rpgtoolkit.net <help@rpgtoolkit.net>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.rpgtoolkit.engine;

/**
 *
 * @author Chris Hutchinson <chris@cshutchinson.com>
 */
public interface GameState {
  void pause();

  void quit();

  void render();

  void resume();

  void update();
}
