package net.rpgtoolkit.engine.scripts.lua;

import net.rpgtoolkit.engine.Game;
import net.rpgtoolkit.engine.GameState;

import org.luaj.vm2.LuaBoolean;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

import java.util.logging.Logger;

/**
 * @author Mario Badr
 */
public class LuaGameLibrary {
  private final static Logger LOGGER = Logger.getLogger(LuaGameLibrary.class.getName());
  public static final String NAME = "game";

  public static LuaTable create() {
    LuaTable library = new LuaTable();

    library.set(PushGameState.NAME, new LuaGameLibrary.PushGameState());
    library.set(PopGameState.NAME, new LuaGameLibrary.PopGameState());

    return library;
  }

  public static class PushGameState extends OneArgFunction {
    public static final String NAME = "push";

    @Override
    public LuaValue call(LuaValue stateTable) {
      if(stateTable.istable()) {
        GameState gameState = new LuaGameState((LuaTable) stateTable);
        Game.INSTANCE.push(gameState);

        return LuaBoolean.TRUE;
      }

      LOGGER.warning("Expected LuaTable, got " + stateTable + ".");
      return LuaBoolean.FALSE;
    }
  }

  public static class PopGameState extends ZeroArgFunction {
    public static final String NAME = "pop";

    @Override
    public LuaValue call() {
      Game.INSTANCE.pop();

      return LuaBoolean.TRUE;
    }
  }
}
