package main.java.com.cqut.dao.academic.customInterface;

import java.util.List;

import com.cqut.entity.academic.Academic;

public interface IAcademicEntityDao {

	public List<Academic> findAcademics (String[] property,
			String condition, boolean needLink, int index, int limit);
}
