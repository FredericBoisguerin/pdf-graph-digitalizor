package com.fredericboisguerin.pdf.ui.datasheet.extract;

import java.util.Locale;

import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import com.vaadin.data.converter.StringToFloatConverter;

public class EmptyForNullStringToFloatConverter extends StringToFloatConverter {
    public EmptyForNullStringToFloatConverter(String decimalNumberExpected) {
        super(decimalNumberExpected);
    }

    @Override
    public Result<Float> convertToModel(String value, ValueContext context) {
        if (value.isEmpty()){
            return Result.ok(null);
        }
        return super.convertToModel(value, context);
    }

    @Override
    public String convertToPresentation(Float value, ValueContext context) {
        if (value == null){
            return "";
        }
        return super.convertToPresentation(value, context);
    }
}
