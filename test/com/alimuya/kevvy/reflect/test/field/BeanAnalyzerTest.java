package com.alimuya.kevvy.reflect.test.field;

import java.util.Iterator;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.alimuya.kevvy.reflect.field.BeanAnalyzer;
import com.alimuya.kevvy.reflect.field.GetSetMethodNode;
import com.alimuya.kevvy.reflect.test.bean.TestBeanAnalyzerBean;

public class BeanAnalyzerTest extends TestCase {
	private Class <?> claz=TestBeanAnalyzerBean.class;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetMap() {
		BeanAnalyzer ab=new BeanAnalyzer(claz);
		Map<String, GetSetMethodNode> map = ab.getFieldGetSetMethodMap();
		assertNotNull(map);
	}
	
	@Test
	public void testMapAccess() {
		try {
			TestBeanAnalyzerBean bean=new TestBeanAnalyzerBean();
			BeanAnalyzer ab=new BeanAnalyzer(claz);
			Map<String, GetSetMethodNode> map = ab.getFieldGetSetMethodMap();
			Map<String, String[]> result = bean.getResult();
			if(map.size()==result.size()){
				Iterator<String> it = result.keySet().iterator();
				while(it.hasNext()){
					String key = it.next();
					if(map.containsKey(key)){
						GetSetMethodNode node = map.get(key);
						String[] gs = result.get(key);
						String getName = gs[0];
						if(getName==null){
							assertNull(node.getGetter());
						}else{
							assertEquals(node.getGetter().name, getName);
						}
						String setName = gs[1];
						if(setName==null){
							assertNull(node.getSetter());
						}else{
							assertEquals(node.getSetter().name, setName);
						}
					}else{
						break;
					}
				}
				return;
			}
			fail();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
}
