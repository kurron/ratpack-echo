/*
 * Copyright (c) 2016. Ronald D. Kurr kurr@jvmguy.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kurron.example

import groovy.json.JsonBuilder
import java.time.LocalDateTime
import java.util.concurrent.ThreadLocalRandom
import ratpack.groovy.Groovy
import ratpack.server.RatpackServer

/**
 * Ratpack entry point.
 **/
class MainGroovy {
    static void main( String[] args ) {
        def instanceID = Integer.toHexString( ThreadLocalRandom.current().nextInt( Integer.MAX_VALUE ) ).toUpperCase()
        def addresses = NetworkInterface.networkInterfaces.findAll { it.up }.collectMany { NetworkInterface nic ->
            nic.inetAddresses.collect { it.hostAddress }
        }
        def handlers= Groovy.chain {
            get {
                def builder = new JsonBuilder()
                builder.info {
                    instance instanceID
                    now LocalDateTime.now().toString()
                    addresses.eachWithIndex { address, index ->
                        "ip-${index}"  address
                    }
                }
                render builder.toPrettyString()
            }
        }
        RatpackServer.start { spec ->
            spec.handlers( handlers )
        }
    }
}
