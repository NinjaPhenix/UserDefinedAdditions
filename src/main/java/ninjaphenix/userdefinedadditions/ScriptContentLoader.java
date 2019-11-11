package ninjaphenix.userdefinedadditions;

import net.minecraft.util.Identifier;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptContentLoader {
    public static final ScriptEngineManager SCRIPT_MANAGER = new ScriptEngineManager();

    public static void loadConfig() {

    }

    public static void loadData() {

    }

    private static void runScript(Identifier file, String script) {
        String extension = file.getPath().substring(file.getPath().lastIndexOf('.') + 1);
        ScriptEngine engine = SCRIPT_MANAGER.getEngineByExtension(extension);
        if (engine == null) {
            //TODO: log
            return;
        }
        ScriptContext ctx = engine.getContext();
        //TODO: adding readers to bindings
        engine.setContext(ctx);
        try
        {
            engine.eval(script);
        } catch (ScriptException e) {
            //TODO: log
        }
    }
}
