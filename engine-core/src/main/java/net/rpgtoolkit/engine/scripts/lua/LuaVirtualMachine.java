package net.rpgtoolkit.engine.scripts.lua;

import net.rpgtoolkit.engine.EmptyGameState;
import net.rpgtoolkit.engine.GameState;
import net.rpgtoolkit.engine.scripts.EmptyScript;
import net.rpgtoolkit.engine.scripts.Script;
import net.rpgtoolkit.engine.scripts.VirtualMachine;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LoadState;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.PackageLib;
import org.luaj.vm2.lib.StringLib;
import org.luaj.vm2.lib.jse.JseBaseLib;
import org.luaj.vm2.lib.jse.JseMathLib;

import java.io.Reader;

/**
 * @author Mario Badr
 */
public class LuaVirtualMachine implements VirtualMachine {
  private final Globals globals;

  public LuaVirtualMachine() {
    this.globals = new Globals();

    // Create a lua sandbox by only including specific libraries
    this.globals.load(new JseBaseLib());  // Load lua's base library
    this.globals.load(new PackageLib());  // Load lua's package library
    this.globals.load(new StringLib());   // Load lua's string library
    this.globals.load(new JseMathLib());  // Load lua's math library

    // Register Java functions to be called from scripts
    this.globals.set(LuaEngineLibrary.NAME, LuaEngineLibrary.create());

    LoadState.install(this.globals); // TODO: Is this needed?
    LuaC.install(this.globals); // Set up the lua compiler with this sandbox

  }

  @Override
  public GameState loadGameState(Reader reader, String name) {
    try {
      return loadGameState(globals.load(reader, name));
    } catch (LuaError luaError) {
      luaError.printStackTrace();

      return new EmptyGameState();
    }
  }

  @Override
  public Script loadScript(Reader reader, String name) {
    try {
      return new LuaScript(globals.load(reader, name));
    } catch (LuaError luaError) {
      luaError.printStackTrace();

      return new EmptyScript();
    }
  }

  @Override
  public Script loadScript(String sourceCode) {
    try {
      return new LuaScript(globals.load(sourceCode));
    } catch (LuaError luaError) {
      luaError.printStackTrace();

      return new EmptyScript();
    }
  }

  private GameState loadGameState(LuaValue luaValue) {
    if(luaValue.istable()) {
      return new LuaGameState((LuaTable) luaValue);
    }

    return new EmptyGameState();
  }
}
