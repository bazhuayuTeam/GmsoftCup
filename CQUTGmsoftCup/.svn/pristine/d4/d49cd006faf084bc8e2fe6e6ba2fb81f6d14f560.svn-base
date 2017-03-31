package com.cqut.util.struts;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * SQL注入拦截器
 * @author Ming
 * @description 进行非法字符的转换，防止SQL注入
 * @date 2016-04-11
 */
public class IllegalCharacterInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		ActionContext actionContext = invocation.getInvocationContext();
		ValueStack valueStack = actionContext.getValueStack();
		Map<String, Object> valueMap = actionContext.getParameters();
		Map<String, Object> newValueMap = new HashMap<String, Object>();
		for (Map.Entry<String, Object> map : valueMap.entrySet()) {
			String key = map.getKey();
			Object value = map.getValue();
			if (value != null && value instanceof String[]) {
				String[] oldValue = (String[]) value;
				String[] newValue = new String[oldValue.length];
				for (int i = 0; i < oldValue.length; i++) {
					String oldString = oldValue[i];
					String newString = oldString.replaceAll("'", "''");
					newValue[i] = newString;
				}
				newValueMap.put(key, newValue);
				try {
					valueStack.setValue(key, newValue);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		for (Map.Entry<String, Object> map : newValueMap.entrySet())
			valueStack.set(map.getKey(), map.getValue());
		return invocation.invoke();
	}
}
