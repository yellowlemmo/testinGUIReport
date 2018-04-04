package com.itestin.guitest.report;

import com.xiaoleilu.hutool.util.XmlUtil;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AnalyzeGuiReport
{
    private List<GuiTestSuiteDataModel> guiTestSuiteDataModelList;
    private List<GuiTestCaseDataModel> guiTestCaseDataModelList;
    private GuiTestCaseDataModel testCaseDataModel;
    GuiTestSuiteDataModel tmpModel;
    private int failures;
    private int errors;
    private int skipped;
    private int tests;
    private float time;
    public AnalyzeGuiReport(File[] x) {
        for (int i = 0; i < x.length; i++) {
            String xmlPath = x[i].getAbsolutePath();
            if (xmlPath.endsWith("Start.xml")) {
                Element rootElement = XmlUtil.getRootElement(XmlUtil.readXML(xmlPath));
                if (this.guiTestSuiteDataModelList == null) {
                    this.guiTestSuiteDataModelList = new ArrayList();
                }
                tmpModel = new GuiTestSuiteDataModel();
                failures += Integer.parseInt(rootElement.getAttributes().getNamedItem("failures")
                        .getNodeValue());
                tmpModel.setFailures(failures);
                errors += Integer.parseInt(rootElement.getAttributes().getNamedItem("errors").getNodeValue());
                tmpModel.setErrors(errors);
                tmpModel.setName(rootElement.getAttributes().getNamedItem("name").getNodeValue());
                skipped += Integer.parseInt(rootElement.getAttributes().getNamedItem("skipped").getNodeValue());
                tmpModel.setSkipped(skipped);
                tests+=Integer.parseInt(rootElement.getAttributes().getNamedItem("tests").getNodeValue());
                tmpModel.setTests(tests);
                time += Float.valueOf(rootElement.getAttributes().getNamedItem("time").getNodeValue());
                tmpModel.setTime(String.valueOf(time));
                tmpModel.setTimestamp(rootElement.getAttributes().getNamedItem("timestamp").getNodeValue());
                tmpModel.setPass((int) (tmpModel.getTests() - failures - skipped - errors));
                if (this.guiTestCaseDataModelList == null) {
                    guiTestCaseDataModelList = new ArrayList();
                }
                NodeList testcaseNodeList = rootElement.getElementsByTagName("testcase");
                Element testCaseNode = (Element) testcaseNodeList.item(0);
                testCaseDataModel = new GuiTestCaseDataModel();
                if(testCaseNode!=null) {
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
                    Element errorNode = (Element) testCaseNode.getElementsByTagName("error").item(0);
                    Element failuresNode = (Element) testCaseNode.getElementsByTagName("failure").item(0);
                    if (errorNode != null) {
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
                        testCaseDataModel.setResult("错误");
                    } else if (failuresNode != null) {
                        if (failuresNode.getAttributes().getNamedItem("type") != null) {
                            testCaseDataModel.setErrorType(failuresNode.getAttributes().getNamedItem("type").getNodeValue());
                        } else {
                            testCaseDataModel.setErrorType("null");
                        }
                        if (failuresNode.getAttributes().getNamedItem("message") != null) {
                            testCaseDataModel.setErrorMessage(failuresNode.getAttributes().getNamedItem("message").getNodeValue());
                        } else {
                            testCaseDataModel.setErrorMessage("未知");
                        }
                        testCaseDataModel.setResult("失败");
                    } else {
                        testCaseDataModel.setErrorType("用例执行成功");
                        testCaseDataModel.setErrorMessage("");
                        testCaseDataModel.setResult("成功");
                    }
                }else{
                    testCaseDataModel.setName("无法加载到类");
                    testCaseDataModel.setClassName("无法加载到类");
                    testCaseDataModel.setTime("***");
                    testCaseDataModel.setCasedesc("...");
                }
                guiTestCaseDataModelList.add(testCaseDataModel);
            }
        }
        this.guiTestSuiteDataModelList.add(tmpModel);
        ((GuiTestSuiteDataModel) this.guiTestSuiteDataModelList.get(0)).setGuiTestCaseDataModelList(guiTestCaseDataModelList);
            System.err.println("hello");
        }
    public List<GuiTestSuiteDataModel> getGuiTestSuiteDataModelList()
    {
        return this.guiTestSuiteDataModelList;
    }
}
