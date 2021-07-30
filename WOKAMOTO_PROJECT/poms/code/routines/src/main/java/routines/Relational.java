// ============================================================================
//
// Copyright (c) 2006-2014, Talend Inc.\n//\n// 本ソースコードは以下の機能で自動作成されたものである_Talend Cloud Real-Time Big Data Platform\n// CodeGenerator version 7.3.1.20200219_1130\n// Talend製品に関しての詳細な情報は以下のサイトに記載されている www.talend.com.\n// GNU LGPL ライセンスにより本コードを配布することが可能である\n// http://www.gnu.org/licenses/lgpl.html).
//
// ============================================================================
package routines;

public class Relational {

    /**
     * Indicates when a variable is the null value.
     *
     * {talendTypes} boolean | Boolean
     *
     * {Category} Relational
     *
     * {param} Object(null)
     *
     * {example} ISNULL(null)
     *
     *
     */
    public static boolean ISNULL(Object variable) {
        return variable == null;
    }

    /**
     * Returns the complement of the logical value of an expression.
     *
     * {talendTypes} boolean | Boolean
     *
     * {Category} Relational
     *
     * {param} boolean(true)
     *
     * {example} NOT(false)
     */
    public static boolean NOT(boolean expression) {
        return !expression;
    }
    
    /**
     * Indicates when a variable is the null value.
     * 
     * @param value :the object need to judge.
     * 
     * @return 1 if value == null, 0 if not.
     * 
     * {Category} Relational
     * 
     * {param} Object(null)
     * 
     * {example} ISNULL(null)
     */
	public static int isNull(Object value) {
		if (value == null) {
			return 1;
		}
		return 0;

	}
}
