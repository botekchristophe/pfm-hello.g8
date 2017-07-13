/**
  * Copyright (C) 2017 Inocybe Technologies inc.
  */

package $package$.api

object $name;format="Camel"$Ops {
  implicit class $name;format="Camel"$Operations($name;format="norm"$: $name;format="Camel"$) {
    def update(that: $name;format="Camel"$): $name;format="Camel"$ =
      $name;format="norm"$.copy(
        name = that.name,
        description = that.description)
  }
}
