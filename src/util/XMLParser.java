package util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLParser {
	
	private String filePath;
	private Document doc;
	
	
	//���췽��
	public XMLParser(String filePath) {
		this.filePath = filePath;
		this.Load();
	}
	
	private void Load() {
		
		File file = new File(filePath);

		if(file.exists()){
			SAXReader sr = new SAXReader();
			try {
				this.doc = sr.read(file);
			} catch (DocumentException e) {
				System.out.println("XML�ļ������쳣");
			}
		}else{
			System.out.println("�ļ�������");
		}
	}
	
	public Element getElement(String pathString) {
		return (Element) doc.selectSingleNode(pathString);
	}
	
	@SuppressWarnings("unchecked")
	public List<Element> getElements(String pathString){
		return doc.selectNodes(pathString);
	}
	
	public String getElementText(String pathString){
		Element element = (Element) doc.selectSingleNode(pathString);
		if(element != null){
			return element.getTextTrim();
		}
		return null ;
	}
	
	public boolean isExist(String elementPath){
		boolean flag = false ;
		Element element = this.getElement(elementPath);
		if(element != null){
			flag = true ;
		}
		return flag ;
	}
	
	//��ȡ����ڵ�text
	@SuppressWarnings({ "unchecked", "unused" })
	public List<String> selectNodesText(String pathFile){
		List<String> allNodes = new ArrayList<String>();
		for (Object element : doc.selectNodes(pathFile)) {
			allNodes.add(((Element)element).getTextTrim());
		}
		return allNodes;
	}
	//ͨ��xpath��ȡ�ӽڵ�
	@SuppressWarnings("unchecked")
	public Map<String, String> getChildrenNodes(String xpath){
		Element element =(Element) doc.selectSingleNode(xpath);
		List<Element> elements = element.elements();
		Map<String, String> map = new LinkedHashMap<String,String>();
		for (Element e : elements) {
			map.put(e.getName(), e.getTextTrim());
		}
		
//		ʹ��map.entrySet()����Map	
//		Set<Entry<String,String>> set = map.entrySet();
//		for (Entry<String, String> entry : set) {
//			System.out.println(entry.getKey() +": " +  entry.getValue());
//		}
	
//		ʹ�õ���������Map
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, String> entry = (Entry<String, String>) it.next();
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
		return map;
	}
	//ͨ��Element��ȡ�ӽڵ�
		@SuppressWarnings("unchecked")
	public Map<String, String> getChildrenNodesByElement(Element element){
		List<Element> elements = element.elements();
		Map<String, String> map = new HashMap<String,String>();
		for (Element e : elements) {
			map.put(e.getName(), e.getTextTrim());
		}
		return map;
	}

}
