package com.itestin.guitest.report;

import com.xiaoleilu.hutool.util.XmlUtil;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AnalyzeGuiReport
{
    private List<GuiTestSuiteDataModel> guiTestSuiteDataModelList = null;

    public AnalyzeGuiReport(String path)
    {
        Element rootElement = XmlUtil.getRootElement(XmlUtil.readXML(path));
        if (this.guiTestSuiteDataModelList == null) {
            this.guiTestSuiteDataModelList = new ArrayList();
        }
        NodeList testsuiteNodeList = rootElement.getElementsByTagName("testsuite");
        for (int i = 0; i < testsuiteNodeList.getLength(); i++)
        {
            Element testSuiteNode = (Element)testsuiteNodeList.item(i);
            GuiTestSuiteDataModel tmpModel = new GuiTestSuiteDataModel();
            tmpModel.setFailures(Integer.parseInt(testSuiteNode.getAttributes().getNamedItem("failures")
                    .getNodeValue()));
            tmpModel.setErrors(Integer.parseInt(testSuiteNode.getAttributes().getNamedItem("errors").getNodeValue()));
            tmpModel.setName(testSuiteNode.getAttributes().getNamedItem("name").getNodeValue());
            tmpModel.setSkipped(Integer.parseInt(testSuiteNode.getAttributes().getNamedItem("skipped").getNodeValue()));
            tmpModel.setTests(Integer.parseInt(testSuiteNode.getAttributes().getNamedItem("tests").getNodeValue()));
            tmpModel.setTime(testSuiteNode.getAttributes().getNamedItem("time").getNodeValue());
            tmpModel.setTimestamp(testSuiteNode.getAttributes().getNamedItem("timestamp").getNodeValue());
            tmpModel.setPass(Integer.parseInt(testSuiteNode.getAttributes().getNamedItem("tests").getNodeValue()) -
                    Integer.parseInt(testSuiteNode.getAttributes().getNamedItem("failures")
                            .getNodeValue()) - Integer.parseInt(testSuiteNode.getAttributes().getNamedItem("errors").getNodeValue()) -
                    Integer.parseInt(testSuiteNode.getAttributes().getNamedItem("skipped").getNodeValue()));

            List<GuiTestCaseDataModel> guiTestCaseDataModelList = new ArrayList();
            NodeList testcaseNodeList = testSuiteNode.getElementsByTagName("testcase");
            for (int j = 0; j < testcaseNodeList.getLength(); j++)
            {
                Element testCaseNode = (Element)testcaseNodeList.item(j);
                GuiTestCaseDataModel testCaseDataModel = new GuiTestCaseDataModel();
                if (testCaseNode.getAttributes().getNamedItem("name").getNodeValue() != null) {
                    testCaseDataModel.setName(testCaseNode.getAttributes().getNamedItem("name").getNodeValue());
                } else {
                    testCaseDataModel.setName("null");
                }
                if (testCaseNode.getAttributes().getNamedItem("classname").getNodeValue() != null) {
                    testCaseDataModel.setClassName(testCaseNode.getAttributes().getNamedItem("classname").getNodeValue());
                } else {
                    testCaseDataModel.setClassName("null");
                }
                if (testCaseNode.getAttributes().getNamedItem("time").getNodeValue() != null) {
                    testCaseDataModel.setTime(testCaseNode.getAttributes().getNamedItem("time").getNodeValue());
                } else {
                    testCaseDataModel.setTime("null");
                }
                if (testCaseNode.getAttributes().getNamedItem("casedesc") != null) {
                    testCaseDataModel.setCasedesc(testCaseNode.getAttributes().getNamedItem("casedesc")
                            .getNodeValue());
                } else {
                    testCaseDataModel.setCasedesc("null");
                }
                Element errorNode = (Element)testCaseNode.getElementsByTagName("error").item(0);
                if (errorNode != null)
                {
                    if (errorNode.getAttributes().getNamedItem("type") != null) {
                        testCaseDataModel.setErrorType(errorNode.getAttributes().getNamedItem("type").getNodeValue());
                    } else {
                        testCaseDataModel.setErrorType("null");
                    }
                    if (errorNode.getAttributes().getNamedItem("message") != null) {
                        testCaseDataModel.setErrorMessage(errorNode.getAttributes().getNamedItem("message").getNodeValue());
                    } else {
                        testCaseDataModel.setErrorMessage("未知错误");
                    }
                    testCaseDataModel.setResult("失败");
                }
                else
                {
                    testCaseDataModel.setErrorType("用例执行成功");
                    testCaseDataModel.setErrorMessage("");
                    testCaseDataModel.setResult("成功");
                }
                guiTestCaseDataModelList.add(testCaseDataModel);
            }
            this.guiTestSuiteDataModelList.add(tmpModel);
            ((GuiTestSuiteDataModel)this.guiTestSuiteDataModelList.get(i)).setGuiTestCaseDataModelList(guiTestCaseDataModelList);
        }
        System.err.println("hello");
    }

    public List<GuiTestSuiteDataModel> getGuiTestSuiteDataModelList()
    {
        return this.guiTestSuiteDataModelList;
    }
}
