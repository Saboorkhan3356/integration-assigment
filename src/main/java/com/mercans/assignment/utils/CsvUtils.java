package com.mercans.assignment.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mercans.assignment.model.CsvRecord;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Component
public class CsvUtils {
	List<String> errors = new ArrayList<String>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CsvRecord> readCsvFile(String fileName) throws FileNotFoundException {
		File file = new File("./src/main/resources/input_01.csv");
		InputStream is = new FileInputStream(file);
		InputStreamReader isReader = new InputStreamReader(is);
		Reader reader = new BufferedReader(isReader);
		CsvToBean<CsvRecord> csvToBean = new CsvToBeanBuilder(reader).withType(CsvRecord.class)
				.withIgnoreLeadingWhiteSpace(true).build();
		List<CsvRecord> recordList = csvToBean.parse();
		return recordList;

	}


	public List<String[]> readLinesFromFile(String filename) {
		try {
			CSVReader csvReader = new CSVReader(new FileReader(filename));
			return csvReader.readAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, Integer> getHeadersMap(String[] columnName) {
		Map<String, Integer> columnMap = new HashMap<String, Integer>();
		if (columnName != null) {
			for (int i = 0; i < columnName.length; i++)
				columnMap.put(columnName[i], i);
		}
		return columnMap;
	}
}
