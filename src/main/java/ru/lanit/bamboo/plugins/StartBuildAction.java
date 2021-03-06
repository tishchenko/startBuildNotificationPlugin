package ru.lanit.bamboo.plugins;

import com.atlassian.bamboo.build.CustomPreBuildAction;
import com.atlassian.bamboo.notification.*;
import com.atlassian.bamboo.plan.Plan;
import com.atlassian.bamboo.plan.PlanManager;
import com.atlassian.bamboo.utils.error.ErrorCollection;
import com.atlassian.bamboo.v2.build.BuildContext;
import com.atlassian.bamboo.ww2.actions.build.admin.create.BuildConfiguration;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.atlassian.sal.api.transaction.TransactionTemplate;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * User: Tishenko
 * Datetime: 28.01.13 14:52
 */
public class StartBuildAction extends PreActionHelper implements CustomPreBuildAction {
    private BuildContext buildContext;

    private PlanManager planManager;
    private TransactionTemplate transactionTemplate;

    public void init(@NotNull final BuildContext buildContext) {
        this.buildContext = buildContext;
        final Long planId = buildContext.getPlanId();

        if (transactionTemplate != null)
            transactionTemplate.execute(new TransactionCallback() {
                public Object doInTransaction() {
                    Plan plan = planManager.getPlanById(planId);
                    HashSet<NotificationRecipient> recipients = getRecipients(plan, StartBuildNotificationType.class);
                    Map<String, Object> notificationContext = prepeareNotificationContext(buildContext, plan);
                    sendMessage(notificationContext, recipients);
                    return null;
                }
            });
        //else System.out.println("--- [transactionTemplate is " + transactionTemplate + "] ---");
    }

    public ErrorCollection validate(BuildConfiguration buildConfiguration) {
        return null;
    }

    @NotNull
    public BuildContext call() throws InterruptedException, Exception {
        return this.buildContext;
    }

    public PlanManager getPlanManager() {
        return planManager;
    }

    public void setPlanManager(PlanManager planManager) {
        this.planManager = planManager;
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}
