/**
 * Created by Tishenko.
 * User: Tishenko
 * Datetime: 01.02.13 14:14
 */

package ru.lanit.bamboo.plugins;

import com.atlassian.bamboo.chains.Chain;
import com.atlassian.bamboo.chains.ChainExecution;
import com.atlassian.bamboo.notification.*;
import com.atlassian.bamboo.plan.Plan;
import com.atlassian.bamboo.v2.build.BuildContext;
import com.atlassian.bamboo.v2.build.trigger.TriggerReason;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class PreActionHelper {
    private final static String START_DATETIME_FORMAT = "yyyy/MM/dd HH:mm:ss";

    private final static String NOTIF_BUILD = "build";
    private final static String NOTIF_BUILD_NUMBER = "buildNumber";
    private final static String NOTIF_BUILD_KEY = "buildKey";
    private final static String NOTIF_START_DATETIME = "startDateTime";
    private final static String NOTIF_TRIGGER_REASON = "triggerReasonDescription";
    private final static String NOTIF_STATUS_MESSAGE = "statusMessage";
    private final static String MESS_BUILD_STARTED = "Build plan was started";
    private final static String MESS_JOB_STARTED = "Job was started";

    private NotificationManager notificationManager;
    private NotificationDispatcher notificationDispatcher;

    protected Map<String, Object> prepeareNotificationContext(BuildContext buildContext, Plan plan) {
        return prepeareNotificationContext(
                buildContext.getBuildNumber(),
                buildContext.getBuildResultKey(),
                buildContext.getTriggerReason(),
                MESS_JOB_STARTED,
                plan);
    }

    protected Map<String, Object> prepeareNotificationContext(ChainExecution chainExecution, Chain chain) {
        return prepeareNotificationContext(
                chainExecution.getPlanResultKey().getBuildNumber(),
                chainExecution.getPlanResultKey().getKey(),
                chainExecution.getTriggerReason(),
                MESS_BUILD_STARTED,
                chain);
    }

    protected HashSet<NotificationRecipient> getRecipients(Plan plan, Class<? extends AbstractNotificationType> notificationTypeClass) {
        HashSet<NotificationRecipient> recipients = new HashSet<NotificationRecipient>();

        Set<NotificationRule> rules = notificationManager.getNotificationRules(plan);
        for (NotificationRule rule : rules) {
            NotificationType notificationType = rule.getNotificationType();
            if (notificationTypeClass.isAssignableFrom(notificationType.getClass())) {
                NotificationRecipient recipient = rule.getNotificationRecipient();
                recipients.add(recipient);
            }
        }

        return recipients;
    }

    protected boolean sendMessage(Map<String, Object> notificationContext, HashSet<NotificationRecipient> recipients) {
        if (recipients.size() > 0) {
            StartBuildNotification notification = new StartBuildNotification();
            notification.setContext(notificationContext);
            notification.setNotificationRecipients(recipients);
            notificationDispatcher.dispatchNotifications(notification);
        }
        return recipients.size() > 0;
    }

    private Map<String, Object> prepeareNotificationContext(
            int buildNumber, String BuildResultKey, TriggerReason triggerReason, String message, Plan plan) {
        DateFormat dateFormat = new SimpleDateFormat(START_DATETIME_FORMAT);
        Date date = new Date();

        Map<String, Object> context = new HashMap<String, Object>();

        context.put(NOTIF_BUILD, plan);
        context.put(NOTIF_BUILD_NUMBER, buildNumber);
        context.put(NOTIF_BUILD_KEY, BuildResultKey);
        context.put(NOTIF_START_DATETIME, dateFormat.format(date));
        context.put(NOTIF_TRIGGER_REASON, triggerReason.getNameForSentence());
        context.put(NOTIF_STATUS_MESSAGE, message);

        return context;
    }

    public NotificationManager getNotificationManager() {
        return notificationManager;
    }

    public void setNotificationManager(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    public NotificationDispatcher getNotificationDispatcher() {
        return notificationDispatcher;
    }

    public void setNotificationDispatcher(NotificationDispatcher notificationDispatcher) {
        this.notificationDispatcher = notificationDispatcher;
    }
}
