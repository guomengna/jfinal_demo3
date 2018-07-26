package tool;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

/**
 * 根据文章内容提取文章关键字
 * 还未经过测试
 * @author guomn
 *
 */
public class KeywordSort {
	public static String getsubkeywordtitle(String testString){
		Analyzer ika=null;
		ika=new PaodingAnalyzer();
		List<String> keys=null;
		keys=new ArrayList();
		try{
			Reader r=null;
			r=new StringReader(testString);
			TokenStream ts=ika.tokenStream("TestField",r);
			for(Token t=ts.next();t!=null;t=ts.next()){
				String key=t.termText();
				if(key.length()>=2&&!key.equals("nbsp"))
					keys.add(t.termText());
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		Map<String,Integer>keyMap=new HashMap<String,Integer>();
		Integer $=null;
		for(String key:keys){
			keyMap.put(key,($=keyMap.get(key))==null?1:$+1);
		}
		List<Map.Entry<String, Integer>> keyList=new ArrayList<Map.Entry<String,Integer>>(keyMap.entrySet());
		//排序
		Collections.sort(keyList,new Comparator<Map.Entry<String,Integer>>(){
			public int compare(Map.Entry<String, Integer>o1,Map.Entry<String, Integer>o2){
				return (o2.getValue()-o1.getValue());
			}
		});
		//排序后
		testString="";
		String cc="";
		for(int i=0;i<keyList.size()&&i<4;i++){
			cc=keyList.get(i).toString();
			cc=cc.substring(0,cc.lastIndexOf("="))+",";
			testString=testString+cc;
		}
		if(testString.length()>2){
			testString=testString.substring(0,testString.length()-1);
		}
		
		return testString;
	}
	
	public static String getsubkeyword(String testString){
		Analyzer ika =null;
		ika = new PaodingAnalyzer();
		List<String> keys =null;
		keys = new ArrayList();
		try{
			Reader r =null;
            r = new StringReader(testString);
            TokenStream ts = ika.tokenStream("TestField", r);
        
            for(Token t = ts.next(); t != null; t = ts.next()){
            	String key = t.termText();
            	if(key.length() >= 3&&!key.equals("nbsp"))
            		keys.add(t.termText());
            }
            ts.close();
            r.close();
		}catch(IOException e){
           	e.printStackTrace();
       	}
		Map<String, Integer> keyMap = new HashMap<String, Integer>();
		Integer $ = null;
		for (String key : keys) {
			keyMap.put(key, ($ = keyMap.get(key)) == null ? 1 : $ + 1);
		}
		List<Map.Entry<String, Integer>> keyList = new ArrayList<Map.Entry<String, Integer>>(keyMap.entrySet());
		//排序
		Collections.sort(keyList, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue() - o1.getValue());
			}
		});
		//排序后
		testString="";
		String cc="";
		for (int i = 0; i < keyList.size() && i < 4; i++) {
			cc=keyList.get(i).toString();
			cc=cc.substring(0, cc.lastIndexOf("="))+",";
			testString=testString+cc;
		}
		if(testString.length()>2){
			testString=testString.substring(0, testString.length()-1);
		}
		return testString;
	}
}
