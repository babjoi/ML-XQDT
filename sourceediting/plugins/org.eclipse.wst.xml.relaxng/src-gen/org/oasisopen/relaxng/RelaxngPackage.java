/**
 * <copyright>
 * </copyright>
 *
 */
package org.oasisopen.relaxng;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.oasisopen.relaxng.RelaxngFactory
 * @model kind="package"
 * @generated
 */
public interface RelaxngPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "relaxng";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.oasis-open.org/relaxng";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "relaxng";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  RelaxngPackage eINSTANCE = org.oasisopen.relaxng.impl.RelaxngPackageImpl.init();

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.TopLevelImpl <em>Top Level</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.TopLevelImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getTopLevel()
   * @generated
   */
  int TOP_LEVEL = 0;

  /**
   * The feature id for the '<em><b>Decls</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TOP_LEVEL__DECLS = 0;

  /**
   * The feature id for the '<em><b>Pattern</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TOP_LEVEL__PATTERN = 1;

  /**
   * The feature id for the '<em><b>Grammar Content</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TOP_LEVEL__GRAMMAR_CONTENT = 2;

  /**
   * The number of structural features of the '<em>Top Level</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TOP_LEVEL_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.DeclImpl <em>Decl</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.DeclImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getDecl()
   * @generated
   */
  int DECL = 1;

  /**
   * The feature id for the '<em><b>Prefix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECL__PREFIX = 0;

  /**
   * The feature id for the '<em><b>Uri</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECL__URI = 1;

  /**
   * The feature id for the '<em><b>Datatype Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECL__DATATYPE_ID = 2;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECL__VALUE = 3;

  /**
   * The number of structural features of the '<em>Decl</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DECL_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.ExceptPatternImpl <em>Except Pattern</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.ExceptPatternImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getExceptPattern()
   * @generated
   */
  int EXCEPT_PATTERN = 6;

  /**
   * The number of structural features of the '<em>Except Pattern</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXCEPT_PATTERN_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.PatternImpl <em>Pattern</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.PatternImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getPattern()
   * @generated
   */
  int PATTERN = 2;

  /**
   * The feature id for the '<em><b>Val</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATTERN__VAL = EXCEPT_PATTERN_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Pattern</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATTERN__PATTERN = EXCEPT_PATTERN_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATTERN__VALUE = EXCEPT_PATTERN_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Param</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATTERN__PARAM = EXCEPT_PATTERN_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Except Pattern</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATTERN__EXCEPT_PATTERN = EXCEPT_PATTERN_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Uri</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATTERN__URI = EXCEPT_PATTERN_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Inherit</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATTERN__INHERIT = EXCEPT_PATTERN_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Grammar Content</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATTERN__GRAMMAR_CONTENT = EXCEPT_PATTERN_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>Group</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATTERN__GROUP = EXCEPT_PATTERN_FEATURE_COUNT + 8;

  /**
   * The feature id for the '<em><b>Continue</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATTERN__CONTINUE = EXCEPT_PATTERN_FEATURE_COUNT + 9;

  /**
   * The number of structural features of the '<em>Pattern</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PATTERN_FEATURE_COUNT = EXCEPT_PATTERN_FEATURE_COUNT + 10;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.ElementImpl <em>Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.ElementImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getElement()
   * @generated
   */
  int ELEMENT = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT__NAME = 0;

  /**
   * The feature id for the '<em><b>Pattern</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT__PATTERN = 1;

  /**
   * The feature id for the '<em><b>Cardinality</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT__CARDINALITY = 2;

  /**
   * The feature id for the '<em><b>Continue</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT__CONTINUE = 3;

  /**
   * The number of structural features of the '<em>Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.AttributeImpl <em>Attribute</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.AttributeImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getAttribute()
   * @generated
   */
  int ATTRIBUTE = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE__NAME = 0;

  /**
   * The feature id for the '<em><b>Pattern</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE__PATTERN = 1;

  /**
   * The feature id for the '<em><b>Cardinality</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE__CARDINALITY = 2;

  /**
   * The feature id for the '<em><b>Continue</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE__CONTINUE = 3;

  /**
   * The number of structural features of the '<em>Attribute</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.ParamImpl <em>Param</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.ParamImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getParam()
   * @generated
   */
  int PARAM = 5;

  /**
   * The number of structural features of the '<em>Param</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAM_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.GrammarContentImpl <em>Grammar Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.GrammarContentImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getGrammarContent()
   * @generated
   */
  int GRAMMAR_CONTENT = 7;

  /**
   * The feature id for the '<em><b>Grammar Conten Grammar Content</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GRAMMAR_CONTENT__GRAMMAR_CONTEN_GRAMMAR_CONTENT = 0;

  /**
   * The number of structural features of the '<em>Grammar Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GRAMMAR_CONTENT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.IncludeContentImpl <em>Include Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.IncludeContentImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getIncludeContent()
   * @generated
   */
  int INCLUDE_CONTENT = 8;

  /**
   * The feature id for the '<em><b>Include Grammar Content</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INCLUDE_CONTENT__INCLUDE_GRAMMAR_CONTENT = 0;

  /**
   * The number of structural features of the '<em>Include Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INCLUDE_CONTENT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.StartImpl <em>Start</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.StartImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getStart()
   * @generated
   */
  int START = 9;

  /**
   * The feature id for the '<em><b>Grammar Conten Grammar Content</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int START__GRAMMAR_CONTEN_GRAMMAR_CONTENT = GRAMMAR_CONTENT__GRAMMAR_CONTEN_GRAMMAR_CONTENT;

  /**
   * The feature id for the '<em><b>Include Grammar Content</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int START__INCLUDE_GRAMMAR_CONTENT = GRAMMAR_CONTENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Pattern</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int START__PATTERN = GRAMMAR_CONTENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Start</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int START_FEATURE_COUNT = GRAMMAR_CONTENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.DefineImpl <em>Define</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.DefineImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getDefine()
   * @generated
   */
  int DEFINE = 10;

  /**
   * The feature id for the '<em><b>Grammar Conten Grammar Content</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEFINE__GRAMMAR_CONTEN_GRAMMAR_CONTENT = GRAMMAR_CONTENT__GRAMMAR_CONTEN_GRAMMAR_CONTENT;

  /**
   * The feature id for the '<em><b>Include Grammar Content</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEFINE__INCLUDE_GRAMMAR_CONTENT = GRAMMAR_CONTENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Pattern</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEFINE__PATTERN = GRAMMAR_CONTENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Define</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEFINE_FEATURE_COUNT = GRAMMAR_CONTENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.ExceptNameClassImpl <em>Except Name Class</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.ExceptNameClassImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getExceptNameClass()
   * @generated
   */
  int EXCEPT_NAME_CLASS = 12;

  /**
   * The number of structural features of the '<em>Except Name Class</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXCEPT_NAME_CLASS_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.NameClassImpl <em>Name Class</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.NameClassImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getNameClass()
   * @generated
   */
  int NAME_CLASS = 13;

  /**
   * The feature id for the '<em><b>Except Class Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_CLASS__EXCEPT_CLASS_NAME = EXCEPT_NAME_CLASS_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Nc</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_CLASS__NC = EXCEPT_NAME_CLASS_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Name Class</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_CLASS_FEATURE_COUNT = EXCEPT_NAME_CLASS_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.NameImpl <em>Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.NameImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getName_()
   * @generated
   */
  int NAME = 11;

  /**
   * The feature id for the '<em><b>Except Class Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME__EXCEPT_CLASS_NAME = NAME_CLASS__EXCEPT_CLASS_NAME;

  /**
   * The feature id for the '<em><b>Nc</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME__NC = NAME_CLASS__NC;

  /**
   * The number of structural features of the '<em>Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAME_FEATURE_COUNT = NAME_CLASS_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.DataTypeValueImpl <em>Data Type Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.DataTypeValueImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getDataTypeValue()
   * @generated
   */
  int DATA_TYPE_VALUE = 14;

  /**
   * The number of structural features of the '<em>Data Type Value</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_TYPE_VALUE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.AnyURILiteralImpl <em>Any URI Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.AnyURILiteralImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getAnyURILiteral()
   * @generated
   */
  int ANY_URI_LITERAL = 15;

  /**
   * The feature id for the '<em><b>Grammar Conten Grammar Content</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANY_URI_LITERAL__GRAMMAR_CONTEN_GRAMMAR_CONTENT = GRAMMAR_CONTENT__GRAMMAR_CONTEN_GRAMMAR_CONTENT;

  /**
   * The feature id for the '<em><b>Inherit</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANY_URI_LITERAL__INHERIT = GRAMMAR_CONTENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Include Content</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANY_URI_LITERAL__INCLUDE_CONTENT = GRAMMAR_CONTENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Any URI Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANY_URI_LITERAL_FEATURE_COUNT = GRAMMAR_CONTENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.NamespaceURILiteralImpl <em>Namespace URI Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.NamespaceURILiteralImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getNamespaceURILiteral()
   * @generated
   */
  int NAMESPACE_URI_LITERAL = 16;

  /**
   * The number of structural features of the '<em>Namespace URI Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMESPACE_URI_LITERAL_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.InheritImpl <em>Inherit</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.InheritImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getInherit()
   * @generated
   */
  int INHERIT = 17;

  /**
   * The number of structural features of the '<em>Inherit</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INHERIT_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.IdentifierOrKeyWordImpl <em>Identifier Or Key Word</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.IdentifierOrKeyWordImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getIdentifierOrKeyWord()
   * @generated
   */
  int IDENTIFIER_OR_KEY_WORD = 18;

  /**
   * The feature id for the '<em><b>Except Class Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER_OR_KEY_WORD__EXCEPT_CLASS_NAME = PARAM_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Nc</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER_OR_KEY_WORD__NC = PARAM_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Parm Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER_OR_KEY_WORD__PARM_VALUE = PARAM_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Identifier Or Key Word</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER_OR_KEY_WORD_FEATURE_COUNT = PARAM_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.IdentifierImpl <em>Identifier</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.IdentifierImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getIdentifier()
   * @generated
   */
  int IDENTIFIER = 19;

  /**
   * The feature id for the '<em><b>Except Class Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER__EXCEPT_CLASS_NAME = IDENTIFIER_OR_KEY_WORD__EXCEPT_CLASS_NAME;

  /**
   * The feature id for the '<em><b>Nc</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER__NC = IDENTIFIER_OR_KEY_WORD__NC;

  /**
   * The feature id for the '<em><b>Parm Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER__PARM_VALUE = IDENTIFIER_OR_KEY_WORD__PARM_VALUE;

  /**
   * The feature id for the '<em><b>Key Word</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER__KEY_WORD = IDENTIFIER_OR_KEY_WORD_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Identifier</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER_FEATURE_COUNT = IDENTIFIER_OR_KEY_WORD_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.AnyNameImpl <em>Any Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.AnyNameImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getAnyName()
   * @generated
   */
  int ANY_NAME = 20;

  /**
   * The feature id for the '<em><b>Except Class Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANY_NAME__EXCEPT_CLASS_NAME = NAME_CLASS__EXCEPT_CLASS_NAME;

  /**
   * The feature id for the '<em><b>Nc</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANY_NAME__NC = NAME_CLASS__NC;

  /**
   * The number of structural features of the '<em>Any Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANY_NAME_FEATURE_COUNT = NAME_CLASS_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.LiteralImpl <em>Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.LiteralImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getLiteral()
   * @generated
   */
  int LITERAL = 21;

  /**
   * The feature id for the '<em><b>Grammar Conten Grammar Content</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL__GRAMMAR_CONTEN_GRAMMAR_CONTENT = DATA_TYPE_VALUE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Inherit</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL__INHERIT = DATA_TYPE_VALUE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Include Content</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL__INCLUDE_CONTENT = DATA_TYPE_VALUE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Literal Segment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL__LITERAL_SEGMENT = DATA_TYPE_VALUE_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_FEATURE_COUNT = DATA_TYPE_VALUE_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.oasisopen.relaxng.impl.KeyWordImpl <em>Key Word</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.oasisopen.relaxng.impl.KeyWordImpl
   * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getKeyWord()
   * @generated
   */
  int KEY_WORD = 22;

  /**
   * The feature id for the '<em><b>Except Class Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KEY_WORD__EXCEPT_CLASS_NAME = IDENTIFIER_OR_KEY_WORD__EXCEPT_CLASS_NAME;

  /**
   * The feature id for the '<em><b>Nc</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KEY_WORD__NC = IDENTIFIER_OR_KEY_WORD__NC;

  /**
   * The feature id for the '<em><b>Parm Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KEY_WORD__PARM_VALUE = IDENTIFIER_OR_KEY_WORD__PARM_VALUE;

  /**
   * The number of structural features of the '<em>Key Word</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int KEY_WORD_FEATURE_COUNT = IDENTIFIER_OR_KEY_WORD_FEATURE_COUNT + 0;


  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.TopLevel <em>Top Level</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Top Level</em>'.
   * @see org.oasisopen.relaxng.TopLevel
   * @generated
   */
  EClass getTopLevel();

  /**
   * Returns the meta object for the containment reference list '{@link org.oasisopen.relaxng.TopLevel#getDecls <em>Decls</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Decls</em>'.
   * @see org.oasisopen.relaxng.TopLevel#getDecls()
   * @see #getTopLevel()
   * @generated
   */
  EReference getTopLevel_Decls();

  /**
   * Returns the meta object for the containment reference '{@link org.oasisopen.relaxng.TopLevel#getPattern <em>Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Pattern</em>'.
   * @see org.oasisopen.relaxng.TopLevel#getPattern()
   * @see #getTopLevel()
   * @generated
   */
  EReference getTopLevel_Pattern();

  /**
   * Returns the meta object for the containment reference list '{@link org.oasisopen.relaxng.TopLevel#getGrammarContent <em>Grammar Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Grammar Content</em>'.
   * @see org.oasisopen.relaxng.TopLevel#getGrammarContent()
   * @see #getTopLevel()
   * @generated
   */
  EReference getTopLevel_GrammarContent();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.Decl <em>Decl</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Decl</em>'.
   * @see org.oasisopen.relaxng.Decl
   * @generated
   */
  EClass getDecl();

  /**
   * Returns the meta object for the attribute '{@link org.oasisopen.relaxng.Decl#getPrefix <em>Prefix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Prefix</em>'.
   * @see org.oasisopen.relaxng.Decl#getPrefix()
   * @see #getDecl()
   * @generated
   */
  EAttribute getDecl_Prefix();

  /**
   * Returns the meta object for the attribute '{@link org.oasisopen.relaxng.Decl#getUri <em>Uri</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Uri</em>'.
   * @see org.oasisopen.relaxng.Decl#getUri()
   * @see #getDecl()
   * @generated
   */
  EAttribute getDecl_Uri();

  /**
   * Returns the meta object for the attribute '{@link org.oasisopen.relaxng.Decl#getDatatypeId <em>Datatype Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Datatype Id</em>'.
   * @see org.oasisopen.relaxng.Decl#getDatatypeId()
   * @see #getDecl()
   * @generated
   */
  EAttribute getDecl_DatatypeId();

  /**
   * Returns the meta object for the attribute '{@link org.oasisopen.relaxng.Decl#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.oasisopen.relaxng.Decl#getValue()
   * @see #getDecl()
   * @generated
   */
  EAttribute getDecl_Value();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.Pattern <em>Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pattern</em>'.
   * @see org.oasisopen.relaxng.Pattern
   * @generated
   */
  EClass getPattern();

  /**
   * Returns the meta object for the containment reference '{@link org.oasisopen.relaxng.Pattern#getVal <em>Val</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Val</em>'.
   * @see org.oasisopen.relaxng.Pattern#getVal()
   * @see #getPattern()
   * @generated
   */
  EReference getPattern_Val();

  /**
   * Returns the meta object for the containment reference list '{@link org.oasisopen.relaxng.Pattern#getPattern <em>Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Pattern</em>'.
   * @see org.oasisopen.relaxng.Pattern#getPattern()
   * @see #getPattern()
   * @generated
   */
  EReference getPattern_Pattern();

  /**
   * Returns the meta object for the containment reference '{@link org.oasisopen.relaxng.Pattern#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.oasisopen.relaxng.Pattern#getValue()
   * @see #getPattern()
   * @generated
   */
  EReference getPattern_Value();

  /**
   * Returns the meta object for the containment reference list '{@link org.oasisopen.relaxng.Pattern#getParam <em>Param</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Param</em>'.
   * @see org.oasisopen.relaxng.Pattern#getParam()
   * @see #getPattern()
   * @generated
   */
  EReference getPattern_Param();

  /**
   * Returns the meta object for the containment reference '{@link org.oasisopen.relaxng.Pattern#getExceptPattern <em>Except Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Except Pattern</em>'.
   * @see org.oasisopen.relaxng.Pattern#getExceptPattern()
   * @see #getPattern()
   * @generated
   */
  EReference getPattern_ExceptPattern();

  /**
   * Returns the meta object for the containment reference '{@link org.oasisopen.relaxng.Pattern#getUri <em>Uri</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Uri</em>'.
   * @see org.oasisopen.relaxng.Pattern#getUri()
   * @see #getPattern()
   * @generated
   */
  EReference getPattern_Uri();

  /**
   * Returns the meta object for the containment reference '{@link org.oasisopen.relaxng.Pattern#getInherit <em>Inherit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Inherit</em>'.
   * @see org.oasisopen.relaxng.Pattern#getInherit()
   * @see #getPattern()
   * @generated
   */
  EReference getPattern_Inherit();

  /**
   * Returns the meta object for the containment reference list '{@link org.oasisopen.relaxng.Pattern#getGrammarContent <em>Grammar Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Grammar Content</em>'.
   * @see org.oasisopen.relaxng.Pattern#getGrammarContent()
   * @see #getPattern()
   * @generated
   */
  EReference getPattern_GrammarContent();

  /**
   * Returns the meta object for the containment reference list '{@link org.oasisopen.relaxng.Pattern#getGroup <em>Group</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Group</em>'.
   * @see org.oasisopen.relaxng.Pattern#getGroup()
   * @see #getPattern()
   * @generated
   */
  EReference getPattern_Group();

  /**
   * Returns the meta object for the attribute '{@link org.oasisopen.relaxng.Pattern#getContinue <em>Continue</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Continue</em>'.
   * @see org.oasisopen.relaxng.Pattern#getContinue()
   * @see #getPattern()
   * @generated
   */
  EAttribute getPattern_Continue();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.Element <em>Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Element</em>'.
   * @see org.oasisopen.relaxng.Element
   * @generated
   */
  EClass getElement();

  /**
   * Returns the meta object for the containment reference '{@link org.oasisopen.relaxng.Element#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.oasisopen.relaxng.Element#getName()
   * @see #getElement()
   * @generated
   */
  EReference getElement_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.oasisopen.relaxng.Element#getPattern <em>Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Pattern</em>'.
   * @see org.oasisopen.relaxng.Element#getPattern()
   * @see #getElement()
   * @generated
   */
  EReference getElement_Pattern();

  /**
   * Returns the meta object for the attribute '{@link org.oasisopen.relaxng.Element#getCardinality <em>Cardinality</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Cardinality</em>'.
   * @see org.oasisopen.relaxng.Element#getCardinality()
   * @see #getElement()
   * @generated
   */
  EAttribute getElement_Cardinality();

  /**
   * Returns the meta object for the attribute '{@link org.oasisopen.relaxng.Element#getContinue <em>Continue</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Continue</em>'.
   * @see org.oasisopen.relaxng.Element#getContinue()
   * @see #getElement()
   * @generated
   */
  EAttribute getElement_Continue();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.Attribute <em>Attribute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute</em>'.
   * @see org.oasisopen.relaxng.Attribute
   * @generated
   */
  EClass getAttribute();

  /**
   * Returns the meta object for the containment reference '{@link org.oasisopen.relaxng.Attribute#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.oasisopen.relaxng.Attribute#getName()
   * @see #getAttribute()
   * @generated
   */
  EReference getAttribute_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.oasisopen.relaxng.Attribute#getPattern <em>Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Pattern</em>'.
   * @see org.oasisopen.relaxng.Attribute#getPattern()
   * @see #getAttribute()
   * @generated
   */
  EReference getAttribute_Pattern();

  /**
   * Returns the meta object for the attribute '{@link org.oasisopen.relaxng.Attribute#getCardinality <em>Cardinality</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Cardinality</em>'.
   * @see org.oasisopen.relaxng.Attribute#getCardinality()
   * @see #getAttribute()
   * @generated
   */
  EAttribute getAttribute_Cardinality();

  /**
   * Returns the meta object for the attribute '{@link org.oasisopen.relaxng.Attribute#getContinue <em>Continue</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Continue</em>'.
   * @see org.oasisopen.relaxng.Attribute#getContinue()
   * @see #getAttribute()
   * @generated
   */
  EAttribute getAttribute_Continue();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.Param <em>Param</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Param</em>'.
   * @see org.oasisopen.relaxng.Param
   * @generated
   */
  EClass getParam();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.ExceptPattern <em>Except Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Except Pattern</em>'.
   * @see org.oasisopen.relaxng.ExceptPattern
   * @generated
   */
  EClass getExceptPattern();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.GrammarContent <em>Grammar Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Grammar Content</em>'.
   * @see org.oasisopen.relaxng.GrammarContent
   * @generated
   */
  EClass getGrammarContent();

  /**
   * Returns the meta object for the containment reference list '{@link org.oasisopen.relaxng.GrammarContent#getGrammarContenGrammarContent <em>Grammar Conten Grammar Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Grammar Conten Grammar Content</em>'.
   * @see org.oasisopen.relaxng.GrammarContent#getGrammarContenGrammarContent()
   * @see #getGrammarContent()
   * @generated
   */
  EReference getGrammarContent_GrammarContenGrammarContent();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.IncludeContent <em>Include Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Include Content</em>'.
   * @see org.oasisopen.relaxng.IncludeContent
   * @generated
   */
  EClass getIncludeContent();

  /**
   * Returns the meta object for the containment reference list '{@link org.oasisopen.relaxng.IncludeContent#getIncludeGrammarContent <em>Include Grammar Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Include Grammar Content</em>'.
   * @see org.oasisopen.relaxng.IncludeContent#getIncludeGrammarContent()
   * @see #getIncludeContent()
   * @generated
   */
  EReference getIncludeContent_IncludeGrammarContent();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.Start <em>Start</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Start</em>'.
   * @see org.oasisopen.relaxng.Start
   * @generated
   */
  EClass getStart();

  /**
   * Returns the meta object for the containment reference '{@link org.oasisopen.relaxng.Start#getPattern <em>Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Pattern</em>'.
   * @see org.oasisopen.relaxng.Start#getPattern()
   * @see #getStart()
   * @generated
   */
  EReference getStart_Pattern();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.Define <em>Define</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Define</em>'.
   * @see org.oasisopen.relaxng.Define
   * @generated
   */
  EClass getDefine();

  /**
   * Returns the meta object for the containment reference '{@link org.oasisopen.relaxng.Define#getPattern <em>Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Pattern</em>'.
   * @see org.oasisopen.relaxng.Define#getPattern()
   * @see #getDefine()
   * @generated
   */
  EReference getDefine_Pattern();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.Name <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Name</em>'.
   * @see org.oasisopen.relaxng.Name
   * @generated
   */
  EClass getName_();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.ExceptNameClass <em>Except Name Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Except Name Class</em>'.
   * @see org.oasisopen.relaxng.ExceptNameClass
   * @generated
   */
  EClass getExceptNameClass();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.NameClass <em>Name Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Name Class</em>'.
   * @see org.oasisopen.relaxng.NameClass
   * @generated
   */
  EClass getNameClass();

  /**
   * Returns the meta object for the containment reference '{@link org.oasisopen.relaxng.NameClass#getExceptClassName <em>Except Class Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Except Class Name</em>'.
   * @see org.oasisopen.relaxng.NameClass#getExceptClassName()
   * @see #getNameClass()
   * @generated
   */
  EReference getNameClass_ExceptClassName();

  /**
   * Returns the meta object for the containment reference '{@link org.oasisopen.relaxng.NameClass#getNc <em>Nc</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Nc</em>'.
   * @see org.oasisopen.relaxng.NameClass#getNc()
   * @see #getNameClass()
   * @generated
   */
  EReference getNameClass_Nc();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.DataTypeValue <em>Data Type Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Data Type Value</em>'.
   * @see org.oasisopen.relaxng.DataTypeValue
   * @generated
   */
  EClass getDataTypeValue();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.AnyURILiteral <em>Any URI Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Any URI Literal</em>'.
   * @see org.oasisopen.relaxng.AnyURILiteral
   * @generated
   */
  EClass getAnyURILiteral();

  /**
   * Returns the meta object for the containment reference '{@link org.oasisopen.relaxng.AnyURILiteral#getInherit <em>Inherit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Inherit</em>'.
   * @see org.oasisopen.relaxng.AnyURILiteral#getInherit()
   * @see #getAnyURILiteral()
   * @generated
   */
  EReference getAnyURILiteral_Inherit();

  /**
   * Returns the meta object for the containment reference list '{@link org.oasisopen.relaxng.AnyURILiteral#getIncludeContent <em>Include Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Include Content</em>'.
   * @see org.oasisopen.relaxng.AnyURILiteral#getIncludeContent()
   * @see #getAnyURILiteral()
   * @generated
   */
  EReference getAnyURILiteral_IncludeContent();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.NamespaceURILiteral <em>Namespace URI Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Namespace URI Literal</em>'.
   * @see org.oasisopen.relaxng.NamespaceURILiteral
   * @generated
   */
  EClass getNamespaceURILiteral();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.Inherit <em>Inherit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Inherit</em>'.
   * @see org.oasisopen.relaxng.Inherit
   * @generated
   */
  EClass getInherit();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.IdentifierOrKeyWord <em>Identifier Or Key Word</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Identifier Or Key Word</em>'.
   * @see org.oasisopen.relaxng.IdentifierOrKeyWord
   * @generated
   */
  EClass getIdentifierOrKeyWord();

  /**
   * Returns the meta object for the containment reference '{@link org.oasisopen.relaxng.IdentifierOrKeyWord#getParmValue <em>Parm Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parm Value</em>'.
   * @see org.oasisopen.relaxng.IdentifierOrKeyWord#getParmValue()
   * @see #getIdentifierOrKeyWord()
   * @generated
   */
  EReference getIdentifierOrKeyWord_ParmValue();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.Identifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Identifier</em>'.
   * @see org.oasisopen.relaxng.Identifier
   * @generated
   */
  EClass getIdentifier();

  /**
   * Returns the meta object for the containment reference '{@link org.oasisopen.relaxng.Identifier#getKeyWord <em>Key Word</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Key Word</em>'.
   * @see org.oasisopen.relaxng.Identifier#getKeyWord()
   * @see #getIdentifier()
   * @generated
   */
  EReference getIdentifier_KeyWord();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.AnyName <em>Any Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Any Name</em>'.
   * @see org.oasisopen.relaxng.AnyName
   * @generated
   */
  EClass getAnyName();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.Literal <em>Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Literal</em>'.
   * @see org.oasisopen.relaxng.Literal
   * @generated
   */
  EClass getLiteral();

  /**
   * Returns the meta object for the attribute '{@link org.oasisopen.relaxng.Literal#getLiteralSegment <em>Literal Segment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Literal Segment</em>'.
   * @see org.oasisopen.relaxng.Literal#getLiteralSegment()
   * @see #getLiteral()
   * @generated
   */
  EAttribute getLiteral_LiteralSegment();

  /**
   * Returns the meta object for class '{@link org.oasisopen.relaxng.KeyWord <em>Key Word</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Key Word</em>'.
   * @see org.oasisopen.relaxng.KeyWord
   * @generated
   */
  EClass getKeyWord();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  RelaxngFactory getRelaxngFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.TopLevelImpl <em>Top Level</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.TopLevelImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getTopLevel()
     * @generated
     */
    EClass TOP_LEVEL = eINSTANCE.getTopLevel();

    /**
     * The meta object literal for the '<em><b>Decls</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TOP_LEVEL__DECLS = eINSTANCE.getTopLevel_Decls();

    /**
     * The meta object literal for the '<em><b>Pattern</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TOP_LEVEL__PATTERN = eINSTANCE.getTopLevel_Pattern();

    /**
     * The meta object literal for the '<em><b>Grammar Content</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TOP_LEVEL__GRAMMAR_CONTENT = eINSTANCE.getTopLevel_GrammarContent();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.DeclImpl <em>Decl</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.DeclImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getDecl()
     * @generated
     */
    EClass DECL = eINSTANCE.getDecl();

    /**
     * The meta object literal for the '<em><b>Prefix</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DECL__PREFIX = eINSTANCE.getDecl_Prefix();

    /**
     * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DECL__URI = eINSTANCE.getDecl_Uri();

    /**
     * The meta object literal for the '<em><b>Datatype Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DECL__DATATYPE_ID = eINSTANCE.getDecl_DatatypeId();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DECL__VALUE = eINSTANCE.getDecl_Value();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.PatternImpl <em>Pattern</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.PatternImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getPattern()
     * @generated
     */
    EClass PATTERN = eINSTANCE.getPattern();

    /**
     * The meta object literal for the '<em><b>Val</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PATTERN__VAL = eINSTANCE.getPattern_Val();

    /**
     * The meta object literal for the '<em><b>Pattern</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PATTERN__PATTERN = eINSTANCE.getPattern_Pattern();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PATTERN__VALUE = eINSTANCE.getPattern_Value();

    /**
     * The meta object literal for the '<em><b>Param</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PATTERN__PARAM = eINSTANCE.getPattern_Param();

    /**
     * The meta object literal for the '<em><b>Except Pattern</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PATTERN__EXCEPT_PATTERN = eINSTANCE.getPattern_ExceptPattern();

    /**
     * The meta object literal for the '<em><b>Uri</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PATTERN__URI = eINSTANCE.getPattern_Uri();

    /**
     * The meta object literal for the '<em><b>Inherit</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PATTERN__INHERIT = eINSTANCE.getPattern_Inherit();

    /**
     * The meta object literal for the '<em><b>Grammar Content</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PATTERN__GRAMMAR_CONTENT = eINSTANCE.getPattern_GrammarContent();

    /**
     * The meta object literal for the '<em><b>Group</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PATTERN__GROUP = eINSTANCE.getPattern_Group();

    /**
     * The meta object literal for the '<em><b>Continue</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PATTERN__CONTINUE = eINSTANCE.getPattern_Continue();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.ElementImpl <em>Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.ElementImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getElement()
     * @generated
     */
    EClass ELEMENT = eINSTANCE.getElement();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT__NAME = eINSTANCE.getElement_Name();

    /**
     * The meta object literal for the '<em><b>Pattern</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT__PATTERN = eINSTANCE.getElement_Pattern();

    /**
     * The meta object literal for the '<em><b>Cardinality</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ELEMENT__CARDINALITY = eINSTANCE.getElement_Cardinality();

    /**
     * The meta object literal for the '<em><b>Continue</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ELEMENT__CONTINUE = eINSTANCE.getElement_Continue();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.AttributeImpl <em>Attribute</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.AttributeImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getAttribute()
     * @generated
     */
    EClass ATTRIBUTE = eINSTANCE.getAttribute();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ATTRIBUTE__NAME = eINSTANCE.getAttribute_Name();

    /**
     * The meta object literal for the '<em><b>Pattern</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ATTRIBUTE__PATTERN = eINSTANCE.getAttribute_Pattern();

    /**
     * The meta object literal for the '<em><b>Cardinality</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ATTRIBUTE__CARDINALITY = eINSTANCE.getAttribute_Cardinality();

    /**
     * The meta object literal for the '<em><b>Continue</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ATTRIBUTE__CONTINUE = eINSTANCE.getAttribute_Continue();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.ParamImpl <em>Param</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.ParamImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getParam()
     * @generated
     */
    EClass PARAM = eINSTANCE.getParam();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.ExceptPatternImpl <em>Except Pattern</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.ExceptPatternImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getExceptPattern()
     * @generated
     */
    EClass EXCEPT_PATTERN = eINSTANCE.getExceptPattern();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.GrammarContentImpl <em>Grammar Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.GrammarContentImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getGrammarContent()
     * @generated
     */
    EClass GRAMMAR_CONTENT = eINSTANCE.getGrammarContent();

    /**
     * The meta object literal for the '<em><b>Grammar Conten Grammar Content</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GRAMMAR_CONTENT__GRAMMAR_CONTEN_GRAMMAR_CONTENT = eINSTANCE.getGrammarContent_GrammarContenGrammarContent();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.IncludeContentImpl <em>Include Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.IncludeContentImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getIncludeContent()
     * @generated
     */
    EClass INCLUDE_CONTENT = eINSTANCE.getIncludeContent();

    /**
     * The meta object literal for the '<em><b>Include Grammar Content</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INCLUDE_CONTENT__INCLUDE_GRAMMAR_CONTENT = eINSTANCE.getIncludeContent_IncludeGrammarContent();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.StartImpl <em>Start</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.StartImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getStart()
     * @generated
     */
    EClass START = eINSTANCE.getStart();

    /**
     * The meta object literal for the '<em><b>Pattern</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference START__PATTERN = eINSTANCE.getStart_Pattern();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.DefineImpl <em>Define</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.DefineImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getDefine()
     * @generated
     */
    EClass DEFINE = eINSTANCE.getDefine();

    /**
     * The meta object literal for the '<em><b>Pattern</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DEFINE__PATTERN = eINSTANCE.getDefine_Pattern();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.NameImpl <em>Name</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.NameImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getName_()
     * @generated
     */
    EClass NAME = eINSTANCE.getName_();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.ExceptNameClassImpl <em>Except Name Class</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.ExceptNameClassImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getExceptNameClass()
     * @generated
     */
    EClass EXCEPT_NAME_CLASS = eINSTANCE.getExceptNameClass();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.NameClassImpl <em>Name Class</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.NameClassImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getNameClass()
     * @generated
     */
    EClass NAME_CLASS = eINSTANCE.getNameClass();

    /**
     * The meta object literal for the '<em><b>Except Class Name</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAME_CLASS__EXCEPT_CLASS_NAME = eINSTANCE.getNameClass_ExceptClassName();

    /**
     * The meta object literal for the '<em><b>Nc</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAME_CLASS__NC = eINSTANCE.getNameClass_Nc();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.DataTypeValueImpl <em>Data Type Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.DataTypeValueImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getDataTypeValue()
     * @generated
     */
    EClass DATA_TYPE_VALUE = eINSTANCE.getDataTypeValue();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.AnyURILiteralImpl <em>Any URI Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.AnyURILiteralImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getAnyURILiteral()
     * @generated
     */
    EClass ANY_URI_LITERAL = eINSTANCE.getAnyURILiteral();

    /**
     * The meta object literal for the '<em><b>Inherit</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANY_URI_LITERAL__INHERIT = eINSTANCE.getAnyURILiteral_Inherit();

    /**
     * The meta object literal for the '<em><b>Include Content</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANY_URI_LITERAL__INCLUDE_CONTENT = eINSTANCE.getAnyURILiteral_IncludeContent();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.NamespaceURILiteralImpl <em>Namespace URI Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.NamespaceURILiteralImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getNamespaceURILiteral()
     * @generated
     */
    EClass NAMESPACE_URI_LITERAL = eINSTANCE.getNamespaceURILiteral();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.InheritImpl <em>Inherit</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.InheritImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getInherit()
     * @generated
     */
    EClass INHERIT = eINSTANCE.getInherit();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.IdentifierOrKeyWordImpl <em>Identifier Or Key Word</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.IdentifierOrKeyWordImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getIdentifierOrKeyWord()
     * @generated
     */
    EClass IDENTIFIER_OR_KEY_WORD = eINSTANCE.getIdentifierOrKeyWord();

    /**
     * The meta object literal for the '<em><b>Parm Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IDENTIFIER_OR_KEY_WORD__PARM_VALUE = eINSTANCE.getIdentifierOrKeyWord_ParmValue();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.IdentifierImpl <em>Identifier</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.IdentifierImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getIdentifier()
     * @generated
     */
    EClass IDENTIFIER = eINSTANCE.getIdentifier();

    /**
     * The meta object literal for the '<em><b>Key Word</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IDENTIFIER__KEY_WORD = eINSTANCE.getIdentifier_KeyWord();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.AnyNameImpl <em>Any Name</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.AnyNameImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getAnyName()
     * @generated
     */
    EClass ANY_NAME = eINSTANCE.getAnyName();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.LiteralImpl <em>Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.LiteralImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getLiteral()
     * @generated
     */
    EClass LITERAL = eINSTANCE.getLiteral();

    /**
     * The meta object literal for the '<em><b>Literal Segment</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LITERAL__LITERAL_SEGMENT = eINSTANCE.getLiteral_LiteralSegment();

    /**
     * The meta object literal for the '{@link org.oasisopen.relaxng.impl.KeyWordImpl <em>Key Word</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.oasisopen.relaxng.impl.KeyWordImpl
     * @see org.oasisopen.relaxng.impl.RelaxngPackageImpl#getKeyWord()
     * @generated
     */
    EClass KEY_WORD = eINSTANCE.getKeyWord();

  }

} //RelaxngPackage
