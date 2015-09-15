package net.rpgtoolkit.engine.scripts.lua;

import com.badlogic.gdx.Gdx;

import net.rpgtoolkit.engine.GameState;
import net.rpgtoolkit.engine.LogTags;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;

public class LuaGameState implements GameState {
  private LuaTable gameStateTable;

  public LuaGameState(LuaTable gameStateTable) {
    this.gameStateTable = gameStateTable;
  }

  @Override
  public void pause() {
    callFunction("pause");
  }

  @Override
  public void quit() {
    callFunction("quit");
  }

  @Override
  public void render() {
    callFunction("render");
  }

  @Override
  public void resume() {
    callFunction("resume");
  }

  @Override
  public void update() {
    callFunction("update");
  }

  private void callFunction(String functionName) {
    LuaValue function = gameStateTable.get(functionName);

    if(function.isfunction()) {
      function.call();
    } else {
      Gdx.app.debug(LogTags.LUA, "Could not find " + functionName + "function in lua game state.");
    }
  }
}
