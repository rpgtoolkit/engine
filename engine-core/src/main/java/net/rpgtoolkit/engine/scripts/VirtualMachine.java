package net.rpgtoolkit.engine.scripts;


/**
 * @author Mario Badr
 */
public interface VirtualMachine {

  /**
   * Load a script into memory.
   *
   * @param sourceCode The source code in string format. Useful to inline scripts
   * @return A runnable Script, or an EmptyScript if an error occurred
   */
  Script loadScript(String sourceCode);
}
