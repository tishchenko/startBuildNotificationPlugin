[#import "NotificationCommonsText.ftl" as nc ]
----------------------------------------------------------------------------
[@nc.buildNotificationTitleText build buildNumber/] was started at ${startDateTime}
----------------------------------------------------------------------------

${baseUrl}/browse/${buildKey}/

[@nc.showEmailFooter/]
