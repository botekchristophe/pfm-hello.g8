/**
  * Copyright (C) 2017 Inocybe Technologies inc.
  */

package $package$.api

import com.inocybe.pfm.lib.cassandra.CassandraObject

object $name;format="Camel"$Cassandra extends CassandraObject[$name;format="Camel"$] {

  override val tableName = "$name;format="norm"$s"
  override val primaryIndex = "id"
}
