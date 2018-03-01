package com.itestin.guitest.Utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class XmlUtils
{
    public static Map<String, String> returnXMLData(String xmlStr, String... flags)
    {
        Map<String, String> xmlData = new HashMap();
        Element root = parseXML(xmlStr);
        if (root != null) {
            if ((flags == null) || (flags.length == 0))
            {
                xmlData = parseXMLNode(root);
            }
            else
            {
                List<Element> children = root.getChildren();
                for (Element child : children) {
                    if (ArrayUtils.contains(flags, child.getName())) {
                        xmlData.putAll(parseXMLNode(child));
                    }
                }
            }
        }
        return xmlData;
    }

    private static Element parseXML(String resXml)
    {
        SAXBuilder sb = new SAXBuilder();

        StringReader read = new StringReader(resXml);

        InputSource inSource = new InputSource(read);
        Document doc = null;
        Element root = null;
        try
        {
            doc = sb.build(inSource);
        }
        catch (JDOMException e1)
        {
            System.out.println("解析文本报错");
            e1.printStackTrace();
        }
        catch (IOException e1)
        {
            System.out.println("获取输入流报错");
            e1.printStackTrace();
        }
        if (doc != null) {
            root = doc.getRootElement();
        } else {
            System.out.println("解析root报错");
        }
        return root;
    }

    private static Map<String, String> parseXMLNode(Element e)
    {
        Map<String, String> result = new HashMap();
        Element child = null;
        for (Iterator childs = e.getChildren().iterator(); childs.hasNext();)
        {
            child = (Element)childs.next();
            result.put(child.getName(), child.getValue());
        }
        return result;
    }

    public static Map<String, String> returnXMLDataMulti(String xmlStr, String multiNode, String... flags)
    {
        Map<String, String> xmlData = new HashMap();
        Element root = parseXML(xmlStr);
        if (root != null) {
            if ((flags == null) || (flags.length == 0))
            {
                xmlData = parseXMLNode(root);
            }
            else
            {
                List<Element> children = root.getChildren();
                for (Element child : children) {
                    if (ArrayUtils.contains(flags, child.getName())) {
                        xmlData.putAll(parseXMLNodeMulti(child, multiNode));
                    }
                }
            }
        }
        return xmlData;
    }

    private static Map<String, String> parseXMLNodeMulti(Element e, String multiNode)
    {
        Map<String, String> result = new HashMap();
        Element child = null;
        for (Iterator childs = e.getChildren().iterator(); childs.hasNext();)
        {
            child = (Element)childs.next();
            if (multiNode.equals(child.getName()))
            {
                JSONArray jsonAll = new JSONArray();
                for (Iterator pciInfos = child.getChildren().iterator(); pciInfos.hasNext();) {
                    jsonAll.add(parseXMLNodeToString((Element)pciInfos.next()));
                }
                result.put(child.getName(), jsonAll.toString());
            }
            else
            {
                result.put(child.getName(), child.getValue());
            }
        }
        return result;
    }

    private static JSONObject parseXMLNodeToString(Element e)
    {
        JSONObject jsonObj = new JSONObject();
        for (Iterator childs = e.getChildren().iterator(); childs.hasNext();)
        {
            Element child = (Element)childs.next();

            jsonObj.put(child.getName(), child.getValue());
        }
        return jsonObj;
    }
}
