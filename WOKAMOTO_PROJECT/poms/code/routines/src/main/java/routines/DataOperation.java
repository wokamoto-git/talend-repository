// ============================================================================
//
// Copyright (c) 2006-2014, Talend Inc.\n//\n// 本ソースコードは以下の機能で自動作成されたものである_Talend Cloud Real-Time Big Data Platform\n// CodeGenerator version 7.3.1.20200219_1130\n// Talend製品に関しての詳細な情報は以下のサイトに記載されている www.talend.com.\n// GNU LGPL ライセンスにより本コードを配布することが可能である\n// http://www.gnu.org/licenses/lgpl.html).
//
// ============================================================================
package routines;

public class DataOperation {

    /**
     * Converts a numeric value to its ASCII character string equivalent.
     *
     * {talendTypes} char | Character
     *
     * {Category} DataOperation
     *
     * {param} int(1) i: numeric value
     *
     * {example} CHAR(1):int
     *
     */
    public static char CHAR(int i) {
        return Character.forDigit(i, 10);
    }

    /**
     * Converts a decimal integer into its hexadecimal equivalent.
     *
     * {talendTypes} String
     *
     * {Category} DataOperation
     *
     * {param} int(1) i:decimal integer
     *
     * {example} DTX(1)
     *
     */
    public static String DTX(int i) {
        return Integer.toHexString(i);
    }

    /**
     * Rounds a number of type Double to a number of type Long with the precision specified in the PRECISION statement.
     *
     * {talendTypes} long | Long
     *
     * {Category} DataOperation
     *
     * {param} double (0.0) d:double number
     *
     * {example} FIX(3.14)
     *
     */
    public static long FIX(double d) {
        return Math.round(d);
    }

    /**
     * Converts a hexadecimal string into its decimal equivalent.
     *
     * {talendTypes} int | Integer
     *
     * {Category} DataOperation
     *
     * {param} string ("0") text: hexadecimal string
     *
     * {example} XTD(\"1\")
     *
     */
    public static int XTD(String text) {
        return Integer.valueOf(text, 16);
    }
}
