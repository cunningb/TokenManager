/*
 *
 *   This file is part of TokenManager, licensed under the MIT License.
 *
 *   Copyright (c) Realized
 *   Copyright (c) contributors
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 *
 */

package me.realized.tokenmanager.command.commands.subcommands;

import me.realized.tokenmanager.TokenManagerPlugin;
import me.realized.tokenmanager.command.BaseCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class VersionCommand extends BaseCommand {

    public VersionCommand(final TokenManagerPlugin plugin) {
        super(plugin, "version", "version", null, 1, false, "v");
    }

    @Override
    protected void execute(final CommandSender sender, final String label, final String[] args) {
        sender.sendMessage(ChatColor.BLUE + plugin.getDescription().getFullName());
        sender.sendMessage(ChatColor.BLUE + "Download: " + plugin.getDescription().getWebsite());
    }
}
