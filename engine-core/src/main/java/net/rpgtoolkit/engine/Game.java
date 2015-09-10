/**
 * Copyright (c) 2015, rpgtoolkit.net <help@rpgtoolkit.net>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of
 * the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.rpgtoolkit.engine;

import java.util.Stack;

/**
 * The Game class manages GameStates. Only one GameState can be active at any time. The active
 * GameState is the one that is updated and rendered. Inactive GameStates are paused until they
 * become active again.
 *
 * @author Chris Hutchinson <chris@cshutchinson.com>
 * @author Mario Badr
 */
public class Game {

  private Stack<GameState> gameStates;

  public Game() {
    this.gameStates = new Stack<>();
  }

  /**
   * Pause the active GameState, if one exists.
   */
  public void pause() {
    if(!gameStates.empty()) {
      gameStates.peek().pause();
    }
  }

  /**
   * Pop the current GameState off the stack, removing it from memory. The next GameState on the
   * stack, if it exists, will be made active.
   */
  public void pop() {
    if(!gameStates.empty()) {
      gameStates.pop();
    }

    if(!gameStates.empty()) {
      gameStates.peek().resume();
    }
  }

  /**
   * Put a new GameState at the top of the stack, making it the active GameState that will be
   * regularly updated and rendered to the screen.
   *
   * @param state The new GameState
   */
  public void push(GameState state) {
    if(!gameStates.empty()) {
      gameStates.peek().pause();
    }

    gameStates.push(state);
  }

  /**
   * Exit out of all GameStates being managed.
   */
  public void quit() {
    for(GameState gameState : gameStates) {
      gameState.quit();
    }

    gameStates.clear();
  }

  /**
   * Render the active GameState onto the screen.
   */
  public void render() {
    if(!gameStates.empty()) {
      gameStates.peek().render();
    }
  }

  /**
   * Resume the last active GameState.
   */
  public void resume() {
    if(!gameStates.empty()) {
      gameStates.peek().resume();
    }
  }

  /**
   * Update the game logic of the active GameState by one time unit.
   */
  public void update() {
    if(!gameStates.empty()) {
      gameStates.peek().update();
    }
  }

}
