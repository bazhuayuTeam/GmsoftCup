package com.cqut.service.util.fileManage.excel;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class StopReadWhenNull implements IStopReadFlag
{
	@Override
	public boolean isEnd(Map<String, Object> map)
	{
		Set<String> keySet = map.keySet();
		Iterator<String> ir = keySet.iterator();
		while(ir.hasNext())
		{
			String key = ir.next();
			Object obj = map.get(key);
			if(obj != null)
			{
				return false;
			}
		}
		return true;
	}
}
