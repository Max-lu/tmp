package cn.maxlu.demo.jpush;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * Created by luwei on 2017/7/20.
 */
public class Demo {

    public static void main(String[] args) {
        JPushClient jpushClient = new JPushClient("", "", 3);
        PushResult result = jpushClient.sendPush(buildPushObject_all_all_alert("hello world"));
    }

    private static PushPayload buildPushObject_all_all_alert(String content) {
        return PushPayload.alertAll(content);
    }

    public static PushPayload buildPushObject_all_alias_alert(String alias, String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())//所有平台
                .setAudience(Audience.alias(alias))//向选定的人推送
                .setNotification(Notification.alert(content))//消息内容
                .build();
    }

    public static PushPayload buildPushObject_android_tag_alertWithTitle(String alias, String title, String content) {
        return PushPayload.newBuilder().setPlatform(Platform.android())
                .setAudience(Audience.tag(""))//向指定的组推送
                .setNotification(Notification.android("", title, null)).build();
    }
}