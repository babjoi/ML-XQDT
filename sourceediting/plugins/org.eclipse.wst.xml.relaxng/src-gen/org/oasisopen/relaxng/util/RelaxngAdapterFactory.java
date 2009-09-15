/**
 * <copyright>
 * </copyright>
 *
 */
package org.oasisopen.relaxng.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.oasisopen.relaxng.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.oasisopen.relaxng.RelaxngPackage
 * @generated
 */
public class RelaxngAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static RelaxngPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RelaxngAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = RelaxngPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RelaxngSwitch<Adapter> modelSwitch =
    new RelaxngSwitch<Adapter>()
    {
      @Override
      public Adapter caseTopLevel(TopLevel object)
      {
        return createTopLevelAdapter();
      }
      @Override
      public Adapter caseDecl(Decl object)
      {
        return createDeclAdapter();
      }
      @Override
      public Adapter casePattern(Pattern object)
      {
        return createPatternAdapter();
      }
      @Override
      public Adapter caseElement(Element object)
      {
        return createElementAdapter();
      }
      @Override
      public Adapter caseAttribute(Attribute object)
      {
        return createAttributeAdapter();
      }
      @Override
      public Adapter caseParam(Param object)
      {
        return createParamAdapter();
      }
      @Override
      public Adapter caseExceptPattern(ExceptPattern object)
      {
        return createExceptPatternAdapter();
      }
      @Override
      public Adapter caseGrammarContent(GrammarContent object)
      {
        return createGrammarContentAdapter();
      }
      @Override
      public Adapter caseIncludeContent(IncludeContent object)
      {
        return createIncludeContentAdapter();
      }
      @Override
      public Adapter caseStart(Start object)
      {
        return createStartAdapter();
      }
      @Override
      public Adapter caseDefine(Define object)
      {
        return createDefineAdapter();
      }
      @Override
      public Adapter caseName(Name object)
      {
        return createNameAdapter();
      }
      @Override
      public Adapter caseExceptNameClass(ExceptNameClass object)
      {
        return createExceptNameClassAdapter();
      }
      @Override
      public Adapter caseNameClass(NameClass object)
      {
        return createNameClassAdapter();
      }
      @Override
      public Adapter caseDataTypeValue(DataTypeValue object)
      {
        return createDataTypeValueAdapter();
      }
      @Override
      public Adapter caseAnyURILiteral(AnyURILiteral object)
      {
        return createAnyURILiteralAdapter();
      }
      @Override
      public Adapter caseNamespaceURILiteral(NamespaceURILiteral object)
      {
        return createNamespaceURILiteralAdapter();
      }
      @Override
      public Adapter caseInherit(Inherit object)
      {
        return createInheritAdapter();
      }
      @Override
      public Adapter caseIdentifierOrKeyWord(IdentifierOrKeyWord object)
      {
        return createIdentifierOrKeyWordAdapter();
      }
      @Override
      public Adapter caseIdentifier(Identifier object)
      {
        return createIdentifierAdapter();
      }
      @Override
      public Adapter caseAnyName(AnyName object)
      {
        return createAnyNameAdapter();
      }
      @Override
      public Adapter caseLiteral(Literal object)
      {
        return createLiteralAdapter();
      }
      @Override
      public Adapter caseKeyWord(KeyWord object)
      {
        return createKeyWordAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.TopLevel <em>Top Level</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.TopLevel
   * @generated
   */
  public Adapter createTopLevelAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.Decl <em>Decl</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.Decl
   * @generated
   */
  public Adapter createDeclAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.Pattern <em>Pattern</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.Pattern
   * @generated
   */
  public Adapter createPatternAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.Element <em>Element</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.Element
   * @generated
   */
  public Adapter createElementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.Attribute <em>Attribute</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.Attribute
   * @generated
   */
  public Adapter createAttributeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.Param <em>Param</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.Param
   * @generated
   */
  public Adapter createParamAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.ExceptPattern <em>Except Pattern</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.ExceptPattern
   * @generated
   */
  public Adapter createExceptPatternAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.GrammarContent <em>Grammar Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.GrammarContent
   * @generated
   */
  public Adapter createGrammarContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.IncludeContent <em>Include Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.IncludeContent
   * @generated
   */
  public Adapter createIncludeContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.Start <em>Start</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.Start
   * @generated
   */
  public Adapter createStartAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.Define <em>Define</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.Define
   * @generated
   */
  public Adapter createDefineAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.Name <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.Name
   * @generated
   */
  public Adapter createNameAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.ExceptNameClass <em>Except Name Class</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.ExceptNameClass
   * @generated
   */
  public Adapter createExceptNameClassAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.NameClass <em>Name Class</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.NameClass
   * @generated
   */
  public Adapter createNameClassAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.DataTypeValue <em>Data Type Value</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.DataTypeValue
   * @generated
   */
  public Adapter createDataTypeValueAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.AnyURILiteral <em>Any URI Literal</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.AnyURILiteral
   * @generated
   */
  public Adapter createAnyURILiteralAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.NamespaceURILiteral <em>Namespace URI Literal</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.NamespaceURILiteral
   * @generated
   */
  public Adapter createNamespaceURILiteralAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.Inherit <em>Inherit</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.Inherit
   * @generated
   */
  public Adapter createInheritAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.IdentifierOrKeyWord <em>Identifier Or Key Word</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.IdentifierOrKeyWord
   * @generated
   */
  public Adapter createIdentifierOrKeyWordAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.Identifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.Identifier
   * @generated
   */
  public Adapter createIdentifierAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.AnyName <em>Any Name</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.AnyName
   * @generated
   */
  public Adapter createAnyNameAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.Literal <em>Literal</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.Literal
   * @generated
   */
  public Adapter createLiteralAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.oasisopen.relaxng.KeyWord <em>Key Word</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.oasisopen.relaxng.KeyWord
   * @generated
   */
  public Adapter createKeyWordAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //RelaxngAdapterFactory
