package com.itestin.guitest.report;

public class GuiTestCaseDataModel
{
    String casedesc;
    String name;
    String className;
    String time;
    String errorMessage;
    String errorType;
    String errorDetail;
    String result;

    public String getClassName()
    {
        return this.className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public String getCasedesc()
    {
        return this.casedesc;
    }

    public void setCasedesc(String casedesc)
    {
        this.casedesc = casedesc;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTime()
    {
        return this.time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getErrorMessage()
    {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getErrorType()
    {
        return this.errorType;
    }

    public void setErrorType(String errorType)
    {
        this.errorType = errorType;
    }

    public String getResult()
    {
        return this.result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public String getErrorDetail()
    {
        return this.errorDetail;
    }

    public void setErrorDetail(String errorDetail)
    {
        this.errorDetail = errorDetail;
    }
}
