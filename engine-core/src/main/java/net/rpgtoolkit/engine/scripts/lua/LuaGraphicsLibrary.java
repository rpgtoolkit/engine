package net.rpgtoolkit.engine.scripts.lua;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;

import net.rpgtoolkit.engine.Game;
import net.rpgtoolkit.engine.LogTags;

import org.luaj.vm2.LuaBoolean;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

/**
 * @author Mario Badr
 */
public class LuaGraphicsLibrary {
  public static final String NAME = "graphics";

  public static LuaTable create() {
    LuaTable library = new LuaTable();

    library.set(ClearScreen.NAME, new LuaGraphicsLibrary.ClearScreen());
    library.set(DrawTexture.NAME, new LuaGraphicsLibrary.DrawTexture());

    return library;
  }

  public static class ClearScreen extends ZeroArgFunction {
    public static final String NAME = "clearScreen";
    @Override
    public LuaValue call() {
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      return LuaBoolean.TRUE;
    }
  }

  public static class DrawTexture extends VarArgFunction {
    public static final String NAME = "drawTexture";

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
            } else if (args.narg() >= 3) {
              LuaValue x = args.arg(2);
              LuaValue y = args.arg(3);

              if(x.isnumber() && y.isnumber()) {

                if(args.narg() == 3) {
                  Game.INSTANCE.getBatch().draw(texture, x.tofloat(), y.tofloat());
                } else if (args.narg() == 5) {
                  LuaValue width = args.arg(4);
                  LuaValue height = args.arg(5);

                  if(width.isnumber() && height.isnumber()) {
                    Game.INSTANCE.getBatch().draw(texture, x.tofloat(), y.tofloat(), width.tofloat(),
                        height.tofloat());
                  } else {
                    Gdx.app.error(LogTags.TK, "During DrawTexture: expected width, height to be " +
                        "LuaNumbers, got " + width + " and " + height);

                    return LuaBoolean.FALSE;
                  }
                } else {
                  Gdx.app.error(LogTags.TK, "During DrawTexture: unexpected number of arguments");

                  return LuaBoolean.FALSE;
                }
              } else {
                Gdx.app.error(LogTags.TK, "During DrawTexture: expected x, y to be LuaNumbers, got "
                    + x + " and " + y);

                return LuaBoolean.FALSE;
              }
            }
          } catch (GdxRuntimeException exception) {
            Gdx.app.error(LogTags.TK, "During DrawTexture: asset could not be drawn.", exception);

            return LuaBoolean.FALSE;
          }

          return LuaBoolean.TRUE;
        } else {
          Gdx.app.log(LogTags.TK, "During DrawTexture: asset is still loading.");

          // There was no error, the asset is just being loaded, so we return true
          return LuaBoolean.TRUE;
        }
      }

      return LuaBoolean.FALSE;
    }
  }

}
