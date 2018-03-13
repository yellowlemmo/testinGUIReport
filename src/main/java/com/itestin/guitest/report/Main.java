package com.itestin.guitest.report;

import com.itestin.guitest.Utils.MailContentUtils;
import com.itestin.guitest.Utils.ReadJson;
import io.github.biezhi.ome.OhMyEmail;
import jetbrick.template.JetEngine;
import jetbrick.template.JetTemplate;


import java.io.File;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

public class Main
{
    public static void main(String[] args)
    {
        String filePath = args[0];
        File file = new File(filePath);
        File[] xmlFiles = file.listFiles();
        AnalyzeGuiReport guiReport = new AnalyzeGuiReport(xmlFiles);
        String output = contextToSend(guiReport);

        OhMyEmail.config(getDefaultProperties(), "itestinpro@testin.cn", "testin.cn123");
        try
        {
            OhMyEmail.subject("GUI 自动化测试邮件").from("itestinpro@testin.cn").to(ReadJson.getEmailAccounts()).html(output).send();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static String contextToSend(AnalyzeGuiReport report)
    {
        JetEngine engine = JetEngine.create();
        JetTemplate template = engine.getTemplate("/register.jetx");
        Map<String, Object> context = MailContentUtils.getContent(report);
        StringWriter writer = new StringWriter();
        template.render(context, writer);
        String output = writer.toString();
        return output;
    }

    private static Properties getDefaultProperties()
    {
        Properties props = new Properties();

        props.put("mail.smtp.host", "mail.testin.cn");

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.checkserveridentity", "false");
        props.put("mail.smtp.ssl.trust", "mail.testin.cn");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.connectiontimeout", Integer.valueOf(6000));
        props.put("mail.smtp.port", "587");
        return props;
    }
}
