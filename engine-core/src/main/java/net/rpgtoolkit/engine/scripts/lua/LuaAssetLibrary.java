package net.rpgtoolkit.engine.scripts.lua;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import net.rpgtoolkit.engine.Game;
import net.rpgtoolkit.engine.LogTags;

import org.luaj.vm2.LuaBoolean;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

/**
 * @author Mario Badr
 */
public class LuaAssetLibrary {
  public static final String NAME = "assets";

  public static LuaTable create() {
    LuaTable library = new LuaTable();

    library.set(LoadTexture.NAME, new LuaAssetLibrary.LoadTexture());

    return library;
  }

  public static class LoadTexture extends OneArgFunction {
    public static final String NAME = "loadTexture";

    @Override
    public LuaValue call(LuaValue filename) {
      if(filename.isstring()) {
        // Gdx's AssetManager takes care of caching the texture and loading it asynchronously
        Game.INSTANCE.getAssetManager().load(filename.checkjstring(), Texture.class);

        return LuaBoolean.TRUE;
      }

      Gdx.app.debug(LogTags.LUA, "During LoadTexture: expected LuaString, got " + filename + ".");
      return LuaBoolean.FALSE;
    }
  }
}
