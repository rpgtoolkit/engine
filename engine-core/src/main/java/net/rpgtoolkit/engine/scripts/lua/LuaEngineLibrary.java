package net.rpgtoolkit.engine.scripts.lua;

import org.luaj.vm2.LuaTable;

/**
 * @author Mario Badr
 */
public class LuaEngineLibrary {
  public static final String NAME = "tk";

  public static LuaTable create() {
    LuaTable library = new LuaTable();

    library.set(LuaGameLibrary.NAME, LuaGameLibrary.create());

    return library;
  }
}
