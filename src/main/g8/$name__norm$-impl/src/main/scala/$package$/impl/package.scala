/**
  * Copyright (C) 2017 Inocybe Technologies inc.
  */

package $package$

import java.nio.charset.StandardCharsets
import java.util.Base64

import com.typesafe.config.{Config, ConfigFactory}

package object impl {
  val config: Config = ConfigFactory.load()

  val oauth_id: String = config.getString("service.oauth_id")
  val oauth_secret: String = config.getString("service.oauth_secret")

  def basicEncode: (String, String) => String = { (username, password) =>
    s"Basic ${Base64.getEncoder.encodeToString(s"$username:$password".getBytes(StandardCharsets.UTF_8))}"}
}