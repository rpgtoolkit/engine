package net.rpgtoolkit.engine.scripts.lua;

import net.rpgtoolkit.engine.scripts.Script;

import org.luaj.vm2.LuaValue;

/**
 * @author Mario Badr
 */
public class LuaScript implements Script {
  private LuaValue chunk;

  public LuaScript(LuaValue chunk) {
    this.chunk = chunk;
  }

  @Override
  public void run() {
    chunk.call();
  }
}
