package com.itestin.guitest.Utils;

import com.itestin.guitest.report.AnalyzeGuiReport;
import com.itestin.guitest.report.GuiTestCaseDataModel;
import com.itestin.guitest.report.GuiTestSuiteDataModel;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MailContentUtils
{
    public static Map<String, Object> getContent(AnalyzeGuiReport report)
    {
        Map<String, Object> context = new HashMap();
        List<Map<String, Object>> contexts = new ArrayList();
        GuiTestSuiteDataModel guiTestSuiteDataModel = null;

        guiTestSuiteDataModel = (GuiTestSuiteDataModel)report.getGuiTestSuiteDataModelList().get(0);
        context.put("tests", Float.valueOf(guiTestSuiteDataModel.getTests()));
        context.put("pass", Float.valueOf(guiTestSuiteDataModel.getPass()));
        System.out.println(guiTestSuiteDataModel.getPass());
        context.put("failures", Float.valueOf(guiTestSuiteDataModel.getFailures()));
        context.put("errors", Float.valueOf(guiTestSuiteDataModel.getErrors()));
        context.put("skipped", Float.valueOf(guiTestSuiteDataModel.getSkipped()));
        double sucPercent = guiTestSuiteDataModel.getPass() / guiTestSuiteDataModel.getTests() * 100.0F;
        BigDecimal b = new BigDecimal(sucPercent);
        sucPercent = b.setScale(2, 4).doubleValue();
        context.put("sucPercent", Double.valueOf(sucPercent));
        double errPercent = guiTestSuiteDataModel.getErrors() / guiTestSuiteDataModel.getTests() * 100.0F;
        b = new BigDecimal(errPercent);
        errPercent = b.setScale(2, 4).doubleValue();
        context.put("errPercent", Double.valueOf(errPercent));
        double falPercent = guiTestSuiteDataModel.getFailures() / guiTestSuiteDataModel.getTests() * 100.0F;
        b = new BigDecimal(falPercent);
        falPercent = b.setScale(2, 4).doubleValue();
        context.put("falPercent", Double.valueOf(falPercent));
        double skiPercent = guiTestSuiteDataModel.getSkipped() / guiTestSuiteDataModel.getTests() * 100.0F;
        b = new BigDecimal(skiPercent);
        skiPercent = b.setScale(2, 4).doubleValue();
        context.put("falPercent", Double.valueOf(falPercent));
        context.put("skiPercent", Double.valueOf(skiPercent));
        context.put("time", guiTestSuiteDataModel.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strtime = simpleDateFormat.format(new Date());
        context.put("timestamp", strtime);

        GuiTestCaseDataModel guiTestCaseDataModel = null;
        List<GuiTestCaseDataModel> testCases = new ArrayList();
        for (int i = 0; i < guiTestSuiteDataModel.getGuiTestCaseDataModelList().size(); i++)
        {
            guiTestCaseDataModel = (GuiTestCaseDataModel)guiTestSuiteDataModel.getGuiTestCaseDataModelList().get(i);
            testCases.add(guiTestCaseDataModel);
        }
        if (guiTestCaseDataModel != null) {
            context.put("testCases", testCases);
        }
        return context;
    }
}
