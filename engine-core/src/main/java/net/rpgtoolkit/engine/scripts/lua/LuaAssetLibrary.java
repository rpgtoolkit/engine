package net.rpgtoolkit.engine.scripts.lua;

import com.badlogic.gdx.graphics.Texture;

import net.rpgtoolkit.engine.Game;

import org.luaj.vm2.LuaBoolean;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

import java.util.logging.Logger;

/**
 * @author Mario Badr
 */
public class LuaAssetLibrary {
  private final static Logger LOGGER = Logger.getLogger(LuaAssetLibrary.class.getName());
  public static final String NAME = "assets";

  public static LuaTable create() {
    LuaTable library = new LuaTable();

    library.set(FinishLoading.NAME, new LuaAssetLibrary.FinishLoading());
    library.set(LoadTexture.NAME, new LuaAssetLibrary.LoadTexture());


    return library;
  }

  public static class FinishLoading extends ZeroArgFunction {
    public static final String NAME = "finishLoading";

    @Override
    public LuaValue call() {
      Game.INSTANCE.getAssetManager().finishLoading();

      return LuaBoolean.TRUE;
    }
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

      LOGGER.warning("Expected LuaString, got " + filename + ".");
      return LuaBoolean.FALSE;
    }
  }
}
