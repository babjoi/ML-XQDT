/**
 * <copyright>
 * </copyright>
 *
 */
package org.oasisopen.relaxng;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Top Level</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.TopLevel#getDecls <em>Decls</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.TopLevel#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.TopLevel#getGrammarContent <em>Grammar Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.oasisopen.relaxng.RelaxngPackage#getTopLevel()
 * @model
 * @generated
 */
public interface TopLevel extends EObject
{
  /**
   * Returns the value of the '<em><b>Decls</b></em>' containment reference list.
   * The list contents are of type {@link org.oasisopen.relaxng.Decl}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Decls</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Decls</em>' containment reference list.
   * @see org.oasisopen.relaxng.RelaxngPackage#getTopLevel_Decls()
   * @model containment="true"
   * @generated
   */
  EList<Decl> getDecls();

  /**
   * Returns the value of the '<em><b>Pattern</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pattern</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pattern</em>' containment reference.
   * @see #setPattern(Pattern)
   * @see org.oasisopen.relaxng.RelaxngPackage#getTopLevel_Pattern()
   * @model containment="true"
   * @generated
   */
  Pattern getPattern();

  /**
   * Sets the value of the '{@link org.oasisopen.relaxng.TopLevel#getPattern <em>Pattern</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pattern</em>' containment reference.
   * @see #getPattern()
   * @generated
   */
  void setPattern(Pattern value);

  /**
   * Returns the value of the '<em><b>Grammar Content</b></em>' containment reference list.
   * The list contents are of type {@link org.oasisopen.relaxng.GrammarContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Grammar Content</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Grammar Content</em>' containment reference list.
   * @see org.oasisopen.relaxng.RelaxngPackage#getTopLevel_GrammarContent()
   * @model containment="true"
   * @generated
   */
  EList<GrammarContent> getGrammarContent();

} // TopLevel
