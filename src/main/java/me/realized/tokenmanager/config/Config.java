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

package me.realized.tokenmanager.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import me.realized.tokenmanager.TokenManagerPlugin;
import me.realized.tokenmanager.util.config.AbstractConfiguration;
import me.realized.tokenmanager.util.config.convert.Converter;
import org.bukkit.configuration.file.FileConfiguration;

public class Config extends AbstractConfiguration<TokenManagerPlugin> {

    @Getter
    private int version;
    @Getter
    private boolean checkForUpdates;
    @Getter
    private String onlineMode;
    @Getter
    private boolean altPrevention;
    @Getter
    private int defaultBalance;
    @Getter
    private long sendMin;
    @Getter
    private long sendMax;
    @Getter
    private boolean openSelectedEnabled;
    @Getter
    private String openSelectedShop;
    @Getter
    private String confirmPurchaseTitle;
    @Getter
    private String confirmPurchaseConfirm;
    @Getter
    private String confirmPurchaseCancel;
    @Getter
    private int clickDelay;
    @Getter
    private boolean mysqlEnabled;
    @Getter
    private String mysqlUsername;
    @Getter
    private String mysqlPassword;
    @Getter
    private String mysqlHostname;
    @Getter
    private String mysqlPort;
    @Getter
    private String mysqlDatabase;
    @Getter
    private String mysqlTable;
    @Getter
    private boolean redisEnabled;
    @Getter
    private String redisServer;
    @Getter
    private int redisDatabase;
    @Getter
    private int redisPort;
    @Getter
    private String redisPassword;
    @Getter
    private boolean registerEconomy;
    @Getter
    private int balanceTopUpdateInterval;

    public Config(final TokenManagerPlugin plugin) {
        super(plugin, "config");
    }

    @Override
    protected void loadValues(FileConfiguration configuration) throws IOException {
        if (!configuration.isInt("config-version")) {
            configuration = convert(new Converter2_3());
        } else if (configuration.getInt("config-version") < getLatestVersion()) {
            configuration = convert(null);
        }

        version = configuration.getInt("config-version");
        checkForUpdates = configuration.getBoolean("check-for-updates", true);
        onlineMode = configuration.getString("online-mode", "auto");
        altPrevention = configuration.getBoolean("alt-prevention", false);
        defaultBalance = configuration.getInt("default-balance", 25);
        sendMin = configuration.getInt("send-amount-limit.min", 1);
        sendMax = configuration.getInt("send-amount-limit.max", -1);
        openSelectedEnabled = configuration.getBoolean("shop.open-selected.enabled", false);
        openSelectedShop = configuration.getString("shop.open-selected.shop", "test");
        confirmPurchaseTitle = configuration.getString("shop.confirm-purchase-gui.title", "Confirm Your Purchase");
        confirmPurchaseConfirm = configuration.getString("shop.confirm-purchase-gui.confirm-button", "STAINED_CLAY:5 1 name:&a&lBUY lore:&7Price:_&a%price%_tokens");
        confirmPurchaseCancel = configuration.getString("shop.confirm-purchase-gui.cancel-button", "STAINED_CLAY:14 1 name:&c&lCANCEL");
        clickDelay = configuration.getInt("shop.click-delay", 0);
        mysqlEnabled = configuration.getBoolean("data.mysql.enabled", false);
        mysqlUsername = configuration.getString("data.mysql.username", "root");
        mysqlPassword = configuration.getString("data.mysql.password", "password");
        mysqlHostname = configuration.getString("data.mysql.hostname", "127.0.0.1");
        mysqlPort = configuration.getString("data.mysql.port", "3306");
        mysqlDatabase = configuration.getString("data.mysql.database", "database");
        mysqlTable = configuration.getString("data.mysql.table", "tokenmanager");
        redisEnabled = configuration.getBoolean("data.mysql.redis.enabled", false);
        redisServer = configuration.getString("data.mysql.redis.server", "127.0.0.1");
        redisDatabase = configuration.getInt("data.mysql.redis.database", 0);
        redisPort = configuration.getInt("data.mysql.redis.port", 6379);
        redisPassword = configuration.getString("data.mysql.redis.password", null);
        registerEconomy = configuration.getBoolean("data.register-economy", false);
        balanceTopUpdateInterval = configuration.getInt("data.balance-top-update-interval", 5);
    }

    private class Converter2_3 implements Converter {

        Converter2_3() {}

        @Override
        public Map<String, String> renamedKeys() {
            final Map<String, String> keys = new HashMap<>();
            keys.put("use-default.enabled", "shop.open-selected.enabled");
            keys.put("use-default.shop", "shop.open-selected.shop");
            keys.put("mysql.enabled", "data.mysql.enabled");
            keys.put("mysql.hostname", "data.mysql.hostname");
            keys.put("mysql.port", "data.mysql.port");
            keys.put("mysql.username", "data.mysql.username");
            keys.put("mysql.password", "data.mysql.password");
            keys.put("mysql.database", "data.mysql.database");
            keys.put("click-delay", "shop.click-delay");
            keys.put("update-balance-top", "data.balance-top-update-interval");
            keys.put("vault-hooks", "data.register-economy");
            return keys;
        }
    }
}
