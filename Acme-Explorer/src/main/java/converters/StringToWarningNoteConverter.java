package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import repositories.WarningNoteRepository;

import domain.WarningNote;

public class StringToWarningNoteConverter implements Converter<String, WarningNote> {

	@Autowired
	WarningNoteRepository	warningNoteRepository;


	@Override
	public WarningNote convert(final String source) {
		final WarningNote result;
		final int id;

		try {
			if (StringUtils.isEmpty(source))
				result = null;
			else {
				id = Integer.valueOf(source);
				result = this.warningNoteRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new RuntimeException(oops);
		}
		return result;
	}
}
