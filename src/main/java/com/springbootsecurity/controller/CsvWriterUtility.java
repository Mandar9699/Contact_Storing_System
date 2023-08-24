package com.springbootsecurity.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class CsvWriterUtility<T> {
    private Writer writer;
    private Class<T> clazz;

    public CsvWriterUtility(Writer writer, Class<T> clazz) {
        this.writer = writer;
        this.clazz = clazz;
    }

    public void writeHeader(String[] columns) throws IOException {
        CSVWriter csvWriter = new CSVWriter(writer);
        csvWriter.writeNext(columns);
    }

    public void writeAll(List<T> data, String[] columnMapping)
            throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(clazz);
        strategy.setColumnMapping(columnMapping);

        StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                .withMappingStrategy(strategy)
                .build();

        beanToCsv.write(data);
    }
}
