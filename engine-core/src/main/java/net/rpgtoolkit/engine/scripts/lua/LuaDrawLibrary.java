package net.rpgtoolkit.engine.scripts.lua;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;

import net.rpgtoolkit.engine.Game;
import net.rpgtoolkit.engine.LogTags;

import org.luaj.vm2.LuaBoolean;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;

/**
 * @author Mario Badr
 */
public class LuaDrawLibrary {
  public static final String NAME = "draw";

  public static LuaTable create() {
    LuaTable library = new LuaTable();

    library.set(DrawTexture.NAME, new LuaDrawLibrary.DrawTexture());

    return library;
  }

  public static class DrawTexture extends VarArgFunction {
    public static final String NAME = "texture";

    @Override
    public LuaValue invoke(Varargs args) {
      LuaValue filename = args.arg1();

      if (filename.isstring()) {
        AssetManager assetManager = Game.INSTANCE.getAssetManager();

        if (assetManager.isLoaded(filename.checkjstring())) {
          try {
            Texture texture = assetManager.get(filename.checkjstring());

            if(args.narg() == 1) {
              Game.INSTANCE.getBatch().draw(texture, 0, 0);
            }
          } catch (GdxRuntimeException exception) {
            Gdx.app.error(LogTags.TK, "During DrawTexture: asset could not be drawn.", exception);
          }

          return LuaBoolean.TRUE;
        } else {
          Gdx.app.log(LogTags.TK, "During DrawTexture: asset is still loading.");
        }
      }

      return LuaBoolean.FALSE;
    }
  }

}
