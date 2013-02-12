/**
 * Created by Tishenko.
 * User: Tishenko
 * Datetime: 29.01.13 13:57
 */

package ru.lanit.bamboo.plugins;

import com.atlassian.bamboo.notification.AbstractNotification;
import com.atlassian.bamboo.template.TemplateRenderer;
import com.atlassian.spring.container.ContainerManager;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class StartBuildNotification extends AbstractNotification {
    private final static String TEMPLATE_RENDERER = "templateRenderer";

    private Map<String, Object> context;
    private TemplateRenderer templateRenderer;

    public StartBuildNotification() {
        templateRenderer = (TemplateRenderer) ContainerManager.getComponent(TEMPLATE_RENDERER);
    }

    @NotNull
    public String getDescription() {
        return "";
    }

    public String getTextEmailContent() throws Exception {
        return templateRenderer.render("StartBuildNotificationTextEmail.ftl", context);
    }

    public String getHtmlEmailContent() throws Exception {
        return templateRenderer.render("StartBuildNotificationHtmlEmail.ftl", context);
    }

    public String getEmailSubject() throws Exception {
        return templateRenderer.render("StartBuildNotificationEmailSubject.ftl", context);
    }

    public String getIMContent() {
        return templateRenderer.render("StartBuildNotificationIm.ftl", context);
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }
}
