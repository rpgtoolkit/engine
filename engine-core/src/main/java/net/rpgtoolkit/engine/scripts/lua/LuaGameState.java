package net.rpgtoolkit.engine.scripts.lua;

import net.rpgtoolkit.engine.GameState;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;

import java.util.logging.Logger;

public class LuaGameState implements GameState {
  private final static Logger LOGGER = Logger.getLogger(LuaGameState.class.getName());

  private LuaTable gameStateTable;

  public LuaGameState(LuaTable gameStateTable) {
    this.gameStateTable = gameStateTable;

    initialize();
  }

  @Override
  public void initialize() {
    callFunction("initialize");
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
      LOGGER.warning("Could not find " + functionName + "function in lua game state.");
    }
  }
}
