package com.example.learn.net;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import android.content.Context;

import com.example.learn.model.AllClass;
import com.example.learn.model.DataDB;
import com.example.learn.model.GradeTest;
import com.example.learn.model.MyScore;
import com.example.learn.tool.MyApplication;

public class Analysis {
	private Context context;
	private DataDB dataDB;
	public Analysis() {
		context=MyApplication.getContext();
		dataDB=DataDB.getInstance(context);
	}

	// html解析，只提取科目与成绩
	public void analysisScore(String source) {
		StringBuffer sff = new StringBuffer();
		String html = source;
		org.jsoup.nodes.Document doc = Jsoup.parse(html);
		Elements table = doc.select("tr[class=smartTr]");
		Elements text = table.select("td[height=23]");
		int i = 1;
		List list = new ArrayList<MyScore>();
		for (org.jsoup.nodes.Element link : text) {
			MyScore myClass = new MyScore();
			if (i % 13 == 4) {
				myClass.setTime(link.text());
			}
			if (i % 13 == 5) {
				myClass.setName(link.text());
			}

			if (i % 13 == 6) {
				myClass.setStudyScore(link.text());
			}
			if (i % 13 == 9) {
				myClass.setType(link.text());
			}
			if (i % 13 == 10) {
				myClass.setStudyTime(link.text());
			}
			if (i % 13 == 11) {
				myClass.setStudyScore(link.text());
			}
			list.add(myClass);
			i++;

		}
		dataDB.saveMyScore(list);

	}

	// 新闻网页解析新闻标题与地址
	public void analysisNews(String html) {
		List list1 = new ArrayList<String>();
		List list2 = new ArrayList<String>();
		if (null == html) {
			return;
		}
		org.jsoup.nodes.Document doc = Jsoup.parse(html);
		Elements class1 = doc.select("div[id=HeadNewsTitle]>a");
		list1.add(class1.attr("href"));
		list2.add(class1.attr("title"));
		Elements class2 = doc.select("div[class=list]>ul>li>a[class=gray]");
		for (org.jsoup.nodes.Element link : class2) {
			list1.add(link.attr("href"));
			list2.add(link.attr("title"));
		}
		dataDB.saveNews(list1, list2);

	}

	// 解析新闻内容
	public void analysisNewText(String source) {
		org.jsoup.nodes.Document doc = Jsoup.parse(source);
		Elements links_class = doc.select("div[class=text]");
		String text = links_class.text();
	}

	// 解析大学全部课程
	public void analyseAllClass(String source) {
		List list = new ArrayList<AllClass>();
		org.jsoup.nodes.Document doc = Jsoup.parse(source);
		Elements table=doc.select("table[border=1]");
		Elements links_class = table.select("td[height=23]");
		int a=1;
		for (org.jsoup.nodes.Element link : links_class) {
			AllClass allClass=new AllClass();
			String text = link.text();
			if(a%11==5){
				allClass.setName(text);
			}
			if(a%11==6){
				allClass.setStudyTime(text);
			}
			if(a%11==7){
				allClass.setStudyTime(text);
			}
			if(a%11==8){
				allClass.setTerm(text);
			}
			if(a%11==9){
				allClass.setTestType(text);
			}
			list.add(allClass);
			a++;
		}
		dataDB.saveAllClass(list);
		
		
	}

	// 大学等级证书考试解析
	public void analysisGradeTest(String source) {
		List list=new ArrayList<GradeTest>();
		ArrayList<String> list1 = new ArrayList<String>();
		if (null == source) {
			return;
		}
		String html = source;
		org.jsoup.nodes.Document doc = Jsoup.parse(html);

		Elements links_class = doc.select("td[height=23]");
		for (org.jsoup.nodes.Element link : links_class) {
			String text = link.text();
			list1.add(text);
		}
		for (int i = 0; i <= list1.size(); i++) {
			if ((i + 1) % 14 == 6) {
				GradeTest gradeTest=new GradeTest();
				gradeTest.setName(list1.get(i));
				gradeTest.setEnd(list1.get(i+1));
				gradeTest.setScore(list1.get(i+4));
				list.add(gradeTest);
			}
		}
		dataDB.saveGradeTest(list);
		
	}
	
	

	private  static ArrayList<ArrayList<ArrayList<String>>> AllClass = new ArrayList<ArrayList<ArrayList<String>>>();
	
	public void analysisMyClass(String source) {
		AllClass.clear();
		String html = source;
		org.jsoup.nodes.Document doc = Jsoup.parse(html);
		for (int one = 1; one <= 6; one++) {
			for (int two = 1; two <= 7; two++) {
				Elements class1 = doc.select("div[id=" + one + "-" + two
						+ "-2]");
				Elements class2 = doc.select("div[id=" + one + "-" + two
						+ "-1]");
				ArrayList<String> Class = new ArrayList<String>();
				// System.out.println("1----"+class2);
				// System.out.println("2----"+class2.text().toString());
				Pattern pattern1 = Pattern.compile("\\;(.*?)\\&");
				Matcher matcher1 = pattern1.matcher(class2.toString());
				int numble = 0;
				while (matcher1.find() && numble == 0) {
					numble = 9;
					// System.out.println("3----"+matcher1.group(1));
					Class.add(matcher1.group(1));
				}
				Pattern pattern2 = Pattern.compile("\\>(.*?)\\s");
				Matcher matcher2 = pattern2.matcher(class1.toString());
				int nub = 0;
				while (matcher2.find()) {
					if (!matcher2.group(1).equals("")) {
						if (matcher2.group(1).matches("(.*?)周(.*?)")) {
						} else {
							// System.out.println("8----"+matcher2.group(1));
							Class.add(matcher2.group(1));
						}
					}

				}
				// System.out.println(nub);
				Pattern pattern3 = Pattern.compile("\\S(.*?)周(.*?)\\]");
				Matcher matcher3 = pattern3.matcher(class1.toString());
				while (matcher3.find()) {
					// System.out.println("10----"+matcher3.group(0));
					Class.add(matcher3.group(0));
					nub++;
				}
				Class.add(nub + "");
				// System.out.println(Class);
				AnalysisTwo(Class);

			}
		}
	}

	@SuppressWarnings("unused")
	 private void AnalysisTwo(ArrayList<String> Class) {
		int n = Class.size();

		int numble = Integer.parseInt(Class.get(n - 1));
		ArrayList<String> ClassName = new ArrayList<String>();
		ArrayList<String> Teacher = new ArrayList<String>();
		ArrayList<String> Place = new ArrayList<String>();
		ArrayList<String> Time = new ArrayList<String>();
		ArrayList<ArrayList<String>> MyClass = new ArrayList<ArrayList<String>>();
		// System.out.println("lala----"+"---"+numble);
		if (numble != 0) {
			int cycle = (n - numble - 1) / numble;
			// System.out.println("lala----"+cycle+"---"+numble);
			for (int i = 0; i < numble; i++) {
				ClassName.add(Class.get(0 + 4 * i));
				Teacher.add(Class.get(cycle - 2 + i * cycle));
				Place.add(Class.get(cycle - 1 + i * cycle));
				Time.add(Class.get(n - 1 - numble + i));
			}
			MyClass.add(ClassName);
			MyClass.add(Teacher);
			MyClass.add(Place);
			MyClass.add(Time);
			MyClass.add(AnalysisTime(Time));
			// System.out.println(ClassName+"---"+Teacher+"---"+Place+"---"+Time);
			addClass(MyClass);
		} else {
			MyClass.add(null);
			MyClass.add(null);
			MyClass.add(null);
			MyClass.add(null);
			MyClass.add(null);
			// System.out.println(ClassName+"---"+Teacher+"---"+Place+"---"+Time);
			addClass(MyClass);
		}

	}

	
	private void addClass(ArrayList<ArrayList<String>> MyClass) {
		AllClass.add(MyClass);
	}

	private ArrayList<String> AnalysisTime(ArrayList<String> Ti) {
		ArrayList<String> RET = new ArrayList<String>();
		for (int nub = 0; nub < Ti.size(); nub++) {
			String MyTime = Ti.get(nub);
			// 匹配单周
			int danzhou = 0;
			int shuangzhou = 0;
			Pattern dan = Pattern.compile("(.*?)单周");
			Matcher Mdan = dan.matcher(MyTime);
			while (Mdan.find()) {
				MyTime=MyTime.replaceAll("单", "");
//				System.out.println("MyTime---"+MyTime);
				danzhou = 1;
			}
			// 匹配双周
			Pattern shuang = Pattern.compile("(.*?)双周");
			Matcher Mshuang = shuang.matcher(MyTime);
			while (Mshuang.find()) {
				MyTime=MyTime.replaceAll("双", "");
				shuangzhou = 1;
			}
//			System.out.println("MyTime---"+MyTime);
			// 解析周数
			Pattern pattern1 = Pattern.compile("(.*?)周");
			Matcher matcher1 = pattern1.matcher(MyTime);
			ArrayList<String> list1 = new ArrayList<String>();
			ArrayList<String> list2 = new ArrayList<String>();
			ArrayList<String> list3 = new ArrayList<String>();
			while (matcher1.find()) {
				String Time = matcher1.group(1);

				Pattern pattern5 = Pattern.compile("(.*?)\\,");
				Matcher matcher5 = pattern5.matcher(Time + ",");
				while (matcher5.find()) {
					Pattern pattern3 = Pattern.compile("(.*?)\\-");
					Matcher matcher3 = pattern3.matcher(matcher5.group(1));
					while (matcher3.find()) {
						// System.out.println("2---" + matcher3.group(1));
						list1.add(matcher3.group(1));
					}
					Pattern pattern4 = Pattern.compile("\\-(.*)");
					Matcher matcher4 = pattern4.matcher(matcher5.group(1));
					while (matcher4.find()) {
						// System.out.println("3---" + matcher4.group(1));
						list2.add(matcher4.group(1));
					}
					if (matcher5.group(1).length() < 3) {
						Pattern pattern2 = Pattern.compile("\\d{1,2}");
						Matcher matcher2 = pattern2.matcher(matcher5.group(1));
						while (matcher2.find()) {
							// System.out.println("4---" +
							// matcher2.group(0));
							list3.add(matcher2.group(0));
						}
					}

				}

			}
			for (int i = 0; i < list1.size(); i++) {
//				System.out.println("0.0---"+list1.get(i));
				for (int j = Integer.parseInt(list1.get(i)); j <= Integer
						.parseInt(list2.get(i)); j++) {
					if (danzhou == 1) {
						if (j % 2 == 1) {
							list3.add(j + "");
						}
					} else if (shuangzhou == 1) {
						if (j % 2 == 0) {
							list3.add(j + "");
						}
					} else {
						list3.add(j + "");
					}
				}
			}
			RET.add(list3.toString());
//			System.out.println("9---" + list3.toString());
		}
		return RET;
	}
	
	public ArrayList<ArrayList<ArrayList<String>>> GetClass (){
		System.out.println("AllClass"+AllClass.toString()+"AllclassSize"+AllClass.size());
		return AllClass;
	}
	
	
}
