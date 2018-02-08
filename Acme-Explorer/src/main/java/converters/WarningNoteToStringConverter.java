package converters;

import org.springframework.core.convert.converter.Converter;

import domain.WarningNote;

public class WarningNoteToStringConverter implements Converter<WarningNote, String> {

	@Override
	public String convert(final WarningNote source) {
		final String result;

		if (source == null)
			result = null;
		else
			result = String.valueOf(source.getId());
		return result;
	}
}
