/**
 * Created by Tishenko.
 * User: Tishenko
 * Datetime: 28.01.13 15:06
 */

package ru.lanit.bamboo.plugins;

import com.atlassian.bamboo.notification.AbstractNotificationType;
import com.atlassian.event.Event;
import org.jetbrains.annotations.NotNull;


public class StartBuildNotificationType extends AbstractNotificationType {
    public boolean isNotificationRequired(@NotNull Event event) {
        return false;
    }
}
