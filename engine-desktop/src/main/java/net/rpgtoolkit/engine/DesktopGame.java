package net.rpgtoolkit.engine;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import net.rpgtoolkit.engine.scripts.Script;
import net.rpgtoolkit.engine.scripts.VirtualMachine;
import net.rpgtoolkit.engine.scripts.lua.LuaVirtualMachine;

/**
 * @author Mario Badr
 */
public class DesktopGame implements ApplicationListener {
  private final Game game;

  public DesktopGame() {
    this.game = Game.INSTANCE;
  }

  @Override
  public void create() {
    FileHandle scriptHandle = Gdx.files.internal("main.lua");
    VirtualMachine vm = new LuaVirtualMachine();
    Script script = vm.loadScript(scriptHandle.readString());
    script.run();

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
