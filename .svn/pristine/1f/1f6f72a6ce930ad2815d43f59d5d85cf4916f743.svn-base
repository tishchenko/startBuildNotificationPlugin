/**
 * Created by Tishenko.
 * User: Tishenko
 * Datetime: 01.02.13 13:52
 */

package ru.lanit.bamboo.plugins;

import com.atlassian.bamboo.chains.Chain;
import com.atlassian.bamboo.chains.ChainExecution;
import com.atlassian.bamboo.chains.plugins.PreChainAction;
import com.atlassian.bamboo.notification.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Map;

public class StartChainAction extends PreActionHelper implements PreChainAction {

    public void execute(@NotNull Chain chain, @NotNull ChainExecution chainExecution) throws InterruptedException, Exception {
        HashSet<NotificationRecipient> recipients = getRecipients(chain, StartChainNotificationType.class);
        Map<String, Object> notificationContext = prepeareNotificationContext(chainExecution, chain);
        sendMessage(notificationContext, recipients);
    }
}
