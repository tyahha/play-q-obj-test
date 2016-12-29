package controllers.request.query

import play.api.mvc.QueryStringBindable

case class Cells(columns: Int, rows: Int)

object Cells {
  implicit def queryStringLocalDateBinder(implicit intBinder: QueryStringBindable[Int]) = new QueryStringBindable[Cells] {
    val columnsKey = "columns"
    val rowsKey = "rows"

    override def bind(key: String, params: Map[String, Seq[String]]): Option[Either[String, Cells]] = {
      for {
        columns <- intBinder.bind(s"$key.$columnsKey", params)
        rows <- intBinder.bind(s"$key.$rowsKey", params)
      } yield {
        (columns, rows) match {
          case (Right(c), Right(r)) => Right(Cells(c, r))
          case _ => Left(s"cell's columns and rows must be Integer.")
        }
      }
    }

    override def unbind(key: String, cells: Cells): String = s"${intBinder.unbind(s"$key.$columnsKey", cells.columns)}&${intBinder.unbind(s"$key.$rowsKey", cells.rows)}"
  }
}
