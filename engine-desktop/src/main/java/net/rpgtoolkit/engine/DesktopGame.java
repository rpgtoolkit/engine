package net.rpgtoolkit.engine;

import com.badlogic.gdx.ApplicationListener;

/**
 * @author Mario Badr
 */
public class DesktopGame implements ApplicationListener {
  private final Game game;

  public DesktopGame() {
    this.game = new Game();
  }

  @Override
  public void create() {
    EmptyGameState initialState = new EmptyGameState();
    game.push(initialState);
  }

  @Override
  public void resize(int width, int height) {
    // TODO: figure out what to do on resize, if anything
  }

  @Override
  public void render() {
    game.update();
    game.render();
  }

  @Override
  public void pause() {
    game.pause();
  }

  @Override
  public void resume() {
    game.resume();
  }

  @Override
  public void dispose() {
    game.quit();
  }
}
