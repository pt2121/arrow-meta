package arrow.meta.quotes

import arrow.meta.Meta
import arrow.meta.phases.ExtensionPhase
import org.jetbrains.kotlin.psi.KtBinaryExpression
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtOperationReferenceExpression

/**
 * A template destructuring [Scope] for a [KtBinaryExpression]
 */
fun Meta.binaryExpression(
  match: KtBinaryExpression.() -> Boolean,
  map: BinaryExpressionScope.(KtBinaryExpression) -> Transform<KtBinaryExpression>
): ExtensionPhase =
  quote(match, map) { BinaryExpressionScope(it) }

/**
 * A template destructuring [Scope] for a [KtBinaryExpression]
 */
class BinaryExpressionScope(
  override val value: KtBinaryExpression,
  val left: Scope<KtExpression>? = Scope(value.left),
  val right: Scope<KtExpression>? = Scope(value.right),
  val operationReference: Scope<KtOperationReferenceExpression>? = Scope(value.operationReference)
): Scope<KtBinaryExpression>(value)