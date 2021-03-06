/*
 * Copyright 2013 David Edwards
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.loopfor.zookeeper.cli

import com.loopfor.scalop._
import com.loopfor.zookeeper._

object PwdCommand {
  val Usage = """usage: pwd [OPTIONS]

  Shows the current working path.

options:
  --check, -c                : check existence of node at working path
"""

  def apply(zk: Zookeeper) = new Command {
    private implicit val _zk = zk

    private lazy val parser = ("check", 'c') ~> enable ~~ false

    def apply(cmd: String, args: Seq[String], context: Path): Path = {
      val opts = parser parse args
      val check = opts[Boolean]("check")
      print(context)
      if (check && Node(context).exists().isEmpty) print(": does not exist")
      println()
      context
    }
  }
}
