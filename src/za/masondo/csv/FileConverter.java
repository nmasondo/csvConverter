package za.masondo.csv;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import lombok.Getter;

public abstract class FileConverter {

	@Getter
	protected List<String> content;
	
	public FileConverter() {
		content = new ArrayList<>();
	}

	public FileConverter(InputStream inputStream) {
		this(new InputStreamReader(inputStream));
	}

	public FileConverter(Path path) throws IOException {
		this(Files.lines(path));
	}

	public FileConverter(Reader inputReader) {
		this(new BufferedReader(inputReader).lines());
	}

	public FileConverter(List<String> lines) {
		this();
		content.addAll(lines);
	}
	
	public FileConverter(Stream<String> lines) {
		this();
		lines.forEach(content::add);
		lines.close();
	}
	
	public FileConverter(CSVfile csvFile) {
		this(csvFile.getContent());
	}

	public void saveToFile(String pathToSave) throws IOException {
		writeToStream(new FileOutputStream(pathToSave));
	}
	
	public abstract void writeToStream(OutputStream out) throws IOException;
}
