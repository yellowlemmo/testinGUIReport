package com.itestin.guitest.report;

import java.util.ArrayList;
import java.util.List;

public class GuiTestSuiteDataModel
{
    String name;
    float failures;
    float tests;
    float skipped;
    float errors;
    float pass;
    String time;
    String timestamp;
    List<GuiTestCaseDataModel> guiTestCaseDataModelList;

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public float getFailures()
    {
        return this.failures;
    }

    public void setFailures(int failures)
    {
        this.failures = failures;
    }

    public float getTests()
    {
        return this.tests;
    }

    public void setTests(int tests)
    {
        this.tests = tests;
    }

    public float getSkipped()
    {
        return this.skipped;
    }

    public void setSkipped(int skipped)
    {
        this.skipped = skipped;
    }

    public float getErrors()
    {
        return this.errors;
    }

    public void setErrors(int errors)
    {
        this.errors = errors;
    }

    public float getPass()
    {
        return this.pass;
    }

    public void setPass(int pass)
    {
        this.pass = pass;
    }

    public String getTime()
    {
        return this.time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getTimestamp()
    {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    public List<GuiTestCaseDataModel> getGuiTestCaseDataModelList()
    {
        if (this.guiTestCaseDataModelList == null) {
            this.guiTestCaseDataModelList = new ArrayList();
        }
        return this.guiTestCaseDataModelList;
    }

    public void setGuiTestCaseDataModelList(List<GuiTestCaseDataModel> guiTestCaseDataModelList)
    {
        this.guiTestCaseDataModelList = guiTestCaseDataModelList;
    }
}
