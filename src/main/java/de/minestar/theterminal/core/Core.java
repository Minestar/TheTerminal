package de.minestar.theterminal.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.bukkit.gemo.BukkitHTTP.HTTPCore;
import com.bukkit.gemo.BukkitHTTP.HTTPPlugin;

import de.minestar.minestarlibrary.AbstractCore;
import de.minestar.minestarlibrary.utils.ConsoleUtils;
import de.minestar.theterminal.http.TerminalHTTP;

public class Core extends AbstractCore {

    private static String NAME;

    // WEBSERVER
    private static HTTPPlugin thisHTTP;

    public Core() {
        NAME = "The Terminal";
    }

    @Override
    protected boolean commonEnable() {
        Plugin httpPlugin = Bukkit.getPluginManager().getPlugin("BukkitHTTP");
        if (httpPlugin != null) {
            if (!httpPlugin.isEnabled()) {
                Bukkit.getPluginManager().enablePlugin(httpPlugin);
            }
            HTTPCore http = (HTTPCore) httpPlugin;
            thisHTTP = new TerminalHTTP("terminal", "The Terminal", "TheTerminal/web", true);
            thisHTTP.setOwn404Page(true);
            http.registerPlugin(thisHTTP);
            return true;
        } else {
            ConsoleUtils.printError(NAME, "BukkitHTTP not found!");
            return false;
        }
    }
}