package net.rpgtoolkit.engine.scripts;

import net.rpgtoolkit.engine.GameState;

import java.io.Reader;

/**
 * @author Mario Badr
 */
public interface VirtualMachine {

  /**
   * Load a GameState from the script source code.
   *
   * @param reader Reader containing the text of the script
   * @param name Name of the script (e.g. file name)
   * @return A GameState that relies on the script, or an EmptyGameState if an error occurred
   */
  GameState loadGameState(Reader reader, String name);

  /**
   * Load a script into memory.
   *
   * @param reader Reader containing the text of the script
   * @param name Name of the script (e.g. file name)
   * @return A runnable Script, or an EmptyScript if an error occurred
   */
  Script loadScript(Reader reader, String name);

  /**
   * Load a script into memory.
   *
   * @param sourceCode The source code in string format. Useful to inline scripts
   * @return A runnable Script, or an EmptyScript if an error occurred
   */
  Script loadScript(String sourceCode);
}
