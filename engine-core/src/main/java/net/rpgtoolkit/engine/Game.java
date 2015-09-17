/**
 * Copyright (c) 2015, rpgtoolkit.net <help@rpgtoolkit.net>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of
 * the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.rpgtoolkit.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.Stack;

/**
 * The Game class manages GameStates. Only one GameState can be active at any time. The active
 * GameState is the one that is updated and rendered. Inactive GameStates are paused until they
 * become active again.
 *
 * @author Chris Hutchinson <chris@cshutchinson.com>
 * @author Mario Badr
 */
public enum Game {
  INSTANCE; //Singleton

  private Stack<GameState> gameStates;

  private AssetManager assetManager;

  private Batch batch;

  Game() {
    this.gameStates = new Stack<>();
  }

  public void create() {
    this.assetManager = new AssetManager();
    this.batch = new SpriteBatch();
    this.batch.begin();
  }

  public AssetManager getAssetManager() {
    return assetManager;
  }

  public Batch getBatch() {
    return batch;
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

    // We do end first to allow draw calls to happen anywhere (i.e. not just in this function)
    batch.end();
    batch.begin();
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
    try {
      // Continue loading assets asynchronously
      assetManager.update();
    } catch (GdxRuntimeException exception) {
      Gdx.app.error(LogTags.TK, "During update: an unexpected error occurred.",
          exception);
    }

    if(!gameStates.empty()) {
      gameStates.peek().update();
    }
  }

}
