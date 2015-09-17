package net.rpgtoolkit.engine.scripts.lua;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;

import net.rpgtoolkit.engine.Game;

import org.luaj.vm2.LuaBoolean;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Mario Badr
 */
public class LuaGraphicsLibrary {
  private final static Logger LOGGER = Logger.getLogger(LuaGraphicsLibrary.class.getName());

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
                    Game.INSTANCE.getBatch().draw(texture, x.tofloat(), y.tofloat(),
                        width.tofloat(), height.tofloat());
                  } else {
                    LOGGER.warning("Expected width, height to be LuaNumbers, got " + width + " and "
                        + height);

                    return LuaBoolean.FALSE;
                  }
                } else {
                  LOGGER.warning("Unexpected number of arguments.");

                  return LuaBoolean.FALSE;
                }
              } else {
                LOGGER.warning("Expected x, y to be LuaNumbers, got " + x + " and " + y);

                return LuaBoolean.FALSE;
              }
            }
          } catch (GdxRuntimeException exception) {
            LOGGER.log(Level.SEVERE, "Asset could not be drawn.", exception);

            return LuaBoolean.FALSE;
          }

          return LuaBoolean.TRUE;
        } else {
          LOGGER.info("Asset: <" + filename.checkjstring() + "> is still loading.");

          // There was no error, the asset is just being loaded, so we return true
          return LuaBoolean.TRUE;
        }
      }

      return LuaBoolean.FALSE;
    }
  }

}
