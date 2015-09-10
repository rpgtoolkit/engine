package net.rpgtoolkit.engine.scripts.lua;

import org.luaj.vm2.LuaBoolean;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

/**
 * @author Mario Badr
 */
public class LuaGameFunctions extends TwoArgFunction {

  /**
   * Must have a default public constructor.
   */
  public LuaGameFunctions() {}

  @Override
  public LuaValue call(LuaValue moduleName, LuaValue environment) {
    LuaValue library = tableOf();   // Create an empty table

    // Add the Game functions to be exposed in lua
    //library.set("functionName", new function());

    environment.set(moduleName, library);

    return library;
  }

  static class PushGameState extends OneArgFunction {
    @Override
    public LuaValue call(LuaValue stateTable) {
      return LuaBoolean.TRUE;
    }
  }

  static class PopGameState extends ZeroArgFunction {
    @Override
    public LuaValue call() {
      return LuaBoolean.TRUE;
    }
  }
}
