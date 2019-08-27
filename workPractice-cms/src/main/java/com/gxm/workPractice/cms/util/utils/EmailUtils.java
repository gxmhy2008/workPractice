package com.gxm.workPractice.cms.util.utils;

import com.gxm.workPractice.common.util.R;
import com.gxm.workPractice.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Component
public class EmailUtils {

    private static Logger _log = LoggerFactory.getLogger(EmailUtils.class);

    private static String emailPassWord;
    private static String sendServer;
    private static String sendPort;
    private static String emailAccount;

    //为了直接使用EmailUtils.sendEmail调用，属性和方法都是static，需要如下注入变量
    //也可以不使用static方法，使用@Autowired注入EmailUtils调用
    @Value("${email.account}")
    public void setEmailAccount(String emailAccount) {
        EmailUtils.emailAccount = emailAccount;
    }

    @Value("${email.passWord}")
    public void setEmailPassWord(String emailPassWord) {
        EmailUtils.emailPassWord = emailPassWord;
    }

    @Value("${email.sendServer}")
    public void setSendServer(String sendServer) {
        EmailUtils.sendServer = sendServer;
    }

    @Value("${email.sendPort}")
    public void setSendPort(String sendPort) {
        EmailUtils.sendPort = sendPort;
    }

    /**
     * @param receive  接收人邮箱
     * @param copy     抄送人列表,多个逗号分隔
     * @param content  邮件内容
     * @param filePath 附件路径
     * @return
     */
    public static R sendEmail(String receive, String copy, String content, String filePath) {
        try {
            Properties props = new Properties();
            //设置用户的认证方式
            props.setProperty("mail.smtp.auth", "true");
            //设置传输协议
            props.setProperty("mail.transport.protocol", "smtp");
            //设置启用ssl
            props.setProperty("mail.smtp.ssl.enable", "true");
            //设置发件人的SMTP服务器地址,账户信息
            props.setProperty("mail.smtp.host", sendServer);
            props.setProperty("mail.smtp.port", sendPort);
            props.setProperty("username", emailAccount);
            props.setProperty("password", emailPassWord);
            //2、创建定义整个应用程序所需的环境信息的 Session 对象
            Session session = Session.getInstance(props);
            //设置调试信息在控制台打印出来
            session.setDebug(true);
            //邮件
            MimeMessage mimeMsg = new MimeMessage(session);
            // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象
            Multipart mp = new MimeMultipart();
            //设置自定义昵称
            String nick = MimeUtility.encodeText("全省信号机联网远程运维监管平台");
            mimeMsg.setFrom(new InternetAddress(emailAccount, nick));
            // 设置主题
            mimeMsg.setSubject("消息通知");
            // 设置收件人
            mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receive));
            if (StringUtils.isNotBlank(copy)) {
                // 设置抄送人
                mimeMsg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(copy));
            }
            if (!StringUtils.isNotBlank(content)) {
                content = "附件为分析报告";
            }
            // 设置正文
            BodyPart bp = new MimeBodyPart();
            bp.setContent(content, "text/html;charset=utf-8");
            mp.addBodyPart(bp);
            // 设置附件
            if (StringUtils.isNotBlank(filePath)) {
                BodyPart attach = new MimeBodyPart();
                FileDataSource fds = new FileDataSource(filePath);
                attach.setDataHandler(new DataHandler(fds));
                attach.setFileName(MimeUtility.encodeText("分析报告.docx", "UTF-8", "B"));
                mp.addBodyPart(attach);
            }
            mimeMsg.setContent(mp);
            mimeMsg.saveChanges();
            // 发送邮件
            if (props.get("mail.smtp.auth").equals("true")) {
                Transport transport = session.getTransport("smtp");
                transport.connect((String) props.get("mail.smtp.host"), Integer.valueOf(props.get("mail.smtp.port").toString()), (String) props.get("username"), (String) props.get("password"));
                transport.sendMessage(mimeMsg, mimeMsg.getAllRecipients());
                transport.close();
            } else {
                Transport.send(mimeMsg);
            }
            return R.ok();
        } catch (Exception e) {
            _log.error("邮件发送失败：" + e.getMessage());
            return R.error("邮件发送失败：" + e.getMessage());
        }
    }

}
