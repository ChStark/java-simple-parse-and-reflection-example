package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Entry {

    public static void main(String[] args) {

        try {
            Product product = generateInstanceFromString( Product.class , "Name:UnnecessarySuffixForOurLogicParacetamol|Quantity:5|Price:5.00" );
            Employee employee = generateInstanceFromString( Employee.class , "Name:Jorge|Age:29" );

            System.out.println( product );
            System.out.println( employee );
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static <T> T generateInstanceFromString( Class<T> tClass , String string ) throws Exception {
        T obj = tClass.newInstance();
        List<java.lang.reflect.Field> fields = Arrays.stream( tClass.getDeclaredFields() ).filter(x -> x.isAnnotationPresent( FieldAnnotation.class ) ).collect( Collectors.toList() );
        fields.forEach( x -> x.setAccessible(true) );

        for ( java.lang.reflect.Field field : fields ) {
            FieldAnnotation fieldAnnotation = field.getAnnotation( FieldAnnotation.class );
            Pattern pattern = Pattern.compile( fieldAnnotation.regex() , Pattern.CASE_INSENSITIVE );
            Matcher matcher = pattern.matcher( string );

            boolean matches = matcher.find();

            if( !matches && fieldAnnotation.required() ) throw new Exception("Field required");

            if( !matches ) continue;

            String value = matcher.group( fieldAnnotation.capturingGroup() );

            if ( String.class.equals( field.getType() ) ){
                field.set( obj , value.trim() );
            }else if ( long.class.equals( field.getType() ) ){
                field.set( obj , Long.parseLong( value ) );
            }else if ( double.class.equals( field.getType() ) ){
                field.set( obj , Double.parseDouble( value ) );
            }else if ( int.class.equals( field.getType() ) ){
                field.set( obj , Integer.parseInt( value ) );
            }
        }

        return obj;
    }

}
