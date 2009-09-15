/**
 * <copyright>
 * </copyright>
 *
 */
package org.oasisopen.relaxng.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.oasisopen.relaxng.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RelaxngFactoryImpl extends EFactoryImpl implements RelaxngFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static RelaxngFactory init()
  {
    try
    {
      RelaxngFactory theRelaxngFactory = (RelaxngFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.oasis-open.org/relaxng"); 
      if (theRelaxngFactory != null)
      {
        return theRelaxngFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new RelaxngFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RelaxngFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case RelaxngPackage.TOP_LEVEL: return createTopLevel();
      case RelaxngPackage.DECL: return createDecl();
      case RelaxngPackage.PATTERN: return createPattern();
      case RelaxngPackage.ELEMENT: return createElement();
      case RelaxngPackage.ATTRIBUTE: return createAttribute();
      case RelaxngPackage.PARAM: return createParam();
      case RelaxngPackage.EXCEPT_PATTERN: return createExceptPattern();
      case RelaxngPackage.GRAMMAR_CONTENT: return createGrammarContent();
      case RelaxngPackage.INCLUDE_CONTENT: return createIncludeContent();
      case RelaxngPackage.START: return createStart();
      case RelaxngPackage.DEFINE: return createDefine();
      case RelaxngPackage.NAME: return createName();
      case RelaxngPackage.EXCEPT_NAME_CLASS: return createExceptNameClass();
      case RelaxngPackage.NAME_CLASS: return createNameClass();
      case RelaxngPackage.DATA_TYPE_VALUE: return createDataTypeValue();
      case RelaxngPackage.ANY_URI_LITERAL: return createAnyURILiteral();
      case RelaxngPackage.NAMESPACE_URI_LITERAL: return createNamespaceURILiteral();
      case RelaxngPackage.INHERIT: return createInherit();
      case RelaxngPackage.IDENTIFIER_OR_KEY_WORD: return createIdentifierOrKeyWord();
      case RelaxngPackage.IDENTIFIER: return createIdentifier();
      case RelaxngPackage.ANY_NAME: return createAnyName();
      case RelaxngPackage.LITERAL: return createLiteral();
      case RelaxngPackage.KEY_WORD: return createKeyWord();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TopLevel createTopLevel()
  {
    TopLevelImpl topLevel = new TopLevelImpl();
    return topLevel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Decl createDecl()
  {
    DeclImpl decl = new DeclImpl();
    return decl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Pattern createPattern()
  {
    PatternImpl pattern = new PatternImpl();
    return pattern;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Element createElement()
  {
    ElementImpl element = new ElementImpl();
    return element;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Attribute createAttribute()
  {
    AttributeImpl attribute = new AttributeImpl();
    return attribute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Param createParam()
  {
    ParamImpl param = new ParamImpl();
    return param;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExceptPattern createExceptPattern()
  {
    ExceptPatternImpl exceptPattern = new ExceptPatternImpl();
    return exceptPattern;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GrammarContent createGrammarContent()
  {
    GrammarContentImpl grammarContent = new GrammarContentImpl();
    return grammarContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IncludeContent createIncludeContent()
  {
    IncludeContentImpl includeContent = new IncludeContentImpl();
    return includeContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Start createStart()
  {
    StartImpl start = new StartImpl();
    return start;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Define createDefine()
  {
    DefineImpl define = new DefineImpl();
    return define;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Name createName()
  {
    NameImpl name = new NameImpl();
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExceptNameClass createExceptNameClass()
  {
    ExceptNameClassImpl exceptNameClass = new ExceptNameClassImpl();
    return exceptNameClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NameClass createNameClass()
  {
    NameClassImpl nameClass = new NameClassImpl();
    return nameClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DataTypeValue createDataTypeValue()
  {
    DataTypeValueImpl dataTypeValue = new DataTypeValueImpl();
    return dataTypeValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnyURILiteral createAnyURILiteral()
  {
    AnyURILiteralImpl anyURILiteral = new AnyURILiteralImpl();
    return anyURILiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamespaceURILiteral createNamespaceURILiteral()
  {
    NamespaceURILiteralImpl namespaceURILiteral = new NamespaceURILiteralImpl();
    return namespaceURILiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Inherit createInherit()
  {
    InheritImpl inherit = new InheritImpl();
    return inherit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IdentifierOrKeyWord createIdentifierOrKeyWord()
  {
    IdentifierOrKeyWordImpl identifierOrKeyWord = new IdentifierOrKeyWordImpl();
    return identifierOrKeyWord;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Identifier createIdentifier()
  {
    IdentifierImpl identifier = new IdentifierImpl();
    return identifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnyName createAnyName()
  {
    AnyNameImpl anyName = new AnyNameImpl();
    return anyName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Literal createLiteral()
  {
    LiteralImpl literal = new LiteralImpl();
    return literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public KeyWord createKeyWord()
  {
    KeyWordImpl keyWord = new KeyWordImpl();
    return keyWord;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RelaxngPackage getRelaxngPackage()
  {
    return (RelaxngPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static RelaxngPackage getPackage()
  {
    return RelaxngPackage.eINSTANCE;
  }

} //RelaxngFactoryImpl
