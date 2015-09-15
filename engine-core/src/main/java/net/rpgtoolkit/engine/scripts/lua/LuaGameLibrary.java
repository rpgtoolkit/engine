package net.rpgtoolkit.engine.scripts.lua;

import com.badlogic.gdx.Gdx;

import net.rpgtoolkit.engine.Game;
import net.rpgtoolkit.engine.GameState;
import net.rpgtoolkit.engine.LogTags;

import org.luaj.vm2.LuaBoolean;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

/**
 * @author Mario Badr
 */
public class LuaGameLibrary {
  public static final String NAME = "game";

  public static LuaTable create() {
    LuaTable library = new LuaTable();

    library.set("push", new LuaGameLibrary.PushGameState());
    library.set("pop", new LuaGameLibrary.PopGameState());

    return library;
  }

  public static class PushGameState extends OneArgFunction {
    @Override
    public LuaValue call(LuaValue stateTable) {
      if(stateTable.istable()) {
        GameState gameState = new LuaGameState((LuaTable) stateTable);
        Game.INSTANCE.push(gameState);

        return LuaBoolean.TRUE;
      }

      Gdx.app.debug(LogTags.LUA, "During PushGameState: expected LuaTable, got " + stateTable +
          ".");
      return LuaBoolean.FALSE;
    }
  }

  public static class PopGameState extends ZeroArgFunction {
    @Override
    public LuaValue call() {
      Game.INSTANCE.pop();

      return LuaBoolean.TRUE;
    }
  }
}
