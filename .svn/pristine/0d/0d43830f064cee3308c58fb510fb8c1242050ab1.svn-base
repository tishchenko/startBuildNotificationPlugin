[#macro templateOuter baseUrl showTitleStatus=true statusMessage='']
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width"/>
    <style type="text/css">
        a:hover, a:focus {
            text-decoration: underline !important;
        }

        @media handheld, only screen and (max-device-width: 480px) {
            div, a, p, td, th, li, dt, dd {
                -webkit-text-size-adjust: auto;
            }

            small, small a {
                -webkit-text-size-adjust: 90%;
            }

            small[class=email-metadata] {
                -webkit-text-size-adjust: 93%;
                font-size: 12px;
            }

            table[id=email-wrap] > tbody > tr > td {
                padding: 2px !important;
            }

            table[id=email-wrap-inner] > tbody > tr > td {
                padding: 8px !important;
            }

            table[id=email-footer] td {
                padding: 8px 12px !important;
            }

            table[id=email-actions] td {
                padding-top: 0 !important;
            }

            table[id=email-actions] td.right {
                text-align: right !important;
            }

            table[id=email-actions] .email-list-item {
                display: block;
                margin: 1em 0 !important;
                word-wrap: normal !important;
            }

            span[class=email-list-divider] {
                display: none;
            }

            .commentsummary small[class=email-metadata] {
                display: block;
            }

            td.comment-avatar {
                padding: 8px 8px 0 8px !important;
            }

            .comment > td + td {
                padding: 8px 8px 8px 0 !important;
            }
        }

        #title-status.inProgress > table {
            background: #3c78b5;
            border-bottom-color: #000080;
        }
    </style>
</head>
<body>
<table id="email-wrap" align="center" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
    <tr>
        <td>
            [#if showTitleStatus]
                [@notificationTitleStatus baseUrl=baseUrl build=build buildResultKey=buildKey buildNumber=buildNumber statusMessage=statusMessage /]
            [/#if]
            <table id="email-wrap-inner" cellpadding="0" cellspacing="0" width="100%">
                <tbody>
                <tr>
                    <td>
                        [#nested]
                    </td>
                </tr>
                </tbody>
            </table>
            [@showEmailFooter baseUrl=baseUrl /]
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>
[/#macro]

[#macro showActions]
<table width="100%" cellpadding="0" cellspacing="0" id="email-actions" class="email-metadata">
    <tbody>
    <tr>
        <td class="left">
            [#nested]
        </td>
    </tr>
    </tbody>
</table>
[/#macro]

[#macro addAction name url first=false]
    [#if !first]<span class="email-list-divider">|</span>[/#if]
<span class="email-list-item"><a href="${url}">${name}</a></span>
[/#macro]

[#macro notificationTitleStatus baseUrl build buildResultKey buildNumber statusMessage='']
<div id="title-status" class="inProgress">
    <table cellpadding="0" cellspacing="0" width="100%">
        <tbody>
        <tr>
            <td id="title-status-icon">
                <img src="${baseUrl}/images/iconsv3/queued_16.png" alt="${statusMessage}">
            </td>
            <td id="title-status-text">
                [@displayBuildTitle baseUrl build buildResultKey buildNumber /]
                <span class="status">${statusMessage?trim}</span>
            </td>
        </tr>
        </tbody>
    </table>
</div>
[/#macro]

[#macro displayBranchIcon]
<img class="icon-branch" alt="Branch" src="${baseUrl}/images/icons/branch.png"/>[#t]
[/#macro]

[#macro displayBuildTitle baseUrl build buildResultKey buildNumber]
<span class="build">
    [#if build.parent?has_content]
        <a href="${baseUrl}/browse/${build.project.key}/">${build.project.name}</a> &rsaquo;
        [#if build.parent.master?has_content]
            <a href="${baseUrl}/browse/${build.parent.master.key}/">${build.parent.master.buildName}</a> &rsaquo; [@displayBranchIcon /]
        [/#if]
        <a href="${baseUrl}/browse/${build.parent.key}/">${build.parent.buildName}</a> &rsaquo;
        <a href="${baseUrl}/browse/${build.parent.key}-${buildNumber}/">#${buildNumber}</a> &rsaquo;
        <a href="${baseUrl}/browse/${buildResultKey}/">${build.buildName}</a>
    [#else]
        <a href="${baseUrl}/browse/${build.project.key}/">${build.project.name}</a> &rsaquo;
        [#if build.master?has_content]
            <a href="${baseUrl}/browse/${build.master.key}/">${build.master.buildName}</a> &rsaquo; [@displayBranchIcon /]
        [/#if]
        <a href="${baseUrl}/browse/${build.key}/">${build.buildName}</a> &rsaquo;
        <a href="${baseUrl}/browse/${buildResultKey}/">#${buildNumber}</a>
    [/#if]
</span>
[/#macro]

[#macro displayTriggerReason startDateTime]
    [#if triggerReasonDescription?? && triggerReasonDescription?has_content]
    <p class="trigger">[#nested] ${triggerReasonDescription}
        [#if startDateTime??]at ${startDateTime}[/#if]
    </p><br>
    [/#if]
[/#macro]

[#macro showEmailFooter baseUrl]
<table id="email-footer" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
    <tr>
        <td>
            <p>
                <small>This message was sent by <a href="${baseUrl}">Atlassian
                    Bamboo</a> (Start Build Notification Plugin)
                </small>
            </p>
            <p>
                <small>If you wish to stop receiving these emails edit your <a
                        href="${baseUrl}/profile/userNotifications.action">user profile</a> or <a
                        href="${baseUrl}/viewAdministrators.action">notify your administrator</a>.
                </small>
            </p>
        </td>
    </tr>
    </tbody>
</table>
[/#macro]
